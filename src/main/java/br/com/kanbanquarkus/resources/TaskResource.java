package br.com.kanbanquarkus.resources;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import br.com.kanbanquarkus.dto.TaskDTO;
import br.com.kanbanquarkus.services.TaskService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("tasks")
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
public class TaskResource {

    private final TaskService taskService;

    @Inject
    public TaskResource(TaskService taskService) {
        this.taskService = taskService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll() {
        return Response.status(Status.OK).entity(taskService.listAll()).build();
    }

    // TODO: Implementar o método filter com paginação
    @POST
    @Path("/filter")
    public Response filter(@RequestBody TaskDTO taskDTO) {
        return Response.ok().entity(taskService.filter(taskDTO)).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") String id) {
        return Response.ok().entity(taskService.findById(id)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTask(@PathParam("id") String id) {
        taskService.deleteById(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @POST
    @Path("/{id}")
    public Response update(@PathParam("id") String id, @RequestBody TaskDTO task) {
        taskService.update(id, task);
        return Response.status(Status.OK).build();
    }

    @POST
    public Response persist(@RequestBody TaskDTO task) {
        taskService.persist(task);
        return Response.status(Status.CREATED).build();
    }
}
