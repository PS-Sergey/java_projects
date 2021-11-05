package com.opencode.questionare.controller;

import com.opencode.questionare.entity.Role;
import com.opencode.questionare.entity.User;
import com.opencode.questionare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createUser")
    public String saveUser(@RequestParam("username") String username, @RequestParam("password") String password,
                           @RequestParam("role") String roleName) {
        User user = new User(username, password);
        Role role = new Role(roleName);
        user.addRole(role);
        userService.saveUser(user);
        return "redirect:/";
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
