package com.cgi.favourites.model;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class Favourite {
	@Id
	String userId;
	List<Restaurant> favList;

	public Favourite() {
		super();
	}
	public Favourite(String userId, List<Restaurant> favList) {
		super();
		this.userId = userId;
		this.favList = favList;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<Restaurant> getFavList() {
		return favList;
	}
	public void setFavList(List<Restaurant> favList) {
		this.favList = favList;
	}
	@Override
	public String toString() {
		return "favRestaurant [UserId=" + userId + ", favList=" + favList + "]";
	}


}