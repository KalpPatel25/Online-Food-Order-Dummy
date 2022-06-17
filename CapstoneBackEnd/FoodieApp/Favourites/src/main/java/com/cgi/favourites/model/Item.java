package com.cgi.favourites.model;

import org.springframework.data.annotation.Id;

public class Item {


	@Id
	private String itemId;
	private String itemName;
	private double price;
	private String imageUrl;
	private String category;
	private String itemStatus;

	public Item() {
	}
	public Item(String itemId,String itemName, double price, String imageUrl, String category) {
		this.itemId =itemId;
		this.itemName = itemName;
		this.price = price;
		this.imageUrl = imageUrl;
		this.category = category;
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
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}

	@Override
	public String toString() {
		return "Item{" +
				"itemId='" + itemId + '\'' +
				", itemName='" + itemName + '\'' +
				", price=" + price +
				", imageUrl='" + imageUrl + '\'' +
				", category='" + category + '\'' +
				'}';
	}
}
