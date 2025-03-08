package br.com.kanbanquarkus.projection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.kanbanquarkus.model.Pessoa;
import io.quarkus.mongodb.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PessoaService {

    public PaginatedResponse<PessoaProjection> buscarPessoas(String nome, Integer idade, String cidade, String profissao,
            String email, int page, int size) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        if (nome != null) {
            query.append("nome = :nome ");
            params.put("nome", nome);
        }
        if (idade != null) {
            if (!params.isEmpty())
                query.append("and ");
            query.append("idade = :idade ");
            params.put("idade", idade);
        }
        if (cidade != null) {
            if (!params.isEmpty())
                query.append("and ");
            query.append("cidade = :cidade ");
            params.put("cidade", cidade);
        }
        if (profissao != null) {
            if (!params.isEmpty())
                query.append("and ");
            query.append("profissao = :profissao ");
            params.put("profissao", profissao);
        }
        if (email != null) {
            if (!params.isEmpty())
                query.append("and ");
            query.append("email = :email ");
            params.put("email", email);
        }

        // Criar a consulta
        PanacheQuery<PessoaProjection> panacheQuery = Pessoa.find(query.toString(), params)
                .project(PessoaProjection.class);

        // Obter total de registros
        long totalRegistros = panacheQuery.count();

        // Calcular total de páginas
        int totalPaginas = (int) Math.ceil((double) totalRegistros / size);

        // Aplicar paginação e obter os resultados
        List<PessoaProjection> resultados = panacheQuery.page(Page.of(page, size)).list();

        // Criar resposta paginada
        return new PaginatedResponse<>(resultados, totalRegistros, totalPaginas, page, size);
    }
}
