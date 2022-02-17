package com.stackrouute.UserAuthenticationService.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.stackrouute.UserAuthenticationService.exception.EmailAlreadyExists;
import com.stackrouute.UserAuthenticationService.exception.UserNotFoundException;
import com.stackrouute.UserAuthenticationService.model.User;
import com.stackrouute.UserAuthenticationService.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Test
    void testSaveUser() throws EmailAlreadyExists {
        User user = new User();
        user.setAccountType("3");
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        Optional<User> ofResult = Optional.of(user);
        when(this.userRepository.findById((String) any())).thenReturn(ofResult);

        User user1 = new User();
        user1.setAccountType("3");
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        assertThrows(EmailAlreadyExists.class, () -> this.userServiceImpl.saveUser(user1));
        verify(this.userRepository).findById((String) any());
    }

    @Test
    void testSaveUser2() throws EmailAlreadyExists {
        User user = new User();
        user.setAccountType("3");
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        when(this.userRepository.save((User) any())).thenReturn(user);
        when(this.userRepository.findById((String) any())).thenReturn(Optional.empty());

        User user1 = new User();
        user1.setAccountType("3");
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        assertSame(user, this.userServiceImpl.saveUser(user1));
        verify(this.userRepository).findById((String) any());
        verify(this.userRepository).save((User) any());
        assertTrue(this.userServiceImpl.getAllUsers().isEmpty());
    }

    @Test
    void testFindByUserEmailAndPassword() throws UserNotFoundException {
        User user = new User();
        user.setAccountType("3");
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        when(this.userRepository.findByEmailAndPassword((String) any(), (String) any())).thenReturn(user);
        assertSame(user, this.userServiceImpl.findByUserEmailAndPassword("jane.doe@example.org", "iloveyou"));
        verify(this.userRepository).findByEmailAndPassword((String) any(), (String) any());
        assertTrue(this.userServiceImpl.getAllUsers().isEmpty());
    }

    @Test
    void testGetAllUsers() {
        ArrayList<User> userList = new ArrayList<>();
        when(this.userRepository.findAll()).thenReturn(userList);
        List<User> actualAllUsers = this.userServiceImpl.getAllUsers();
        assertSame(userList, actualAllUsers);
        assertTrue(actualAllUsers.isEmpty());
        verify(this.userRepository).findAll();
    }
}

