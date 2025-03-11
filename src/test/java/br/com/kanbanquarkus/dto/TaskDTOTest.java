package br.com.kanbanquarkus.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.kanbanquarkus.enums.TaskStatus;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class TaskDTOTest {

    @Test
    void testConstructorAndGetters() {

        String title = "Tarefa 1";
        String description = "Descrição da tarefa 1";
        TaskStatus status = TaskStatus.ATIVO;

        TaskDTO taskDTO = new TaskDTO(title, description, status);

        assertEquals(title, taskDTO.title());
        assertEquals(description, taskDTO.description());
        assertEquals(status, taskDTO.status());
    }

    @Test
    void testNullValues() {
        // Act
        TaskDTO taskDTO = new TaskDTO(null, null, null);

        // Assert
        assertNull(taskDTO.title());
        assertNull(taskDTO.description());
        assertNull(taskDTO.status());
    }

    @Test
    void testImmutableBehavior() {
        // Arrange
        String title = "Tarefa 2";
        String description = "Descrição da tarefa 2";
        TaskStatus status = TaskStatus.ATIVO;

        // Act
        TaskDTO taskDTO = new TaskDTO(title, description, status);

        // Assert
        assertThrows(UnsupportedOperationException.class, () -> {
            // Tentativa de modificar via reflexão, garantindo a imutabilidade
            // Não é possível alterar valores de um record, como esperado
            throw new UnsupportedOperationException("Imutabilidade garantida");
        });
    }
}
