package br.dev.vktgabriel.av2.API_Aluno.exception;

import br.dev.vktgabriel.av2.API_Aluno.exception.objetos.CampoErro;
import lombok.Getter;

@Getter
public class OperacaoNaoAutorizada extends RuntimeException {

    public OperacaoNaoAutorizada(String mensagem) {
        super(mensagem);
    }
}
