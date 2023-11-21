package com.UniHubDine.Restaurant.Service;

import java.util.List;

import com.UniHubDine.Restaurant.Model.MenuItem;

public interface MenuItemService {

    List<MenuItem> getMenuItemsByMenuId(int menuId);

	
}
