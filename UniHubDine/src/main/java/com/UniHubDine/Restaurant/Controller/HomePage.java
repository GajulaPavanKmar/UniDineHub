package com.UniHubDine.Restaurant.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomePage {
	@RequestMapping("home")
	@ResponseBody()
	public String home() {
		return "hello world";
	}
	@RequestMapping("homepage")
	public String homePage() {
		return "NewFile";
	}

}
