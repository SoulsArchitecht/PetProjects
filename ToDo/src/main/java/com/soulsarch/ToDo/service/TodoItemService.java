package com.soulsarch.ToDo.service;

import com.soulsarch.ToDo.model.entity.TodoItem;

import java.util.List;

public interface TodoItemService {

    TodoItem getTodoItem(long id);

    List<TodoItem> getTodoItemsList();
}
