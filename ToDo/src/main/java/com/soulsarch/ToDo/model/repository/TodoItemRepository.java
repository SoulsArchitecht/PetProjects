package com.soulsarch.ToDo.model.repository;

import com.soulsarch.ToDo.model.entity.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {

    int countAllByCompleted(boolean completed);

    List<TodoItem> findAllByCompleted(boolean completed);

    String query1 = "SELECT * FROM todo_items  WHERE "
            + "title LIKE %:keyword%";

    @Query(value = query1, nativeQuery = true)
    List<TodoItem> search(@Param("keyword") String keyword);
}
