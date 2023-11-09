package com.UniHubDine.Restaurant.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.UniHubDine.Restaurant.Controller.Service.ContactFormService;
import com.UniHubDine.Restaurant.Controller.Service.MenuItemService;
import com.UniHubDine.Restaurant.Controller.Service.MenuService;
import com.UniHubDine.Restaurant.Controller.Service.UserService;
import com.UniHubDine.Restaurant.Controller.bean.ContactForm;
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

	private final MenuItemService menuItemService;

    @Autowired
    public UserController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

	@Autowired
	ContactFormService service;

	@RequestMapping("home")
	public String homePage(Model model) {
		model.addAttribute("contactForm", new ContactForm());
		return "HomePage";
	}

	@PostMapping("/home")
	public String submitContactForm(@ModelAttribute ContactForm contactForm, RedirectAttributes redirectAttributes) {
		service.saveContactForm(contactForm);
		redirectAttributes.addFlashAttribute("message", "Your contact form has been successfully submitted.");
		return "redirect:/success";
	}

	@GetMapping("/success")
	public String showSuccessPage() {
		return "ContactFormSuccess";
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
				model.addAttribute("user", user);
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

	@GetMapping("/menu/{menuId}/items")
    public String showMenuItems(@PathVariable("menuId") int menuId, Model model) {
        List<MenuItem> menuItems = menuItemService.getMenuItemsByMenuId(menuId);
        model.addAttribute("menuItems", menuItems);
        return "PostLoginPages/MenuItems";
    }
}
