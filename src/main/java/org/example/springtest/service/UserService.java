package org.example.springtest.service;

import org.example.springtest.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public String delete(int id);
    public Iterable<User> find(String nameFilter, String lastNameFilter);
    public Iterable<User> findAll();
    public boolean save(User user);
    public boolean save(User user, String pass1);
    public boolean updateUser(User user);
    public User findUserByMail(String mail);
    public User findUserById(int id);
}
