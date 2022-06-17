package com.cgi.restaurant.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Restaurant {



    @Id
    private String restaurantId;
    private List<Item> itemList;
    private Boolean status;
    private String username;
    public String restaurantType;
    private String restaurantDesc;
    private String restaurantName;
    private String restaurantAddress;
    private long phoneNumber;
//    private double rating;
    private String restaurantImageUrl;
    private boolean addToFav;

//    public List<Item> getItemList() {
//        return itemList;
//    }
//
//    public void setItemList(List<Item> itemList) {
//        this.itemList = itemList;
//    }

    public Restaurant() {
    }

    public Restaurant(String restaurantId,List<Item> itemList, String username, Boolean status, String restaurantType, String restaurantDesc, String restaurantName, String restaurantAddress, long phoneNumber, String restaurantImageUrl,boolean addToFav) {

//        this.restaurantId = restaurantName.concat("User").concat(String.valueOf(ownerUserId));
        this.restaurantId=restaurantId;
        this.itemList = itemList;
        this.status=status;
        this.username = username;
        this.restaurantType = restaurantType;
        this.restaurantDesc = restaurantDesc;
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.phoneNumber = phoneNumber;
        this.restaurantImageUrl = restaurantImageUrl;
        this.addToFav=addToFav;

    }

    public boolean isAddToFav() {
        return addToFav;
    }

    public void setAddToFav(boolean addToFav) {
        this.addToFav = addToFav;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRestaurantType() {
        return restaurantType;
    }

    public void setRestaurantType(String restaurantType) {
        this.restaurantType = restaurantType;
    }

    public String getRestaurantDesc() {
        return restaurantDesc;
    }

    public void setRestaurantDesc(String restaurantDesc) {
        this.restaurantDesc = restaurantDesc;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRestaurantImageUrl() {
        return restaurantImageUrl;
    }

    public void setRestaurantImageUrl(String restaurantImageUrl) {
        this.restaurantImageUrl = restaurantImageUrl;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantId='" + restaurantId + '\'' +
                ", itemList=" + itemList +
                ", status='" + status + '\'' +
                ", username='" + username + '\'' +
                ", restaurantType='" + restaurantType + '\'' +
                ", restaurantDesc='" + restaurantDesc + '\'' +
                ", restaurantName='" + restaurantName + '\'' +
                ", restaurantAddress='" + restaurantAddress + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", restaurantImageUrl='" + restaurantImageUrl + '\'' +
                '}';
    }
}
