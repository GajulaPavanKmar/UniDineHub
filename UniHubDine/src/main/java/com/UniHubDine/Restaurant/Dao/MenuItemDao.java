package com.UniHubDine.Restaurant.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.UniHubDine.Restaurant.Model.MenuItem;

public interface MenuItemDao extends JpaRepository<MenuItem, Integer> {
	List<MenuItem> findByMenuId(int menuId);

}
