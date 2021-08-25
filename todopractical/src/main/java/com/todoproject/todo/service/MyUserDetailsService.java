package com.todoproject.todo.service;

import com.todoproject.todo.model.MyUserDetails;
import com.todoproject.todo.model.User;
import com.todoproject.todo.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       // System.out.println(username);
        Optional<User> user=usersRepository.findByEmail(username);
        user.orElseThrow(()->new UsernameNotFoundException("User Not Found"));
        return user.map(MyUserDetails::new).get();
    }
}
