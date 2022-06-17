package com.cgi.favourites.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.cgi.favourites.exception.RestaurantNotFoundException;
import com.cgi.favourites.model.Restaurant;
import com.cgi.favourites.repository.FavouriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cgi.favourites.exception.RestaurantAlreadyExistsException;
import com.cgi.favourites.model.Favourite;
@Service
public class FavouriteServiceImpl implements FavouriteService{
    @Autowired
    FavouriteRepository favrepo;

    @Override
    public boolean addFav(Restaurant restaurant, String userId) throws RestaurantAlreadyExistsException {

        Optional<Favourite> favOpt = favrepo.findById(userId);
        if(favOpt.isEmpty()){
            Favourite favourite = new Favourite(userId,(new ArrayList<Restaurant>(List.of(restaurant))));
            restaurant.setAddToFav(true);
            Favourite favourite1 = favrepo.insert(favourite);


            if(favourite1!=null)
                return true;
            else
                return false;
        }
        Favourite fav = favOpt.get();
        Optional<Restaurant> restaurantOpt = fav.getFavList().stream().filter(item->item.getRestaurantId().equals(restaurant.getRestaurantId())).findAny();
        if(restaurantOpt.isEmpty()){
            restaurant.setAddToFav(true);
            fav.getFavList().add(restaurant);
            return favrepo.save(fav) != null;
        }
        else {
            throw new RestaurantAlreadyExistsException("Restaurant Already Exists");
        }



    }
    @Override
    public boolean deleteUserById(String userId, String restaurantId) throws RestaurantNotFoundException {

        Optional<Favourite> result = favrepo.findById(userId);

        if(!result.isEmpty()) {
            Favourite fav = result.get();
            List<Restaurant> favList = fav.getFavList();

            boolean deleteElement = favList.removeIf(f -> f.getRestaurantId().equals(restaurantId));

            if(deleteElement) {
                fav.setFavList(favList);
                favrepo.save(fav);
            }

            return deleteElement;

        }

        return false;
    }
    @Override
    public List<Restaurant> getUserById(String userId) throws RestaurantNotFoundException {
        try {

            Optional<Favourite> favObj = favrepo.findById(userId);

            if(favObj.isPresent()) {
                Favourite favres = favObj.get();
                List<Restaurant> resList = favres.getFavList();
                return resList;
            }
            else {
                throw new RestaurantNotFoundException("User Id not found");
            }

        }
        catch(RestaurantNotFoundException e) {
            throw new RestaurantNotFoundException(e.getMessage());
        }
    }

}