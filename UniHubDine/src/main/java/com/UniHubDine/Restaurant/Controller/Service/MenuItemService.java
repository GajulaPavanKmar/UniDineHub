package com.UniHubDine.Restaurant.Controller.Service;

import java.util.List;

import com.UniHubDine.Restaurant.Controller.bean.MenuItem;

public interface MenuItemService {
	List<MenuItem> findAll();

	MenuItem findById(int id);
}