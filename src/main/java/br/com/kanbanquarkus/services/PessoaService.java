package br.com.kanbanquarkus.services;

import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.jboss.logging.Logger;

import br.com.kanbanquarkus.dao.PessoaDao;
import br.com.kanbanquarkus.dto.PessoaDTO;
import br.com.kanbanquarkus.mapper.PessoaMapper;
import br.com.kanbanquarkus.model.Pessoa;
import br.com.kanbanquarkus.projection.PaginatedResponse;
import br.com.kanbanquarkus.projection.PessoaProjection;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PessoaService {

    private final PessoaDao pessoaDao;
    private static final Logger LOGGER = Logger.getLogger(PessoaService.class);

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

    public PaginatedResponse<List<PessoaProjection>> filter(PessoaDTO pessoaDTO, int pagina, int tamanho) {
        LOGGER.info(">>> PessoaService -> filter");
        LOGGER.info(pessoaDTO);

        Document query = new Document();

        if (pessoaDTO == null) {
            throw new RuntimeException("Nenhum filtro foi informado e a busca não pode ser realizada.");
        }

        if (pessoaDTO.getNome() != null && !pessoaDTO.getNome().isEmpty()) {
            query.append("title", pessoaDTO.getNome());
        }

        if (pessoaDTO.getEmail() != null && !pessoaDTO.getEmail().isEmpty()) {
            query.append("description", pessoaDTO.getEmail());
        }

        LOGGER.info(">>> PessoaService -> query");
        LOGGER.info(query);

        if (query.isEmpty()) {
            throw new RuntimeException("Nenhum filtro foi informado e a busca não pode ser realizada.");
        } else {

            List<PessoaProjection> resultados = pessoaDao.find(query).project(PessoaProjection.class)
                    .page(pagina, tamanho).list();
            LOGGER.info(">>> PessoaService -> resultados");

            PaginatedResponse<List<PessoaProjection>> paginatedResponse = new PaginatedResponse(resultados,
                    resultados.size(), resultados.size() / tamanho,
                    pagina, tamanho);

            LOGGER.info(">>> PessoaService -> paginatedResponse");
            return paginatedResponse;
        }
    }

}
