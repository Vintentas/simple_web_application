package org.example.springtest.controllers;

import lombok.RequiredArgsConstructor;
import org.example.springtest.entities.User;
import org.example.springtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.example.springtest.common.Paths.*;


@Controller
@RequiredArgsConstructor
public class UserController {
    final private UserService userService;

    @GetMapping(value = "find")
    public String findLoad(Model model) {
        model.addAttribute(USERS, userService.findAll());
        return createFormFind(model);
    }

    @PostMapping(value = "filter")
    public String filter(@RequestParam String nameFilter, @RequestParam String lastNameFilter, Model model) {
        model.addAttribute(USERS, userService.find(nameFilter, lastNameFilter));
        return createFormFind(model);
    }

    @PostMapping(value = "delete")
    public String delete(@RequestParam int id, Model model) {
        model.addAttribute(MESSAGE, userService.delete(id));
        return createFormFind(model);
    }

    @PostMapping(value = "updateUser")
    public String updateUser(@ModelAttribute User user, Model model){
        userService.updateUser(user);
        model.addAttribute(USERS, userService.findAll());
        return createFormFind(model);
    }

    private String createFormFind(Model model){
        model.addAttribute(USER, new User());
        return "find";
    }

}