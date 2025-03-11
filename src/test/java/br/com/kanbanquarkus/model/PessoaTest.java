package br.com.kanbanquarkus.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

import br.com.kanbanquarkus.enums.PessoaStatus;

class PessoaTest {

    @Test
    void testConstructorAndGetters() {

        ObjectId id = new ObjectId();
        String nome = "João";
        int idade = 30;
        String cidade = "São Paulo";
        String profissao = "Engenheiro";
        String email = "joao@example.com";
        PessoaStatus status = PessoaStatus.ATIVO;

        Pessoa pessoa = new Pessoa(id, nome, idade, cidade, profissao, email, status);

        assertEquals(id, pessoa.getId());
        assertEquals(nome, pessoa.getNome());
        assertEquals(idade, pessoa.getIdade());
        assertEquals(cidade, pessoa.getCidade());
        assertEquals(profissao, pessoa.getProfissao());
        assertEquals(email, pessoa.getEmail());
        assertEquals(status, pessoa.getStatus());
    }

    @Test
    void testSetters() {

        Pessoa pessoa = new Pessoa();
        ObjectId id = new ObjectId();
        String nome = "Maria";
        int idade = 25;
        String cidade = "Rio de Janeiro";
        String profissao = "Médica";
        String email = "maria@example.com";
        PessoaStatus status = PessoaStatus.INATIVO;

        pessoa.setId(id);
        pessoa.setNome(nome);
        pessoa.setIdade(idade);
        pessoa.setCidade(cidade);
        pessoa.setProfissao(profissao);
        pessoa.setEmail(email);
        pessoa.setStatus(status);

        assertEquals(id, pessoa.getId());
        assertEquals(nome, pessoa.getNome());
        assertEquals(idade, pessoa.getIdade());
        assertEquals(cidade, pessoa.getCidade());
        assertEquals(profissao, pessoa.getProfissao());
        assertEquals(email, pessoa.getEmail());
        assertEquals(status, pessoa.getStatus());
    }

    @Test
    void testDefaultConstructor() {

        Pessoa pessoa = new Pessoa();

        assertNull(pessoa.getId());
        assertNull(pessoa.getNome());
        assertNull(pessoa.getIdade());
        assertNull(pessoa.getCidade());
        assertNull(pessoa.getProfissao());
        assertNull(pessoa.getEmail());
        assertNull(pessoa.getStatus());
    }
}
