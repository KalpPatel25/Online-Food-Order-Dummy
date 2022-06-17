package com.cgi.restaurant.service.test;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.cgi.restaurant.model.Item;
import com.cgi.restaurant.model.Restaurant;
import com.cgi.restaurant.repository.RestaurantRepository;
import com.cgi.restaurant.service.RestaurantServiceImpl;
import com.cgi.restaurant.util.exception.RestaurantAlreadyExistsException;
import com.cgi.restaurant.util.exception.RestaurantNotFoundException;

public class RestaurantServiceTest {

    private Restaurant restaurant;
    private Item item;
    private List<Restaurant> favList;

    @Mock
    private RestaurantRepository resrepo;

    @InjectMocks
    private RestaurantServiceImpl resServiceImpl;
    private List<Item> items;
    Optional<Restaurant> resList;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.initMocks(this);


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

        resList = Optional.of(restaurant);
    }

    @Test
    public void addRestaurantSuccess() throws RestaurantAlreadyExistsException{
        when(resrepo.insert((Restaurant) any())).thenReturn(restaurant);
        Restaurant status = resServiceImpl.addRestaurant(restaurant);
        assertEquals(restaurant, status);
    }
    @Test
    public void addRestaurantFailure() throws RestaurantAlreadyExistsException{
        when(resrepo.insert(restaurant)).thenReturn(null);
        assertThrows(RestaurantAlreadyExistsException.class,
                ()->{
                    resServiceImpl.addRestaurant(restaurant);
                });
    }
    @Test
    public void getRestaurantSuccess() throws RestaurantNotFoundException{
        when(resrepo.findByRestaurantId(restaurant.getRestaurantId())).thenReturn(resList);
        Restaurant status = resServiceImpl.getRestaurantByRestaurantId("R01");
        assertEquals(restaurant, status);
    }
    @Test
    public void getRestaurantFailure() throws RestaurantNotFoundException{
        when(resrepo.findById("R02")).thenReturn(null);
        assertThrows(RestaurantNotFoundException.class,
                ()->{
                    resServiceImpl.getRestaurantByRestaurantId("R02");
                });
    }

    @Test
    public void getRestaurantUsernameSuccess() throws RestaurantNotFoundException{
        when(resrepo.findByUsername(restaurant.getUsername())).thenReturn(resList);
        Restaurant status = resServiceImpl.getRestaurantByUsername("User1");
        assertEquals(restaurant, status);
    }
    @Test
    public void getRestaurantUsernameFailure() throws RestaurantNotFoundException{
        when(resrepo.findByUsername("User2")).thenReturn(null);
        assertThrows(RestaurantNotFoundException.class,
                ()->{
                    resServiceImpl.getRestaurantByUsername(null);
                });
    }

    @Test
    public void updateRestaurantItemSuccess() throws RestaurantNotFoundException{
        when(resrepo.findById("R01")).thenReturn(resList);
        restaurant.setStatus(false);
        Restaurant status = resServiceImpl.updateRestaurant(restaurant);
        assertEquals(restaurant, status);
    }
    @Test
    public void updateRestaurantItemFailure() throws RestaurantNotFoundException{
        when(resrepo.findById("R02")).thenReturn(null);
        assertThrows(RestaurantNotFoundException.class,
                ()->{
                    resServiceImpl.updateRestaurant(restaurant);
                });
    }

    @Test
    public void deleteAllRestaurantSuccess() throws RestaurantNotFoundException {

        when(resrepo.findById("R01")).thenReturn(resList);
        when(resrepo.save(restaurant)).thenReturn(restaurant);
        boolean flag = resServiceImpl.deleteRestaurant("R01");
        assertEquals(true, flag);

    }




}

