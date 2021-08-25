package com.todoproject.todo.controller;

import com.todoproject.todo.model.TodoItem;
import com.todoproject.todo.model.User;
import com.todoproject.todo.model.validateuserModel;
import com.todoproject.todo.repo.TodoItemRepository;
import com.todoproject.todo.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/todo")
@RestController

public class TodoController {
    @Autowired
    TodoItemRepository todoItemRepository;

    @Autowired
    UsersRepository usersRepository;

    public int fetchidbyemail(String email){
        Optional<User> user=usersRepository.findByEmail(email);
        User user1=user.get();
        //System.out.println(user1.getId());
        return user1.getId();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/get")
    public List<TodoItem> getAllTodo()
    {
        return todoItemRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/get/{id}")
    public TodoItem getById(@PathVariable int id)
    {
        TodoItem temp=new TodoItem();
        int ch=0;
        List<TodoItem>todoItems=todoItemRepository.findAll();

        for(TodoItem check:todoItems)
        {
            if(check.getId()==id)
            {
                temp=check;
                ch=1;
                break;
            }
            if(ch==1)
                break;
        }
        return temp;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/save")
    public TodoItem save(@RequestBody TodoItem todoItem)
    {
        User user=todoItem.getUsers();
        int id=fetchidbyemail(user.getEmail());
        Optional<User> temp=usersRepository.findById(id);
        System.out.println(user.getId());
        user=temp.get();
        todoItem.setUsers(user);
        //todoItem.getUsers().setId(fetchidbyemail(todoItem.getUsers().getEmail()));
        return todoItemRepository.save(todoItem);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/deltodo")
    public void deltodo(@RequestBody TodoItem todoItem)
    {
        deletetodo(todoItem);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/updateisdone")
    public void updateisdone(@RequestBody TodoItem todo)
    {
        todo=getById(todo.getId());
        todo.setIsDone(!todo.isIsDone());
        update(todo);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/save/many")
    public void saveById(@RequestBody List<TodoItem> todoItem)
    {
        for (TodoItem todoItem1:todoItem)
        {
            todoItemRepository.save(todoItem1);
        }

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/update")
    public TodoItem update(@RequestBody TodoItem todoItem)
    {
               return todoItemRepository.save(todoItem);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/del/{id}")
    public void delete(@PathVariable int id)
    {
        TodoItem todoItem=getById(id);
        if(todoItem.getId()==0)
            return ;
        todoItemRepository.deleteById(id);
        return ;

    }

    @DeleteMapping("/delete")
    public void deletetodo(@RequestBody TodoItem todoItem)
    {
        todoItemRepository.delete(todoItem);
    }
}
