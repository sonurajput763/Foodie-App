package com.stackrouute.UserAuthenticationService.services;

import com.stackrouute.UserAuthenticationService.exception.EmailAlreadyExists;
import com.stackrouute.UserAuthenticationService.exception.UserNotFoundException;
import com.stackrouute.UserAuthenticationService.model.User;
import com.stackrouute.UserAuthenticationService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService{
    UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) throws EmailAlreadyExists {
        if(userRepository.findById(user.getEmail()).isPresent())
        {
            throw new EmailAlreadyExists();
        }
        return userRepository.save(user);

    }

    @Override
    public User findByUserEmailAndPassword(String userEmail, String password) throws UserNotFoundException {
        User user=userRepository.findByEmailAndPassword(userEmail, password);
        if(user==null)
        {
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public String getAccountType(String email,String password) throws UserNotFoundException {
        User getUser=userRepository.findByEmailAndPassword(email,password);
        if(getUser==null)
        {
            throw new UserNotFoundException();
        }
        return getUser.getAccountType();
    }
}
