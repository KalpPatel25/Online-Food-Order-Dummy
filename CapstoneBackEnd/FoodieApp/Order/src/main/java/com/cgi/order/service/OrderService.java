package com.cgi.order.service;

import com.cgi.order.model.CartItem;
import com.cgi.order.model.Order;
import com.cgi.order.util.exception.OrderNotFoundException;
import com.cgi.order.util.exception.ItemNotFoundException;
import com.cgi.order.util.exception.OrderAlreadyExistsException;

import java.util.List;

public interface OrderService {

    public CartItem addItemToCart(CartItem item, String username) throws ItemNotFoundException;

    public Boolean removeItemFromCart(String itemId, String username) throws ItemNotFoundException, OrderNotFoundException;

    public List<?> showItemsInCart(String username);

    public CartItem updateItemQuantity(String itemId, int quantity, String username) throws ItemNotFoundException;

    public Order placeOrder(Order order, String username) throws OrderNotFoundException, OrderAlreadyExistsException;

    public Order viewOrderByOrderId(String orderId) throws OrderNotFoundException;

    public List<Order> viewAllOrdersByRestaurantId(String restaurantId) throws OrderNotFoundException;

    public List<Order> viewAllOrdersAdmin();

    public List<Order> viewAllOrdersByUsername(String username) throws OrderNotFoundException;

    public Order updateOrderStatus(String orderId, String orderStatus) throws OrderNotFoundException;

    public Boolean deleteOrder(String orderId) throws OrderNotFoundException;

}
