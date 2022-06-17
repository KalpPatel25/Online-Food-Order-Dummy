package com.cgi.favourites.service;

import java.util.List;

import com.cgi.favourites.exception.RestaurantAlreadyExistsException;
import com.cgi.favourites.exception.RestaurantNotFoundException;
import com.cgi.favourites.model.Restaurant;

public interface FavouriteService {

	public boolean addFav(Restaurant restaurant, String userId) throws RestaurantAlreadyExistsException;
	public boolean deleteUserById(String userId, String restaurantId) throws RestaurantNotFoundException;
	public List<Restaurant> getUserById(String userId) throws RestaurantNotFoundException;
}
