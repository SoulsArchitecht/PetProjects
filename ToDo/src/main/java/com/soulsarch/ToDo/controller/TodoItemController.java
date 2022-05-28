package com.soulsarch.ToDo.controller;

import com.soulsarch.ToDo.model.dto.TodoItemFormData;
import com.soulsarch.ToDo.model.entity.TodoItem;
import com.soulsarch.ToDo.model.enums.ListFilter;
import com.soulsarch.ToDo.service.TodoServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        addAttributesForIndex(model, ListFilter.ALL);
        return "index";
    }

    @GetMapping("/active")
    public String indexActive(Model model) {
        addAttributesForIndex(model, ListFilter.ACTIVE);
        return "index";
    }

    @GetMapping("/completed")
    public String indexCompleted(Model model) {
        addAttributesForIndex(model, ListFilter.COMPLETED);
        return "index";
    }

    @PostMapping
    public String addNewTodoItem(@Valid @ModelAttribute("todoItem") TodoItemFormData formData) {
        todoService.saveTodoItem(new TodoItem(formData.getTitle(), false));
        return "redirect:/";
    }

    @PutMapping("/{id}/toggle")
    public String toggleSelection(@PathVariable("id") Long id) {
        TodoItem todoItem = todoService.getTodoItem(id); // throw exception needed
        todoItem.setCompleted(!todoItem.isCompleted());
        todoService.saveTodoItem(todoItem);
        return "redirect:/";
    }


    private void addAttributesForIndex (Model model, ListFilter listFilter) {
        model.addAttribute("item", new TodoItemFormData());
        model.addAttribute("filter", listFilter);
        model.addAttribute("todoList", todoService.getTodoItem(listFilter));
        model.addAttribute("totalNumberOfItems", todoService.countItem());
        model.addAttribute("numberOfActiveItems", todoService.getNumberOfActiveItems());
        model.addAttribute("numberOfCompletedItems", todoService.getNumberOfCompletedItems());
    }

    /*    @GetMapping
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
    }*/

    /*    @PostMapping("/")
    public String addNewTodoItem(@ModelAttribute("todoItem") TodoItemFormData formData) {
        TodoItem todoItem = new TodoItem();
        model.addAttribute("todoItem", todoItem);
        todoService.saveTodoItem(new TodoItem(formData.getTitle(), false));
        return "redirect:/";
    }*/
}
