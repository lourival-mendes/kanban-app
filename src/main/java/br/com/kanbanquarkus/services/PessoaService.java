package br.com.kanbanquarkus.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import br.com.kanbanquarkus.dao.PessoaDao;
import br.com.kanbanquarkus.dto.PessoaDTO;
import br.com.kanbanquarkus.mapper.PessoaMapper;
import br.com.kanbanquarkus.model.Pessoa;
import br.com.kanbanquarkus.projection.PaginatedResponse;
import br.com.kanbanquarkus.projection.PessoaProjection;
import io.quarkus.mongodb.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PessoaService {

    private final PessoaDao pessoaDao;

    @Inject
    public PessoaService(PessoaDao pessoaDao) {
        this.pessoaDao = pessoaDao;
    }

    public void persist(PessoaDTO pessoaDTO) {
        pessoaDao.persist(PessoaMapper.toPessoa(pessoaDTO));
    }

    public List<Pessoa> listAll() {
        return pessoaDao.listAll();
    }

    public List<Pessoa> findByStatus(String status) {
        return pessoaDao.findByStatus(status);
    }

    public void deleteById(String id) {
        pessoaDao.deleteById(new ObjectId(id));
    }

    public Pessoa findById(String id) {
        return pessoaDao.findById(new ObjectId(id));
    }

    public void update(String id, PessoaDTO pessoaDTO) {

        ObjectId objectId = new ObjectId(id);
        pessoaDao.findByIdOptional(objectId)
                .orElseThrow(() -> new RuntimeException("Pessoa not found"));

        Pessoa pessoa = PessoaMapper.toPessoa(pessoaDTO);
        pessoa.id = objectId;

        pessoaDao.update(pessoa);
    }

    public void update(PessoaDTO pessoaDTO) {
        throw new RuntimeException("Pessoa not found");
    }

    public PaginatedResponse<PessoaProjection> buscarPessoas(PessoaDTO pessoaDTO, int page, int size) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        if (pessoaDTO.nome() != null) {
            query.append("nome = :nome ");
            params.put("nome", pessoaDTO.nome());
        }
        if (pessoaDTO.idade() != null) {
            if (!params.isEmpty())
                query.append("and ");
            query.append("idade = :idade ");
            params.put("idade", pessoaDTO.idade());
        }
        if (pessoaDTO.cidade() != null) {
            if (!params.isEmpty())
                query.append("and ");
            query.append("cidade = :cidade ");
            params.put("cidade", pessoaDTO.cidade());
        }
        if (pessoaDTO.profissao() != null) {
            if (!params.isEmpty())
                query.append("and ");
            query.append("profissao = :profissao ");
            params.put("profissao", pessoaDTO.profissao());
        }
        if (pessoaDTO.email() != null) {
            if (!params.isEmpty())
                query.append("and ");
            query.append("email = :email ");
            params.put("email", pessoaDTO.email());
        }

        PanacheQuery<PessoaProjection> panacheQuery = pessoaDao.find(query.toString(), params)
                .project(PessoaProjection.class);

        long totalRegistros = panacheQuery.count();
        int totalPaginas = (int) Math.ceil((double) totalRegistros / size);
        List<PessoaProjection> resultado = panacheQuery.page(Page.of(page, size)).list();

        return new PaginatedResponse<>(resultado, totalRegistros, totalPaginas, page, size);
    }

}
