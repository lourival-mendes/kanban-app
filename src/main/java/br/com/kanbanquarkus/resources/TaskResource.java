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

@Path("tasks")
@APIResponses(value = {
        @APIResponse(responseCode = "200", description = "Requisição bem sucedida"),
        @APIResponse(responseCode = "400", description = "Requisição inválida"),
        @APIResponse(responseCode = "401", description = "Não autorizado"),
        @APIResponse(responseCode = "403", description = "Acesso negado"),
        @APIResponse(responseCode = "404", description = "Recurso não encontrado"),
        @APIResponse(responseCode = "500", description = "Erro interno do servidor"),
})
public class TaskResource {

    private final TaskService taskService;

    @Inject
    public TaskResource(TaskService taskService) {
        this.taskService = taskService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll() {
        return Response.ok().entity(taskService.listAll()).build();
    }

    @POST
    @Path("/filter")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response filter(@RequestBody TaskDTO taskDTO) {
        System.out.println(">>> RestAPI -> filter");

        return Response.ok().entity(taskService.filter(taskDTO)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") String id) {
        return Response.ok().entity(taskService.findById(id)).build();
    }

    @DELETE
    @Path("/{id}")
    public void deleteTask(@PathParam("id") String id) {
        taskService.deleteById(id);
    }

    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void update(@PathParam("id") String id, @RequestBody TaskDTO task) {
        taskService.update(id, task);
    }

    @POST
    public void persist(@RequestBody TaskDTO task) {
        taskService.persist(task);
    }

}
