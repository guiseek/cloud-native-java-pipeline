package br.com.munif.stella.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "stella.keycloak")
public record KeycloakProperties(
        String baseUrl,
        String realm,
        String publicClientId
) {
    public String tokenUrl() {
        return baseUrl + "/realms/" + realm + "/protocol/openid-connect/token";
    }
}
