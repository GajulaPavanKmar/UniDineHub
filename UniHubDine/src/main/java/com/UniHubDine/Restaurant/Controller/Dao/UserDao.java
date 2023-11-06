package com.UniHubDine.Restaurant.Controller.Dao;

import com.UniHubDine.Restaurant.Controller.bean.User;

public interface UserDao {
	User getUserById(String userId);

	int createNewUser(User user);
}
