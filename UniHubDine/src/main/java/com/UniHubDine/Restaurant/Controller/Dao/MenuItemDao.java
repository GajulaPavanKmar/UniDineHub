package com.UniHubDine.Restaurant.Controller.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.UniHubDine.Restaurant.Controller.bean.MenuItem;

public interface MenuItemDao extends JpaRepository<MenuItem, Integer> {
}
