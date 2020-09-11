package org.example.springtest.service;

import lombok.RequiredArgsConstructor;
import org.example.springtest.dao.mybatis.UserMapper;
import org.example.springtest.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    final UserMapper userRepository;

    @Override
    public String delete(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "SUCCESS, USER WITH ID: " + id + " DELETED";
        }
        return "WARNING, USER WITH ID: " + id + " NOT FOND.";
    }

    @Override
    public Iterable<User> find(String nameFilter, String lastNameFilter) {

        if (nameFilter != null && !nameFilter.isEmpty() && lastNameFilter != null && !lastNameFilter.isEmpty()) {
            return userRepository.findByNameAndLastName(nameFilter, lastNameFilter);
        }

        if (nameFilter != null && !nameFilter.isEmpty()) {
            return userRepository.findByName(nameFilter);
        }

        if (lastNameFilter != null && !lastNameFilter.isEmpty()) {
            return userRepository.findByLastName(lastNameFilter);
        }

        return userRepository.findAll();

    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }


    @Override
    public boolean save(User user) {
        if (user == null || user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is empty");
        }
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new IllegalArgumentException("Name is empty");
        }
        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            throw new IllegalArgumentException("Last name is empty");
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }


    @Override
    public boolean save(User user, String pass1) {
        user.setPassword(pass1);
        return save(user);
    }


    @Override
    public boolean updateUser(User user) {
        return userRepository.updateUser(user);
    }

    @Override
    public User findUserByMail(String mail) {
        return userRepository.findByEmail(mail);
    }

    @Override
    public User findUserById(int id) {
        return userRepository.findById(id);
    }


}
