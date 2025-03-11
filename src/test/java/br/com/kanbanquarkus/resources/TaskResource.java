package br.com.kanbanquarkus.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.kanbanquarkus.dto.TaskDTO;
import br.com.kanbanquarkus.enums.TaskStatus;
import br.com.kanbanquarkus.model.Task;
import br.com.kanbanquarkus.services.TaskService;
import jakarta.ws.rs.core.Response;

class TaskResourceTest {

    @Mock
    TaskService taskService;

    @InjectMocks
    TaskResource taskResource;

    private TaskDTO taskDTO;
    private Task task;
    private List<Task> taskList;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        taskDTO = new TaskDTO("title", "description", TaskStatus.ATIVO);

        task = new Task("title", "description", TaskStatus.ATIVO);
        taskList = Arrays.asList(task);

        when(taskService.listAll()).thenReturn(taskList);
        when(taskService.filter(any(TaskDTO.class))).thenReturn(taskList);
        when(taskService.findById(anyString())).thenReturn(task);
    }

    @Test
    void testListAll() {
        Response response = taskResource.listAll();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(taskList, response.getEntity());
    }

    @Test
    void testFilter() {
        Response response = taskResource.filter(taskDTO);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(taskList, response.getEntity());
    }

    // @Test
    void testFindById() {
        Response response = taskResource.findById("id");

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(taskDTO, response.getEntity());
    }

    @Test
    void testDeleteTask() {
        Response response = taskResource.deleteTask("id");

        verify(taskService, times(1)).deleteById("id");
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }

    @Test
    void testUpdate() {
        Response response = taskResource.update("id", taskDTO);

        verify(taskService, times(1)).update("id", taskDTO);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    void testPersist() {
        Response response = taskResource.persist(taskDTO);

        verify(taskService, times(1)).persist(taskDTO);
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
    }
}
