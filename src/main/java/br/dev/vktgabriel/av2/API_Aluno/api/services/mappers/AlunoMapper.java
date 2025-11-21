package br.dev.vktgabriel.av2.API_Aluno.api.services.mappers;

import br.dev.vktgabriel.av2.API_Aluno.api.controllers.dto.request.RequestAlunoDTO;
import br.dev.vktgabriel.av2.API_Aluno.api.controllers.dto.response.ResponseAlunoDTO;
import br.dev.vktgabriel.av2.API_Aluno.api.controllers.dto.response.SimpleResponseAlunoDTO;
import br.dev.vktgabriel.av2.API_Aluno.api.models.Aluno;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {CursoMapper.class})
public abstract class AlunoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cursos", ignore = true)
    @Mapping(target = "dataCadastro", ignore = true)
    @Mapping(target = "matricula", ignore = true)
    @Mapping(target = "dataNascimento", expression = "java( convert(requestAlunoDTO) )")
    public abstract Aluno toEntity(RequestAlunoDTO requestAlunoDTO);


    public abstract ResponseAlunoDTO toResponse(Aluno aluno);

    public abstract List<ResponseAlunoDTO> toResponseList(List<Aluno> alunos);

    public abstract SimpleResponseAlunoDTO toSimpleResponse(Aluno aluno);

    public abstract List<SimpleResponseAlunoDTO> toSimpleResponseList(List<Aluno> alunos);

    public LocalDate convert(RequestAlunoDTO requestAlunoDTO) {
        return LocalDate.parse(requestAlunoDTO.dataNascimento(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
