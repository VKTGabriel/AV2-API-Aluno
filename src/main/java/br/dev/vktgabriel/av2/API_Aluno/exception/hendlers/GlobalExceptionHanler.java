package br.dev.vktgabriel.av2.API_Aluno.exception.hendlers;

import br.dev.vktgabriel.av2.API_Aluno.exception.OperacaoNaoAutorizada;
import br.dev.vktgabriel.av2.API_Aluno.exception.RegistroDuplicadoException;
import br.dev.vktgabriel.av2.API_Aluno.exception.RegistroNaoEncontradoException;
import br.dev.vktgabriel.av2.API_Aluno.exception.objetos.CampoErro;
import br.dev.vktgabriel.av2.API_Aluno.exception.objetos.RespostaErro;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHanler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RespostaErro> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<CampoErro> erros = e.getFieldErrors()
                .stream()
                .map((error) -> new CampoErro(error.getField(), error.getDefaultMessage()))
                .toList();

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(RespostaErro.unprocessableEntity(erros));
    }

    @ExceptionHandler(RegistroDuplicadoException.class)
    public ResponseEntity<RespostaErro> handleRegistroDuplicadoException(RegistroDuplicadoException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(RespostaErro.dupicado(e.getCampoErro()));
    }

    @ExceptionHandler(RegistroNaoEncontradoException.class)
    public ResponseEntity<RespostaErro> handleRegistroNaoEncontradoException(RegistroNaoEncontradoException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(RespostaErro.notFound(e.getCampoErro()));
    }
    @ExceptionHandler(OperacaoNaoAutorizada.class)
    public ResponseEntity<RespostaErro> handleOperacaoNaoAutorizada(OperacaoNaoAutorizada e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(RespostaErro.conflict(e.getMessage()));
    }

}
