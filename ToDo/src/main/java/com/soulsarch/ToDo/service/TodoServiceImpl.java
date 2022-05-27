package com.soulsarch.ToDo.service;

import com.soulsarch.ToDo.model.dto.TodoItemDto;
import com.soulsarch.ToDo.model.entity.TodoItem;
import com.soulsarch.ToDo.model.enums.ListFilter;
import com.soulsarch.ToDo.model.repository.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.AllNestedConditions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.transaction.annotation.Transactional;

//import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.soulsarch.ToDo.model.enums.ListFilter.*;

@Service

@Transactional
public class TodoServiceImpl implements TodoItemService{

    @Autowired
    private TodoItemRepository todoItemRepository;

    ListFilter listFilter;

/*    @Override
    public List<TodoItem> getTodoItemsList() {
        return todoItemRepository.findAll();
    }*/

/*    public List<TodoItemDto> getTodoItemsList() {
        return todoItemRepository.findAll()
                .stream()
                .map(todoItem -> new TodoItemDto(todoItem.getId(),
                        todoItem.getTitle(),
                        todoItem.isCompleted()))
                .collect(Collectors.toList());
    }*/
    private List<TodoItemDto> convertToDto(List<TodoItem> todoItemList) {
        return todoItemList
                .stream()
                .map(todoItem -> new TodoItemDto(todoItem.getId(),
                        todoItem.getTitle(),
                        todoItem.isCompleted()))
                .collect(Collectors.toList());
    }

/*    private List<TodoItemDto> getTodoItem(ListFilter listFilter) {
        return switch (listFilter) {
            case ALL:
                convertToDto(todoItemRepository.findAll());
            case ACTIVE:
                convertToDto(todoItemRepository.findAllByCompleted(false));
            case COMPLETED:
                convertToDto(todoItemRepository.findAllByCompleted(true));
            default:
                convertToDto(todoItemRepository.findAll());

        };
    }*/

    public List<TodoItemDto> getTodoItem(ListFilter listFilter) {
        if (listFilter.equals(ALL)) {
            return convertToDto(todoItemRepository.findAll());
        } else if (listFilter.equals(ACTIVE)) {
            return convertToDto(todoItemRepository.findAllByCompleted(false));
        } else if (listFilter.equals(COMPLETED)) {
            return convertToDto(todoItemRepository.findAllByCompleted(true));
        } else {
            return convertToDto(todoItemRepository.findAll());
        }
    }

    public int getNumberOfActiveItems() {
        return todoItemRepository.countAllByCompleted(false);
    }

    public int getNumberOfCompletedItems() {
        return todoItemRepository.countAllByCompleted(true);
    }



    public void saveTodoItem(TodoItem todoItem) {
        todoItemRepository.save(todoItem);
    }

    public TodoItem getTodoItem(long id) {
        return todoItemRepository.getById(id);
    }

    @Override
    public List<TodoItem> getTodoItemsList() {
        return null;
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

    public long countItem() {

        return todoItemRepository.count();
    }

}
