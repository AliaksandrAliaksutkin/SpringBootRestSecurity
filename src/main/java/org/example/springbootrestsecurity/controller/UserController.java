package org.example.springbootrestsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootrestsecurity.exception.NoEntityException;
import org.example.springbootrestsecurity.model.Address;
import org.example.springbootrestsecurity.model.User;
import org.example.springbootrestsecurity.service.UserService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    //    @Secured("hasAnyAuthority('users:read')")
    @PreAuthorize("hasAnyAuthority('users:read')")
    @GetMapping("/homepage")
    public List<User> viewHomePage() {
        return userService.getAllUsers();
    }

    //    @Secured("hasAnyAuthority('users:write')")
    @PreAuthorize("hasAnyAuthority('users:write')")
    @GetMapping("/address/{id}")
    public Address getAddress(@PathVariable Long id) throws NoEntityException { //todo @PathVariable, способ передачи параметров запроса — в виде частей адреса запроса, используется
        return userService.getById(id).getAddress();                            //todo для работы с параметрами, передаваемыми через адрес запроса в Spring WebMVCпш
    }

    //    @Secured("hasAnyAuthority('users:write')")
    @PreAuthorize("hasAnyAuthority('users:write')")
    @PutMapping("/update")
    public void updateUser(@RequestBody User user) {
        userService.save(user);
    }

    //    @Secured(value = {"ROLE_ADMIN"})
    @PreAuthorize("hasAnyAuthority('users:write')")
    @PostMapping(value = "new")
    public void userCreate(@RequestBody User user) {
        userService.save(user);
    }

    //    @Secured("hasAnyAuthority('users:write')")
    @PreAuthorize("hasAnyAuthority('users:write')")
    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteById(id);
    }
}

