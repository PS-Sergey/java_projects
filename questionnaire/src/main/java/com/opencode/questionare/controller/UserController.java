package com.opencode.questionare.controller;

import com.opencode.questionare.entity.Role;
import com.opencode.questionare.entity.User;
import com.opencode.questionare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Size;

@Controller
@Validated
public class UserController {

    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createUser")
    public String saveUser(@RequestParam("username") @Size(min = 3, message = "username length must be > 3") String username,
                           @RequestParam("password") @Size(min = 3, message = "password length must be > 3") String password,
                           @RequestParam("role") String roleName, Model model) {
        User user = new User(username, password);
        Role role = new Role(roleName);
        user.addRole(role);
        if (!userService.saveUser(user)) {
            System.out.println(String.format("Username '%s' is already used", username));
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }
        return "redirect:/";
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public String handlerException(ConstraintViolationException ex, Model model) {
        if (ex.getMessage().contains("username")) {
            model.addAttribute("usernameError", "Имя должно содержать не менее трех символов");
        } else {
            model.addAttribute("passwordError", "Пароль должен содержать не менее трех символов");
        }
        return "registration";
    }

    @GetMapping("/registration")
    public String newUser() {
        return "registration";
    }

    @GetMapping("/questionnaire/users")
    public String getUsers(Model model){
        model.addAttribute("users", userService.getAllUsernames());
        return "users";
    }
}
