package com.UniHubDine.Restaurant.Controller.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "menu_items") 
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private int itemId;

    @Column(name = "menu_id")
    private int menuId;

    @Column(name = "item_name")
    private String name;

    private String description;

    private double price;

    private int calories;

    @Column(name = "image_url")
    private String imageUrl;
    
    @Column(name = "restaurant_name")
     private String restaurantName;

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getRestuarantName() {
		return restaurantName;
	}

	public void setRestuarantName(String restuarantName) {
		this.restaurantName = restuarantName;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "MenuItem [itemId=" + itemId + ", menuId=" + menuId + ", name=" + name + ", description=" + description
				+ ", price=" + price + ", calories=" + calories + ", imageUrl=" + imageUrl + ", restuarantName="
				+ restaurantName + "]";
	}

	

}