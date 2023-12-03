package com.UniHubDine.Restaurant.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.UniHubDine.Restaurant.Dao.CartJdbcRepository;
import com.UniHubDine.Restaurant.Model.Cart;
import com.UniHubDine.Restaurant.Model.CartItem;

@Service
@Transactional
public class CartService {

	private final CartJdbcRepository cartJdbcRepository;

	@Autowired
	public CartService(CartJdbcRepository cartJdbcRepository) {
		this.cartJdbcRepository = cartJdbcRepository;
	}

	public Cart createCart(String userId) {
		Cart newCart = new Cart(userId);
		newCart.setTimestamp(LocalDateTime.now());
		cartJdbcRepository.createCart(newCart);
		return newCart;
	}

	public Cart findCartByUserId(String userId) {
		return cartJdbcRepository.findCartByUserId(userId);
	}

	public void updateCart(Cart cart) {
		cartJdbcRepository.updateCart(cart);
	}

	public boolean deleteCart(Integer cartId) {
		boolean delCartAfterPlcOrd = cartJdbcRepository.deleteCart(cartId);
		return delCartAfterPlcOrd;
	}

	public boolean deleteFullCart(Integer cartId) {
		boolean delCartAfterPlcOrd = cartJdbcRepository.deleteFullCart(cartId);
		return delCartAfterPlcOrd;
	}

	public List<Cart> getAllCarts() {
		return cartJdbcRepository.getAllCarts();
	}

	public void addToCart(Integer cartId, String userId, Integer itemId, Integer quantity) {
		cartJdbcRepository.addToCart(cartId, userId, itemId, quantity);
	}

	public List<CartItem> viewCartItems(String userId) {
		return cartJdbcRepository.viewCartItems(userId);
	}
}
