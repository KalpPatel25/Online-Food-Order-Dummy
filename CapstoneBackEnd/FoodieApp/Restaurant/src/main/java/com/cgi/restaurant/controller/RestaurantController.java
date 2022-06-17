package com.cgi.restaurant.controller;

import com.cgi.restaurant.model.Item;
import com.cgi.restaurant.model.Restaurant;
import com.cgi.restaurant.service.RestaurantService;
import com.cgi.restaurant.util.exception.ItemAlreadyExistsException;
import com.cgi.restaurant.util.exception.ItemNotFoundException;
import com.cgi.restaurant.util.exception.RestaurantAlreadyExistsException;
import com.cgi.restaurant.util.exception.RestaurantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class RestaurantController {

    private RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService){
        this.restaurantService=restaurantService;
    }

    @PostMapping("/foodie/addRestaurant")
    public ResponseEntity<?> addRestaurantForOwner(@RequestBody Restaurant restaurant){
        try{
            restaurant.setRestaurantId(restaurant.getRestaurantName().concat("User").concat(restaurant.getUsername()));
            restaurant.setStatus(false);
            Restaurant result = restaurantService.addRestaurant(restaurant);
            return new ResponseEntity<Restaurant>(result,HttpStatus.CREATED);
        }
        catch (RestaurantAlreadyExistsException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/foodie/restaurantOwner/add/{restaurantId}")
    public ResponseEntity<?> addItemForOwner(@PathVariable String restaurantId,@RequestBody Item item)
    {
        try{
            item.setItemId(item.getItemName().concat(item.getCategory()));
            item.setItemStatus("Enable");
            Item result = restaurantService.addItem(restaurantId,item);
            return new ResponseEntity<Item>(result,HttpStatus.CREATED);
        } catch (ItemAlreadyExistsException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        } catch (RestaurantNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/foodie/restaurant/getRestaurant/{restaurantId}")
    public ResponseEntity<?> getRestaurantById(@PathVariable String restaurantId)
    {
        try {
            Restaurant result = restaurantService.getRestaurantByRestaurantId(restaurantId);
            return new ResponseEntity<Restaurant>(result, HttpStatus.OK);
        } catch (RestaurantNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/foodie/viewRestaurantId/{username}")
    public ResponseEntity<?> getRestaurantId(@PathVariable String username)
    {
        try {
            Restaurant result = restaurantService.getRestaurantByUsername(username);
            return new ResponseEntity<Restaurant>(result, HttpStatus.OK);
        } catch (RestaurantNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/foodie/viewRestaurant/viewByType/{restaurantType}")
    public ResponseEntity<?> viewRestaurantByType(@PathVariable  String restaurantType){
        List<?> restaurantList = restaurantService.viewAllRestaurantsByType(restaurantType);
        return new ResponseEntity<List<?>>(restaurantList,HttpStatus.OK);
    }

    @GetMapping("/foodie/viewRestaurant/viewByItem/{itemName}")
    public ResponseEntity<?> viewRestaurantByItemName(@PathVariable String itemName){
        List<?> restaurantList = restaurantService.viewAllRestaurantByItemName(itemName);
        return new ResponseEntity<List<?>>(restaurantList,HttpStatus.OK);
    }

    @GetMapping("/foodie/viewRestaurant")
    public ResponseEntity<?> viewAllRestaurants()
    {

            List<?> restaurantList = restaurantService.viewAllRestaurantsUser();
            return new ResponseEntity<List<?>>(restaurantList,HttpStatus.OK);

    }

    @GetMapping("/foodie/restaurantAdmin/viewRes")
    public ResponseEntity<?> viewAllRestaurantsAdmin()
    {
        List<?> restaurantList = restaurantService.viewAllRestaurantsAdmin();
        return new ResponseEntity<List<?>>(restaurantList,HttpStatus.OK);
    }

    @GetMapping("/foodie/restaurantOwner/viewRes/{restaurantId}")
    public ResponseEntity<?> viewAllItemsRestaurantOwner(@PathVariable  String restaurantId)
    {
        List<?> itemList = restaurantService.viewAllItemsByRestaurantIdOwner(restaurantId);
        return new ResponseEntity<List<?>>(itemList,HttpStatus.OK);
    }

    @GetMapping("/foodie/restaurantUser/viewRes/{restaurantId}")
    public ResponseEntity<?> viewAllItemsRestaurantUser(@PathVariable  String restaurantId)
    {
        List<?> itemList = restaurantService.viewAllItemsByRestaurantIdUser(restaurantId);
        return new ResponseEntity<List<?>>(itemList,HttpStatus.OK);
    }

    @GetMapping("/foodie/restaurantOwner/viewCategory/{restaurantId}/{category}")
    public ResponseEntity<?> viewAllItemsByCategoryOwner(@PathVariable String restaurantId, @PathVariable String category)
    {
        List<?> itemList = restaurantService.viewAllItemsByCategoryOwner(restaurantId,category);
        return new ResponseEntity<List<?>>(itemList,HttpStatus.OK);
    }

    @GetMapping("/foodie/restaurantUser/viewCategory/{restaurantId}/{category}")
    public ResponseEntity<?> viewAllItemsByCategoryUser(@PathVariable String restaurantId, @PathVariable String category)
    {
        List<?> itemList = restaurantService.viewAllItemsByCategoryUser(restaurantId,category);
        return new ResponseEntity<List<?>>(itemList,HttpStatus.OK);
    }

    @GetMapping("/foodie/restaurantUser/viewItem/{restaurantId}/{itemId}")
    public ResponseEntity<?> viewAnItemByItemId(@PathVariable String restaurantId,@PathVariable String itemId)
    {
        try{
            Item result = restaurantService.viewItemByItemId(restaurantId,itemId);
            return new ResponseEntity<Item>(result,HttpStatus.OK);
        } catch (RestaurantNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        } catch (ItemNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/foodie/restaurantOwner/updateItem/{restaurantId}/{itemId}")
    public ResponseEntity<?> updateItemRestaurantOwner(@PathVariable String restaurantId,  @RequestBody Item item)
    {
        try{
            Item result = restaurantService.updateMenu(restaurantId,item);
            return new ResponseEntity<Item>(result,HttpStatus.OK);
        } catch (RestaurantNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        } catch (ItemNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/foodie/restaurantOwner/updateRes/{restaurantId}")
    public ResponseEntity<?> updateRestaurantInfoRestaurantOwner(@RequestBody Restaurant restaurant){
        try{
            Restaurant  result = restaurantService.updateRestaurant(restaurant);
            return new ResponseEntity<Restaurant>(result,HttpStatus.OK);
        } catch (RestaurantNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/foodie/restaurantOwner/updateItemStatus/{restaurantId}/{itemId}")
    public ResponseEntity<?> updateItemStatusRestaurantOwner(@PathVariable String restaurantId, @PathVariable String itemId, @RequestBody String status)
    {
        try{
            Boolean result = restaurantService.itemStatus(restaurantId,itemId,status);
            if(result)
                return new ResponseEntity<String>("status updated to "+status,HttpStatus.OK);
            else
                return new ResponseEntity<String>("returned false",HttpStatus.NOT_FOUND);
        } catch (RestaurantNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        } catch (ItemNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/foodie/restaurantAdmin/updateResStatus/{restaurantId}")
    public ResponseEntity<?> updateRestaurantStatusRestaurantOwner(@PathVariable String restaurantId, @RequestBody Boolean status)
    {
        try {
            Boolean result = restaurantService.restaurantStatus(restaurantId, status);
            if(result)
                return new ResponseEntity<String>("status updated to "+status,HttpStatus.OK);
            else
                return new ResponseEntity<String>("returned false",HttpStatus.NOT_FOUND);
        } catch (RestaurantNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.OK);
        }
    }

    @DeleteMapping("/foodie/restaurantOwner/deleteRes/{restaurantId}")
    public ResponseEntity<?> deleteRestaurantRestaurantOwner(@PathVariable String restaurantId){
        try{
            Boolean result = restaurantService.deleteRestaurant(restaurantId);
            if(result)
                return new ResponseEntity<String>("Restaurant Deleted", HttpStatus.OK);
            else
                return new ResponseEntity<String>("returned False",HttpStatus.NOT_FOUND);
        } catch (RestaurantNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/foodie/restaurantAdmin/deleteAll")
    public ResponseEntity<?> deleteAllRestaurantsAdmin(){
        try{
            Boolean result = restaurantService.deleteAllRestaurant();
            if(result)
                return new ResponseEntity<String>("All restaurants deleted",HttpStatus.OK);
            else
                return new ResponseEntity<String>("returned false",HttpStatus.NOT_FOUND);
        } catch (RestaurantNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/foodie/restaurantOwner/deleteItem/{restaurantId}/{itemId}")
    public ResponseEntity<?> deleteItemRestaurantOwner(@PathVariable String restaurantId, @PathVariable String itemId)
    {
        try{
            Boolean result = restaurantService.deleteItem(restaurantId,itemId);
            if(result)
                return new ResponseEntity<String>("Item Deleted",HttpStatus.OK);
            else
                return new ResponseEntity<String>("returned false",HttpStatus.NOT_FOUND);
        } catch (RestaurantNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        } catch (ItemNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/foodie/restaurantOwner/deleteAllItems/{restaurantId}")
    public ResponseEntity<?> deleteAllItemsByRestaurantId(@PathVariable String restaurantId)
    {
        Boolean result = restaurantService.deleteAllItemByRestaurantId(restaurantId);
        if(result)
            return new ResponseEntity<String>("Menu Cleared",HttpStatus.OK);
        else
            return new ResponseEntity<String>("Error in clearing Menu",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/foodie/restaurantOwner/deleteAllItemsCategory/{restaurantId}/{category}")
    public ResponseEntity<?> deleteAllItemsByCategory(@PathVariable String restaurantId, @PathVariable String category)
    {
        Boolean result = restaurantService.deleteAllItemByCategory(restaurantId,category);
        if(result)
            return new ResponseEntity<String>("All items deleted",HttpStatus.OK);
        else
            return new ResponseEntity<String>("Error deleting items by category",HttpStatus.NOT_FOUND);
    }



}
