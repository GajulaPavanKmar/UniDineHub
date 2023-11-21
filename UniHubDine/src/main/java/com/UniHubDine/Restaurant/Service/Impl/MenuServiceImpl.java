package com.UniHubDine.Restaurant.Service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UniHubDine.Restaurant.Dao.MenuDao;
import com.UniHubDine.Restaurant.Model.Menu;
import com.UniHubDine.Restaurant.Service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Menu> findAll() {
        return menuDao.findAll();
    }

    @Override
    public Menu findById(int id) {
        return menuDao.findById(id);
    }
}
