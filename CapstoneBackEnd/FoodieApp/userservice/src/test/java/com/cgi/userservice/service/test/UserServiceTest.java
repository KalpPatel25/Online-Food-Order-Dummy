package com.cgi.userservice.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cgi.userservice.exception.UserAlreadyExistsException;
import com.cgi.userservice.exception.UserNotFoundException;
import com.cgi.userservice.model.User;
import com.cgi.userservice.repo.UsersRepository;
import com.cgi.userservice.service.UserServiceImpl;

public class UserServiceTest {

    @Mock
    private UsersRepository userrepo;

    @InjectMocks
    private UserServiceImpl userservice;

    private User user;
    List<User> userList;

    User options;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        user = new User();


        user.setUsername("DimpleJ");
        user.setFirstName("Dimple");
        user.setLastName("Jayswal");
        user.setEmail("dimpleJ@gmail.com");
        user.setPassword("dimple123");
        user.setCity("Toronto");
        user.setCountry("Canada");
        user.setMobile("123457");
        user.setType("User");

        userList = new ArrayList<User>();
        userList.add(user);
        options = user;
    }

    @Test
    public void registerNewUserSuccess() throws UserAlreadyExistsException{

        Mockito.when(userrepo.save(user)).thenReturn(user);
        assertEquals(user, userservice.registerNewUser(user));
        verify(userrepo, times(1)).save(any());
    }

    @Test
    public void registerNewUserFailure() throws UserAlreadyExistsException {
        Mockito.when(userrepo.findById(any())).thenReturn(Optional.of(user));
        assertThrows(UserAlreadyExistsException.class,
                () -> userservice.registerNewUser(user));

    }


    @Test
    @Rollback(true)
    public void testUpdateUserSuccess() throws UserNotFoundException {

        when(userrepo.getById("DimpleJ")).thenReturn(user);
        when(userrepo.save(any())).thenReturn(user);
        assertEquals(user,userservice.updateUser(user, user.getUsername()));

    }

    @Test
    @Rollback(true)
    public void testUpdateUserFailure() throws UserNotFoundException {

        when(userrepo.findById(any())).thenReturn(null);
        assertThrows(UserNotFoundException.class,
                () -> userservice.updateUser(user, "abc"));

    }

    @Test
    @Rollback(true)
    public void testDeleteUserSuccess() throws UserNotFoundException {

        when(userrepo.getById(any())).thenReturn(user);
        userservice.deleteUserById(user.getUsername());
        verify(userrepo,times(1)).deleteById(any());
    }

    @Test
    @Rollback(true)
    public void testDeleteUserFailure() throws UserNotFoundException {

        when(userrepo.findById(any())).thenReturn(null);
        assertThrows(UserNotFoundException.class,
                () -> userservice.deleteUserById(user.getUsername()));
        verify(userrepo,times(0)).deleteById(any());

    }

//		@Test
//		@Rollback(true)
//		public void testGetAllUsersSuccess() throws UserNotFoundException {
//
//			when(userrepo.findAll()).thenReturn(userList);
//
//			assertEquals(user, userservice.getAllUser());
//			verify(userrepo,times(1)).findAll();
//		}


}

