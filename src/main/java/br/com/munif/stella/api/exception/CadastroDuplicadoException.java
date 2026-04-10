package br.com.munif.stella.api.exception;

public class CadastroDuplicadoException extends RuntimeException {

    public CadastroDuplicadoException(String message) {
        super(message);
    }
}