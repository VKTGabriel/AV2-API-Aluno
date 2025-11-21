package br.dev.vktgabriel.av2.API_Aluno.api.controllers.dto.response;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ResponseAlunoDTO(
        UUID id,
        String matricula,
        String nome,
        String email,
        LocalDate dataNascimento,
        List<SimpleResponseCursoDTO> cursos

) {
}
