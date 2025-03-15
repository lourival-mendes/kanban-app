package br.com.kanbanquarkus.resources;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import br.com.kanbanquarkus.dto.PessoaDTO;
import br.com.kanbanquarkus.projection.PaginatedResponse;
import br.com.kanbanquarkus.projection.PessoaProjection;
import br.com.kanbanquarkus.services.PessoaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("pessoas")
@APIResponses(value = {
        @APIResponse(responseCode = "200", description = "Requisição bem sucedida"),
        @APIResponse(responseCode = "400", description = "Requisição inválida"),
        @APIResponse(responseCode = "401", description = "Não autorizado"),
        @APIResponse(responseCode = "403", description = "Acesso negado"),
        @APIResponse(responseCode = "404", description = "Recurso não encontrado"),
        @APIResponse(responseCode = "500", description = "Erro interno do servidor"),
})
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PessoaResource {

    private final PessoaService pessoaService;

    @Inject
    public PessoaResource(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GET
    public Response listAll() {
        return Response.ok().entity(pessoaService.listAll()).build();
    }

    @POST
    @Path("/filter")
    public PaginatedResponse<PessoaProjection> filter(@RequestBody PessoaDTO pessoaDTO,
            @QueryParam("page") int pagina,
            @QueryParam("size") int tamanho) {
        return pessoaService.buscarPessoas(pessoaDTO, pagina, tamanho);
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") String id) {
        return Response.ok().entity(pessoaService.findById(id)).build();
    }

    @DELETE
    @Path("/{id}")
    public void deletePessoa(@PathParam("id") String id) {
        pessoaService.deleteById(id);
    }

    @POST
    @Path("/{id}")
    public void update(@PathParam("id") String id, @RequestBody PessoaDTO pessoa) {
        pessoaService.update(id, pessoa);
    }

    @POST
    public void persist(@RequestBody PessoaDTO pessoa) {
        pessoaService.persist(pessoa);
    }

}
