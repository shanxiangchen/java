package com.app.mapper;

import java.util.List;

import com.app.entity.Menu;

public interface MenuMapper {
	List<Menu> listPageAllParentMenu(Menu menu);
	List<Menu> listAllParentMenus(Menu menu);
	int getCount(Menu menu);
	List<Menu> listSubMenuByParentId(Integer parentId);
	Menu getMenuById(Integer menuId);
	void insertMenu(Menu menu);

	void insertMenumanage(Menu menu);
	void updateMenu(Menu menu);
	
	void deleteMenuById(Integer menuId);
	List<Menu> listAllSubMenu();
	
	
}
