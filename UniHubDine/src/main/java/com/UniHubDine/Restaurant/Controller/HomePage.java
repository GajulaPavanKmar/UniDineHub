package com.UniHubDine.Restaurant.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePage {
	
	@RequestMapping("homepage")
	public String homePage() {
		return "HomePage";
	}

}
