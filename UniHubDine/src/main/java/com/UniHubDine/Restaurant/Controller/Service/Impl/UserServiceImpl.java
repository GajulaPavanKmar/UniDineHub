package com.UniHubDine.Restaurant.Controller.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UniHubDine.Restaurant.Controller.Dao.UserDao;
import com.UniHubDine.Restaurant.Controller.Service.UserService;
import com.UniHubDine.Restaurant.Controller.bean.User;

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
