package br.com.kanbanquarkus.repositories;

import java.util.List;

import br.com.kanbanquarkus.entities.Task;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TaskRepository implements PanacheMongoRepository<Task> {

    public List<Task> findByStatus(String status) {
        return find("status", status).list();
    }
}

