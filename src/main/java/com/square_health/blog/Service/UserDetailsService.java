package com.square_health.blog.Service;


import com.square_health.blog.DAO.UserDao;
import com.square_health.blog.Entity.CustomUserDetails;
import com.square_health.blog.Entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<UserEntity> optionalUsers = userDao.findByEmail(s);

        optionalUsers
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return optionalUsers
                .map(CustomUserDetails::new).get();
    }
}
