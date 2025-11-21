package br.dev.vktgabriel.av2.API_Aluno.api.services.mappers;

import br.dev.vktgabriel.av2.API_Aluno.api.controllers.dto.request.RequestCursoDTO;
import br.dev.vktgabriel.av2.API_Aluno.api.controllers.dto.response.ResponseCursoDTO;
import br.dev.vktgabriel.av2.API_Aluno.api.controllers.dto.response.SimpleResponseCursoDTO;
import br.dev.vktgabriel.av2.API_Aluno.api.models.Curso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CursoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "alunos", ignore = true)
    @Mapping(target = "dataCadastro", ignore = true)
    Curso toEntity(RequestCursoDTO requestCursoDTO);

    ResponseCursoDTO toResponse(Curso curso);

    List<ResponseCursoDTO> toResponseList(List<Curso> cursos);

    SimpleResponseCursoDTO toSimpleResponse(Curso curso);

    List<SimpleResponseCursoDTO> toSimpleResponseList(List<Curso> cursos);
}
