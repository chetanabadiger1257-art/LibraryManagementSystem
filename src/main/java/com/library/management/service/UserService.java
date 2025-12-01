package com.library.management.service;

import com.library.management.dao.UserDAO;
import com.library.management.dao.impl.UserDAOImpl;
import com.library.management.model.User;

import java.time.LocalDate;
import java.util.List;

public class UserService {
    private UserDAO userDAO;
    
    public UserService() {
        this.userDAO = new UserDAOImpl();
    }
    
    public boolean registerUser(String name, String email, String phone) {
        // Check if user already exists
        if (userDAO.userExists(email)) {
            return false;
        }
        
        // Create new user
        User user = new User(name, email, phone, LocalDate.now());
        userDAO.addUser(user);
        return true;
    }
    
    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }
    
    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }
    
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
    
    public boolean updateUser(int userId, String name, String email, String phone) {
        User user = userDAO.getUserById(userId);
        if (user == null) {
            return false;
        }
        
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        userDAO.updateUser(user);
        return true;
    }
    
    public boolean deactivateUser(int userId) {
        User user = userDAO.getUserById(userId);
        if (user == null) {
            return false;
        }
        
        user.setMembershipStatus("INACTIVE");
        userDAO.updateUser(user);
        return true;
    }
}