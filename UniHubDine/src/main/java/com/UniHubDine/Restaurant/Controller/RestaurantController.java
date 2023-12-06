package com.UniHubDine.Restaurant.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.UniHubDine.Restaurant.Model.Menu;
import com.UniHubDine.Restaurant.Model.MenuItem;
import com.UniHubDine.Restaurant.Model.User;
import com.UniHubDine.Restaurant.Service.CartService;
import com.UniHubDine.Restaurant.Service.MenuItemService;
import com.UniHubDine.Restaurant.Service.MenuService;
import com.UniHubDine.Restaurant.Service.OrderService;
import com.UniHubDine.Restaurant.Service.UserService;

@Controller
@SessionAttributes("user")
public class RestaurantController {

	@Autowired
	UserService userService;

	@Autowired
	MenuService menuService;

	@Autowired
	CartService cartService;

	@Autowired
	MenuItemService menuItemService;

	@Autowired
	OrderService orderService;

	@Autowired
	OrderService orderservice;

	@GetMapping("/restMenu")
	public String showRestMenu(Model model) {
		List<Menu> menus = menuService.findAll();
		model.addAttribute("menus", menus);
		return "PostLoginPages/RestuarantMenu";
	}

	@GetMapping("/restMenu/{menuId}/items")
	public String manageRestMenu(@PathVariable("menuId") int menuId, Model model) {
		User user = (User) model.getAttribute("user");
		List<MenuItem> menuItems = cartService.getMIByMenuId(menuId, user.getUserId());
		model.addAttribute("menuItems", menuItems);
		model.addAttribute("menuId", menuId);
		return "PostLoginPages/restMenuItems";
	}

	@PostMapping("/updateMenu/{id}/menuItems")
	public String updateRestMenu(@PathVariable("id") int menuId, @RequestParam("itemId") Integer itemId,
			@RequestParam("calories") Double calories, @RequestParam("price") Double price, Model model) {
		User user = (User) model.getAttribute("user");
		boolean updateValue = cartService.updateItem(itemId, user.getUserId(), calories, price);
		if (updateValue == false) {
			model.addAttribute("status", "Failed to update the item in menu");
		} else {
			model.addAttribute("status", "Successfully updated in menu");
		}
		List<MenuItem> menuItems = cartService.getMIByMenuId(menuId, user.getUserId());
		model.addAttribute("menuItems", menuItems);
		model.addAttribute("menuId", menuId);
		return "PostLoginPages/restMenuItems";
	}

	@PostMapping("/deleteMenu/{id}/menuItems")
	public String deleteRestMenu(@PathVariable("id") int menuId, @RequestParam("itemId") Integer itemId, Model model) {
		User user = (User) model.getAttribute("user");
		boolean deleteValue = cartService.deleteMenuItem(itemId, user.getUserId());
		if (deleteValue == false) {
			model.addAttribute("status", "Failed to delete the item from menu");
		} else {
			model.addAttribute("status", "Successfully deleted from menu");
		}
		List<MenuItem> menuItems = cartService.getMIByMenuId(menuId, user.getUserId());
		model.addAttribute("menuItems", menuItems);
		model.addAttribute("menuId", menuId);
		return "PostLoginPages/restMenuItems";
	}

	@PostMapping("/addNewMenu/{id}/menuItems")
	public String addNewMenuItem(@PathVariable("id") int menuId, @RequestParam("newItemName") String newItemName,
			@RequestParam("newItemCalories") Double newItemCalories, @RequestParam("newItemPrice") Double newItemPrice,
			Model model) {
		User user = (User) model.getAttribute("user");
		boolean insertNewItem = cartService.insertNewItem(menuId, newItemName, newItemCalories, newItemPrice,
				user.getUserId());
		if (insertNewItem == false) {
			model.addAttribute("status", "Failed to add the item in menu");
		} else {
			model.addAttribute("status", "Successfully added the item in menu");
		}
		List<MenuItem> menuItems = cartService.getMIByMenuId(menuId, user.getUserId());
		model.addAttribute("menuItems", menuItems);
		model.addAttribute("menuId", menuId);
		return "PostLoginPages/restMenuItems";
	}

	@GetMapping("/api/restMenu")
	@ResponseBody
	public ResponseEntity<List<Menu>> showRestMenuApi() {
		List<Menu> menus = menuService.findAll();
		return ResponseEntity.ok(menus);
	}

	@GetMapping("/api/restMenu/{menuId}/items")
	@ResponseBody
	public ResponseEntity<List<MenuItem>> manageRestMenuApi(@PathVariable("menuId") int menuId,
			@RequestParam("userId") String userId) {
		User user = userService.getUserByUserId(userId);
		List<MenuItem> menuItems = cartService.getMIByMenuId(menuId, user.getUserId());
		return ResponseEntity.ok(menuItems);
	}

	@PostMapping("/api/updateMenu/{id}/menuItems")
	@ResponseBody
	public String updateRestMenuApi(@PathVariable("id") int menuId, 
	    @RequestParam("itemId") Integer itemId, @RequestParam("calories") Double calories, 
	    @RequestParam("price") Double price, @RequestParam("userId") String userId) {
		User user = userService.getUserByUserId(userId);
		boolean updateValue = cartService.updateItem(itemId, user.getUserId(), calories, price);
		List<MenuItem> menuItems = cartService.getMIByMenuId(menuId, user.getUserId());
		if(!updateValue) {
	        return "Failed to update the item in menu";
	    }
	    return "Successfully updated in menu";
	}


}
