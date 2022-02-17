package com.stackrouute.UserAuthenticationService.services;

import com.stackrouute.UserAuthenticationService.exception.EmailAlreadyExists;
import com.stackrouute.UserAuthenticationService.exception.UserNotFoundException;
import com.stackrouute.UserAuthenticationService.model.User;

import java.util.List;

public interface UserService {
    public User saveUser(User user) throws EmailAlreadyExists;
    public User findByUserEmailAndPassword(String userEmail,String password) throws UserNotFoundException;
    List<User> getAllUsers();
    String getAccountType(String email,String password) throws UserNotFoundException;
}
