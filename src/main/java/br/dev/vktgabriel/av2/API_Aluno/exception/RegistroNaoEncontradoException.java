package br.dev.vktgabriel.av2.API_Aluno.exception;

import br.dev.vktgabriel.av2.API_Aluno.exception.objetos.CampoErro;
import lombok.Getter;

@Getter
public class RegistroNaoEncontradoException extends RuntimeException {
    private final CampoErro campoErro;

    public RegistroNaoEncontradoException(String registro, String campo, String valor) {
        String mansegaem = String.format("Não existe um registro de %s com esse(a) %s: %s", registro, campo, valor);
        this.campoErro = new CampoErro(campo, mansegaem);
    }
}
