package com.UniHubDine.Restaurant.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UniHubDine.Restaurant.Dao.UserDao;
import com.UniHubDine.Restaurant.Model.User;
import com.UniHubDine.Restaurant.Service.UserService;

@Service
public class UserServiceImpl implements  UserService{
	
	@Autowired 
	UserDao userDao;
	
	public User getUserByUserId(String userId) {
		User user = userDao.getUserById(userId);
		return user;
	}

	@Override
	public int createNewUser(User user) {
		return userDao.createNewUser(user);
	}

}
