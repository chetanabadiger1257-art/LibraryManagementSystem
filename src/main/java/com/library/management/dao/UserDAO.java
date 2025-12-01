package com.library.management.dao;

import com.library.management.model.User;
import java.util.List;

public interface UserDAO {
    void addUser(User user);
    User getUserById(int userId);
    User getUserByEmail(String email);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(int userId);
    boolean userExists(String email);
}