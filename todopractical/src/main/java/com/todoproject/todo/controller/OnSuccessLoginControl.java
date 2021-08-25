package com.todoproject.todo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class OnSuccessLoginControl {
    @GetMapping("")
    public boolean show()
    {
        System.out.println("Hii User");
        return true;
    }

}
