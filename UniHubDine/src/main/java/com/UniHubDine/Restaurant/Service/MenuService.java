package com.UniHubDine.Restaurant.Service;

import java.util.List;

import com.UniHubDine.Restaurant.Model.Menu;

public interface MenuService {
	List<Menu> findAll();

	Menu findById(int id);
}
