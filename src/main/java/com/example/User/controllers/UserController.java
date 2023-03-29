package com.example.User.controllers;

import com.example.User.model.User;
import com.example.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/")
    public String getAllUsers(ModelMap model) {
        List<User> list = userService.getAllUser();
        model.addAttribute("list", list);
        return "index";
    }

    @GetMapping("/new")
    public String getPageNewUser(@ModelAttribute("user") User user) {
        return "newUser";
    }

    @PostMapping("/")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/user_{id}/edit")
    public String getPageEdit(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PatchMapping("/user_{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/";
    }

    @DeleteMapping("/user_{id}")
    public String removeUser(@PathVariable("id") int id) {
        userService.removeUser(id);
        return "redirect:/";
    }
}
