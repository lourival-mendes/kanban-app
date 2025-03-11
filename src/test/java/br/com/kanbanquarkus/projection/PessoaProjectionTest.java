package br.com.kanbanquarkus.projection;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PessoaProjectionTest {

    @Test
    void testPessoaProjection() {

        String nome = "João";
        Integer idade = 30;
        String cidade = "Brasília";
        String profissao = "Desenvolvedor";
        PessoaProjection pessoa = new PessoaProjection(nome, idade, cidade, profissao);

        assertEquals(nome, pessoa.nome());
        assertEquals(idade, pessoa.idade());
        assertEquals(cidade, pessoa.cidade());
        assertEquals(profissao, pessoa.profissao());
    }
}
