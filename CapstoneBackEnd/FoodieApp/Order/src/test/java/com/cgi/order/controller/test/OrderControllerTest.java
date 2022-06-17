package com.cgi.order.controller.test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cgi.order.controller.OrderController;
import com.cgi.order.model.CartItem;
import com.cgi.order.model.Order;
import com.cgi.order.service.OrderService;
import com.cgi.order.util.exception.OrderAlreadyExistsException;
import com.cgi.order.util.exception.OrderNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    private MockMvc mockMvc;
    private Order order;
    private CartItem item;
    private List<CartItem> itemList;
    private List<Order> orderList ;

    @MockBean
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();

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

    }

    @Test
    public void addOrderSuccess() throws Exception{
        when(orderService.addItemToCart(item, "User1")).thenReturn(item);
        mockMvc.perform(post("/foodie/orderCart/addToCart/User1").contentType(MediaType.APPLICATION_JSON).content(asJsonString(item)))
                .andExpect(status().isCreated()).
                andDo(MockMvcResultHandlers.print());

    }


//	@Test
//	public void addOrderFailure() throws Exception{
//		when(orderService.addItemToCart(any(),eq("User1"))).thenThrow(OrderAlreadyExistsException.class);
//		mockMvc.perform(post("/foodie/orderCart/addToCart/User1").contentType(MediaType.APPLICATION_JSON).content(asJsonString(item)))
//		.andExpect(status().isConflict()).
//		andDo(MockMvcResultHandlers.print());
//
//	}


    @Test
    public void getOrderSuccess() throws Exception{
        when(orderService.viewAllOrdersByRestaurantId("R01")).thenReturn(orderList);
        mockMvc.perform(get("/foodie/restaurantOwner/viewAllOrders/R01").contentType(MediaType.APPLICATION_JSON).content(asJsonString(item)))
                .andExpect(status().isOk()).
                andDo(MockMvcResultHandlers.print());

    }


    @Test
    public void getOrderFailure() throws Exception{
        when(orderService.viewAllOrdersByRestaurantId(any())).thenThrow(OrderNotFoundException.class);
        mockMvc.perform(get("/foodie/restaurantOwner/viewAllOrders/R02").contentType(MediaType.APPLICATION_JSON).content(asJsonString(item)))
                .andExpect(status().isNotFound()).
                andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void getOrderByUsernameSuccess() throws Exception{
        when(orderService.viewAllOrdersByUsername("User1")).thenReturn(orderList);
        mockMvc.perform(get("/foodie/restaurantUser/viewAllOrders/User1").contentType(MediaType.APPLICATION_JSON).content(asJsonString(item)))
                .andExpect(status().isOk()).
                andDo(MockMvcResultHandlers.print());

    }


    @Test
    public void getOrderByUsernameFailure() throws Exception{
        when(orderService.viewAllOrdersByUsername(any())).thenThrow(OrderNotFoundException.class);
        mockMvc.perform(get("/foodie/restaurantUser/viewAllOrders/User1").contentType(MediaType.APPLICATION_JSON).content(asJsonString(item)))
                .andExpect(status().isNotFound()).
                andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void getOrderByIdSuccess() throws Exception{
        when(orderService.viewOrderByOrderId("O01")).thenReturn(order);
        mockMvc.perform(get("/foodie/order/viewOrder/O01").contentType(MediaType.APPLICATION_JSON).content(asJsonString(item)))
                .andExpect(status().isOk()).
                andDo(MockMvcResultHandlers.print());

    }


    @Test
    public void getOrderByIdFailure() throws Exception{
        when(orderService.viewOrderByOrderId(any())).thenThrow(OrderNotFoundException.class);
        mockMvc.perform(get("/foodie/order/viewOrder/O01").contentType(MediaType.APPLICATION_JSON).content(asJsonString(item)))
                .andExpect(status().isNotFound()).
                andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void updateOrderByIdSuccess() throws Exception{
        when(orderService.updateOrderStatus("O01","Approve")).thenReturn(order);
        mockMvc.perform(put("/foodie/restaurantOwner/updateStatus/O01").contentType(MediaType.APPLICATION_JSON).content(asJsonString(item)))
                .andExpect(status().isOk()).
                andDo(MockMvcResultHandlers.print());

    }


    @Test
    public void updateOrderByIdFailure() throws Exception{
        when(orderService.updateOrderStatus(eq("o01"),any())).thenThrow(OrderNotFoundException.class);
        mockMvc.perform(put("/foodie/restaurantOwner/updateStatus/o01").contentType(MediaType.APPLICATION_JSON).content(asJsonString(item)))
                .andExpect(status().isNotFound()).
                andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void deleteOrderByIdSuccess() throws Exception{
        when(orderService.deleteOrder("O01")).thenReturn(true);
        mockMvc.perform(delete("/foodie/orderDelete/O01").contentType(MediaType.APPLICATION_JSON).content(asJsonString(item)))
                .andExpect(status().isOk()).
                andDo(MockMvcResultHandlers.print());

    }


    @Test
    public void deleteOrderByIdFailure() throws Exception{
        when(orderService.deleteOrder(eq("o01"))).thenThrow(OrderNotFoundException.class);
        mockMvc.perform(delete("/foodie/orderDelete/o01").contentType(MediaType.APPLICATION_JSON).content(asJsonString(item)))
                .andExpect(status().isNotFound()).
                andDo(MockMvcResultHandlers.print());

    }




    private static String asJsonString(final CartItem obj) {
        try {
            ObjectMapper objmapper = new ObjectMapper();
            objmapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objmapper.registerModule(new JavaTimeModule());
            return objmapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
