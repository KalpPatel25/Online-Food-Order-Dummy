package com.cgi.restaurant.controller.test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.cgi.restaurant.controller.RestaurantController;
import com.cgi.restaurant.model.Item;
import com.cgi.restaurant.model.Restaurant;
import com.cgi.restaurant.service.RestaurantService;
import com.cgi.restaurant.util.exception.RestaurantAlreadyExistsException;
import com.cgi.restaurant.util.exception.RestaurantNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RestaurantControllerTest {

    private MockMvc mockMvc;

    private Restaurant restaurant;
    private Item item;
    private List<Item> items;



    @MockBean
    private RestaurantService resService;

    @InjectMocks
    private RestaurantController resController;


    @BeforeEach
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(resController).build();

        item = new Item();

        item.setItemId("I01");
        item.setItemName("Garlic Bread");
        item.setPrice(7.99);
        item.setItemStatus("Enable");
        item.setCategory("starter");
        item.setImageUrl("https://www.shugarysweets.com/wp-content/uploads/2020/04/garlic-bread-4-720x540.jpg");

        items =  new ArrayList<>();
        items.add(item);


        restaurant = new Restaurant();
        restaurant.setRestaurantId("R01");
        restaurant.setRestaurantName("Pizza Pizza");
        restaurant.setRestaurantAddress("Toronto");
        restaurant.setPhoneNumber(1234567890);
        restaurant.setRestaurantDesc("Best place for pizza");
        restaurant.setRestaurantImageUrl("https://media.blogto.com/articles/201959-pizza-pizza.jpg?w=2048&cmd=resize_then_crop&height=1365&quality=70");
        restaurant.setRestaurantType("Bar");
        restaurant.setUsername("User1");
        restaurant.setStatus(true);
        restaurant.setAddToFav(false);
        restaurant.setItemList(items);


    }

    @Test
    public void addRestaurantSuccess() throws Exception{
        when(resService.addRestaurant(any())).thenReturn(restaurant);
        mockMvc.perform(post("/foodie/addRestaurant").contentType(MediaType.APPLICATION_JSON).content(asJsonString(restaurant)))
                .andExpect(status().isCreated()).
                andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void addRestaurantFailure() throws Exception{
        when(resService.addRestaurant(any())).thenThrow(RestaurantAlreadyExistsException.class);
        mockMvc.perform(post("/foodie/addRestaurant").contentType(MediaType.APPLICATION_JSON).content(asJsonString(restaurant)))
                .andExpect(MockMvcResultMatchers.status().isConflict()).
                andDo(MockMvcResultHandlers.print());

    }


    @Test
    public void getRestaurantByIdSuccess() throws Exception{
        when(resService.getRestaurantByRestaurantId(any())).thenReturn(restaurant);
        mockMvc.perform(MockMvcRequestBuilders.get("/foodie/restaurant/getRestaurant/R01").contentType(MediaType.APPLICATION_JSON).content(asJsonString(restaurant)))
                .andExpect(status().isOk()).
                andDo(MockMvcResultHandlers.print());

    }
    @Test
    public void getRestaurantByIdFailure() throws Exception{
        when(resService.getRestaurantByRestaurantId(any())).thenThrow(RestaurantNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/foodie/restaurant/getRestaurant/R02").contentType(MediaType.APPLICATION_JSON).content(asJsonString(restaurant)))
                .andExpect(status().isNotFound()).
                andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void getRestaurantByUsernameSuccess() throws Exception{
        when(resService.getRestaurantByUsername(any())).thenReturn(restaurant);
        mockMvc.perform(MockMvcRequestBuilders.get("/foodie/viewRestaurantId/User1").contentType(MediaType.APPLICATION_JSON).content(asJsonString(restaurant)))
                .andExpect(status().isOk()).
                andDo(MockMvcResultHandlers.print());

    }
    @Test
    public void getRestaurantByUsernameFailure() throws Exception{
        when(resService.getRestaurantByUsername(any())).thenThrow(RestaurantNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/foodie/viewRestaurantId/User2").contentType(MediaType.APPLICATION_JSON).content(asJsonString(restaurant)))
                .andExpect(status().isNotFound()).
                andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void getRestaurantByTypeSuccess() throws Exception{
        when(resService.viewAllRestaurantsByType("Bar")).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/foodie/viewRestaurant/viewByType/Bar").contentType(MediaType.APPLICATION_JSON).content(asJsonString(restaurant)))
                .andExpect(status().isOk()).
                andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void updateRestaurantSuccess() throws Exception{
        when(resService.updateRestaurant(restaurant)).thenReturn(restaurant);
        mockMvc.perform(MockMvcRequestBuilders.put("/foodie/restaurantOwner/updateRes/R01").contentType(MediaType.APPLICATION_JSON).content(asJsonString(restaurant)))
                .andExpect(status().isOk()).
                andDo(MockMvcResultHandlers.print());

    }
    @Test
    public void updateRestaurantFailure() throws Exception{
        when(resService.updateRestaurant(any())).thenThrow(RestaurantNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.put("/foodie/restaurantOwner/updateRes/R02").contentType(MediaType.APPLICATION_JSON).content(asJsonString(restaurant)))
                .andExpect(status().isNotFound()).
                andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteRestaurantSuccess() throws Exception{
        when(resService.deleteRestaurant(restaurant.getRestaurantId())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/foodie/restaurantOwner/deleteRes/R01").contentType(MediaType.APPLICATION_JSON).content(asJsonString(restaurant)))
                .andExpect(status().isOk()).
                andDo(MockMvcResultHandlers.print());

    }
    @Test
    public void deleteRestaurantFailure() throws Exception{
        when(resService.deleteRestaurant(any())).thenThrow(RestaurantNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.delete("/foodie/restaurantOwner/deleteRes/R02").contentType(MediaType.APPLICATION_JSON).content(asJsonString(restaurant)))
                .andExpect(status().isNotFound()).
                andDo(MockMvcResultHandlers.print());
    }

    private static String asJsonString(final Restaurant obj) {
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

