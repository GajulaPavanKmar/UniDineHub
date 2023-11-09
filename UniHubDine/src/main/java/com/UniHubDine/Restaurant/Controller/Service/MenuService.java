package com.UniHubDine.Restaurant.Controller.Service;

import java.util.List;

import com.UniHubDine.Restaurant.Controller.bean.Menu;

public interface MenuService {
	List<Menu> findAll();

	Menu findById(int id);
}
