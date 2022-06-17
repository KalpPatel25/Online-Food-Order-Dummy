package com.cgi.favourites.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cgi.favourites.repository.FavouriteRepository;
import com.cgi.favourites.service.FavouriteServiceImpl;
import com.cgi.favourites.exception.RestaurantNotFoundException;
import com.cgi.favourites.exception.RestaurantAlreadyExistsException;
import com.cgi.favourites.model.Favourite;
import com.cgi.favourites.model.Item;
import com.cgi.favourites.model.Restaurant;


public class FavouriteServiceTest {


    private Item item;
    private List<Favourite> favouriteList;
    private Favourite favourite;
    private Restaurant restaurant;


    @Mock
    private FavouriteRepository resrepo;

    @InjectMocks
    private FavouriteServiceImpl resServiceImpl;
    private List<Item> items = new ArrayList<>();;
    private List<Restaurant>  resList = new ArrayList<>(); ;
    Optional<Favourite> favList;


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


        resList.add(restaurant);




        favourite = new Favourite();
        favourite.setUserId("U01");
        favourite.setFavList(resList);

        favList = Optional.of(favourite);

        favouriteList = new ArrayList<>();

        favouriteList.add(favourite);

    }

    @Test
    public void addFavRestaurantSuccess() throws RestaurantAlreadyExistsException{
        when(resrepo.insert((Favourite) any())).thenReturn(favourite);
        boolean status = resServiceImpl.addFav(restaurant, any());
        assertEquals(true, status);
    }

    @Test
    public void addFavRestaurantFailure() throws RestaurantAlreadyExistsException{
        when(resrepo.insert((Favourite) any())).thenReturn(null);
        boolean status = resServiceImpl.addFav(restaurant, any());
        assertEquals(false, status);
    }

    @Test
    public void getFavRestaurantSuccess() throws RestaurantNotFoundException{
        when(resrepo.findById(any())).thenReturn(favList);
        List<Restaurant> status = resServiceImpl.getUserById(any());
        assertEquals(resList, status);
    }

    @Test
    public void deleteFavRestaurantSuccess() throws RestaurantNotFoundException{
        when(resrepo.findById(any())).thenReturn(favList);
        boolean status = resServiceImpl.deleteUserById("U01", "R01");
        assertEquals(true, status);
    }

    @Test
    public void deleteFavRestaurantFailure() throws RestaurantNotFoundException{
        when(resrepo.findById("U02")).thenReturn(null);
        boolean status = resServiceImpl.deleteUserById(any(),"R02");
        assertEquals(false, status);
    }




}
