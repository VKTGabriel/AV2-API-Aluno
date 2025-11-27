package br.dev.vktgabriel.av2.API_Aluno.api.controllers.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RequestTokenDTO(
        @NotBlank(message = "Campo Vazio ou nulo")
        String username,
        @NotBlank(message = "Campo Vazio ou nulo")
        String password
) {
}
