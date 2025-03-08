package br.com.kanbanquarkus.model;

import br.com.kanbanquarkus.enums.TaskStatus;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MongoEntity(collection = "tasks")
public class Task extends PanacheMongoEntity {

    private String title;
    private String description;
    private TaskStatus status;

}
