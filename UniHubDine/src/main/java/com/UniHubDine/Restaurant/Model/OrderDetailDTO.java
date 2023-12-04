package com.UniHubDine.Restaurant.Model;

import java.sql.Timestamp;

public class OrderDetailDTO {
	private int order_detail_id;
    private String firstname;
    private String restaurantName;
    private Timestamp orderTimestamp;
    private String status;
    private Integer quantity;
    private Double totalPrice;
    private String itemName;
    private int orderId;
    private String status_order;
    
	public int getOrder_detail_id() {
		return order_detail_id;
	}
	public void setOrder_detail_id(int order_detail_id) {
		this.order_detail_id = order_detail_id;
	}
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
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public String getStatus_order() {
		return status_order;
	}
	public void setStatus_order(String status_order) {
		this.status_order = status_order;
	}
	@Override
	public String toString() {
		return "OrderDetailDTO [order_detail_id=" + order_detail_id + ", firstname=" + firstname + ", restaurantName="
				+ restaurantName + ", orderTimestamp=" + orderTimestamp + ", status=" + status + ", quantity="
				+ quantity + ", totalPrice=" + totalPrice + ", itemName=" + itemName + ", orderId=" + orderId
				+ ", status_order=" + status_order + "]";
	}


	
}
