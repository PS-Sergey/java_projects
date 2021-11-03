package com.opencode.questionare.controller;

import com.opencode.questionare.entity.Role;
import com.opencode.questionare.entity.User;
import com.opencode.questionare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

//    @PostMapping("/createUser")
//    @ResponseBody
//    public void saveUser(@RequestBody User user) {
//        userService.saveUser(user);
//    }

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

//    @GetMapping("/registration")
//    public String newUser(Model model) {
//        User user = new User();
//        Role role = new Role();
//        user.addRole(role);
//        model.addAttribute("user", user);
//        return "registration";
//    }
//
//    @PostMapping("/createUser")
//    public String createUser(@ModelAttribute("user") User user) {
//        userService.saveUser(user);
//        return "redirect:/forms";
//    }
}
