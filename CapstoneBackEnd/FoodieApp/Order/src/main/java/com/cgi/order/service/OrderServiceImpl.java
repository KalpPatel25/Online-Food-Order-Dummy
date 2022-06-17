package com.cgi.order.service;

import com.cgi.order.model.CartItem;
import com.cgi.order.model.Order;
import com.cgi.order.repository.OrderRepository;
import com.cgi.order.util.exception.OrderNotFoundException;
import com.cgi.order.util.exception.ItemNotFoundException;
import com.cgi.order.util.exception.OrderAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{

    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository=orderRepository;
    }

    @Override
    public CartItem addItemToCart(CartItem item, String username) throws ItemNotFoundException {

        Optional<Order> orderOptional = orderRepository.findById("tempOrder"+username);

        if(orderOptional.isEmpty()) {
            Order order =new Order("tempOrder"+username,username,new ArrayList<CartItem>(List.of(item)), "inProgress", item.getRestaurantId());
            orderRepository.insert(order);
            return item;
        }


        Order order = orderOptional.get();
        if(order.getRestaurantId().equalsIgnoreCase(item.getRestaurantId())) {
            Optional<CartItem> cartItemOptional = order.getItemList().stream()
                    .filter(food -> food.getItemId().equalsIgnoreCase(item.getItemId())).findAny();

            if (cartItemOptional.isPresent()) {
                item.setItemQuantity(item.getItemQuantity() + cartItemOptional.get().getItemQuantity());
                order.getItemList().remove(cartItemOptional.get());
                order.getItemList().add(item);
                orderRepository.save(order);
                return item;
            }
            order.getItemList().add(item);
            orderRepository.save(order);
            return item;
        }

            throw new ItemNotFoundException("Order should be from same restaurant");


    }

    @Override
    public Boolean removeItemFromCart(String itemId, String username) throws ItemNotFoundException, OrderNotFoundException {

        Optional<Order> orderOptional = orderRepository.findById("tempOrder"+username);
        if(orderOptional.isEmpty()){

            throw new ItemNotFoundException("The cart you are trying to edit is empty");

        }

        Order order = orderOptional.get();

        Optional<CartItem> cartItemOptional = order.getItemList().stream()
                .filter(food->food.getItemId().equalsIgnoreCase(itemId)).findAny();

        if(cartItemOptional.isPresent()){
            order.getItemList().remove(cartItemOptional.get());

            if(order.getItemList()==null||order.getItemList().isEmpty())
            {
                deleteOrder("tempOrder"+username);
                return true;
            }
            orderRepository.save(order);
        }

        throw new ItemNotFoundException("Item you are trying to remove is not present");

    }

    @Override
    public List<?> showItemsInCart(String username) {

        Optional<Order> orderOptional =orderRepository.findById("tempOrder"+username);
        if(orderOptional.isEmpty()){
            List<String> stringList = Collections.singletonList("The Cart is Empty");
            return stringList;
        }

        List<CartItem> itemList =orderOptional.get().getItemList();

        if(itemList.isEmpty()||itemList==null){
            List<String> stringList = Collections.singletonList(("The Cart is Empty! Add items to cart"));
            return stringList;
        }
        Double price=0.0;
        for(CartItem items: itemList){
            price =( items.getPrice() * items.getItemQuantity() );
            items.setQuantityPrice(price);
        }

        Order order = orderOptional.get();
        orderRepository.save(order);
        return itemList;

    }

    @Override
    public CartItem updateItemQuantity(String itemId, int itemQuantity, String username) throws ItemNotFoundException {
        Optional<Order> orderOptional = orderRepository.findById("tempOrder"+username);
        if(orderOptional.isEmpty()){
            throw new ItemNotFoundException("There are no items to display");
        }

        Order order = orderOptional.get();

        Optional<CartItem> cartItemOptional = order.getItemList().stream()
                .filter(food->food.getItemId().equalsIgnoreCase(itemId)).findAny();

        if(cartItemOptional.isEmpty()){
            throw new ItemNotFoundException("The item is not present");
        }

        order.getItemList().remove(cartItemOptional.get());
        cartItemOptional.get().setItemQuantity(itemQuantity);
        order.getItemList().add(cartItemOptional.get());
        orderRepository.save(order);
        return cartItemOptional.get();
    }

    @Override
    public Order placeOrder(Order order, String username) throws OrderNotFoundException, OrderAlreadyExistsException {

        if(order.getOrderId().equalsIgnoreCase("temporder"+username)){
            order.setOrderId(order.getRestaurantId().concat(String.valueOf(LocalDateTime.now())));
            order.setOrderTime(LocalDateTime.now());
            order.setDeliveryTime(LocalDateTime.now().plusMinutes(40));
            order.setOrderStatus("Placed");
            order.setUsername(username);
            String[] split = order.getRestaurantId().split("User");
            String resName = split[0];
            order.setRestaurantName(resName);
            orderRepository.insert(order);
            orderRepository.deleteById("tempOrder"+username);
            return order;
        }

        Optional<Order> orderOptional = orderRepository.findById(order.getOrderId());
        if(orderOptional.isEmpty()){
            throw new OrderNotFoundException("Something wrong with orderId");
        }

        throw new OrderAlreadyExistsException("OrderId Already Exists");

    }

    @Override
    public Order viewOrderByOrderId(String orderId) throws OrderNotFoundException {

        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if(orderOptional.isEmpty()){
            throw new OrderNotFoundException("Order Not Found");
        }
        Order order = orderOptional.get();
        List<Double> priceList = order.getItemList().stream().map(amount->amount.getQuantityPrice()).collect(Collectors.toList());
        Double total=0.0;
        for(Double price: priceList){
            total += price;
        }
        double tax = total*0.13;
        order.setAmount(total);
        order.setTax(tax);
        order.setTotalPrice((total+tax));
        return orderOptional.get();

    }

    @Override
    public List<Order> viewAllOrdersByRestaurantId(String restaurantId) throws OrderNotFoundException {

        List<Order> orderList = orderRepository.findAll();

        if(orderList.isEmpty()||orderList==null){
            throw new OrderNotFoundException("No orders found");
        }

        List<Order> orders = orderList.stream().filter(food->food.getRestaurantId().equalsIgnoreCase(restaurantId)).collect(Collectors.toList());
        if(orders.isEmpty()||orders==null){
            throw new OrderNotFoundException("No orders to display");
        }
        List<Order> orderOptional = orderRepository.findByOrderIdStartsWith("tempOrder");
        if(orderOptional.isEmpty()||orderOptional==null){
            return orders;
        }
        orders.removeAll(orderOptional);
        return orders;

    }


    @Override
    public List<Order> viewAllOrdersByUsername(String username) throws OrderNotFoundException {

        List<Order> orderList = orderRepository.findAll();

        if(orderList.isEmpty()||orderList==null){
            throw new OrderNotFoundException("No orders found");
        }


        List<Order> orders = orderList.stream().filter(food->food.getUsername().equalsIgnoreCase(username)).collect(Collectors.toList());
        if(orders.isEmpty()||orders==null){
            throw new OrderNotFoundException("No orders to display");
        }
        List<Order> orderOptional = orderRepository.findByOrderIdStartsWith("tempOrder");
        if(orderOptional.isEmpty()||orderOptional==null){
            return orders;
        }
        orders.removeAll(orderOptional);
        return orders;

    }


    @Override
    public List<Order> viewAllOrdersAdmin() {
        List<Order> orderList = orderRepository.findAll();
        List<Order> orderOptional = orderRepository.findByOrderIdStartsWith("tempOrder");
        if(orderOptional.isEmpty()||orderOptional==null){
            return orderList;
        }
        orderList.removeAll(orderOptional);
        return orderList;
    }

    @Override
    public Order updateOrderStatus(String orderId, String orderStatus) throws OrderNotFoundException {
        Optional<Order> orderOptional = orderRepository.findById(orderId);

        if(orderOptional.isEmpty()){
            throw new OrderNotFoundException("Order Not Found");

        }

        Order order = orderOptional.get();
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
        return order;
    }

    @Override
    public Boolean deleteOrder(String orderId) throws OrderNotFoundException {

        Optional<Order> orderOptional = orderRepository.findById(orderId);

        if(orderOptional.isEmpty()){
            throw new OrderNotFoundException("Order Not Found");
        }

        Order order = orderOptional.get();
        orderRepository.delete(order);
        return true;

    }
}
