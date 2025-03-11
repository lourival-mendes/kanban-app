package br.com.kanbanquarkus.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.ArrayList;

import br.com.kanbanquarkus.enums.TaskStatus;
import io.quarkus.mongodb.panache.PanacheQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

class TaskTest {

    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task();
    }

    @Test
    void testConstructorAndGetters() {

        String title = "Título da Tarefa";
        String description = "Descrição da Tarefa";
        TaskStatus status = TaskStatus.ATIVO;

        Task task = new Task(title, description, status);

        assertEquals(title, task.getTitle());
        assertEquals(description, task.getDescription());
        assertEquals(status, task.getStatus());
    }

    @Test
    void testSetters() {

        String title = "Novo Título";
        String description = "Nova Descrição";
        TaskStatus status = TaskStatus.ATIVO;

        task.setTitle(title);
        task.setDescription(description);
        task.setStatus(status);

        assertEquals(title, task.getTitle());
        assertEquals(description, task.getDescription());
        assertEquals(status, task.getStatus());
    }

    // @Test
    void testFindByStatus() {

        String status = "ATIVO";
        List<Task> expectedTasks = new ArrayList<>();
        expectedTasks.add(new Task("Task 1", "Description 1", TaskStatus.ATIVO));
        expectedTasks.add(new Task("Task 2", "Description 2", TaskStatus.ATIVO));

        try (MockedStatic<Task> mockedStatic = mockStatic(Task.class)) {
            PanacheQuery<Task> mockQuery = mock(PanacheQuery.class);
            mockedStatic.when(() -> Task.find("status", TaskStatus.ATIVO)).thenReturn(mockQuery);
            when(mockQuery.list()).thenReturn(expectedTasks);

            List<Task> result = task.findByStatus(status);

            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals(expectedTasks, result);
        }
    }

    @Test
    void testDefaultConstructor() {

        Task task = new Task();

        assertNull(task.getTitle());
        assertNull(task.getDescription());
        assertNull(task.getStatus());
    }
}
