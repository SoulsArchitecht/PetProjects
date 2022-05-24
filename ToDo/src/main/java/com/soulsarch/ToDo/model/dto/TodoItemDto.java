package com.soulsarch.ToDo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class TodoItemDto {

    private long id;

    private String title;

    private boolean completed;

    public TodoItemDto(long id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }
}
