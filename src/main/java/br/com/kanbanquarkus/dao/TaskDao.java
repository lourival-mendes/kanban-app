package br.com.kanbanquarkus.dao;

import br.com.kanbanquarkus.model.Task;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TaskDao implements PanacheMongoRepository<Task> {

}
