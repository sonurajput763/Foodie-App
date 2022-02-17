package com.stackrouute.UserAuthenticationService.controller;


import com.stackrouute.UserAuthenticationService.exception.EmailAlreadyExists;
import com.stackrouute.UserAuthenticationService.exception.UserNotFoundException;
import com.stackrouute.UserAuthenticationService.model.User;
import com.stackrouute.UserAuthenticationService.services.SecurityTokenGenerator;
import com.stackrouute.UserAuthenticationService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v3")
public class UserController {
    private UserService userService;
    private SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    private ResponseEntity responseEntity;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody User user)
    {
        try{
            responseEntity=new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
        }
        catch(EmailAlreadyExists e)
        {
            responseEntity=new ResponseEntity<>("Email already exist", HttpStatus.CONFLICT);
        }
        catch(Exception e)
        {
            responseEntity=new ResponseEntity<>("Try again later",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping("/user")
    public ResponseEntity returnAccountType(@RequestBody User user)
    {
        try{
            responseEntity=new ResponseEntity<>(userService.getAccountType(user.getEmail(),user.getPassword()), HttpStatus.OK);
        }
        catch(Exception e)
        {
            responseEntity=new ResponseEntity<>("Try again later",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody User user)
    {
        Map<String,String> map=null;
        try{
            User loggedInUser=userService.findByUserEmailAndPassword(user.getEmail(), user.getPassword());
            if(loggedInUser!=null)
            {
                map=securityTokenGenerator.generateToken(loggedInUser);
            }
            responseEntity=new ResponseEntity<>(map,HttpStatus.OK);

        }
        catch(UserNotFoundException e)
        {
            responseEntity=new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            responseEntity=new ResponseEntity<>("Try again later",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    @GetMapping("/users")
    public ResponseEntity getAllUsers()
    {
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }
}
