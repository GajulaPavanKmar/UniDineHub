package com.UniHubDine.Restaurant.Controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.UniHubDine.Restaurant.Model.Cart;
import com.UniHubDine.Restaurant.Model.CartItem;
import com.UniHubDine.Restaurant.Model.Menu;
import com.UniHubDine.Restaurant.Model.MenuItem;
import com.UniHubDine.Restaurant.Model.User;
import com.UniHubDine.Restaurant.Service.CartService;
import com.UniHubDine.Restaurant.Service.MenuItemService;
import com.UniHubDine.Restaurant.Service.MenuService;
import com.UniHubDine.Restaurant.Service.OrderService;

@Controller
@SessionAttributes("user")
public class MenuController {

	@Autowired
	MenuService menuService;

	@Autowired
	private CartService cartService;
	private final MenuItemService menuItemService;
	
	@Autowired
    private OrderService orderService;


	@Autowired
	public MenuController(MenuItemService menuItemService) {
		this.menuItemService = menuItemService;
	}

	@GetMapping("/menu")
	public String showMenu(Model model) {
		List<Menu> menus = menuService.findAll();
		model.addAttribute("menus", menus);
		return "PostLoginPages/Menu";
	}

	@GetMapping("/menu/{menuId}/items")
	public String showMenuItems(@PathVariable("menuId") int menuId, Model model) {
		List<MenuItem> menuItems = menuItemService.getMenuItemsByMenuId(menuId);
		model.addAttribute("menuItems", menuItems);
		model.addAttribute("menuId", menuId);
		return "PostLoginPages/MenuItems";
	}

	@PostMapping("/menu/{menuId}/items/addToCart")
	public String addToCart(@RequestParam("itemId") Integer itemId, @RequestParam("quantity") Integer quantity,
			@PathVariable("menuId") int menuId, Model model, RedirectAttributes redirectAttributes) {
		User user = (User) model.getAttribute("user");
		String userId = user.getUserId();
		Cart userCart = new Cart();
		Cart cart = cartService.findCartByUserId(userId);
		if (cart == null) {
			cart = cartService.createCart(userId);
		}
		Cart cartFromDB = cartService.findCartByUserId(user.getUserId());
		cartService.addToCart(cartFromDB.getCartId(), userId, itemId, quantity);
		model.addAttribute("message", "Item added to cart!");
		List<MenuItem> menuItems = menuItemService.getMenuItemsByMenuId(menuId);
		model.addAttribute("menuItems", menuItems);
		model.addAttribute("menuId", menuId);
		return "PostLoginPages/MenuItems";
	}

	@GetMapping("/userCart")
	public String viewCart(Model model) {
		User user = (User) model.getAttribute("user");
		if (user == null) {
			return "LoginPage";
		}

		Cart cart = cartService.findCartByUserId(user.getUserId());
		String userId = user.getUserId();
		List<CartItem> cartItems = cartService.viewCartItems(userId);
		if (cart == null) {
			model.addAttribute("emptyCart", true);
		} else {
			model.addAttribute("cart", cart);
			model.addAttribute("cartItems", cartItems);
		}
		return "PostLoginPages/ViewCart";
	}
	
	@PostMapping("/placeOrder")
    public String placeOrder(Model model,RedirectAttributes redirectAttributes) {
        User user = (User) model.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        // Get cart items from CartService
        List<CartItem> cartItems = cartService.viewCartItems(user.getUserId());

		Cart cart = cartService.findCartByUserId(user.getUserId());
        // Place the order
        orderService.placeOrder(user, cartItems);
        cartService.deleteCart(cart.getCartId());
		redirectAttributes.addFlashAttribute("message", "Your Order Placed!");

	    return "redirect:/orderConfirmation";
    }
	
	@GetMapping("/orderConfirmation")
	public String orderConfirmation() {
	    return "PostLoginPages/orderConfirm";
	}

}
