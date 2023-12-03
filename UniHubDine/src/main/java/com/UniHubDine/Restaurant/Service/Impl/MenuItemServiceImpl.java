package com.UniHubDine.Restaurant.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UniHubDine.Restaurant.Dao.MenuItemDao;
import com.UniHubDine.Restaurant.Model.MenuItem;
import com.UniHubDine.Restaurant.Service.MenuItemService;

@Service
public class MenuItemServiceImpl implements MenuItemService {

	private final MenuItemDao menuItemDao;

    @Autowired
    public MenuItemServiceImpl(MenuItemDao menuItemDao) {
        this.menuItemDao = menuItemDao;
    }

    @Override
    public List<MenuItem> getMenuItemsByMenuId(int menuId) {
    	System.out.println(menuItemDao.findByMenuId(menuId));
        return menuItemDao.findByMenuId(menuId);
        
    }
    
}