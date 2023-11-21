package com.UniHubDine.Restaurant.Controller;

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
import com.UniHubDine.Restaurant.Model.Menu;
import com.UniHubDine.Restaurant.Model.MenuItem;
import com.UniHubDine.Restaurant.Model.User;
import com.UniHubDine.Restaurant.Service.CartService;
import com.UniHubDine.Restaurant.Service.MenuItemService;
import com.UniHubDine.Restaurant.Service.MenuService;

@Controller
@SessionAttributes("user")
public class MenuController {

	@Autowired
	MenuService menuService;

	@Autowired
	private CartService cartService;
	private final MenuItemService menuItemService;

	@Autowired
	public MenuController(MenuItemService menuItemService) {
		this.menuItemService = menuItemService;
	}

	@GetMapping("/menu")
	public String showMenu( Model model) {
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
			@PathVariable("menuId") int menuId,
			Model model, RedirectAttributes redirectAttributes) {
		User user = (User) model.getAttribute("user");
		String userId = user.getUserId();
		Cart userCart = new Cart();
		Cart cart = cartService.findCartByUserId(userId);
		if (cart == null) {
			cart = cartService.createCart(userId);
		}
		Integer cartId = cart.getCartId();
		cartService.addToCart(cartId, userId, itemId, quantity);
		redirectAttributes.addFlashAttribute("message", "Item added to cart!");
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
        if (cart == null) {
            model.addAttribute("emptyCart", true);
        } else {
            model.addAttribute("cart", cart);
//            model.addAttribute("cartItems", cart.getAllCarts()); // Assuming Cart has a method getCartItems()
        }

        return "PostLoginPages/ViewCart"; // The name of the Thymeleaf template for the cart page
    }
}
