package com.Test.StudentApp.dao;

import com.Test.StudentApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DAO for the User.
 */
public interface UserRepository extends JpaRepository<User, String> {

}
