package com.todoproject.todo.controller;

import com.todoproject.todo.model.TodoItem;
import com.todoproject.todo.model.User;
import com.todoproject.todo.model.validateuserModel;
import com.todoproject.todo.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/user")
@RestController
public class UsersController {


    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UsersRepository usersRepository;

    @PostMapping("/temp")
    public void vali()
    {
        System.out.println("Hii Logged in User");
        //return true;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/get")
    public List<User> finding()
    {
        return usersRepository.findAll();
    }

    public int fetchidbyemail(String email){
        Optional<User> user=usersRepository.findByEmail(email);
        User user1=user.get();
        //System.out.println(user1.getId());
        return user1.getId();
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/get/{id}")
    public User getById(@PathVariable int id)
    {

        Optional<User> user=usersRepository.findById(id);
        return user.get();

//        User temp=new User();
//        int ch=0;
//        for(User check:users)
//        {
//            if(check.getId()==id)
//            {
//                temp=check;
//                ch=1;
//                break;
//            }
//            if(ch==1)
//                break;
//        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/getuserdata")
    public User getuserdata(@RequestBody validateuserModel validateuserModel)
    {
        int id=fetchidbyemail(validateuserModel.getId());
        return getById(id);
    }

    @GetMapping
    public List<TodoItem> gettodolist(int id)
    {
        User user =getById(id);
        List<TodoItem>todoItems;
        System.out.println(user.getId()+" "+user.getEmail());
        todoItems= user.getTodoItems();
        return todoItems;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/gettodo")
    public List<TodoItem> gettodo(@RequestBody validateuserModel validateuserModels)
    {
        String email=validateuserModels.getId();
        int id=fetchidbyemail(email);
        return gettodolist(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getall")
    public List<String> getallname()
    {
        List<String>temp=new ArrayList<>();
        List<User> users=finding();
        for (User user:users)
        {
            String res=user.getFirstname()+" "+user.getLastname();
            temp.add(res);
        }
        return temp;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/save")
    public User post(@RequestBody User user)
    {
        System.out.println(user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usersRepository.save(user);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/update")
    public User update(@RequestBody User user)
    {
       return usersRepository.save(user);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/del/{id}")
    public User delete(@PathVariable int id)
    {
        User user =getById(id);
        if(user.getId()==0)
            return user;
        usersRepository.deleteById(id);
        return user;

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/validate")
    public boolean validate()
    {

        System.out.println("Hello");
        return true;
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/signup")
    public User signup(@RequestBody User user)
    {
        System.out.println(user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user=usersRepository.save(user);
        return user;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/changepassword")
    public  boolean changepassword(@RequestBody validateuserModel validateuserModel)
    {

        User user = getById(fetchidbyemail(validateuserModel.getId()));
        user.setPassword(passwordEncoder.encode(validateuserModel.getPassword()));
        update(user);
        return true;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/deleteuser")
    public boolean deleteuser(@RequestBody validateuserModel validateuserModel)
    {
        delete(fetchidbyemail(validateuserModel.getId()));
        return true;
    }

    @GetMapping("/logout")
    public void logout()
    {

    }


}
