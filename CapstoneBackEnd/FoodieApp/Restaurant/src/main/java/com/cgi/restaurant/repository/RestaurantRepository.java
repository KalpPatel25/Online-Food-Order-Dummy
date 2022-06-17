package com.cgi.restaurant.repository;

import com.cgi.restaurant.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant,String> {

//    public Optional<Restaurant> findByRestaurantNameAndUserId(String restaurantName, int userId);
    public Optional<Restaurant> findByUsername(String username);
    public Optional<Restaurant> findByRestaurantId(String restaurantId);

}
