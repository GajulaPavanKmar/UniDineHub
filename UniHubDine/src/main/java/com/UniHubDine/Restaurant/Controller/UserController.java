package com.UniHubDine.Restaurant.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.UniHubDine.Restaurant.Model.ContactForm;
import com.UniHubDine.Restaurant.Model.Menu;
import com.UniHubDine.Restaurant.Model.OrderDetailDTO;
import com.UniHubDine.Restaurant.Model.User;
import com.UniHubDine.Restaurant.Service.ContactFormMongoService;
import com.UniHubDine.Restaurant.Service.MenuService;
import com.UniHubDine.Restaurant.Service.OrderService;
import com.UniHubDine.Restaurant.Service.UserService;

@Controller
@SessionAttributes("user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	MenuService menuService;

	@Autowired
	ContactFormMongoService mongoService;
	
	@Autowired
	OrderService orderservice;

	@RequestMapping("home")
	public String homePage(Model model) {
		model.addAttribute("contactForm", new ContactForm());
		List<Menu> menus = menuService.findAll();
		model.addAttribute("menus", menus);
		return "HomePage";
	}
	
	@GetMapping("/api/menus")
	@ResponseBody
	public List<Menu> getMenusApi() {
	    List<Menu> menus = menuService.findAll();
	    return menus;
	}


	@PostMapping("/home")
	public String submitContactForm(Model model, @ModelAttribute ContactForm contactForm, RedirectAttributes redirectAttributes) {
		mongoService.saveContactForm(contactForm);
		List<Menu> menus = menuService.findAll();
		model.addAttribute("menus", menus);
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
	public String welcomePage(ModelMap model, @RequestParam String userId, @RequestParam String password,RedirectAttributes redirectAttributes) {
		User user = userService.getUserByUserId(userId);
		try {
			if (user.getPassword().equals(password)) {
				model.addAttribute("user", user);
				List<Menu> menus = menuService.findAll();
				model.addAttribute("menus", menus);
				if(user.getUserRole().equalsIgnoreCase("REST")) {
					return "redirect:/restDashBoard";
				}else {
				return "PostLoginPages/DashBoard";
				}
			}
			model.put("errorMsg", "Please provide the correct details");
			return "LoginPage";
		} catch (Exception e) {
			model.put("errorMsg", "Please provide the correct details");
			return "LoginPage";
		}
	}
	
	@RequestMapping(value = "restDashBoard", method = RequestMethod.GET)
	public String restDashboard(ModelMap model) {
		User user = (User) model.getAttribute("user");
		List<OrderDetailDTO> orderList = orderservice.getOrderDetails(user.getUserId());
		model.addAttribute("orderList", orderList);
		return "PostLoginPages/RestDashBoard";
	}
	
	@RequestMapping(value = "loginhome", method = RequestMethod.GET)
	public String DashBoard(ModelMap model) {
		User user = (User) model.getAttribute("user");
		try {
			List<Menu> menus = menuService.findAll();
			model.addAttribute("menus", menus);
			return "PostLoginPages/DashBoard";
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
}
