package br.com.kanbanquarkus.services;

import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import br.com.kanbanquarkus.dao.TaskDao;
import br.com.kanbanquarkus.dto.TaskDTO;
import br.com.kanbanquarkus.entities.Task;
import br.com.kanbanquarkus.mapper.TaskMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TaskService {

    private final TaskDao taskDao;

    @Inject
    public TaskService(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public void persist(TaskDTO taskDTO) {
        taskDao.persist(TaskMapper.toTask(taskDTO));
    }

    public List<Task> listAll() {
        return taskDao.listAll();
    }

    public List<Task> findByStatus(String status) {
        return taskDao.findByStatus(status);
    }

    public void deleteById(String id) {
        taskDao.deleteById(new ObjectId(id));
    }

    public Task findById(String id) {
        return taskDao.findById(new ObjectId(id));
    }

    public void update(String id, TaskDTO taskDTO) {

        ObjectId objectId = new ObjectId(id);
        taskDao.findByIdOptional(objectId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        Task task = TaskMapper.toTask(taskDTO);
        task.id = objectId;

        taskDao.update(task);
    }

    public void update(TaskDTO taskDTO) {
        throw new RuntimeException("Task not found");
    }

    public List<Task> filter(TaskDTO taskDTO) {
        System.out.println(">>> TaskService -> filter");
        System.out.println(taskDTO);

        Document query = new Document();

        if (taskDTO == null) {
            throw new RuntimeException("Nenhum filtro foi informado e a busca não pode ser realizada.");
        }

        if (taskDTO.title() != null && !taskDTO.title().isEmpty()) {
            query.append("title", taskDTO.title());
        }

        if (taskDTO.description() != null && !taskDTO.description().isEmpty()) {
            query.append("description", taskDTO.description());
        }

        if (taskDTO.status() != null && !taskDTO.status().isEmpty()) {
            query.append("status", taskDTO.status());
        }
        System.out.println(">>> TaskService -> query");
        System.out.println(query);

        if (query.isEmpty()) {
            throw new RuntimeException("Nenhum filtro foi informado e a busca não pode ser realizada.");
        } else {
            return taskDao.find(query).list();
        }
    }

}
