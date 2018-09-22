package com.app.controller;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.entity.Menu;
import com.app.entity.Role;
import com.app.entity.User;
import com.app.listener.RightsHelper;
import com.app.listener.Tools;
import com.app.service.MenuService;
import com.app.service.RoleService;
import com.app.service.UserService;
import com.app.util.PageBean;


 
@Controller
@RequestMapping(value="/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private UserService userService;
	
	/**
	 * 显示角色列表
	 * @param map
	 * @return
	 */
	@RequestMapping
	public String list(Map<String, Object> map, Role role,
			HttpServletRequest request, ModelMap modelMap) {
		User user = (User) request.getSession().getAttribute("user");
		List<User> userList = userService.userSel(user.getUserId());
		userList.add(0, user);
		List<Role> list = roleService.listAllChildRole(userList, role);
		// role.setRoleId(user.getRoleId());
		String permissionsCategory = user.getPermissionsCategory();
		// List<Role> list = roleService.allRolesPageList(role);

		// 判断list是否为空
		List<Role> pageList = new ArrayList<Role>();
		PageBean<Role> listPage = new PageBean<Role>();
		int pageNo = role.getPageNo();
		if (list.size() > 0) {
			if (pageNo == 0) {
				pageNo = 1;
			}
			int pageSize = 10;
			int itemNo = pageSize * (pageNo - 1);
			int totalRecords = list.size();
			int totalPage = totalRecords % pageSize == 0 ? totalRecords
					/ pageSize : totalRecords / pageSize + 1;
			if (pageNo == totalPage) {
				for (int i = itemNo; i < totalRecords; i++) {
					Role role2 = list.get(i);
					pageList.add(role2);
				}
			} else {
				for (int i = itemNo, b = itemNo + pageSize; i < b; i++) {
					Role role2 = list.get(i);
					pageList.add(role2);
				}
			}
			listPage.setList(pageList);
			listPage.setPageNo(pageNo);
			listPage.setPageSize(pageSize);
			listPage.setTotalRecordes(list.size());
		}
		modelMap.put("listPage", listPage);

		// modelMap.put("roles", roles);
		request.setAttribute("role", role);
		request.setAttribute("permissionsCategory", permissionsCategory);
		return "roles/roles";
	}
	
	/**
	 * 保存角色信息
	 * @param out
	 * @param role
	 */
	@RequestMapping(value="/save")
	public void save(Role role,HttpServletRequest request,HttpServletResponse response){
		boolean flag = true;
		try {
			User user=(User)request.getSession().getAttribute("user");
			String roleId=String.valueOf(user.getRoleId());	
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");
			if(role.getRoleId()!=null && role.getRoleId().intValue()>0){
				flag = roleService.updateRoleBaseInfo(role);
			}else{
				role.setRoleTwo(roleId);
				role.setCreateId(user.getUserId());
				flag = roleService.insertRole(role);
			}
			Map<String,Object> data =  new HashMap<String, Object>();
			if(flag){
				data.put("flag", "success");
			}else{
				data.put("flag", "failed");
			}
			PrintWriter pt = null;
			JSONObject json = JSONObject.fromObject(data);
			String jsonStr = json.toString();
			pt = response.getWriter();
			pt.write(jsonStr);
			pt.flush();
			pt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 请求角色授权页面
	 * @param roleId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/auth")
	public String auth(@RequestParam int roleId,Model model,Menu menus,HttpServletRequest request){
		List<Menu> menuList = menuService.listAllMenu(menus);
		User user=(User)request.getSession().getAttribute("user");
		Role seRole = roleService.getRoleById(user.getRoleId());
		String seRights = seRole.getRights();
		List<Menu> newList=new ArrayList<Menu>();
		JSONArray arr=null;
		if("1".equals(String.valueOf(roleId))){
			if(Tools.notEmpty(seRights)){
				for(Menu menu : menuList){
					menu.setHasMenu(RightsHelper.testRights(seRights, menu.getMenuId()));
					if(menu.isHasMenu()){
						List<Menu> subMenuList = menu.getSubMenu();
						for(Menu sub : subMenuList){
							sub.setHasMenu(RightsHelper.testRights(seRights, sub.getMenuId()));
						}
					}
				}
			}
			arr = JSONArray.fromObject(menuList);
		}else{
			if(Tools.notEmpty(seRights)){
				for(Menu menu : menuList){
					menu.setHasMenu(RightsHelper.testRights(seRights, menu.getMenuId()));
					if(menu.isHasMenu()){
						List<Menu> subMenuList = menu.getSubMenu();
						List<Menu> listOne=new ArrayList<Menu>();
						for(Menu sub : subMenuList){
							sub.setHasMenu(RightsHelper.testRights(seRights, sub.getMenuId()));
							if(sub.isHasMenu()){
								listOne.add(sub);
							}
						}
						menu.setSubMenu(listOne);
						newList.add(menu);
						 
					}
				}
			}
			Role role = roleService.getRoleById(roleId);
			String roleRights = role.getRights();
			if(Tools.notEmpty(roleRights)){
				for(Menu menu : newList){
					menu.setHasMenu(RightsHelper.testRights(roleRights, menu.getMenuId()));
					List<Menu> subMenuList = menu.getSubMenu();
					for(Menu sub : subMenuList){
						sub.setHasMenu(RightsHelper.testRights(roleRights, sub.getMenuId()));
						 
					}
				}
			}else{
				for(Menu menu : newList){
					menu.setHasMenu(false);
					List<Menu> subMenuList = menu.getSubMenu();
					for(Menu sub : subMenuList){
						sub.setHasMenu(false);
						 
					}
				}
			}
			arr = JSONArray.fromObject(newList);
		}
		 
		String json = arr.toString();
		json = json.replaceAll("menuId", "id").replaceAll("menuName", "name").replaceAll("subMenu", "nodes").replaceAll("hasMenu", "checked");
		model.addAttribute("zTreeNodes", json);
		model.addAttribute("roleId", roleId);
		return "authorization";
	}
	
	/**
	 * 保存角色权限
	 * @param roleId
	 * @param menuIds
	 * @param out
	 */
	@RequestMapping(value="/auth/save")
	public void saveAuth(@RequestParam int roleId,@RequestParam String menuIds,PrintWriter out){
		BigInteger rights = null;
		Role role = null;
		if(menuIds.length() != 0){
			rights= RightsHelper.sumRights(Tools.str2StrArray(menuIds));
			role = roleService.getRoleById(roleId);
			role.setRights(rights.toString());
			roleService.updateRoleRights(role);
		}else{
			//需要清除rights
			role = roleService.getRoleById(roleId);
			role.setRights("");
			roleService.updateRoleRights(role);
		}
		out.write("success");
		out.close();
	}
	/**
	 * 删除角色信息
	 * @param roleId
	 * @param out
	 */
	@RequestMapping(value="/delete")
	public void deleteRole(@RequestParam int roleId,PrintWriter out){
		//判断角色下是否有用户
		int userNum = roleService.getUserNumById(roleId);
		if(userNum ==0){
			roleService.deleteRoleById(roleId);
			out.write("success");
		}else{
			out.write("failed");
		}
		out.close();
	}	
}
