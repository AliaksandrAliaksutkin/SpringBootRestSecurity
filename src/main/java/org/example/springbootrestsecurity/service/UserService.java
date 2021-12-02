package org.example.springbootrestsecurity.service;

import org.example.springbootrestsecurity.exception.NoEntityException;
import org.example.springbootrestsecurity.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void save(User user);

    User getById(Long id) throws NoEntityException;

    User update(User user);

    void deleteById(Long id);

}

