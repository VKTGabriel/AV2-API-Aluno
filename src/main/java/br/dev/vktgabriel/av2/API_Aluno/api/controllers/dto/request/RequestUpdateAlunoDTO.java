package br.dev.vktgabriel.av2.API_Aluno.api.controllers.dto.request;

import br.dev.vktgabriel.av2.API_Aluno.validation.constrain.DataPassada;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RequestUpdateAlunoDTO(
        @NotBlank(message = "Campo vazio ou nulo")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
        String nome,

        @NotBlank(message = "Campo vazio ou nulo")
        @Email
        String email,

        @NotBlank(message = "Campo vazio ou nulo")
        @DataPassada
        String dataNascimento) {
}
