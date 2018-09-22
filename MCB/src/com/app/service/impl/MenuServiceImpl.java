package com.app.service.impl;

import java.util.List;

import com.app.entity.Menu;
import com.app.mapper.MenuMapper;
import com.app.service.MenuService;

public class MenuServiceImpl implements MenuService{

	private MenuMapper menuMapper;
	
	public void deleteMenuById(Integer menuId) {
		
			menuMapper.deleteMenuById(menuId);
      		
	}

	public Menu getMenuById(Integer menuId) {
		 
		return menuMapper.getMenuById(menuId);
	}


	public void saveMenu(Menu menu) {
		menuMapper.insertMenu(menu);
	}

	public List<Menu> listSubMenuByParentId(Integer parentId) {
		 
		return menuMapper.listSubMenuByParentId(parentId);
	}
	
	public List<Menu> listAllMenu(Menu menus) {
		 
		List<Menu> rl = this.listAllParentMenus(menus);
		for(Menu menu : rl){
			List<Menu> subList = this.listSubMenuByParentId(menu.getMenuId());
			menu.setSubMenu(subList);
		}
		return rl;
	}

	public List<Menu> listAllSubMenu(){
		return menuMapper.listAllSubMenu();
	}
	
	public MenuMapper getMenuMapper() {
		return menuMapper;
	}

	public void setMenuMapper(MenuMapper menuMapper) {
		this.menuMapper = menuMapper;
	}

	@Override
	public void insertMenumanage(Menu menu) {
		 
		menuMapper.insertMenumanage(menu);
	}

	@Override
	public void saverMenu(Menu menu) {
		menuMapper.updateMenu(menu);
	}

	@Override
	public List<Menu> listAllParentMenu(Menu menu) {
		 
		return menuMapper.listPageAllParentMenu(menu);
	}

	@Override
	public List<Menu> listAllParentMenus(Menu menu) {
		  
		List<Menu> list=menuMapper.listAllParentMenus(menu);
		return list;
	}
	
	
	

	
	


	


}
