package br.com.kanbanquarkus.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

import br.com.kanbanquarkus.dto.PessoaDTO;
import br.com.kanbanquarkus.enums.PessoaStatus;
import br.com.kanbanquarkus.model.Pessoa;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class PessoaMapperTest {

    @Test
    void testToPessoa() {
        // Arrange
        PessoaDTO pessoaDTO = new PessoaDTO("João", 30, "São Paulo", "Engenheiro", "joao@example.com",
                PessoaStatus.ATIVO);

        // Act
        Pessoa pessoa = PessoaMapper.toPessoa(pessoaDTO);

        // Assert
        assertNotNull(pessoa);
        assertEquals("João", pessoa.getNome());
        assertEquals(30, pessoa.getIdade());
        assertEquals("São Paulo", pessoa.getCidade());
        assertEquals("Engenheiro", pessoa.getProfissao());
        assertEquals("joao@example.com", pessoa.getEmail());
        assertEquals(PessoaStatus.ATIVO, pessoa.getStatus());
    }

    @Test
    void testToPessoa_NullInput() {
        // Act
        Pessoa pessoa = PessoaMapper.toPessoa(null);

        // Assert
        assertNull(pessoa);
    }

    @Test
    void testToPessoaDTO() {
        // Arrange
        Pessoa pessoa = new Pessoa(new ObjectId("111111111111111111111111"), "Maria", 25, "Rio de Janeiro", "Médica",
                "maria@example.com", PessoaStatus.ATIVO);

        // Act
        PessoaDTO pessoaDTO = PessoaMapper.toPessoaDTO(pessoa);

        // Assert
        assertNotNull(pessoaDTO);
        assertEquals("Maria", pessoaDTO.nome());
        assertEquals(25, pessoaDTO.idade());
        assertEquals("Rio de Janeiro", pessoaDTO.cidade());
        assertEquals("Médica", pessoaDTO.profissao());
        assertEquals("maria@example.com", pessoaDTO.email());
        assertEquals(PessoaStatus.ATIVO, pessoaDTO.status());
    }

    @Test
    void testToPessoaDTO_NullInput() {
        // Act
        PessoaDTO pessoaDTO = PessoaMapper.toPessoaDTO(null);

        // Assert
        assertNull(pessoaDTO);
    }
}