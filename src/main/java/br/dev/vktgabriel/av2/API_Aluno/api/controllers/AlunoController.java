package br.dev.vktgabriel.av2.API_Aluno.api.controllers;

import br.dev.vktgabriel.av2.API_Aluno.api.controllers.dto.request.RequestAlunoDTO;
import br.dev.vktgabriel.av2.API_Aluno.api.controllers.dto.request.RequestUpdateAlunoDTO;
import br.dev.vktgabriel.av2.API_Aluno.api.controllers.dto.response.ResponseAlunoDTO;
import br.dev.vktgabriel.av2.API_Aluno.api.controllers.dto.response.SimpleResponseAlunoDTO;
import br.dev.vktgabriel.av2.API_Aluno.api.controllers.intercaces.GenericController;
import br.dev.vktgabriel.av2.API_Aluno.api.models.Aluno;
import br.dev.vktgabriel.av2.API_Aluno.api.services.AlunoService;
import br.dev.vktgabriel.av2.API_Aluno.api.services.CursoService;
import br.dev.vktgabriel.av2.API_Aluno.api.services.mappers.AlunoMapper;
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
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/alunos")
@RequiredArgsConstructor
@Tag(name = "Alunos")
public class AlunoController implements GenericController {
    private final AlunoService alunoService;
    private final AlunoMapper alunoMapper;

    @GetMapping
    @Operation(summary = "Buscar", description = "Busca todos os registros de aluno no banco de dados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso", content = {@Content(mediaType = "application/json" , schema = @Schema(implementation = SimpleResponseAlunoDTO.class))})
    })
    public ResponseEntity<List<SimpleResponseAlunoDTO>> getAll() {
        return ResponseEntity.ok(alunoMapper.toSimpleResponseList(alunoService.getAll()));
    }


    @PostMapping
    @Operation(summary = "Salvar", description = "Cria um registro de aluno no banco de dados")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cadastrado com sucesso", content = {@Content(mediaType = "application/json" , schema = @Schema(implementation = SimpleResponseAlunoDTO.class))}),
            @ApiResponse(responseCode = "409", description = "Já existe um aluno com esse cpf ou email", content = {@Content(mediaType = "application/json" , schema = @Schema(implementation = RespostaErro.class))}),
            @ApiResponse(responseCode = "422", description = "Erro de validação de dados", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RespostaErro.class))})
    })
    public ResponseEntity<SimpleResponseAlunoDTO> save(@Valid @RequestBody RequestAlunoDTO requestAlunoDTO) {
        Aluno aluno = alunoService.save(requestAlunoDTO);
        return ResponseEntity.created(getUri(aluno.getId())).body(alunoMapper.toSimpleResponse(aluno));
    }


    @GetMapping("{id}")
    @Operation(summary = "Buscar por Id", description = "Busca um registro de aluno no banco de dados pelo id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso", content = {@Content(mediaType = "application/json" , schema = @Schema(implementation = ResponseAlunoDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Não existe existe um aluno com esse id", content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RespostaErro.class))}),
    })
    public ResponseEntity<ResponseAlunoDTO> getById(@PathVariable String id) {
        return ResponseEntity.ok(alunoMapper.toResponse(alunoService.getById(id)));
    }


    @PutMapping("{id}")
    @Operation(summary = "Atualizar", description = "atualiza um registro de aluno no banco de dados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Atualizado com sucesso", content = {@Content(mediaType = "application/json" , schema = @Schema(implementation = SimpleResponseAlunoDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Não existe existe um aluno com esse id", content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RespostaErro.class))}),
            @ApiResponse(responseCode = "409", description = "Já existe um aluno com esse Email", content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RespostaErro.class))}),
            @ApiResponse(responseCode = "422", description = "Erro de validação de dados", content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RespostaErro.class))})
    })
    public ResponseEntity<SimpleResponseAlunoDTO> update(@PathVariable String id, @Valid @RequestBody RequestUpdateAlunoDTO requestUpdateAlunoDTO) {
        return ResponseEntity.ok(alunoMapper.toSimpleResponse(alunoService.update(id, requestUpdateAlunoDTO)));
    }


    @DeleteMapping("{id}")
    @Operation(summary = "Deletar", description = "Deleta um registro de aluno no banco de dados")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Deletado com sucesso", content = {@Content(mediaType = "application/json" , schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "404", description = "Não existe existe um aluno com esse id", content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RespostaErro.class))})
    })
    public ResponseEntity<Aluno> delete(@PathVariable String id) {
        alunoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
