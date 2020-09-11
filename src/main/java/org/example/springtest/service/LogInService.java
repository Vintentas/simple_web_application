package org.example.springtest.service;

import org.example.springtest.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.validation.Valid;

@Service
public interface LogInService {
    public String createUser(User user, String pass1, String pass2, Model model);
    public String changePassword(String oldPassword, String newPassword, String confirmPassword);
}
