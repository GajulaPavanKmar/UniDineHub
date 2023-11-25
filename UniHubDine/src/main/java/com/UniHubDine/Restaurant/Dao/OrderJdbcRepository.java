package com.UniHubDine.Restaurant.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.UniHubDine.Restaurant.Model.Order;
import com.UniHubDine.Restaurant.Model.OrderDetails;

@Repository
public class OrderJdbcRepository {

	private final JdbcTemplate jdbcTemplate;

	public OrderJdbcRepository(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// RowMapper for Order entity
	private final RowMapper<Order> orderRowMapper = (resultSet, rowNum) -> {
		Order order = new Order();
		order.setOrderId(resultSet.getInt("order_id"));
		order.setUserId(resultSet.getString("user_id"));
		order.setOrderTimestamp(resultSet.getTimestamp("order_timestamp").toLocalDateTime());
		order.setStatus(resultSet.getString("status"));
		order.setPickupTimestamp(resultSet.getTimestamp("pickup_timestamp").toLocalDateTime());
		return order;
	};

	// RowMapper for OrderDetail entity
	private final RowMapper<OrderDetails> orderDetailRowMapper = (resultSet, rowNum) -> {
		OrderDetails orderDetail = new OrderDetails();
		orderDetail.setOrderDetailId(resultSet.getInt("order_detail_id"));
		orderDetail.setOrderId(resultSet.getInt("order_id"));
		orderDetail.setItemId(resultSet.getInt("item_id"));
		orderDetail.setQuantity(resultSet.getInt("quantity"));
		orderDetail.setPrice(resultSet.getDouble("price"));
		return orderDetail;
	};

	public int createOrder(Order order) {
		String sql = "INSERT INTO orders (user_id, order_timestamp, status, pickup_timestamp) VALUES (?, ?, ?, ?)";

		// Define KeyHolder to retrieve the generated order_id
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, new String[] { "order_id" });
				ps.setString(1, order.getUserId());
				ps.setTimestamp(2, Timestamp.valueOf(order.getOrderTimestamp()));
				ps.setString(3, order.getStatus());
				ps.setTimestamp(4, Timestamp.valueOf(order.getPickupTimestamp()));
				return ps;
			}
		}, keyHolder);

		// Get the generated order_id
		int generatedOrderId = keyHolder.getKey().intValue();

		return generatedOrderId;
	}

	// Insert a new order detail into the database
	public void createOrderDetails(List<OrderDetails> orderDetails) {
		String sql = "INSERT INTO order_details (order_id, item_id, quantity, price,user_id) VALUES (?, ?, ?, ?,?)";

		List<Object[]> batchArgs = new ArrayList<>();
		for (OrderDetails orderDetail : orderDetails) {
			Object[] values = new Object[] { orderDetail.getOrderId(), orderDetail.getItemId(),
					orderDetail.getQuantity(), orderDetail.getPrice(), orderDetail.getUser_id() };
			batchArgs.add(values);
		}

		jdbcTemplate.batchUpdate(sql, batchArgs);
	}

}
