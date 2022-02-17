package com.stackroute.FoodieService.repositorty;

import com.stackroute.FoodieService.domain.User;
import com.stackroute.FoodieService.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    User user;

    @BeforeEach
    public void setUp()
    {
        userRepository.deleteAll();
        user=new User("email1@gmail.com","1234","Ankur","9876543210","customer","D://photos//photo1.jpg");
    }

    @AfterEach
    public void tearDown()
    {
        userRepository.deleteAll();
        user=null;
    }

    @Test
    public void shouldReturnUserWhenUserIsSaved()
    {
        userRepository.save(user);
        User savedUser=userRepository.findById(user.getEmail()).get();
        assertNotNull(savedUser);
        assertEquals(savedUser.getEmail(),user.getEmail());
    }

    @Test
    public void shouldReturnAllUsers()
    {
        userRepository.save(user);
        List<User> savedUsers=userRepository.findAll();
        assertNotNull(savedUsers);
        assertEquals(1,savedUsers.size());
    }

    @Test
    public void shouldDeleteUserByEmail()
    {
        userRepository.save(user);
        User savedUser=userRepository.findById(user.getEmail()).get();
        assertNotNull(savedUser);
        userRepository.deleteById(user.getEmail());
        assertEquals(Optional.empty(),userRepository.findById(user.getEmail()));
    }
}