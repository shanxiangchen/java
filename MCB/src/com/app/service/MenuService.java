package com.app.service;

import java.util.List;

import com.app.entity.Menu;

public interface MenuService {
	List<Menu> listAllMenu(Menu menu);
	List<Menu> listAllParentMenu(Menu menu);
	List<Menu> listAllParentMenus(Menu menu);
	List<Menu> listSubMenuByParentId(Integer parentId);
	Menu getMenuById(Integer menuId);
	void saveMenu(Menu menu);
	void saverMenu(Menu menu);

	void deleteMenuById(Integer menuId);
	List<Menu> listAllSubMenu();
	void insertMenumanage(Menu menu);
	
}
