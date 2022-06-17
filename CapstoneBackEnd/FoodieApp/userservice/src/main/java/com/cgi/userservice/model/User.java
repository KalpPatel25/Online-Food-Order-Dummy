package com.cgi.userservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class User {


	private String firstName;
	private String lastName;
	private String city;
	private String country;
	private String type;
	@Id
	private String username;
	private String password;
	private String email;
	private String mobile;

	public User(String username,String password){
		this.username=username;
		this.password=password;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User() {
	}

	public User(String firstName, String lastName, String city, String country, String type, String username, String password, String email, String mobile) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.country = country;
		this.type = type;
		this.username = username;
		this.password = password;
		this.email = email;
		this.mobile = mobile;
	}
}
