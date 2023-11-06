package com.UniHubDine.Restaurant.Controller.Service;

import com.UniHubDine.Restaurant.Controller.bean.User;

public interface UserService {

	User getUserByUserId(String userId);
	
	int createNewUser(User user);
}
