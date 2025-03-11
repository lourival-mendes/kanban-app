package br.com.kanbanquarkus.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.com.kanbanquarkus.dto.TaskDTO;
import br.com.kanbanquarkus.enums.TaskStatus;
import br.com.kanbanquarkus.model.Task;

class TaskMapperTest {

    @Test
    void testToTask() {
        TaskDTO taskDTO = new TaskDTO("title", "description", TaskStatus.ATIVO);
        Task task = TaskMapper.toTask(taskDTO);

        assertEquals(taskDTO.title(), task.getTitle());
        assertEquals(taskDTO.description(), task.getDescription());
        assertEquals(taskDTO.status(), task.getStatus());
    }

    @Test
    void testToTaskNull() {
        Task task = TaskMapper.toTask(null);
        assertNull(task);
    }

    @Test
    void testToTaskDTO() {
        Task task = new Task("title", "description", TaskStatus.ATIVO);
        TaskDTO taskDTO = TaskMapper.toTaskDTO(task);

        assertEquals(task.getTitle(), taskDTO.title());
        assertEquals(task.getDescription(), taskDTO.description());
        assertEquals(task.getStatus(), taskDTO.status());
    }

    @Test
    void testToTaskDTONull() {
        TaskDTO taskDTO = TaskMapper.toTaskDTO(null);
        assertNull(taskDTO);
    }

    // Adicionando testes para verificar campos individuais e valores padrão
    @Test
    void testToTaskEmptyFields() {
        TaskDTO taskDTO = new TaskDTO("", "", TaskStatus.ATIVO);
        Task task = TaskMapper.toTask(taskDTO);

        assertEquals(taskDTO.title(), task.getTitle());
        assertEquals(taskDTO.description(), task.getDescription());
        assertEquals(taskDTO.status(), task.getStatus());
    }

    @Test
    void testToTaskDTOEmptyFields() {
        Task task = new Task("", "", TaskStatus.ATIVO);
        TaskDTO taskDTO = TaskMapper.toTaskDTO(task);

        assertEquals(task.getTitle(), taskDTO.title());
        assertEquals(task.getDescription(), taskDTO.description());
        assertEquals(task.getStatus(), taskDTO.status());
    }
}
