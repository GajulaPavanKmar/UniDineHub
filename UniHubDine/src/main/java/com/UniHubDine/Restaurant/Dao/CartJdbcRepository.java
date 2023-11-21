package com.UniHubDine.Restaurant.Dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.UniHubDine.Restaurant.Model.Cart;

@Repository
public class CartJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public CartJdbcRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // RowMapper for Cart entity
    private final RowMapper<Cart> cartRowMapper = (resultSet, rowNum) -> {
        Cart cart = new Cart();
        cart.setCartId(resultSet.getInt("cart_id"));
        cart.setUserId(resultSet.getString("user_id"));
        cart.setTimestamp(resultSet.getTimestamp("timestamp").toLocalDateTime());
        return cart;
    };
    
    public void addToCart(Integer cartId, String userId, Integer itemId, Integer quantity) {
        String sql = "INSERT INTO cart_items (cart_id, user_id, item_id, quantity) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, cartId, userId, itemId, quantity);
    }

    // Insert a new cart into the database
    public void createCart(Cart cart) {
        String sql = "INSERT INTO carts (user_id, timestamp) VALUES (?,?, ?)";
        jdbcTemplate.update(sql, cart.getCartId(), cart.getUserId(), cart.getTimestamp());
    }

    // Find a cart by user ID
    public Cart findCartByUserId(String userId) {
    	try {
        String sql = "SELECT * FROM carts WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, cartRowMapper, userId);
    	}
    	catch (EmptyResultDataAccessException ex) {
            return null; 
        }
    }

    // Update an existing cart in the database
    public void updateCart(Cart cart) {
        String sql = "UPDATE carts SET user_id = ?, timestamp = ? WHERE cart_id = ?";
        jdbcTemplate.update(sql, cart.getUserId(), cart.getTimestamp(), cart.getCartId());
    }

    // Delete a cart by cart ID
    public void deleteCart(Integer cartId) {
        String sql = "DELETE FROM carts WHERE cart_id = ?";
        jdbcTemplate.update(sql, cartId);
    }

    // Get all carts
    public List<Cart> getAllCarts() {
        String sql = "SELECT * FROM carts";
        return jdbcTemplate.query(sql, cartRowMapper);
    }
}
