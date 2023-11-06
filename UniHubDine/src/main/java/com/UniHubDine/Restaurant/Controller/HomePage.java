package com.UniHubDine.Restaurant.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.UniHubDine.Restaurant.Controller.Service.UserService;
import com.UniHubDine.Restaurant.Controller.bean.User;

@Controller
public class HomePage {
	
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
		
		if(user.getPassword().equals(password)) {
			model.put("userId", userId);
			System.out.println("The password is "+user.getPassword());
			return "WelcomePage";
		}
		
		model.put("errorMsg", "Please provide the correct details");
		return "LoginPage";
	}
	@RequestMapping("signup")
	public String signupPage() {
		return "signUp";
	}

}
