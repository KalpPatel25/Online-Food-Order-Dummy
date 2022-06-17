package com.cgi.restaurant.service;

import com.cgi.restaurant.model.Item;
import com.cgi.restaurant.model.Restaurant;
import com.cgi.restaurant.util.exception.RestaurantNotFoundException;
import com.cgi.restaurant.util.exception.ItemAlreadyExistsException;
import com.cgi.restaurant.util.exception.ItemNotFoundException;
import com.cgi.restaurant.util.exception.RestaurantAlreadyExistsException;

import java.util.List;

public interface RestaurantService {

    public Restaurant addRestaurant(Restaurant restaurant) throws RestaurantAlreadyExistsException;

    public Restaurant updateRestaurant(Restaurant restaurant) throws RestaurantNotFoundException;

    public Boolean restaurantStatus(String restaurantId, Boolean Status) throws RestaurantNotFoundException;

    public Boolean deleteRestaurant(String restaurantId) throws RestaurantNotFoundException;

    public Boolean deleteAllRestaurant() throws RestaurantNotFoundException;

    public List<?> viewAllRestaurantsAdmin();

    public List<?> viewAllRestaurantsUser();

    public Restaurant getRestaurantByRestaurantId(String restaurantId) throws RestaurantNotFoundException;

    public Restaurant getRestaurantByUsername(String username) throws RestaurantNotFoundException;

    public Item addItem(String restaurantId, Item item) throws ItemAlreadyExistsException, RestaurantNotFoundException;

    public Item updateMenu(String restaurantId, Item item) throws ItemNotFoundException, RestaurantNotFoundException;

    public Boolean deleteItem(String restaurantId, String itemId) throws ItemNotFoundException, RestaurantNotFoundException;

    public Boolean itemStatus(String restaurantId, String itemId, String status) throws ItemNotFoundException, RestaurantNotFoundException;

    public Boolean deleteAllItemByRestaurantId(String restaurantId);

    public List<?> viewAllItemsByRestaurantIdOwner(String restaurantId);

    public List<?> viewAllItemsByRestaurantIdUser(String restaurantId);

    public List<?> viewAllItemsByCategoryUser(String restaurantId, String category);

    public List<?> viewAllItemsByCategoryOwner(String restaurantId, String category);

    public Boolean deleteAllItemByCategory(String restaurantId, String category);

    public Item viewItemByItemId(String restaurantId, String itemId) throws RestaurantNotFoundException, ItemNotFoundException;

    public List<?> viewAllRestaurantsByType(String restaurantType);

    public List<?> viewAllRestaurantByItemName(String itemName);
}
