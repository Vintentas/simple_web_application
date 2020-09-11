package org.example.springtest.controllers;


import lombok.RequiredArgsConstructor;
import org.example.springtest.entities.User;
import org.example.springtest.service.LogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.example.springtest.common.Paths.*;

@Controller
@RequiredArgsConstructor
public class LogInController {
    final private LogInService logInService;

    @GetMapping("login")
    public String logIn() {
        return "login";
    }


    @GetMapping(value = "signup")
    public String signUp(Model model) {
        model.addAttribute(USER, new User());
        model.addAttribute(MESSAGE, "Please fill the form, its free");
        return "signup";
    }

    @PostMapping(value = "create")
    public String createUser
            (@ModelAttribute @Valid User user,
             @RequestParam String pass1,
             @RequestParam String pass2,
             Model model) {
        return logInService.createUser(user,pass1,pass2,model);
    }

    @GetMapping(value = "changepass")
    public String changePassword() {
        return "changepass";
    }

    @PostMapping(value = "changepass")
    public String changePassword
            (@RequestParam String oldPassword,
             @RequestParam String newPassword,
             @RequestParam String confirmPassword, Model model) {

        model.addAttribute(MESSAGE, logInService.changePassword(oldPassword, newPassword, confirmPassword));
        return "changepass";
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public String errorPage(IllegalArgumentException e) {
        //ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        return "error";
    }

}
