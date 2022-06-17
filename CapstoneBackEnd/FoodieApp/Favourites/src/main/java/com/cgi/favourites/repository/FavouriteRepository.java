package com.cgi.favourites.repository;

import com.cgi.favourites.model.Favourite;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FavouriteRepository extends MongoRepository<Favourite, String>{


	

}
