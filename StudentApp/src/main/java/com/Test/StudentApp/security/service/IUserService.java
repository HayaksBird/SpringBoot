package com.Test.StudentApp.security.service;

import com.Test.StudentApp.entity.User;

/**
 * The class implementing this interface is responsible for providing
 * access to the user data un the database (including their roles).
 */
public interface IUserService {
    /**
     * Add a user to the database.
     */
    String addUser(User user);


    /**
     * Get user by their username.
     */
    User getUser(String username);
}
