package com.cgi.favourites.controller;

import java.util.List;
import com.cgi.favourites.exception.RestaurantAlreadyExistsException;
import com.cgi.favourites.model.Restaurant;
import com.cgi.favourites.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cgi.favourites.exception.RestaurantNotFoundException;
@CrossOrigin
@RestController
@RequestMapping("/fav")
public class FavouriteController {

	@Autowired
	FavouriteService favservice;

	@PostMapping("/addfav/{userId}")
	public ResponseEntity<?> addRestaurant(@RequestBody Restaurant restaurant, @PathVariable String userId){

		try {
			boolean result = favservice.addFav(restaurant, userId);

			if(result) {
				return new ResponseEntity<Boolean>(result,HttpStatus.CREATED);
			}
			else {
				return new ResponseEntity<String>("Already Exists",HttpStatus.CONFLICT);
			}

		}
		catch (RestaurantAlreadyExistsException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
		}
	}

	@GetMapping("/viewByUser/{userId}")
	public ResponseEntity<?> getByUserId(@PathVariable String userId){

		try {
			List<Restaurant> resList = favservice.getUserById(userId);

			if(!resList.isEmpty()) {
				return new ResponseEntity<List>(resList,HttpStatus.OK);
			}
			else {

				return new ResponseEntity<String>("Id not found",HttpStatus.NOT_FOUND);
			}

		}
		catch (RestaurantNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("deleteByResId/{userId}/{restaurantId}")
	public ResponseEntity<?> deleteRestaurant(@PathVariable String userId , @PathVariable String restaurantId){

		try {

			boolean result = favservice.deleteUserById(userId, restaurantId);

			if(result) {
				return new ResponseEntity<Boolean>(result,HttpStatus.OK);
			}
			else {
				return new ResponseEntity<String>("Id not found",HttpStatus.NOT_FOUND);
			}

		}
		catch(RestaurantNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
}