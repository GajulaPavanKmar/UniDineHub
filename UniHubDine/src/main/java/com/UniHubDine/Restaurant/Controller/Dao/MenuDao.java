package com.UniHubDine.Restaurant.Controller.Dao;

import java.util.List;

import com.UniHubDine.Restaurant.Controller.bean.Menu;

public interface MenuDao {
	List<Menu> findAll();

	Menu findById(int id);
}
