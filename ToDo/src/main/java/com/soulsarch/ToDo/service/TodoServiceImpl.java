package com.soulsarch.ToDo.service;

import com.soulsarch.ToDo.model.entity.TodoItem;
import com.soulsarch.ToDo.model.repository.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TodoServiceImpl implements TodoItemService{

    @Autowired
    private TodoItemRepository todoItemRepository;

    @Override
    public List<TodoItem> getTodoItemsList() {
        return todoItemRepository.findAll();
    }

    public void saveTodoItem(TodoItem todoItem) {
        todoItemRepository.save(todoItem);
    }

    public TodoItem getTodoItem(long id) {
        return todoItemRepository.getById(id);
    }

    public void deleteTodoItem(long id) {
        todoItemRepository.deleteById(id);
    }

    public void deleteAllTodoItems() {
        todoItemRepository.deleteAll();
    }

    public List<TodoItem> search (String keyword) {
        return todoItemRepository.search(keyword);
    }

}
