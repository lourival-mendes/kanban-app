package br.com.kanbanquarkus.projection;

public record PessoaProjection(
        String nome,
        Integer idade,
        String cidade,
        String profissao) {
}
