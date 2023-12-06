package com.UniHubDine.Restaurant.Controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.UniHubDine.Restaurant.Model.Cart;
import com.UniHubDine.Restaurant.Model.CartItem;
import com.UniHubDine.Restaurant.Model.Menu;
import com.UniHubDine.Restaurant.Model.MenuItem;
import com.UniHubDine.Restaurant.Model.OrderDetailDTO;
import com.UniHubDine.Restaurant.Model.User;
import com.UniHubDine.Restaurant.Service.CartService;
import com.UniHubDine.Restaurant.Service.MenuItemService;
import com.UniHubDine.Restaurant.Service.MenuService;
import com.UniHubDine.Restaurant.Service.OrderService;
import com.UniHubDine.Restaurant.Service.UserService;

@Controller
@SessionAttributes("user")
public class MenuController {

	@Autowired
	private UserService userService;
	@Autowired
	MenuService menuService;

	@Autowired
	private CartService cartService;
	private final MenuItemService menuItemService;
	
	@Autowired
    private OrderService orderService;

	@Autowired
	OrderService orderservice;
	
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
	
	@GetMapping("/myAccount")
	public String myAccount(Model model) {
		User user = (User) model.getAttribute("user");
		if (user == null) {
			return "LoginPage";
		}
		List<OrderDetailDTO> orderList = orderservice.getCustomerOrderDetails(user.getUserId());
		model.addAttribute("orderList", orderList);
		return "PostLoginPages/myAccount";
	}
	@PostMapping("/placeOrder")
    public String placeOrder(Model model,RedirectAttributes redirectAttributes) {
        User user = (User) model.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        List<CartItem> cartItems = cartService.viewCartItems(user.getUserId());
		Cart cart = cartService.findCartByUserId(user.getUserId());
        boolean placeOrder = orderService.placeOrder(user, cartItems);
        if(placeOrder== false) {
        	redirectAttributes.addFlashAttribute("message", "Order failed");
    	    return "redirect:/orderConfirmation";
        }
        boolean deletingOrder = cartService.deleteFullCart(cart.getCartId());
        if(deletingOrder== false) {
        	redirectAttributes.addFlashAttribute("message", "Order failed");
    	    return "redirect:/orderConfirmation";
        }
		redirectAttributes.addFlashAttribute("message", "Your Order Placed!");

	    return "redirect:/orderConfirmation";
    }
	
	@PostMapping("/removeOrder/{cartItemId}")
    public String removeItemFromCart(@PathVariable("cartItemId") int cartItemId, Model model,RedirectAttributes redirectAttributes) {
        User user = (User) model.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        cartService.deleteCart(cartItemId);
		return "redirect:/userCart";    
	}


	@GetMapping("/orderConfirmation")
	public String orderConfirmation() {
	    return "PostLoginPages/orderConfirm";
	}

	@GetMapping("/api/menus")
	@ResponseBody
	public List<Menu> getMenusApi() {
		List<Menu> menus = menuService.findAll();
		return menus;
	}

    @GetMapping("/api/menus/{menuId}/items")
    @ResponseBody
    public List<MenuItem> getMenuItems(@PathVariable("menuId") int menuId) {
        return menuItemService.getMenuItemsByMenuId(menuId);
    }

    @PostMapping("/api/menus/{menuId}/items/addToCart")
    @ResponseBody
    public String addToCart(@RequestParam("itemId") Integer itemId, @RequestParam("quantity") Integer quantity,
                            @PathVariable("menuId") int menuId, @RequestParam("userId") String userId) {
        User user = userService.getUserByUserId(userId); 
        Cart cart = cartService.findCartByUserId(user.getUserId());
        if (cart == null) {
            cart = cartService.createCart(user.getUserId());
        }
        cartService.addToCart(cart.getCartId(), userId, itemId, quantity);
        return "Item added to cart!";
    }

    @GetMapping("/api/userCart")
    @ResponseBody
    public ResponseEntity<?> viewCart(@RequestParam("userId") String userId) {
        if (userId == null || userId.isEmpty()) {
            return ResponseEntity.badRequest().body("User ID is required");
        }

        Cart cart = cartService.findCartByUserId(userId);
        if (cart == null) {
            return ResponseEntity.ok(new HashMap<String, Boolean>() {{ put("emptyCart", true); }});
        } else {
            List<CartItem> cartItems = cartService.viewCartItems(userId);
            Map<String, Object> response = new HashMap<>();
            response.put("cart", cart);
            response.put("cartItems", cartItems);
            return ResponseEntity.ok(response);
        }
    }


    @GetMapping("/api/myAccount")
    @ResponseBody
    public List<OrderDetailDTO> myAccount(@RequestParam("userId") String userId) {
        if (userId == null) {
            return null;
        }
        return orderService.getCustomerOrderDetails(userId);
    }

    @PostMapping("/api/placeOrder")
    @ResponseBody
    public String placeOrder(@RequestParam("userId") String userId) {
        User user = userService.getUserByUserId(userId); 
        if (user.getUserId() == null) {
            return "User not logged in";
        }
        List<CartItem> cartItems = cartService.viewCartItems(user.getUserId());
        Cart cart = cartService.findCartByUserId(user.getUserId());
        boolean placeOrder = orderService.placeOrder(user, cartItems);
        if (!placeOrder) {
            return "Order failed";
        }
        cartService.deleteFullCart(cart.getCartId());
        return "Your Order Placed!";
    }

    @PostMapping("/api/removeOrder/{cartItemId}")
    @ResponseBody
    public String removeItemFromCart(@PathVariable("cartItemId") int cartItemId, @RequestParam("userId") String userId) {
        User user = userService.getUserByUserId(userId); 
        if (user == null) {
            return "User not logged in";
        }
        cartService.deleteCart(cartItemId);
        return "Item removed from cart";
    }
    
    
}
