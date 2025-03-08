package br.com.kanbanquarkus.dto;

import br.com.kanbanquarkus.enums.TaskStatus;

public record TaskDTO(String title, String description, TaskStatus status) {
}
