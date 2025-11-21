package br.dev.vktgabriel.av2.API_Aluno.exception.objetos;

import org.springframework.http.HttpStatus;

import java.util.List;

public record RespostaErro(Integer codigo, String status, String mensagem, List<CampoErro> dados) {
    public static RespostaErro unprocessableEntity(List<CampoErro> dados) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        String mensagem = String.format("Identifiquei %d campo&s invalido&s", dados.size())
                .replaceAll("&s", (dados.size() > 1) ? "s" : "");

        return new RespostaErro(status.value(), status.getReasonPhrase(), mensagem, dados);
    }

    public static RespostaErro dupicado(CampoErro dado) {
        HttpStatus status = HttpStatus.CONFLICT;
        return new RespostaErro(status.value(), status.getReasonPhrase(), "Registro Duplicado", List.of(dado));
    }

    public static RespostaErro notFound(CampoErro dado) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new RespostaErro(status.value(), status.getReasonPhrase(), "Registro Não Encontrado", List.of(dado));
    }

    public static RespostaErro conflict(String mensagem) {
        HttpStatus status = HttpStatus.CONFLICT;
        return new RespostaErro(status.value(), status.getReasonPhrase(), mensagem, List.of());
    }
}
