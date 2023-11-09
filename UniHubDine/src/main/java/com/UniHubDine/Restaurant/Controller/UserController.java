package com.UniHubDine.Restaurant.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.UniHubDine.Restaurant.Controller.Service.MenuService;
import com.UniHubDine.Restaurant.Controller.Service.UserService;
import com.UniHubDine.Restaurant.Controller.Service.Impl.MenuItemServiceImpl;
import com.UniHubDine.Restaurant.Controller.bean.Menu;
import com.UniHubDine.Restaurant.Controller.bean.MenuItem;
import com.UniHubDine.Restaurant.Controller.bean.User;

@Controller
@SessionAttributes("user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	MenuService menuService;
	
	@Autowired
	MenuItemServiceImpl  menuItemService;

	@RequestMapping("home")
	public String homePage() {
		return "HomePage";
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String loginPage() {
		return "LoginPage";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String welcomePage(ModelMap model, @RequestParam String userId, @RequestParam String password) {
		User user = userService.getUserByUserId(userId);
		try {
			if (user.getPassword().equals(password)) {
				model.addAttribute("user",user);
				List<Menu> menus = menuService.findAll();
	            model.addAttribute("menus", menus);
				return "PostLoginPages/DashBoard";
			}
			model.put("errorMsg", "Please provide the correct details");
			return "LoginPage";
		} catch (Exception e) {
			model.put("errorMsg", "Please provide the correct details");
			return "LoginPage";
		}
	}

	@RequestMapping(value = "signup", method = RequestMethod.GET)
	public String signupPage(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "SignUp";
	}

	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String signup(@ModelAttribute("user") User user, ModelMap model) {
		int count = userService.createNewUser(user);
		if (count != 1) {
			model.put("errorMsg", "Please provide the correct details");
			return "SignUp";
		}
		model.put("successMag", "User Created");
		return "LoginPage";
	}
	
	@RequestMapping(value = "menuItems", method = RequestMethod.GET)
	public String drinksMenu(@ModelAttribute("user") User user, Model model) {
		model.addAttribute("userDetails", user); 
		List<MenuItem> menuitems = menuItemService.findAll();
        model.addAttribute("menuitems", menuitems);
	    return "PostLoginPages/MenuItems";
	}

}
