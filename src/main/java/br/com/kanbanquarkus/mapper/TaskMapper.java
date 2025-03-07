package br.com.kanbanquarkus.mapper;

import br.com.kanbanquarkus.dto.TaskDTO;
import br.com.kanbanquarkus.entities.Task;

public class TaskMapper {

    private TaskMapper() {
    }

    public static Task toTask(TaskDTO taskDTO) {
        if (taskDTO == null) {
            return null;
        }
        return new Task(taskDTO.title(), taskDTO.description(), taskDTO.status());
    }

    public static TaskDTO toTaskDTO(Task task) {
        if (task == null) {
            return null;
        }
        return new TaskDTO(task.getTitle(), task.getDescription(), task.getStatus());
    }
}
