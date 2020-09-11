package org.example.springtest.service;

import org.example.springtest.dao.mybatis.UserMapper;
import org.example.springtest.entities.User;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    User user = new User();

    @Mock
    UserMapper userRepository;
    @InjectMocks
    UserServiceImpl userService;

    @Test
    public void delete() {

    }

    @Test
    public void saveWithNullUser() {
        assertThrows(IllegalArgumentException.class, () -> userService.save(null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveWithNullName() {
        user.setName("null");
        userService.save(user);
    }

    @Test
    public void saveWithNullEmail() {
        user.setEmail(null);
        assertThrows(IllegalArgumentException.class, () -> userService.save(user));
    }


    @Test
    public void save() {
        //when(userRepository.save(user)).thenReturn()
        assertThrows(IllegalArgumentException.class, () -> userService.save(null));
    }

}
