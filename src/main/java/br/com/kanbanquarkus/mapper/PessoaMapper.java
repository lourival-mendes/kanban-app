package br.com.kanbanquarkus.mapper;

import br.com.kanbanquarkus.dto.PessoaDTO;
import br.com.kanbanquarkus.model.Pessoa;

public class PessoaMapper {

    private PessoaMapper() {
    }

    public static Pessoa toPessoa(PessoaDTO pessoaDTO) {
        if (pessoaDTO == null) {
            return null;
        }

        return new Pessoa(pessoaDTO.nome(), pessoaDTO.idade(), pessoaDTO.cidade(), pessoaDTO.profissao(),
                pessoaDTO.email(), pessoaDTO.status());
    }

    public static PessoaDTO toPessoaDTO(Pessoa pessoa) {
        if (pessoa == null) {
            return null;
        }
        return new PessoaDTO(pessoa.getNome(), pessoa.getIdade(), pessoa.getCidade(), pessoa.getProfissao(),
                pessoa.getEmail(), pessoa.getStatus());
    }
}
