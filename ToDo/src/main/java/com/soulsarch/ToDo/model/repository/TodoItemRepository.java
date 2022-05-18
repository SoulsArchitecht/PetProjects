package com.soulsarch.ToDo.model.repository;

import com.soulsarch.ToDo.model.entity.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
}
