package br.com.kanbanquarkus.dao;

import java.util.List;

import br.com.kanbanquarkus.model.Pessoa;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PessoaDao implements PanacheMongoRepository<Pessoa> {

    public List<Pessoa> findByProfissao(String profissao) {
        return find("profissao", profissao).list();
    }

    public List<Pessoa> findByStatus(String status) {
        return find("status", status).list();
    }
}
