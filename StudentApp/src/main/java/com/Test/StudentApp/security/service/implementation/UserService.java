package com.Test.StudentApp.security.service.implementation;

import com.Test.StudentApp.dao.UserRepository;
import com.Test.StudentApp.entity.User;
import com.Test.StudentApp.security.service.IUserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The implementation class of the user service.
 */
@Service
public class UserService implements IUserService {
    private final UserRepository userRepo;


    //CONSTRUCTORS
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }


    /**
     * Add a user to the database.
     */
    @Override
    public String addUser(User user) {
        userRepo.save(user);

        return String.format("User %s was added!", user.getUsername());
    }


    /**
     * Get user by their username.
     */
    @Override
    public User getUser(String username) {
        Optional<User> user = userRepo.findById(username);

        if (user.isPresent()) return user.get();

        throw new UsernameNotFoundException("User '" + username + "' not found");
    }
}
