package com.UniHubDine.Restaurant.Model;

import java.time.LocalDateTime;

public class Order {
	
	private int orderId;
    private String userId;
    private LocalDateTime orderTimestamp;
    private String status;
    private LocalDateTime pickupTimestamp;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public LocalDateTime getOrderTimestamp() {
		return orderTimestamp;
	}
	public void setOrderTimestamp(LocalDateTime orderTimestamp) {
		this.orderTimestamp = orderTimestamp;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getPickupTimestamp() {
		return pickupTimestamp;
	}
	public void setPickupTimestamp(LocalDateTime pickupTimestamp) {
		this.pickupTimestamp = pickupTimestamp;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", userId=" + userId + ", orderTimestamp=" + orderTimestamp + ", status="
				+ status + ", pickupTimestamp=" + pickupTimestamp + "]";
	}

}
