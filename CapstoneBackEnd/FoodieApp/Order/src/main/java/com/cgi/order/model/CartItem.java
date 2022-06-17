package com.cgi.order.model;


public class CartItem {


    private String itemId;
    private String itemName;
    private double price;
    private int itemQuantity;
    private String restaurantId;
    private double quantityPrice;
    private String imageUrl;

    public CartItem() {
    }

    public CartItem(String itemId, String itemName, double price, int itemQuantity, String restaurantId,double quantityPrice, String imageUrl) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
        this.itemQuantity = itemQuantity;
        this.restaurantId = restaurantId;
        this.quantityPrice=quantityPrice;
        this.imageUrl = imageUrl;

    }



    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getQuantityPrice() {
        return quantityPrice;
    }

    public void setQuantityPrice(double quantityPrice) {
        this.quantityPrice = quantityPrice;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return "cartItem{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", price=" + price +
                ", itemQuantity=" + itemQuantity +
                ", restaurantId='" + restaurantId + '\'' +
                '}';
    }
}
