package com.cgi.userservice.controller.test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cgi.userservice.controller.UserController;
import com.cgi.userservice.exception.UserAlreadyExistsException;
import com.cgi.userservice.exception.UserNotFoundException;
import com.cgi.userservice.model.User;
import com.cgi.userservice.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    private MockMvc mockMvc;

    private User user;
    List<User> userList;

    @MockBean
    UserServiceImpl userservice;

    @InjectMocks
    private UserController usercontroller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(usercontroller).build();
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
    }

    @Test
    public void registerNewUserSuccess() throws Exception{
        when(userservice.registerNewUser(any())).thenReturn(user);
        mockMvc.perform(post("/users/api/register").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void registerUserFailure() throws Exception {
        when(userservice.registerNewUser(any())).thenThrow(UserAlreadyExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/users/api/register").contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());

    }
    @Test
    public void updateUserSuccess() throws Exception {

        when(userservice.updateUser(user, "DimpleJ")).thenReturn(user);
        user.setCity("Vancover");
        mockMvc.perform(MockMvcRequestBuilders.put("/users/api/updateUser/DimpleJ").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(status().isOk()).
                andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void updateUserFailure() throws Exception {

        when(userservice.updateUser(any(), eq("Sam"))).thenThrow(UserNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.put("/users/api/updateUser/Sam").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(status().isNotFound()).
                andDo(MockMvcResultHandlers.print());
    }

//    @Test
//    public void deleteUserSuccess() throws Exception {
//
//    	when(userservice.deleteUserById("DimpleJ")).thenReturn(user);
//        mockMvc.perform(MockMvcRequestBuilders.delete("/user/api/deleteUser/DimpleJ").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }

//    @Test
//	public void getAllUsersSuccess() throws Exception {
//
//		when(userservice.getUserType("DimpleJ")).thenReturn(user);
//		mockMvc.perform(MockMvcRequestBuilders.get("/user/api/getAllusers")
//				.header("authorization","Bearer "+userservice.generateJwtToken("DimpleJ")))
//				.andExpect((MockMvcResultMatchers.status().isOk()));
//	}


    private static String asJsonString(final User obj) {
        try {
            ObjectMapper objmapper = new ObjectMapper();
            objmapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objmapper.registerModule(new JavaTimeModule());
            return objmapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
