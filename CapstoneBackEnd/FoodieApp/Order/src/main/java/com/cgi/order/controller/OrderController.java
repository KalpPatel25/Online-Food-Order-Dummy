package com.cgi.order.controller;

import com.cgi.order.model.CartItem;
import com.cgi.order.model.Order;
import com.cgi.order.service.OrderService;
import com.cgi.order.util.exception.ItemNotFoundException;
import com.cgi.order.util.exception.OrderAlreadyExistsException;
import com.cgi.order.util.exception.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService=orderService;
    }

    @PostMapping("/foodie/orderCart/addToCart/{username}")
    public ResponseEntity<?> addToCart(@RequestBody CartItem item,@PathVariable String username){


        try {
            CartItem result = orderService.addItemToCart(item, username);
            return new ResponseEntity<CartItem>(result, HttpStatus.CREATED);
        } catch (ItemNotFoundException e) {
            return new ResponseEntity<String>("Order from different restaurant",HttpStatus.NOT_FOUND);
        }


    }

    @DeleteMapping("/foodie/orderCart/removeFromCart/{itemId}/{username}")
    public ResponseEntity<?> deleteFromCart(@PathVariable String itemId, @PathVariable String username){
        try{
            Boolean result = orderService.removeItemFromCart(itemId, username);
            if(result)
                return new ResponseEntity<String>("Item Removed From Cart",HttpStatus.OK);
            return new ResponseEntity<String>("Error Removing Item",HttpStatus.NOT_FOUND);
        } catch (ItemNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/foodie/orderCart/viewAllItems/{username}")
    public ResponseEntity<?> showItemsInCart(@PathVariable String username){
        List<?> itemList = orderService.showItemsInCart(username);
        return new ResponseEntity<List<?>>(itemList,HttpStatus.OK);
    }

    @PutMapping("/foodie/orderCart/updateQty/{itemId}/{itemQuantity}/{username}")
    public ResponseEntity<?> updateItemQty(@PathVariable String itemId,@PathVariable int itemQuantity, @PathVariable String username){
        try{
            CartItem result = orderService.updateItemQuantity(itemId,itemQuantity, username);
            return new ResponseEntity<CartItem>(result,HttpStatus.OK);
        } catch (ItemNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/foodie/order/placeOrder")
    public ResponseEntity<?> placeOrder(@RequestBody Order order){
        try{
            Order result = orderService.placeOrder(order, order.getUsername());
            return  new ResponseEntity<Order>(result,HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        } catch (OrderAlreadyExistsException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/foodie/order/viewOrder/{orderId}")
    public ResponseEntity<?> viewOrderByOrderId(@PathVariable String orderId){
        try{
            Order result = orderService.viewOrderByOrderId(orderId);
            return new ResponseEntity<Order>(result,HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/foodie/restaurantOwner/viewAllOrders/{restaurantId}")
    public ResponseEntity<?> viewAllOrdersByRestaurantId(@PathVariable String restaurantId){
        try{
            List<Order> orderList = orderService.viewAllOrdersByRestaurantId(restaurantId);
            return new ResponseEntity<List<Order>>(orderList,HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/foodie/restaurantUser/viewAllOrders/{username}")
    public ResponseEntity<?> viewAllOrdersByUsername(@PathVariable String username){
        try{
            List<Order> orderList = orderService.viewAllOrdersByUsername(username);
            return new ResponseEntity<List<Order>>(orderList,HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/foodie/Admin/viewAllOrders")
    public ResponseEntity<?> viewAllOrders(){

            List<Order> orderList = orderService.viewAllOrdersAdmin();
            return new ResponseEntity<List<Order>>(orderList,HttpStatus.OK);

    }

    @PutMapping("/foodie/restaurantOwner/updateStatus/{orderId}")
    public ResponseEntity<?> updateOrderStatus(@PathVariable String orderId, String orderStatus){
        try{
            Order result = orderService.updateOrderStatus(orderId,orderStatus);
            return new ResponseEntity<Order>(result,HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/foodie/orderDelete/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable String orderId){
        try{
            Boolean result = orderService.deleteOrder(orderId);
            if(result)
                return new ResponseEntity<String>("status updated",HttpStatus.OK);
            return new ResponseEntity<String>("Error updating status",HttpStatus.NOT_FOUND);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


}
