package com.UniHubDine.Restaurant.Controller.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.UniHubDine.Restaurant.Controller.bean.MenuItem;

public interface MenuItemDao extends JpaRepository<MenuItem, Integer> {
	List<MenuItem> findByMenuId(int menuId);

}
