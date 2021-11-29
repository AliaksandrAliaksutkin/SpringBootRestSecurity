package org.example.springbootrestsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootrestsecurity.exception.NoEntityException;
import org.example.springbootrestsecurity.model.Address;
import org.example.springbootrestsecurity.model.Role;
import org.example.springbootrestsecurity.model.Status;
import org.example.springbootrestsecurity.model.User;
import org.example.springbootrestsecurity.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/homepage") // /homepage; url - который приводит в данный метод контроллера
    @PreAuthorize("hasAnyAuthority('users:read')")
    public String viewHomePage(Model model) {
        List<User> listUsers = userService.getAllUsers();
        model.addAttribute("listUser", listUsers); // ключ/значение
        return "users";   // имя представления в который преведет данный метод
    }

    @GetMapping("/address")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String getAddress(Model model, @RequestParam Long id) throws NoEntityException {
        User address = userService.getById(id);
        model.addAttribute("address", address);
        return "address";
    }

    @PostMapping("allusers")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String editUser(@RequestParam(required = false) Long id, /*Конструкция require=false сообщает фреймворку о том, что наличие соответствующего bean'а не является обязательным при компиляции программы.*/
                           @RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam Integer age,
                           @RequestParam String password,
                           @RequestParam Role role,
                           @RequestParam Status status,
                           @RequestParam(name = "address.city") String city,
                           @RequestParam(name = "address.street") String street,
                           @RequestParam(name = "address.house") Integer house) {

        String encryptedPassword = passwordEncoder.encode(password);
        User user = new User(id, firstName, lastName, age, encryptedPassword,role,status,new Address(city, street, house));
        userService.save(user);

        return "redirect:/homepage";
    }

    @GetMapping(value = "new")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String userCreate(Model model, User user) {
        model.addAttribute(user);
        return "new";
    }

    @GetMapping("/update")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String updateUser(Model model, @RequestParam Long id) throws NoEntityException {
        model.addAttribute(userService.getById(id));
        return "new";
    }

    @GetMapping("/delete")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public String delete(@RequestParam Long id) {
        userService.deleteById(id);
        return "redirect:/homepage";
    }
}

