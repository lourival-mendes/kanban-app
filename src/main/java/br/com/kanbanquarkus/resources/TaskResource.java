package br.com.kanbanquarkus.resources;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import br.com.kanbanquarkus.dto.TaskDTO;
import br.com.kanbanquarkus.entities.Task;
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

@Path("tasks")
public class TaskResource {

    @Inject
    TaskService taskService;

    @GET
    @APIResponse(responseCode = "200", description = "Requisição bem-sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class)))
    @Produces(MediaType.APPLICATION_JSON)
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GET
    @Path("/{id}")
    @APIResponse(responseCode = "200", description = "Requisição bem-sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class)))
    @Produces(MediaType.APPLICATION_JSON)
    public Task getById(@PathParam("id") String id) {
        return taskService.getById(id);
    }

    @DELETE
    @Path("/{id}")
    @APIResponse(responseCode = "200", description = "Requisição bem-sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class)))
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteTask(@PathParam("id") String id) {
        taskService.deleteTask(id);
    }

    @POST
    @Path("/{id}")
    @APIResponse(responseCode = "200", description = "Requisição bem-sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class)))
    @Produces(MediaType.APPLICATION_JSON)
    public void updateTask(@PathParam("id") String id, @RequestBody TaskDTO task) {
        taskService.updateTask(id, task);
    }

    @POST
    @APIResponse(responseCode = "204", description = "Requisição bem-sucedida")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void addTask(@RequestBody TaskDTO task) {
        taskService.addTask(task);
    }

}
