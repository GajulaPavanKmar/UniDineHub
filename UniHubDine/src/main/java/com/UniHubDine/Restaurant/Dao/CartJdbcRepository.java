package com.UniHubDine.Restaurant.Dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.UniHubDine.Restaurant.Model.Cart;
import com.UniHubDine.Restaurant.Model.CartItem;
import com.UniHubDine.Restaurant.Model.MenuItem;

@Repository
public class CartJdbcRepository {

	private final JdbcTemplate jdbcTemplate;

	public CartJdbcRepository(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private final RowMapper<Cart> cartRowMapper = (resultSet, rowNum) -> {
		Cart cart = new Cart();
		cart.setCartId(resultSet.getInt("cart_id"));
		cart.setUserId(resultSet.getString("user_id"));
		cart.setTimestamp(resultSet.getTimestamp("timestamp").toLocalDateTime());
		return cart;
	};

	private final RowMapper<MenuItem> menuItemMapper = (resultSet, rowNum) -> {
		MenuItem mitem = new MenuItem();
		mitem.setItemId(resultSet.getInt("item_id"));
		mitem.setMenuId(resultSet.getInt("menu_id"));
		mitem.setCalories(resultSet.getInt("calories"));
		mitem.setName(resultSet.getString("item_name"));
		mitem.setPrice(resultSet.getDouble("price"));
		mitem.setImageUrl(resultSet.getString("image_url"));
		return mitem;
	};

	private final RowMapper<CartItem> cartItemRowMapper = (resultSet, rowNum) -> {
		CartItem cartItem = new CartItem();
		cartItem.setCartItemId(resultSet.getInt("cart_item_id"));
		cartItem.setItemId(resultSet.getInt("item_id"));
		cartItem.setQuantity(resultSet.getInt("quantity"));
		cartItem.setNote(resultSet.getString("note"));

		MenuItem menuItem = new MenuItem();
		menuItem.setItemId(resultSet.getInt("item_id"));
		menuItem.setName(resultSet.getString("item_name"));
		menuItem.setPrice(resultSet.getDouble("price"));
		menuItem.setImageUrl(resultSet.getString("image_path"));
		menuItem.setRestuarantName(resultSet.getString("restaurant_name"));
		cartItem.setMenuItem(menuItem);

		return cartItem;
	};

	public void addToCart(Integer cartId, String userId, Integer itemId, Integer quantity) {
		String checkSql = "SELECT quantity FROM cart_items WHERE cart_id = ? AND user_id = ? AND item_id = ?";

	    try {
	        Integer existingQuantity = jdbcTemplate.queryForObject(checkSql, new Object[]{cartId, userId, itemId}, Integer.class);

	        if (existingQuantity != null) {
	            String updateSql = "UPDATE cart_items SET quantity = quantity + ? WHERE cart_id = ? AND user_id = ? AND item_id = ?";
	            jdbcTemplate.update(updateSql, quantity, cartId, userId, itemId);
	        }
	    } catch (EmptyResultDataAccessException e) {
	        String insertSql = "INSERT INTO cart_items (cart_id, user_id, item_id, quantity) VALUES (?, ?, ?, ?)";
	        jdbcTemplate.update(insertSql, cartId, userId, itemId, quantity);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public void createCart(Cart cart) {
		String sql = "INSERT INTO carts (cart_id, user_id, timestamp) VALUES (?,?, ?)";
		jdbcTemplate.update(sql, cart.getCartId(), cart.getUserId(), cart.getTimestamp());
	}

	// Find a cart by user ID
	public Cart findCartByUserId(String userId) {
		try {
			String sql = "SELECT * FROM carts WHERE user_id = ?";
			return jdbcTemplate.queryForObject(sql, cartRowMapper, userId);
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}

	// Update an existing cart in the database
	public void updateCart(Cart cart) {
		String sql = "UPDATE carts SET user_id = ?, timestamp = ? WHERE cart_id = ?";
		jdbcTemplate.update(sql, cart.getUserId(), cart.getTimestamp(), cart.getCartId());
	}

	// Delete a cart by cart ID
	public boolean deleteCart(Integer cartId) {
		String sql = "DELETE FROM cart_items WHERE cart_item_id = ?";
		int rowsEffected = jdbcTemplate.update(sql, cartId);
		return rowsEffected>1;
		
	}
	public boolean deleteFullCart(Integer cartId) {
		String sql = "DELETE FROM carts WHERE cart_id = ?";
		int rowsEffected = jdbcTemplate.update(sql, cartId);
		return rowsEffected>0;
		
	}
	// Get all carts
	public List<Cart> getAllCarts() {
		String sql = "SELECT * FROM carts";
		return jdbcTemplate.query(sql, cartRowMapper);
	}

	// View Cart Items
	public List<CartItem> viewCartItems(String userId) {
		String sql = "SELECT ci.*, mi.menu_id , mi.item_name , (ci.quantity*mi.price) as price,  m.image_path, mi.restaurant_name   " + "FROM cart_items ci "
				+ "JOIN menu_items mi ON ci.item_id = mi.item_id  JOIN menu m ON m.menu_id = mi.menu_id " + "WHERE ci.user_id = ?";
		return jdbcTemplate.query(sql, cartItemRowMapper, userId);
	}

	public List<MenuItem> getMIByMenuId(int menuId, String userId) {
		String sql = "SELECT * from menu_items"
				+ " WHERE menu_id=? and restaurant_name=? ";
		return jdbcTemplate.query(sql, menuItemMapper, menuId, userId);
	}

	public boolean updateItem(Integer itemId, String userId, Double calories, Double price) {
		String sql = "UPDATE menu_items SET calories =?, price = ? where item_id =? and restaurant_name=?  ";
		int rowsEffected =jdbcTemplate.update(sql ,calories,price,itemId,userId);
		return rowsEffected>0;
	}

	public boolean deleteItem(Integer itemId, String userId) {
		String sql = "delete from  menu_items where item_id =? and restaurant_name=?  ";
		int rowsEffected =jdbcTemplate.update(sql ,itemId,userId);
		return rowsEffected>0;
	}

	public boolean insertNewMenuItem(int menuId, String newItemName, Double newItemCalories, Double newItemPrice,
			String userId) {
		String sql = "INSERT INTO menu_items (menu_id , item_name , calories, price , restaurant_name) values "+
			"(?,?,?,?,?)";
		int rowsEffected =jdbcTemplate.update(sql ,menuId,newItemName, newItemCalories, newItemPrice,userId);
		return rowsEffected>0;
	}
}
