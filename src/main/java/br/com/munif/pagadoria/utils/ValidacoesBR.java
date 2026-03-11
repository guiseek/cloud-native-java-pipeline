package br.com.munif.pagadoria.utils;

import java.util.regex.Pattern;

public final class ValidacoesBR {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$", Pattern.CASE_INSENSITIVE);

    private static final Pattern TELEFONE_CELULAR_PATTERN =
            Pattern.compile("^\\d{11}$");

    private static final Pattern TELEFONE_FIXO_PATTERN =
            Pattern.compile("^\\d{10}$");

    private static final Pattern CEP_PATTERN =
            Pattern.compile("^\\d{8}$");

    private ValidacoesBR() {
        throw new UnsupportedOperationException("Classe utilitária não pode ser instanciada.");
    }

    public static String somenteDigitos(String valor) {
        if (valor == null) {
            return null;
        }
        return valor.replaceAll("\\D", "");
    }

    public static String trimToNull(String valor) {
        if (valor == null) {
            return null;
        }
        String texto = valor.trim();
        return texto.isBlank() ? null : texto;
    }

    public static boolean isBlank(String valor) {
        return trimToNull(valor) == null;
    }

    public static boolean isNotBlank(String valor) {
        return !isBlank(valor);
    }

    public static boolean validarCPF(String cpf) {
        String valor = somenteDigitos(cpf);

        if (valor == null || valor.length() != 11) {
            return false;
        }

        if (todosDigitosIguais(valor)) {
            return false;
        }

        int dv1 = calcularDigitoCPF(valor.substring(0, 9), 10);
        int dv2 = calcularDigitoCPF(valor.substring(0, 9) + dv1, 11);

        return valor.equals(valor.substring(0, 9) + dv1 + dv2);
    }

    public static boolean validarCNPJ(String cnpj) {
        String valor = somenteDigitos(cnpj);

        if (valor == null || valor.length() != 14) {
            return false;
        }

        if (todosDigitosIguais(valor)) {
            return false;
        }

        int dv1 = calcularDigitoCNPJ(valor.substring(0, 12), new int[]{5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2});
        int dv2 = calcularDigitoCNPJ(valor.substring(0, 12) + dv1, new int[]{6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2});

        return valor.equals(valor.substring(0, 12) + dv1 + dv2);
    }

    public static boolean validarTelefoneBR(String telefone) {
        String valor = somenteDigitos(telefone);

        if (valor == null) {
            return false;
        }

        if (!TELEFONE_FIXO_PATTERN.matcher(valor).matches()
                && !TELEFONE_CELULAR_PATTERN.matcher(valor).matches()) {
            return false;
        }

        String ddd = valor.substring(0, 2);
        if (!validarDDD(ddd)) {
            return false;
        }

        if (valor.length() == 11) {
            return valor.charAt(2) == '9';
        }

        return valor.length() == 10;
    }

    public static boolean validarCelularBR(String telefone) {
        String valor = somenteDigitos(telefone);

        if (valor == null || !TELEFONE_CELULAR_PATTERN.matcher(valor).matches()) {
            return false;
        }

        return validarDDD(valor.substring(0, 2)) && valor.charAt(2) == '9';
    }

    public static boolean validarTelefoneFixoBR(String telefone) {
        String valor = somenteDigitos(telefone);

        if (valor == null || !TELEFONE_FIXO_PATTERN.matcher(valor).matches()) {
            return false;
        }

        return validarDDD(valor.substring(0, 2));
    }

    public static boolean validarCEP(String cep) {
        String valor = somenteDigitos(cep);
        return valor != null && CEP_PATTERN.matcher(valor).matches();
    }

    public static boolean validarEmail(String email) {
        String valor = trimToNull(email);
        return valor != null && EMAIL_PATTERN.matcher(valor).matches();
    }

    public static boolean validarDDD(String ddd) {
        String valor = somenteDigitos(ddd);

        if (valor == null || valor.length() != 2) {
            return false;
        }

        int numero = Integer.parseInt(valor);

        return switch (numero) {
            case 11, 12, 13, 14, 15, 16, 17, 18, 19,
                 21, 22, 24, 27, 28,
                 31, 32, 33, 34, 35, 37, 38,
                 41, 42, 43, 44, 45, 46, 47, 48, 49,
                 51, 53, 54, 55,
                 61, 62, 63, 64, 65, 66, 67, 68, 69,
                 71, 73, 74, 75, 77, 79,
                 81, 82, 83, 84, 85, 86, 87, 88, 89,
                 91, 92, 93, 94, 95, 96, 97, 98, 99 -> true;
            default -> false;
        };
    }

    public static String formatarCPF(String cpf) {
        String valor = somenteDigitos(cpf);

        if (valor == null || valor.length() != 11) {
            throw new IllegalArgumentException("CPF deve conter 11 dígitos.");
        }

        return valor.substring(0, 3) + "."
                + valor.substring(3, 6) + "."
                + valor.substring(6, 9) + "-"
                + valor.substring(9, 11);
    }

    public static String formatarCNPJ(String cnpj) {
        String valor = somenteDigitos(cnpj);

        if (valor == null || valor.length() != 14) {
            throw new IllegalArgumentException("CNPJ deve conter 14 dígitos.");
        }

        return valor.substring(0, 2) + "."
                + valor.substring(2, 5) + "."
                + valor.substring(5, 8) + "/"
                + valor.substring(8, 12) + "-"
                + valor.substring(12, 14);
    }

    public static String formatarCEP(String cep) {
        String valor = somenteDigitos(cep);

        if (valor == null || valor.length() != 8) {
            throw new IllegalArgumentException("CEP deve conter 8 dígitos.");
        }

        return valor.substring(0, 5) + "-" + valor.substring(5, 8);
    }

    public static String formatarTelefoneBR(String telefone) {
        String valor = somenteDigitos(telefone);

        if (valor == null) {
            throw new IllegalArgumentException("Telefone inválido.");
        }

        if (valor.length() == 11) {
            return "(" + valor.substring(0, 2) + ") "
                    + valor.substring(2, 7) + "-"
                    + valor.substring(7, 11);
        }

        if (valor.length() == 10) {
            return "(" + valor.substring(0, 2) + ") "
                    + valor.substring(2, 6) + "-"
                    + valor.substring(6, 10);
        }

        throw new IllegalArgumentException("Telefone deve conter 10 ou 11 dígitos.");
    }

    public static void exigirCPFValido(String cpf, String nomeCampo) {
        if (!validarCPF(cpf)) {
            throw new IllegalArgumentException(nomeCampo + " inválido.");
        }
    }

    public static void exigirCNPJValido(String cnpj, String nomeCampo) {
        if (!validarCNPJ(cnpj)) {
            throw new IllegalArgumentException(nomeCampo + " inválido.");
        }
    }

    public static void exigirTelefoneValido(String telefone, String nomeCampo) {
        if (!validarTelefoneBR(telefone)) {
            throw new IllegalArgumentException(nomeCampo + " inválido.");
        }
    }

    public static void exigirCEPValido(String cep, String nomeCampo) {
        if (!validarCEP(cep)) {
            throw new IllegalArgumentException(nomeCampo + " inválido.");
        }
    }

    public static void exigirEmailValido(String email, String nomeCampo) {
        if (!validarEmail(email)) {
            throw new IllegalArgumentException(nomeCampo + " inválido.");
        }
    }

    private static boolean todosDigitosIguais(String valor) {
        char primeiro = valor.charAt(0);

        for (int i = 1; i < valor.length(); i++) {
            if (valor.charAt(i) != primeiro) {
                return false;
            }
        }

        return true;
    }

    private static int calcularDigitoCPF(String base, int pesoInicial) {
        int soma = 0;
        int peso = pesoInicial;

        for (int i = 0; i < base.length(); i++) {
            int numero = Character.getNumericValue(base.charAt(i));
            soma += numero * peso;
            peso--;
        }

        int resto = soma % 11;
        return resto < 2 ? 0 : 11 - resto;
    }

    private static int calcularDigitoCNPJ(String base, int[] pesos) {
        int soma = 0;

        for (int i = 0; i < base.length(); i++) {
            int numero = Character.getNumericValue(base.charAt(i));
            soma += numero * pesos[i];
        }

        int resto = soma % 11;
        return resto < 2 ? 0 : 11 - resto;
    }
}