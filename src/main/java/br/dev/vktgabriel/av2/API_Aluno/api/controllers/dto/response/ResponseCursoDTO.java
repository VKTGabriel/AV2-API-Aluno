package br.dev.vktgabriel.av2.API_Aluno.api.controllers.dto.response;

import java.util.List;
import java.util.UUID;

public record ResponseCursoDTO(
        UUID id,
        String nome,
        Integer cargaHoraria,
        List<SimpleResponseAlunoDTO> alunos
) {
}
