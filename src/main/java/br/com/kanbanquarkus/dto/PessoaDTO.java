package br.com.kanbanquarkus.dto;

import br.com.kanbanquarkus.enums.PessoaStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PessoaDTO {
    private String nome;
    private Integer idade;
    private String cidade;
    private String profissao;
    private String email;
    private PessoaStatus status;

    public PessoaDTO(String nome, Integer idade, String cidade, String profissao, String email, PessoaStatus status) {
        this.nome = nome;
        this.idade = idade;
        this.cidade = cidade;
        this.profissao = profissao;
        this.email = email;
        this.status = status;
    }
}
