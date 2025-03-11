package br.com.kanbanquarkus.model;

import org.bson.types.ObjectId;

import br.com.kanbanquarkus.enums.PessoaStatus;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MongoEntity
public class Pessoa {

    private ObjectId id;
    private String nome;
    private Integer idade;
    private String cidade;
    private String profissao;
    private String email;
    private PessoaStatus status;

    public Pessoa(ObjectId id, String nome, Integer idade, String cidade, String profissao, String email,
            PessoaStatus status) {

        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.cidade = cidade;
        this.profissao = profissao;
        this.email = email;
        this.status = status;
    }
}
