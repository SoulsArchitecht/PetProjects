package com.soulsarch.ToDo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "todo_items")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "completed")
    private boolean completed;

    public TodoItem(String title, boolean completed) {
        this.title = title;
        this.completed = completed;
    }
}
