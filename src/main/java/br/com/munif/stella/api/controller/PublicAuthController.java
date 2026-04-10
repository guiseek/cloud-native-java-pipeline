package br.com.munif.stella.api.controller;

import br.com.munif.stella.api.dto.LoginRequestDTO;
import br.com.munif.stella.api.dto.LoginResponseDTO;
import br.com.munif.stella.api.service.KeycloakLoginService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public")
public class PublicAuthController {

    private final KeycloakLoginService keycloakLoginService;

    public PublicAuthController(KeycloakLoginService keycloakLoginService) {
        this.keycloakLoginService = keycloakLoginService;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO request) {
        return keycloakLoginService.login(request);
    }
}