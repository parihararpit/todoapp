package com.todoproject.todo.repo;


import com.todoproject.todo.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsersRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);

}
