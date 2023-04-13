package org.photoalbum.photoalbum.security;

import org.photoalbum.photoalbum.exception.UserNotFoundException;
import org.photoalbum.photoalbum.model.User;
import org.photoalbum.photoalbum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class DatabaseUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userService.findByUsername(username);
            return new DatabaseUserDetails(user);
        } catch (UserNotFoundException e) {
            throw new UsernameNotFoundException("Username Not Found");
        }
    }
}
