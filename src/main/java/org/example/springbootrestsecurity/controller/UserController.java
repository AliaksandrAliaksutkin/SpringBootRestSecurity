package org.example.springbootrestsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootrestsecurity.exception.NoEntityException;
import org.example.springbootrestsecurity.model.Address;
import org.example.springbootrestsecurity.model.User;
import org.example.springbootrestsecurity.service.UserService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @Secured(value = {"hasAnyAuthority('users:read')"})
    @GetMapping("/homepage")
    public List<User> viewHomePage() {
        return userService.getAllUsers();
    }

    @Secured("hasAnyAuthority('users:write')")
    @GetMapping("/address/{id}")
    public Address getAddress(@PathVariable Long id) throws NoEntityException {
        return userService.getById(id).getAddress();
    }

    @Secured("hasAnyAuthority('users:write')")
    @PutMapping("/update")
    public void updateUser(@RequestBody User user) {
        userService.save(user);
    }

    @Secured("hasAnyAuthority('users:write')")
    @GetMapping(value = "new")
    public void userCreate(@RequestBody User user) {
        userService.save(user);
    }

    @Secured("hasAnyAuthority('users:write')")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteById(id);
    }
}

