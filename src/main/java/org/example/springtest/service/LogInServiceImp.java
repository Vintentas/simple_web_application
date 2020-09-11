package org.example.springtest.service;

import lombok.RequiredArgsConstructor;
import org.example.springtest.aspect.LoggerMarker;
import org.example.springtest.dao.mybatis.UserMapper;
import org.example.springtest.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import static org.example.springtest.common.Paths.MESSAGE;
import static org.example.springtest.common.Paths.USER;


@Service
@RequiredArgsConstructor
public class LogInServiceImp implements LogInService {
    final UserMapper userRepository;
    final UserService userService;

    @Override
    public String createUser(User user, String pass1, String pass2, Model model) {
        model.addAttribute(USER, user);
        if (wrongPassword(pass1, pass2)) {
            model.addAttribute(MESSAGE, "Password confirmation failed");
            return "signup";
        }
        if (wrongMail(user)) {
            model.addAttribute(MESSAGE, "This Email is already used");
            return "signup";
        }
        userService.save(user, pass1);
        return "login";
    }


    @Override
    public String changePassword(String oldPassword, String newPassword, String confirmPassword) {
        User user = userRepository.findByEmail(getCurrentUsername());
        String dbPassword = user.getPassword();
        if (!new BCryptPasswordEncoder().matches(oldPassword, dbPassword)) {
            return "You enter wrong old password";
        }
        if (wrongPassword(newPassword, confirmPassword)) {
            return "Something wrong with new password. Please try again.";
        }
        if (oldPassword.equals(newPassword)) {
            return "Old password and new password is same.";
        }

        userRepository.changePassword(user.getId(),new BCryptPasswordEncoder().encode(newPassword));
        return "Success. Password changed.";
    }


    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    public boolean wrongPassword(String password1, String password2) {
        if (password1 == null || password2 == null || password1.isEmpty() || password2.isEmpty() || !password1.equals(password2)) {
            return true;
        }
        return false;
    }

    public boolean wrongMail(User user) {
        User userByEmail = userRepository.findByEmail(user.getEmail());
        return userByEmail != null;
    }


}


