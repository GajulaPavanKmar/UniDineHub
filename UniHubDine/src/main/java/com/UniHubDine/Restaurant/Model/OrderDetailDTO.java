package com.UniHubDine.Restaurant.Model;

import java.sql.Timestamp;

public class OrderDetailDTO {
    private String firstname;
    private String restaurantName;
    private Timestamp orderTimestamp;
    private String status;
    private Integer quantity;
    private Double totalPrice;
    private String itemName;

    
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public Timestamp getOrderTimestamp() {
		return orderTimestamp;
	}
	public void setOrderTimestamp(Timestamp orderTimestamp) {
		this.orderTimestamp = orderTimestamp;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	@Override
	public String toString() {
		return "OrderDetailDTO [firstname=" + firstname + ", restaurantName=" + restaurantName + ", orderTimestamp="
				+ orderTimestamp + ", status=" + status + ", quantity=" + quantity + ", totalPrice=" + totalPrice
				+ ", itemName=" + itemName + "]";
	}
	
	
}
