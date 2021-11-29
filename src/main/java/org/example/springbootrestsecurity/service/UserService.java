package org.example.springbootrestsecurity.service;

import org.example.springbootrestsecurity.exception.NoEntityException;
import org.example.springbootrestsecurity.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User save(User user);

    User getById(Long id) throws NoEntityException;

    void deleteById(Long id);

}

