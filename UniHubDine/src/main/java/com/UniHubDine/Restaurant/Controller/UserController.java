package com.UniHubDine.Restaurant.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.UniHubDine.Restaurant.Controller.Service.UserService;
import com.UniHubDine.Restaurant.Controller.bean.User;

@Controller
@SessionAttributes("user")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping("home")
	@GetMapping
	public String homePage1() {
		return "NewFile";
	}

	@RequestMapping("/")
	public String homePage() {
		return "NewFile";
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
				return "redirect:/dashboard?userId=" + user.getUserId();
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
		return "signUp";
	}

	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String signup(@ModelAttribute("user") User user, ModelMap model) {
		int count = userService.createNewUser(user);
		if (count != 1) {
			model.put("errorMsg", "Please provide the correct details");
			return "signUp";
		}
		model.put("successMag", "User Created");
		return "LoginPage";
	}

	@RequestMapping(value = "dashboard", method = RequestMethod.GET)
	public String userDashboard(@ModelAttribute("user") User user, Model model) {
        // The user object is already in the session and can be used directly
        model.addAttribute("user", user);
        return "dashboard";
    }
	
	@RequestMapping(value = "drinks", method = RequestMethod.GET)
	public String drinksMenu(@ModelAttribute("user") User user, Model model) {
		model.addAttribute("userDetails", user); // This line is optional if you want to rename the attribute in the model
	    return "postLogin/menuItems"; // Return the view for the profile page
	}

}
