package br.dev.vktgabriel.av2.API_Aluno.api.controllers.dto.response;

import java.util.UUID;

public record SimpleResponseCursoDTO(
        UUID id,
        String nome,
        Integer cargaHoraria
) {
}
