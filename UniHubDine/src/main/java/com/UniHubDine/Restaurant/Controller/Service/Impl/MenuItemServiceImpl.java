package com.UniHubDine.Restaurant.Controller.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UniHubDine.Restaurant.Controller.Dao.MenuItemDao;
import com.UniHubDine.Restaurant.Controller.Service.MenuItemService;
import com.UniHubDine.Restaurant.Controller.bean.MenuItem;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemDao menuItemDao;

    @Autowired
    public MenuItemServiceImpl(MenuItemDao MenuItemDao) {
        this.menuItemDao = MenuItemDao;
    }

    @Override
    public List<MenuItem> findAll() {
        return menuItemDao.findAll();
    }

    @Override
    public MenuItem findById(int id) {
        return menuItemDao.findById(id).orElse(null);
    }

}