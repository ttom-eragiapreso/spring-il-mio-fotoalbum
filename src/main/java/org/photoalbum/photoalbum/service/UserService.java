package org.photoalbum.photoalbum.service;

import org.photoalbum.photoalbum.exception.UserNotFoundException;
import org.photoalbum.photoalbum.model.User;
import org.photoalbum.photoalbum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findByUsername(String username) throws UserNotFoundException{
      Optional<User> user = userRepository.findByUsername(username);
      if(user.isEmpty()) throw new UserNotFoundException("user with username: " + username + " not found");
      return user.get();
    }
}
