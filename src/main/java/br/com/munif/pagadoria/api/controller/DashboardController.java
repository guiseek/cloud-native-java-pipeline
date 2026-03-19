package br.com.munif.pagadoria.api.controller;

import br.com.munif.pagadoria.api.dto.DashboardResumoDTO;
import br.com.munif.pagadoria.api.service.PessoaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {

    private final PessoaService pessoaService;

    public DashboardController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping("/api/v0/dashboard/resumo")
    public DashboardResumoDTO resumo() {
        return new DashboardResumoDTO(pessoaService.contarPessoasAtivas());
    }
}
