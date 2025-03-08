package br.com.kanbanquarkus.dto;

import br.com.kanbanquarkus.enums.PessoaStatus;

public record PessoaDTO(
        String nome,
        Integer idade,
        String cidade,
        String profissao,
        String email,
        PessoaStatus status) {
}
