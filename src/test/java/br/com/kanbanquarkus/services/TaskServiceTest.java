package br.com.kanbanquarkus.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.kanbanquarkus.dto.TaskDTO;
import br.com.kanbanquarkus.enums.TaskStatus;
import br.com.kanbanquarkus.mapper.TaskMapper;
import br.com.kanbanquarkus.model.Task;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private Task task;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // @Test
    void testPersist() {

        TaskDTO taskDTO = new TaskDTO("Title", "Description", TaskStatus.ATIVO);
        Task mappedTask = TaskMapper.toTask(taskDTO);
        doNothing().when(task).persist(mappedTask);

        taskService.persist(taskDTO);

        verify(task, times(1)).persist(mappedTask);
    }

    // @Test
    void testListAll() {

        List<Task> expectedTasks = new ArrayList<>();
        expectedTasks.add(new Task("Task 1", "Description 1", TaskStatus.ATIVO));
        // when(task.listAll()).thenReturn(expectedTasks);

        List<Task> result = taskService.listAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(expectedTasks, result);
    }

    // @Test
    void testFindByStatus() {
        // Arrange
        String status = "PENDING";
        List<Task> expectedTasks = new ArrayList<>();
        when(task.findByStatus(status)).thenReturn(expectedTasks);

        // Act
        List<Task> result = taskService.findByStatus(status);

        // Assert
        assertNotNull(result);
        assertEquals(expectedTasks, result);
    }

    // @Test
    void testDeleteById() {

        String id = "507f1f77bcf86cd799439011";
        doNothing().when(task).deleteById(any(ObjectId.class));

        taskService.deleteById(id);

        verify(task, times(1)).deleteById(any(ObjectId.class));
    }

    // @Test
    void testFindById() {
        // Arrange
        String id = "507f1f77bcf86cd799439011";
        Task expectedTask = new Task("Task 1", "Description", null);
        when(task.findById(any(ObjectId.class))).thenReturn(expectedTask);

        // Act
        Task result = taskService.findById(id);

        // Assert
        assertNotNull(result);
        assertEquals(expectedTask, result);
    }

    // @Test
    void testUpdate() {
        // Arrange
        String id = "507f1f77bcf86cd799439011";
        TaskDTO taskDTO = new TaskDTO("Updated Title", "Updated Description", null);
        Task mappedTask = TaskMapper.toTask(taskDTO);
        mappedTask.id = new ObjectId(id);

        when(task.findByIdOptional(any(ObjectId.class))).thenReturn(java.util.Optional.of(new Task()));
        doNothing().when(task).update(mappedTask);

        // Act
        taskService.update(id, taskDTO);

        // Assert
        verify(task, times(1)).update(mappedTask);
    }

    // @Test
    void testUpdateThrowsExceptionWhenTaskNotFound() {
        // Arrange
        String id = "507f1f77bcf86cd799439011";
        TaskDTO taskDTO = new TaskDTO("Updated Title", "Updated Description", null);
        when(task.findByIdOptional(any(ObjectId.class))).thenReturn(java.util.Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> taskService.update(id, taskDTO));
        assertEquals("Task not found", exception.getMessage());
    }

    // @Test
    void testFilter() {
        // Arrange
        TaskDTO taskDTO = new TaskDTO("Title", "Description", null);
        Document query = new Document();
        query.append("title", taskDTO.title());
        query.append("description", taskDTO.description());

        List<Task> expectedTasks = new ArrayList<>();
        // when(task.find(query)).thenReturn(mock(Task.class));
        // when(task.find(query).list()).thenReturn(expectedTasks);

        // Act
        List<Task> result = taskService.filter(taskDTO);

        // Assert
        assertNotNull(result);
        assertEquals(expectedTasks, result);
    }

    // @Test
    void testFilterThrowsExceptionWhenNoFilter() {
        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> taskService.filter(null));
        assertEquals("Nenhum filtro foi informado e a busca não pode ser realizada.", exception.getMessage());
    }
}
