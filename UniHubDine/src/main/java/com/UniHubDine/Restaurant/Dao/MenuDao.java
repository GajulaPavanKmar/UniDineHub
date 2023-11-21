package com.UniHubDine.Restaurant.Dao;

import java.util.List;

import com.UniHubDine.Restaurant.Model.Menu;

public interface MenuDao {
	List<Menu> findAll();

	Menu findById(int id);
}
