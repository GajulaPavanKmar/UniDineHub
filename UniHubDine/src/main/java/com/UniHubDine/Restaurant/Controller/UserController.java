package com.UniHubDine.Restaurant.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.UniHubDine.Restaurant.Controller.Service.UserService;
import com.UniHubDine.Restaurant.Controller.bean.User;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;

	@RequestMapping("homepage")
	public String homePage() {
		return "NewFile";
	}

	@RequestMapping(value= "login", method= RequestMethod.GET)
	public String loginPage() {
		return "LoginPage";
	}

	@RequestMapping(value= "login", method= RequestMethod.POST)
	public String welcomePage(ModelMap model, @RequestParam String userId, @RequestParam String password) {
		User user = userService.getUserByUserId(userId);
		try {
		if(user.getPassword().equals(password)) {
			model.put("userId", userId);
			return "WelcomePage";
		}
		model.put("errorMsg", "Please provide the correct details");
		return "LoginPage";
		}
		catch(Exception e) {
		model.put("errorMsg", "Please provide the correct details");
		return "LoginPage";
		}
	}
	@RequestMapping(value ="signup" ,method= RequestMethod.GET)
	public String signupPage(Model model) {
		User user= new User();
		model.addAttribute("user",user);
		return "signUp";
	}
	@RequestMapping(value ="signup" ,method= RequestMethod.POST)
	public String signup(@ModelAttribute("user") User user, ModelMap model ) {
		int count = userService.createNewUser(user);
		if(count !=1) {
			model.put("errorMsg", "Please provide the correct details");
			return "signUp";
		}
		model.put("successMag", "User Created");
		return "LoginPage";
	}
}
