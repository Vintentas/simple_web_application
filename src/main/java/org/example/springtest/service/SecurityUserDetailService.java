package org.example.springtest.service;

import org.example.springtest.dao.mybatis.UserMapper;
import org.example.springtest.dto.SecurityUserDetails;
import org.example.springtest.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class SecurityUserDetailService implements UserDetailsService {
    @Autowired
    UserMapper userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User  userByEmail = this.userRepository.findByEmail(username);
        if (userByEmail==null){
            throw new UsernameNotFoundException("Wrong email login");
        }
        return new SecurityUserDetails(userByEmail);
    }

}
