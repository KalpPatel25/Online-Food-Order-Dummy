package com.cgi.order.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document
public class Order {

    @Id
    private String orderId;
    private String orderStatus;
    private String restaurantId;
    private LocalDateTime orderTime;
    private LocalDateTime deliveryTime;
    private List<CartItem> itemList;
    private Double totalPrice;
    private Double tax;
    private Double amount;
    private String username;
    private String restaurantName;



    public Order() {
    }

    public Order(String orderId, String orderStatus, String restaurantId, List<CartItem> itemList, Double totalPrice, Double tax,Double amount, String username, String restaurantName) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.restaurantId = restaurantId;
        this.orderTime = LocalDateTime.now();
        this.itemList = itemList;
        this.deliveryTime = LocalDateTime.now().plusMinutes(40);
        this.totalPrice = totalPrice;
        this.tax = tax;
        this.amount=amount;
        this.username=username;
        this.restaurantName=restaurantName;
    }

    public Order(String orderId , String username,List<CartItem> itemList,String orderStatus, String restaurantId){
        this.orderId=orderId;
        this.username=username;
        this.itemList=itemList;
        this.orderStatus=orderStatus;
        this.restaurantId=restaurantId;
    }
    public Order(String orderId, String restaurantId, List<CartItem> itemList,  Double totalPrice, String username, String restaurantName){
        this.orderId=orderId;
        this.restaurantId=restaurantId;
        this.itemList=itemList;
        this.totalPrice=totalPrice;
        this.username=username;
        this.restaurantName=restaurantName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = LocalDateTime.now().plusMinutes(30);
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = LocalDateTime.now();
    }

    public List<CartItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<CartItem> itemList) {
        this.itemList = itemList;
    }
}
