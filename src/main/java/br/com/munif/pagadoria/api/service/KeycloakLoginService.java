package br.com.munif.pagadoria.api.service;

import br.com.munif.pagadoria.api.dto.LoginRequestDTO;
import br.com.munif.pagadoria.api.dto.LoginResponseDTO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
public class KeycloakLoginService {

    private final RestClient restClient;

    public KeycloakLoginService() {
        this.restClient = RestClient.builder().build();
    }

    public LoginResponseDTO login(LoginRequestDTO request) {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("client_id", "pagadoria-cli");
        form.add("grant_type", "password");
        form.add("username", request.username());
        form.add("password", request.password());

        Map<String, Object> response = restClient.post()
                .uri("http://127.0.0.1:9080/realms/pagadoria/protocol/openid-connect/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(form)
                .retrieve()
                .body(Map.class);

        if (response == null) {
            throw new IllegalStateException("Resposta vazia do Keycloak.");
        }

        return new LoginResponseDTO(
                (String) response.get("access_token"),
                (String) response.get("refresh_token"),
                (String) response.get("token_type"),
                response.get("expires_in") == null ? null : ((Number) response.get("expires_in")).longValue()
        );
    }
}

