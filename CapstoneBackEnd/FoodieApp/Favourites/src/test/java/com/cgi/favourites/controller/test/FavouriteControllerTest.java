package com.cgi.favourites.controller.test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import com.cgi.favourites.controller.FavouriteController;
import com.cgi.favourites.exception.RestaurantAlreadyExistsException;
import com.cgi.favourites.exception.RestaurantNotFoundException;
import com.cgi.favourites.model.Favourite;
import com.cgi.favourites.model.Item;
import com.cgi.favourites.model.Restaurant;
import com.cgi.favourites.service.FavouriteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FavouriteControllerTest {


    private MockMvc mockMvc;

    private List<Restaurant> resList;
    private Item item;
    private Favourite favourite;
    private List<Item> items;
    private Restaurant restaurant;



    @MockBean
    private FavouriteService favService;

    @InjectMocks
    private FavouriteController favController;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(favController).build();

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

        resList = new ArrayList<>();
        favourite = new Favourite();
        favourite.setUserId("U01");
        resList.add(restaurant);
        favourite.setFavList(resList);



    }

    @Test
    public void addRestaurantSuccess() throws Exception{
        when(favService.addFav(any(), any())).thenReturn(true);
        mockMvc.perform(post("/fav/addfav/U01").contentType(MediaType.APPLICATION_JSON).content(asJsonString(restaurant)))
                .andExpect(status().isCreated()).
                andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void addRestaurantFailure() throws Exception{
        when(favService.addFav(restaurant, "U01")).thenThrow(RestaurantAlreadyExistsException.class);
        mockMvc.perform(post("/fav/addfav/U01").contentType(MediaType.APPLICATION_JSON).content(asJsonString(restaurant)))
                .andExpect(MockMvcResultMatchers.status().isConflict()).
                andDo(MockMvcResultHandlers.print());

    }


    @Test
    public void getRestaurantByIdSuccess() throws Exception{
        when(favService.getUserById(any())).thenReturn(resList);
        mockMvc.perform(MockMvcRequestBuilders.get("/fav/viewByUser/U01").contentType(MediaType.APPLICATION_JSON).content(asJsonString(restaurant)))
                .andExpect(status().isOk()).
                andDo(MockMvcResultHandlers.print());

    }
    @Test
    public void getRestaurantByIdFailure() throws Exception{
        when(favService.getUserById(any())).thenThrow(RestaurantNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/fav/viewByUser/U01").contentType(MediaType.APPLICATION_JSON).content(asJsonString(restaurant)))
                .andExpect(status().isNotFound()).
                andDo(MockMvcResultHandlers.print());

    }


    @Test
    public void deleteRestaurantSuccess() throws Exception{
        when(favService.deleteUserById("U01","R01")).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/fav/deleteByResId/U01/R01").contentType(MediaType.APPLICATION_JSON).content(asJsonString(restaurant)))
                .andExpect(status().isOk()).
                andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteRestaurantFailure() throws Exception{
        when(favService.deleteUserById(any(),any())).thenThrow(RestaurantNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.delete("/fav/deleteByResId/U02/R02").contentType(MediaType.APPLICATION_JSON).content(asJsonString(restaurant)))
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
