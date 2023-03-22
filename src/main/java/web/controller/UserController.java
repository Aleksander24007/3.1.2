package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String allUsers(ModelMap model) {
        List<User> list = userService.getAllUser();
        model.addAttribute("list", list);
        return "index";
    }

    @GetMapping("/new")
    public String add(@ModelAttribute("user") User user) {
        return "newUser";
    }

    @PostMapping
    public String saveUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/user_{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "edit";
    }

    @PatchMapping("/user_{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/";
    }

    @DeleteMapping("/user_{id}")
    public String remove(@PathVariable("id") int id) {
        userService.remove(id);
        return "redirect:/";
    }

}
