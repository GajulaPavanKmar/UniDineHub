package com.UniHubDine.Restaurant.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

import com.UniHubDine.Restaurant.Model.Cart;
import com.UniHubDine.Restaurant.Model.Order;
import com.UniHubDine.Restaurant.Model.OrderDetailDTO;
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

	public void updateOrder(int orderDetailId) {
		String sql = "UPDATE order_details SET status_order = 'Ready for PickUp' WHERE order_detail_id = ?";
		jdbcTemplate.update(sql,orderDetailId);
	}

	public void updateOrderCancel(int orderDetailId) {
		String sql = "UPDATE order_details SET status_order = 'Cancelled' WHERE order_detail_id = ?";
		jdbcTemplate.update(sql,orderDetailId);
	}
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

	public boolean createOrderDetails(List<OrderDetails> orderDetails) {
		String sql = "INSERT INTO order_details (order_id, item_id, quantity, price, user_id, status_order) VALUES (?, ?, ?, ?, ?, 'Pending')";

		List<Object[]> batchArgs = new ArrayList<>();
		for (OrderDetails orderDetail : orderDetails) {
			Object[] values = new Object[] { orderDetail.getOrderId(), orderDetail.getItemId(),
					orderDetail.getQuantity(), orderDetail.getPrice(), orderDetail.getUser_id() };
			batchArgs.add(values);
		}

		int[] updateCounts = jdbcTemplate.batchUpdate(sql, batchArgs);

		for (int count : updateCounts) {
			if (count != 1) {
				return false;
			}
		}

		return true;
	}

	public List<OrderDetailDTO> findOrderDetailsByUserId(String userId) {
		String sql = "SELECT us.FIRSTNAME, men.restaurant_name, ord.order_timestamp, ord.status, orde.quantity, (orde.quantity * orde.price) as total_price, men.item_name, "
				+ "orde.status_order, orde.order_detail_id  , me.image_path, us.phone_number FROM unidinehub.orders ord "
				+ "JOIN unidinehub.order_details orde ON ord.order_id = orde.order_id "
				+ "JOIN unidinehub.menu_items men ON men.item_id = orde.item_id "
				+ " JOIN unidinehub.menu me ON me.menu_id = men.menu_id "
				+ "JOIN unidinehub.user us ON us.user_id = ord.user_id " + "WHERE men.restaurant_name  = ? and orde.status_order ='Pending'";

		return jdbcTemplate.query(sql, new Object[] { userId }, new OrderDetailRowMapper());
	}

	public List<OrderDetailDTO> findCustomerOrderDetailsByUserId(String userId) {
		String sql = "SELECT us.FIRSTNAME, men.restaurant_name, ord.order_timestamp, ord.status, orde.quantity, (orde.quantity * orde.price) as total_price, men.item_name, orde.status_order ,"
				+ "orde.status_order, orde.order_detail_id , me.image_path, us.phone_number FROM unidinehub.orders ord "
				+ "JOIN unidinehub.order_details orde ON ord.order_id = orde.order_id "
				+ "JOIN unidinehub.menu_items men ON men.item_id = orde.item_id "
				+" JOIN unidinehub.menu me ON me.menu_id = men.menu_id "
				+ "JOIN unidinehub.user us ON us.user_id = ord.user_id " + "WHERE ord.user_id  = ? order by	 ord.order_timestamp desc";

		return jdbcTemplate.query(sql, new Object[] { userId }, new OrderDetailRowMapper());
	}

	private static final class OrderDetailRowMapper implements RowMapper<OrderDetailDTO> {
		public OrderDetailDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			OrderDetailDTO orderDetail = new OrderDetailDTO();
			orderDetail.setFirstname(rs.getString("FIRSTNAME"));
			orderDetail.setRestaurantName(rs.getString("restaurant_name"));
			orderDetail.setOrderTimestamp(rs.getTimestamp("order_timestamp"));
			orderDetail.setStatus(rs.getString("status"));
			orderDetail.setQuantity(rs.getInt("quantity"));
			orderDetail.setTotalPrice(rs.getDouble("total_price"));
			orderDetail.setItemName(rs.getString("item_name"));
			orderDetail.setStatus_order(rs.getString("status_order"));
			orderDetail.setOrder_detail_id(rs.getInt("order_detail_id"));
			orderDetail.setImage_path(rs.getString("image_path"));
			orderDetail.setPhone_number(rs.getString("phone_number"));

			return orderDetail;
		}
	}

}
