package com.UniHubDine.Restaurant.Controller.Service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.UniHubDine.Restaurant.Controller.Dao.MenuDao;
import com.UniHubDine.Restaurant.Controller.bean.Menu;
import com.UniHubDine.Restaurant.Controller.Service.MenuService;

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
