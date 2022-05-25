package com.soulsarch.ToDo.controller;

import com.soulsarch.ToDo.model.dto.TodoItemFormData;
import com.soulsarch.ToDo.model.entity.TodoItem;
import com.soulsarch.ToDo.service.TodoServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class TodoItemController {

    TodoServiceImpl todoService;

    public TodoItemController(TodoServiceImpl todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public String index(Model model) {
        //List<TodoItem> todoItemList = todoService.getTodoItemsList();
        model.addAttribute("todoItem", new TodoItemFormData());
        model.addAttribute("todoList", todoService.getTodoItemsList());
        model.addAttribute("totalNumberOfItems", todoService.countItem());
        return "index";
    }

    @PostMapping
    public String addNewTodoItem (@Valid @ModelAttribute("todoItem") TodoItemFormData formData) {
        todoService.saveTodoItem(new TodoItem(formData.getTitle(), false));

        return "redirect:/";
    }

/*    @PostMapping("/")
    public String addNewTodoItem(@ModelAttribute("todoItem") TodoItemFormData formData) {
        TodoItem todoItem = new TodoItem();
        model.addAttribute("todoItem", todoItem);
        todoService.saveTodoItem(new TodoItem(formData.getTitle(), false));
        return "redirect:/";
    }*/
}
