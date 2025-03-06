package br.com.kanbanquarkus.services;

import java.util.List;

import org.bson.types.ObjectId;

import br.com.kanbanquarkus.dto.TaskDTO;
import br.com.kanbanquarkus.entities.Task;
import br.com.kanbanquarkus.mapper.TaskMapper;
import br.com.kanbanquarkus.repositories.TaskRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TaskService {

    @Inject
    TaskRepository taskRepository;

    public void addTask(TaskDTO taskDTO) {
        taskRepository.persist(TaskMapper.toTask(taskDTO));
    }

    public List<Task> getAllTasks() {
        return taskRepository.listAll();
    }

    public List<Task> getTasksByStatus(String status) {
        return taskRepository.findByStatus(status);
    }

    public void updateTask(Task task) {
        taskRepository.update(task);
    }

    public void deleteTask(String id) {
        taskRepository.deleteById(new ObjectId(id));
    }

    public Task getById(String id) {
        return taskRepository.findById(new ObjectId(id));
    }

    public void updateTask(String id, TaskDTO taskDTO) {
        Task task = taskRepository.findByIdOptional(new ObjectId(id))
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTitle(taskDTO.title());
        task.setDescription(taskDTO.description());
        task.setStatus(taskDTO.status());
        taskRepository.update(task);

    }
}
