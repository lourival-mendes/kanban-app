package br.com.kanbanquarkus.model;

import java.util.List;

import br.com.kanbanquarkus.enums.TaskStatus;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApplicationScoped
@MongoEntity
public class Task extends PanacheMongoEntity {

    private String title;
    private String description;
    private TaskStatus status;

    public List<Task> findByStatus(String status) {
        return find("status", status).list();
    }
}
