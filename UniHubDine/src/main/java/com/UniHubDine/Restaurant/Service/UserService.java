package com.UniHubDine.Restaurant.Service;

import com.UniHubDine.Restaurant.Model.User;

public interface UserService {

	User getUserByUserId(String userId);
	
	int createNewUser(User user);
}
