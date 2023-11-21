package com.UniHubDine.Restaurant.Dao;

import com.UniHubDine.Restaurant.Model.User;

public interface UserDao {
	User getUserById(String userId);

	int createNewUser(User user);
}
