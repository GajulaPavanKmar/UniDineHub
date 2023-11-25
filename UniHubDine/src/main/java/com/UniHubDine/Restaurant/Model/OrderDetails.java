package com.UniHubDine.Restaurant.Model;

public class OrderDetails {
	
	private int orderDetailId;
    private int orderId;
    private int itemId;
    private int quantity;
    private double price;
    private String user_id;
    
	public int getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(int orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "OrderDetails [orderDetailId=" + orderDetailId + ", orderId=" + orderId + ", itemId=" + itemId
				+ ", quantity=" + quantity + ", price=" + price + ", user_id=" + user_id + "]";
	}
	

}
