package com.UniHubDine.Restaurant.Controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@GetMapping("/api/home")
	@ResponseBody
	public Map<String, Object> getHomePageApi() {
		Map<String, Object> response = new HashMap<>();
		response.put("contactForm", new ContactForm());
		response.put("menus", menuService.findAll());
		return response;
	}

	@PostMapping("/home")
	public String submitContactForm(Model model, @ModelAttribute ContactForm contactForm,
			RedirectAttributes redirectAttributes) {
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

	@GetMapping("/api/success")
	@ResponseBody
	public ResponseEntity<String> showSuccessApi() {
		return ResponseEntity.ok("Form submitted successfully.");
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String loginPage() {
		return "LoginPage";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String welcomePage(ModelMap model, @RequestParam String userId, @RequestParam String password,
			RedirectAttributes redirectAttributes) {
		User user = userService.getUserByUserId(userId);
		try {
			if (user.getPassword().equals(password)) {
				model.addAttribute("user", user);
				List<Menu> menus = menuService.findAll();
				model.addAttribute("menus", menus);
				if (user.getUserRole().equalsIgnoreCase("REST")) {
					return "redirect:/restDashBoard";
				} else {
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
	
	@RequestMapping(value = "loginhome", method = RequestMethod.GET)
	public String DashBoard(ModelMap model) {
		User user = (User) model.getAttribute("user");
		try {
			List<Menu> menus = menuService.findAll();
			model.addAttribute("menus", menus);
			if (user.getUserRole().equalsIgnoreCase("REST")) {
				return "redirect:/restDashBoard";
			} else {
				return "PostLoginPages/DashBoard";
			}
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

	@RequestMapping(value = "restDashBoard", method = RequestMethod.GET)
	public String restDashboard(ModelMap model) {
		User user = (User) model.getAttribute("user");
		List<OrderDetailDTO> orderList = orderservice.getOrderDetails(user.getUserId());
		model.addAttribute("orderList", orderList);
		return "PostLoginPages/RestDashBoard";
	}

	@GetMapping("/orderReady/{orderId}")
	public String makeOrderReady(@PathVariable("orderId") Integer orderId, ModelMap model) {
		User user = (User) model.getAttribute("user");
		orderservice.updateOrderDetailId(orderId);
		List<OrderDetailDTO> orderList = orderservice.getOrderDetails(user.getUserId());
		model.addAttribute("orderList", orderList);

		return "PostLoginPages/RestDashBoard";
	}
	
	@GetMapping("/api/dashboard")
	@ResponseBody
    public ResponseEntity<?> getDashboard() {
        try {
            List<Menu> menus = menuService.findAll();
            return ResponseEntity.ok(menus); 
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("An error occurred while fetching the menus");
        }
    }
	@PostMapping("/api/login")
	@ResponseBody
    public ResponseEntity<?> login(@RequestParam String userId, @RequestParam String password) {
        try {
            User user = userService.getUserByUserId(userId);

            if (user != null && user.getPassword().equals(password)) {
                List<Menu> menus = menuService.findAll();
                return ResponseEntity.ok(menus); 
            } else {
                return ResponseEntity.badRequest().body("Please provide the correct details");
            }
        } catch (Exception e) {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }
	@PostMapping("/api/orderReady/{orderId}")
    @ResponseBody
    public String changeOrderStatus(@RequestParam("userId") String userId, @PathVariable("orderId") Integer orderId,  ModelMap model) {
        User user = userService.getUserByUserId(userId); 
        if (user == null) {
            return "User not logged in";
        }
        orderservice.updateOrderDetailId(orderId);
		List<OrderDetailDTO> orderList = orderservice.getOrderDetails(user.getUserId());
		model.addAttribute("orderList", orderList);

        return "Order status changed";
    }

}
