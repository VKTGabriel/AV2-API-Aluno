package br.dev.vktgabriel.av2.API_Aluno.api.controllers.dto.request;

import br.dev.vktgabriel.av2.API_Aluno.validation.constrain.NumericoPositivo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RequestCursoDTO(
        @NotBlank(message = "Campo vazio ou nulo")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
        String nome,

        @NotBlank(message = "Campo vazio ou nulo")
        @NumericoPositivo
        String cargaHoraria) {
}
