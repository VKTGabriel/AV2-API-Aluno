package br.dev.vktgabriel.av2.API_Aluno.api.controllers;

import br.dev.vktgabriel.av2.API_Aluno.api.controllers.dto.request.RequestAlunoDTO;
import br.dev.vktgabriel.av2.API_Aluno.api.controllers.dto.request.RequestCursoDTO;
import br.dev.vktgabriel.av2.API_Aluno.api.controllers.dto.response.ResponseAlunoDTO;
import br.dev.vktgabriel.av2.API_Aluno.api.controllers.dto.response.ResponseCursoDTO;
import br.dev.vktgabriel.av2.API_Aluno.api.controllers.dto.response.SimpleResponseAlunoDTO;
import br.dev.vktgabriel.av2.API_Aluno.api.controllers.dto.response.SimpleResponseCursoDTO;
import br.dev.vktgabriel.av2.API_Aluno.api.controllers.intercaces.GenericController;
import br.dev.vktgabriel.av2.API_Aluno.api.models.Curso;
import br.dev.vktgabriel.av2.API_Aluno.api.services.CursoService;
import br.dev.vktgabriel.av2.API_Aluno.api.services.mappers.CursoMapper;
import br.dev.vktgabriel.av2.API_Aluno.exception.objetos.RespostaErro;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cursos")
@RequiredArgsConstructor
@Tag(name = "Curso")
public class CursoController implements GenericController {
    private final CursoService cursoService;
    private final CursoMapper cursoMapper;

    @GetMapping
    @Operation(summary = "Buscar", description = "Busca todos os registros de curso no banco de dados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso", content = {@Content(mediaType = "application/json" , schema = @Schema(implementation = SimpleResponseCursoDTO.class))})
    })
    public ResponseEntity<List<SimpleResponseCursoDTO>> getAll() {
        return ResponseEntity.ok(cursoMapper.toSimpleResponseList(cursoService.getAll()));
    }

    @PostMapping
    @Operation(summary = "Salvar", description = "Cria um registro de curso no banco de dados")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cadastrado com sucesso", content = {@Content(mediaType = "application/json" , schema = @Schema(implementation = SimpleResponseCursoDTO.class))}),
            @ApiResponse(responseCode = "409", description = "Já existe um curso com esse nome", content = {@Content(mediaType = "application/json" , schema = @Schema(implementation = RespostaErro.class))}),
            @ApiResponse(responseCode = "422", description = "Erro de validação de dados", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RespostaErro.class))})
    })
    public ResponseEntity<SimpleResponseCursoDTO> save(@Valid @RequestBody RequestCursoDTO requestCursoDTO) {
        Curso curso = cursoService.save(requestCursoDTO);
        return ResponseEntity.created(getUri(curso.getId())).body(cursoMapper.toSimpleResponse(curso));
    }

    @GetMapping("{id}")
    @Operation(summary = "Buscar po Id", description = "Busca um registro de curso no banco de dados pelo id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso", content = {@Content(mediaType = "application/json" , schema = @Schema(implementation = ResponseCursoDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Não existe existe um curso com esse id", content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RespostaErro.class))}),
    })
    public ResponseEntity<ResponseCursoDTO> getById(@PathVariable String id) {
        return ResponseEntity.ok(cursoMapper.toResponse(cursoService.getById(id)));
    }

    @PutMapping("{id}")
    @Operation(summary = "Atualizar", description = "atualiza um registro de curso no banco de dados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Atualizado com sucesso", content = {@Content(mediaType = "application/json" , schema = @Schema(implementation = SimpleResponseCursoDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Não existe existe um curso com esse id", content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RespostaErro.class))}),
            @ApiResponse(responseCode = "409", description = "Já existe um curso com esse nome", content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RespostaErro.class))}),
            @ApiResponse(responseCode = "422", description = "Erro de validação de dados", content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RespostaErro.class))})
    })
    public ResponseEntity<SimpleResponseCursoDTO> update (@PathVariable String id, @Valid @RequestBody RequestCursoDTO requestCursoDTO) {
        return ResponseEntity.ok(cursoMapper.toSimpleResponse(cursoService.update(id, requestCursoDTO)));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Deletar", description = "Deleta um registro de curso no banco de dados")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Deletado com sucesso", content = {@Content(mediaType = "application/json" , schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "404", description = "Não existe existe um curso com esse id", content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RespostaErro.class))})
    })
    public ResponseEntity<Void> delete(@PathVariable String id) {
        cursoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("{id}/alunos/{idAluno}")
    @Operation(summary = "Associar Aluno ao Curso", description = "Associa um registro de aluno a um curso no banco de dados")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Associado com sucesso", content = {@Content(mediaType = "application/json" , schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "404", description = "Não existe existe um aluno com esse id", content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RespostaErro.class))}),
            @ApiResponse(responseCode = "404", description = "Não existe existe um curso com esse id", content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RespostaErro.class))})
    })
    public ResponseEntity<Void> addAluno(@PathVariable String id, @PathVariable String idAluno) {
        cursoService.addAluno(id, idAluno);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}/alunos/{idAluno}")
    @Operation(summary = "Desassociar Aluno ao Curso", description = "Remove a associação de um registro de aluno a um curso no banco de dados")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Associação removida com sucesso", content = {@Content(mediaType = "application/json" , schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "404", description = "Não existe existe um aluno com esse id", content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RespostaErro.class))}),
            @ApiResponse(responseCode = "404", description = "Não existe existe um curso com esse id", content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RespostaErro.class))})
    })
    public ResponseEntity<Void> removeAluno(@PathVariable String id, @PathVariable String idAluno) {
        cursoService.remove(id, idAluno);
        return ResponseEntity.noContent().build();
    }
}
