package com.cgi.restaurant.service;


import ch.qos.logback.core.pattern.util.RestrictedEscapeUtil;
import com.cgi.restaurant.model.Item;
import com.cgi.restaurant.model.Restaurant;
import com.cgi.restaurant.util.exception.RestaurantNotFoundException;
import com.cgi.restaurant.repository.RestaurantRepository;
import com.cgi.restaurant.util.exception.ItemAlreadyExistsException;
import com.cgi.restaurant.util.exception.ItemNotFoundException;
import com.cgi.restaurant.util.exception.RestaurantAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService{

    private RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository){
        this.restaurantRepository=restaurantRepository;
    }

    public Restaurant addRestaurant(Restaurant restaurant) throws RestaurantAlreadyExistsException {
//        restaurant.setRestaurantId(restaurant.getRestaurantName().concat("User").concat(String.valueOf(restaurant.getOwnerUserId())));
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurant.getRestaurantId());
        if(restaurantOptional.isEmpty()){
            Restaurant restaurantCheck = restaurantRepository.insert(restaurant);
            if(restaurantCheck!=null){
                return restaurant;
            }
            else{
                throw new RestaurantAlreadyExistsException("Null value returned while inserting to repository");
            }

        }

        throw new RestaurantAlreadyExistsException("Trying to Add Duplicate Restaurant");
    }

    @Override
    public Restaurant updateRestaurant(Restaurant restaurant) throws RestaurantNotFoundException {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurant.getRestaurantId());

        if(restaurantOptional.isPresent()){
            Restaurant result = restaurantOptional.get();
            List<Item> itemList = result.getItemList();
            restaurant.setItemList(itemList);
            Restaurant restaurantCheck = restaurantRepository.save(restaurant);
            return restaurant;
        }
            throw new RestaurantNotFoundException("Restaurant Not Found");
    }

    @Override
    public Boolean restaurantStatus(String restaurantId, Boolean status) throws RestaurantNotFoundException {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);

        if(restaurantOptional.isPresent()){
            restaurantOptional.get().setStatus(status);
            restaurantRepository.save(restaurantOptional.get());
            return true;
        }
        else
            throw new RestaurantNotFoundException("Restaurant Not Found");
    }

    @Override
    public Restaurant getRestaurantByUsername(String username) throws RestaurantNotFoundException
    {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findByUsername(username);

        if(restaurantOptional.isPresent()){
            return restaurantOptional.get();
        }
        throw new RestaurantNotFoundException("Restaurant Not found for given username");
    }

    @Override
    public Restaurant getRestaurantByRestaurantId(String restaurantId) throws RestaurantNotFoundException
    {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findByRestaurantId(restaurantId);

        if(restaurantOptional.isPresent()){
            return restaurantOptional.get();
        }
        throw new RestaurantNotFoundException("Restaurant Not found for given Id");
    }

    @Override
    public Boolean deleteRestaurant(String restaurantId) throws RestaurantNotFoundException {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);

        if(restaurantOptional.isPresent()){
            restaurantRepository.deleteById(restaurantId);
            return true;
        }
        else
            throw new RestaurantNotFoundException("Restaurant Not Found");
    }

    @Override
    public Boolean deleteAllRestaurant() throws RestaurantNotFoundException {
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        if(restaurantList.isEmpty()||restaurantList==null)
            throw new RestaurantNotFoundException("No Restaurants found in the list");
        else{
            restaurantRepository.deleteAll();
            return true;
        }
    }

    @Override
    public List<?> viewAllRestaurantsAdmin() {
        List<Restaurant> restaurantList = restaurantRepository.findAll();

        if(restaurantList.isEmpty()||restaurantList==null){
            List<String> stringList = Collections.singletonList(("There are no restaurants to Display"));

            return stringList;

        }
        return restaurantList;
    }

    @Override
    public List<?> viewAllRestaurantsUser(){
        List<Restaurant> restaurantList = restaurantRepository.findAll().stream()
                .filter(item->item.getStatus()==true).collect(Collectors.toList());

        if(restaurantList.isEmpty()||restaurantList==null){
            List<String> stringList = Collections.singletonList(("There are no restaurants to Display"));

            return stringList;
        }

        return restaurantList;
    }


    @Override
    public Item addItem(String restaurantId, Item item) throws ItemAlreadyExistsException, RestaurantNotFoundException {

            Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);

            if (restaurantOptional.isEmpty()) {
                throw new RestaurantNotFoundException("Restaurant Not Found");
            }

            Restaurant restaurant = restaurantOptional.get();
            if(restaurant.getItemList()!=null) {
                Optional<Item> itemOptional = restaurant.getItemList().stream().filter(food -> food.getItemId().equalsIgnoreCase(item.getItemId())).findAny();

                if (itemOptional.isEmpty()) {
                    restaurant.getItemList().add(item);
                    Restaurant restaurantCheck = restaurantRepository.save(restaurant);
                    if (restaurantCheck != null)
                        return item;
                    else
                        throw new RestaurantNotFoundException("Returned null value while inserting");
                } else {
                    throw new ItemAlreadyExistsException("Trying to add Duplicate item");
                }
            }
        restaurant.setItemList(Collections.singletonList(item));
        Restaurant restaurantCheck = restaurantRepository.save(restaurant);
        if (restaurantCheck != null)
            return item;
        else
            throw new RestaurantNotFoundException("Returned null value while inserting");
        }




    @Override
    public Item updateMenu(String restaurantId, Item item) throws ItemNotFoundException, RestaurantNotFoundException {
        Optional<Restaurant> restaurantOptional =restaurantRepository.findById(restaurantId);

        if(restaurantOptional.isEmpty()){
            throw new RestaurantNotFoundException("Restaurant Not Found");
        }

        Restaurant restaurant = restaurantOptional.get();
        Optional<Item> itemOptional = restaurant.getItemList().stream().filter(food->food.getItemId().equalsIgnoreCase(item.getItemId())).findAny();

        if(itemOptional.isEmpty()){
            throw new ItemNotFoundException("Item Not Found");
        }
        else{
            restaurant.getItemList().remove(itemOptional.get());
            restaurant.getItemList().add(item);
            restaurantRepository.save(restaurant);
            return item;
        }

    }

    @Override
    public Boolean deleteItem(String restaurantId, String itemId) throws ItemNotFoundException, RestaurantNotFoundException {
        Optional<Restaurant> restaurantOptional =restaurantRepository.findById(restaurantId);

        if(restaurantOptional.isEmpty()){
            throw new RestaurantNotFoundException("Restaurant Not Found");
        }

        Restaurant restaurant = restaurantOptional.get();
        Optional<Item> itemOptional = restaurant.getItemList().stream().filter(food->food.getItemId().equalsIgnoreCase(itemId)).findAny();

        if(itemOptional.isEmpty()){
            throw new ItemNotFoundException("Item Not Found");
        }

        else{
            restaurant.getItemList().remove(itemOptional.get());
            restaurantRepository.save(restaurant);
            return true;
        }



    }

    @Override
    public Boolean itemStatus(String restaurantId, String menuId, String status) throws ItemNotFoundException, RestaurantNotFoundException {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);

        if(restaurantOptional.isEmpty()){
            throw new RestaurantNotFoundException("Restaurant Not Found");
        }

        Restaurant restaurant = restaurantOptional.get();
        Optional<Item> itemOptional = restaurant.getItemList().stream().filter(food->food.getItemId().equalsIgnoreCase(menuId)).findAny();

        if(itemOptional.isEmpty()){
            throw new ItemNotFoundException("Item Not Found");
        }

        itemOptional.get().setItemStatus(status);
        restaurant.getItemList().remove(itemOptional.get());
        restaurant.getItemList().add(itemOptional.get());
        restaurantRepository.save(restaurant);
        return true;


    }

    @Override
    public Boolean deleteAllItemByRestaurantId(String restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
        restaurant.setItemList(null);
        restaurantRepository.save(restaurant);
        return true;
    }

    @Override
    public List<?> viewAllItemsByRestaurantIdOwner(String restaurantId) {
        List<Item> itemList = restaurantRepository.findById(restaurantId).get().getItemList();
        if(itemList.isEmpty()||itemList==null){
            List<String> stringList = Collections.singletonList(("There are no items to Display"));
            return stringList;
        }
        return itemList;
    }

    @Override
    public List<?> viewAllItemsByRestaurantIdUser(String restaurantId){


            List<Item> itemList = restaurantRepository.findById(restaurantId).get().getItemList().stream()
                    .filter(food -> food.getItemStatus().equalsIgnoreCase("Enable")).collect(Collectors.toList());


            if (itemList.isEmpty() || itemList == null ) {
                List<String> stringList = Collections.singletonList(("The Menu is Empty"));

                return stringList;
            }

            return itemList;
        }


    @Override
    public List<?> viewAllItemsByCategoryUser(String restaurantId, String category){

        List<Item> itemList = restaurantRepository.findById(restaurantId).get().getItemList().stream()
                .filter(food->((food.getCategory().equalsIgnoreCase(category))&&(food.getItemStatus().equalsIgnoreCase("Enable"))))
                .collect(Collectors.toList());
        if(itemList.isEmpty()||itemList==null){
            List<String> stringList = Collections.singletonList(("The Menu is Empty"));

            return stringList;
        }

        return itemList;

    }

    @Override
    public List<?> viewAllItemsByCategoryOwner(String restaurantId, String category){

        List<Item> itemList = restaurantRepository.findById(restaurantId).get().getItemList().stream()
                .filter(food->food.getCategory().equalsIgnoreCase(category)).collect(Collectors.toList());

        if(itemList.isEmpty()||itemList==null){
            List<String> stringList = Collections.singletonList(("The Menu is Empty"));

            return stringList;
        }

        return itemList;

    }

    @Override
    public Boolean deleteAllItemByCategory(String restaurantId, String category) {
//        restaurantRepository.findById(restaurantId).get().getItemList().removeIf(food->food.getCategory().equalsIgnoreCase(category));
        List<Item> itemList = restaurantRepository.findById(restaurantId).get().getItemList()
                .stream().filter(food->food.getCategory().equalsIgnoreCase(category)).collect(Collectors.toList());
        if(itemList.isEmpty()||itemList==null){
            List<String> stringList = Collections.singletonList(("The Menu is Empty"));
            return false;
        }
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
        restaurant.getItemList().removeIf(food->food.getCategory().equalsIgnoreCase(category));
        restaurantRepository.save(restaurant);

        return true;

    }

    @Override
    public Item viewItemByItemId(String restaurantId, String itemId) throws RestaurantNotFoundException, ItemNotFoundException {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);

        if(restaurantOptional.isEmpty()){
            throw new RestaurantNotFoundException("Restaurant Not Found");
        }

        Restaurant restaurant = restaurantOptional.get();

        Optional<Item> itemOptional = restaurant.getItemList().stream()
                .filter(food->food.getItemId().equalsIgnoreCase(itemId)).findAny();

        if(itemOptional.isEmpty())
            throw new ItemNotFoundException("Item Not Found");

        Item item = itemOptional.get();
        return item;

    }

    @Override
    public List<?> viewAllRestaurantsByType(String restaurantType) {

        List<Restaurant> restaurantList = restaurantRepository.findAll().stream()
                .filter(food->((food.getRestaurantType().equalsIgnoreCase(restaurantType)) && (food.getStatus()==true))).collect(Collectors.toList());

        if(restaurantList.isEmpty()||restaurantList==null){
            List<String> stringList = Collections.singletonList("No restaurant to display");
            return stringList;
        }

        return restaurantList;

    }

    @Override
    public List<?> viewAllRestaurantByItemName(String itemName) {
        List<Restaurant> restaurantList = restaurantRepository.findAll().stream()
                .filter(res->res.getStatus()==true).collect(Collectors.toList());
        List<Restaurant> finalList = new ArrayList<Restaurant>();
        for(Restaurant restaurant: restaurantList){
            Optional<Item> item = restaurant.getItemList().stream().filter(food->food.getItemName().equalsIgnoreCase(itemName)).findAny();
            if(item.isPresent())
                finalList.add(restaurant);
        }

        if(finalList.isEmpty()){
            List<String> stringList = Collections.singletonList("None of the restaurant serves this item");
            return stringList;
        }
        return finalList;
    }




}
