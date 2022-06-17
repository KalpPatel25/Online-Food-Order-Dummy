package com.cgi.order.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cgi.order.controller.OrderController;
import com.cgi.order.model.CartItem;
import com.cgi.order.model.Order;
import com.cgi.order.repository.OrderRepository;
import com.cgi.order.service.OrderService;
import com.cgi.order.service.OrderServiceImpl;
import com.cgi.order.util.exception.ItemNotFoundException;
import com.cgi.order.util.exception.OrderAlreadyExistsException;
import com.cgi.order.util.exception.OrderNotFoundException;

public class OrderServiceTest {

    private MockMvc mockMvc;
    private Order order;
    private CartItem item;
    private List<CartItem> itemList;

    @Mock
    private OrderRepository orderrepo;

    @InjectMocks
    private OrderServiceImpl orderserviceImpl;
    private List<Order> orderList = new ArrayList<>();
    Optional<Order> items;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.initMocks(this);


        item = new CartItem();

        item.setItemId("I01");
        item.setItemName("Pizza");
        item.setItemQuantity(2);
        item.setImageUrl("abc.jpg");
        item.setPrice(10.15);
        item.setRestaurantId("R01");
        item.setQuantityPrice(20.30);

        itemList =  new ArrayList<>();
        itemList.add(item);


        order = new Order();
        order.setOrderId("O01");
        order.setRestaurantId("R01");
        order.setAmount(30.23);
        order.setDeliveryTime(LocalDateTime.now());
        order.setUsername("User1");
        order.setOrderStatus("Approve");
        order.setTotalPrice(50.00);
        order.setItemList(itemList);

        orderList = new ArrayList<>();
        orderList.add(order);
        items = Optional.of(order);

    }

    @Test
    public void addOrderSuccess() throws ItemNotFoundException{
        when(orderrepo.save(any())).thenReturn(item);
        CartItem status = orderserviceImpl.addItemToCart(item, "User1");
        assertEquals(item, status);
    }

    @Test
    public void deleteOrderSuccess() throws OrderNotFoundException{
        when(orderrepo.findById(any())).thenReturn(items);
        Boolean status = orderserviceImpl.deleteOrder("O01");
        assertEquals(true, status);
    }
    @Test
    public void getOrderSuccess() throws OrderNotFoundException{
        when(orderrepo.findById("O01")).thenReturn(Optional.of(order));
        Order status = orderserviceImpl.viewOrderByOrderId("O01");
        assertEquals(order, status);
    }
    @Test
    public void updateOrderSuccess() throws OrderNotFoundException{
        when(orderrepo.findById("O01")).thenReturn(Optional.of(order));
        Order status = orderserviceImpl.updateOrderStatus("O01", "Approve");
        assertEquals(order, status);
    }






}
