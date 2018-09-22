package com.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.Role;
import com.app.entity.User;
import com.app.listener.Const;
import com.app.listener.UserExcelView;
import com.app.service.RoleService;
import com.app.service.UserService;
import com.app.util.MD5Util;
import com.app.util.PageBean;
 


@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	/**
	 * 显示用户列表
	 * @param user
	 * @return
	 */
	@RequestMapping
	public String list(User user,Role role,HttpServletRequest request,ModelMap modelMap,HttpSession session ){
		int pageNo = role.getPageNo();
		if(pageNo == 0){
			pageNo =1;
		}
		return getList(user, role, request, modelMap, session,String.valueOf(pageNo));
	}
	
	/**
	 * 显示用户列表
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/listPage")
	public String listPage(User user,Role role,HttpServletRequest request,@RequestParam String pageNoStr ,ModelMap modelMap,HttpSession session){
		return getList(user, role, request, modelMap, session,pageNoStr);
	}
	
	private String getList(User user,Role role,HttpServletRequest request,ModelMap modelMap,HttpSession session,String pageNoStr){
		User seUser = (User)session.getAttribute(Const.SESSION_USER);
//		role.setRoleId(seUser.getRoleId());
//		List<Role> roles = roleService.listAllChildRole(String.valueOf(role.getRoleId()));
//		List<Integer> roleIdList = new ArrayList<Integer>();
		List<User> list = userService.userSel(seUser.getUserId());
		role = roleService.getRoleById(seUser.getRoleId());
		seUser.setRole(role);
		list.add(0, seUser);
		
		if(!StringUtils.isBlank(user.getLoginname())||!StringUtils.isBlank(user.getUsername())){
			List<User> serchList = new ArrayList<User>();
			for(User u:list){
				if(!StringUtils.isBlank(user.getLoginname())&&!StringUtils.isBlank(user.getUsername())){
					if(u.getLoginname().indexOf(user.getLoginname())>-1&&u.getUsername().indexOf(user.getUsername())>-1){
						serchList.add(u);
					}
				}
				else if(!StringUtils.isBlank(user.getLoginname())){
					if(u.getLoginname().indexOf(user.getLoginname())>-1){
						serchList.add(u);
					}
				}
				else if(!StringUtils.isBlank(user.getUsername())){
					if(u.getUsername().indexOf(user.getUsername())>-1){
						serchList.add(u);
					}
				}
			}
			list = serchList;
		}
//		if(roles.size()>0){
//			for (int i = 0,b=roles.size(); i <b; i++) {
//				roleIdList.add(roles.get(i).getRoleId());
//			}
//			user.setRoleIdList(roleIdList);
//			list= userService.listUser(user);
//		}
		//判断list是否为空
		List<User> pageList = new ArrayList<User>();
		PageBean<User> listPage = new PageBean<User>();
		if(list.size()>0){
			int pageNo = 1;
			//分页
			if(pageNoStr !=null && pageNoStr != ""){
				pageNo = Integer.parseInt(pageNoStr);
			}
			int pageSize = 10;
			int itemNo = pageSize*(pageNo-1);
			int totalRecords = list.size();
			int totalPage = totalRecords%pageSize ==0?totalRecords/pageSize:totalRecords/pageSize+1;
			if(pageNo == totalPage){
				for(int i=itemNo;i<totalRecords;i++){
					User user2 = list.get(i);
					pageList.add(user2);
				}
			}else{
				for (int i = itemNo,b=itemNo+pageSize; i < b; i++) {
					User user2 = list.get(i);
					pageList.add(user2);
				}
			}
			listPage.setList(pageList);
			listPage.setPageNo(pageNo);
			listPage.setPageSize(pageSize);
			listPage.setTotalRecordes(list.size());
		}
		modelMap.put("listPage", listPage);
		//modelMap.put("list", list);
		request.setAttribute("seUser", seUser);
		return "users/users";
	}
	
	/*@RequestMapping(value="/ajax_searchuser")
	public void searchuser(HttpServletResponse response,Role role,HttpServletRequest request){
		try {
			User user=(User)request.getSession().getAttribute("user");
			List<Role> roleList = roleService.listAllRolesSel(String.valueOf(user.getRoleId()));
			request.setAttribute("roleList", roleList);
			//将list集合转换成json格式
			JSONArray array=JSONArray.fromObject(roleList);
			//将数据响应给ajax plain 纯文本（文本格式）
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out=response.getWriter();
			out.print(array.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	} */
	
	@RequestMapping(value="/searchUser")
	public void  searchCityShop(HttpServletResponse response,HttpServletRequest request){
		try {
			String role=request.getParameter("role");
			//调用查询方法
			String name=roleService.getListAllRoless(role);
			//将数据响应给ajax plain 纯文本（文本格式）
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(name);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 请求新增用户页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add")
	public String toAdd(Model model,HttpServletRequest request){
		User user=(User)request.getSession().getAttribute("user");
		List<Role> roleList=null;
		if("1".equals(user.getUserId().toString())){
			roleList = roleService.listAllRoleByAdmin();
		}else{
			roleList = roleService.listAllRolesSel(String.valueOf(user.getRoleId()));
		}
		request.setAttribute("roleList", roleList);
		return "users/addUser";
	}
	
	/**
	 * 保存用户信息
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ModelAndView saveUser(User user,HttpServletRequest request){
		User seUser=(User)request.getSession().getAttribute("user");
		ModelAndView mv = new ModelAndView();
		if(user!=null){
			String pwd=MD5Util.encode(user.getPassword());
			user.setPassword(pwd);
		}
		if(user.getRoleId()==null){
			user.setRoleId(0);
		}
		user.setCreateId(seUser.getUserId());
		if(userService.insertUser(user)==false){
			mv.addObject("msg","failed");
		}else{
			mv.addObject("msg","success");
		}
		mv.setViewName("save_result");
		return mv;
	}
	
	
	
	
	@RequestMapping(value="/saver",method=RequestMethod.POST)
	public ModelAndView saveUserr(@RequestParam String myRoleId,User user,HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		if(user.getRoleId()==null){
			user.setRoleId(0);
		}
		if(myRoleId!=null&&!"".equals(myRoleId)){
			user.setRoleId(Integer.parseInt(myRoleId));
		}
		String password=request.getParameter("password");
		if(password!=null && password!=""){
			String pwd=MD5Util.encode(user.getPassword());
			user.setPassword(pwd);
			userService.updateUserBaseInfo(user);
		}else{
			userService.updateUsers(user);
		}
		
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 请求编辑用户页面
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/edit")
	public ModelAndView toEdit(@RequestParam int userId,Role role,HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		User u=(User)request.getSession().getAttribute("user");
		List<Role> roleList=null;
		if(u.getUserId().equals(userId)){
			roleList = roleService.listAllRolesSels(String.valueOf(u.getRoleId()));
		}else if("1".equals(u.getUserId().toString())){
			roleList = roleService.listAllRoleByAdmin();
		}else{
			roleList = roleService.listAllRolesSel(String.valueOf(u.getRoleId()));
		}
		 
		
		User user = userService.getUserById(userId);
		/*List<Role> roleList = roleService.listAllRolesSels(role);*/
	
		mv.addObject("user", user);
		mv.addObject("roleList", roleList);
		mv.setViewName("users/updateUser");
		return mv;
	}
	/**
	 * 删除某个用户
	 * @param userId
	 * @param out
	 */
	@RequestMapping(value="/delete")
	public void deleteUser(@RequestParam int userId,PrintWriter out){
		userService.deleteUser(userId);
		out.write("success");
		out.close();
	}
	
	
	/**
	 * 导出用户信息到excel
	 * @return
	 */
	@RequestMapping(value="/excel")
	public ModelAndView export2Excel(HttpSession session){
		Map<String,Object> dataMap = new HashMap<String,Object>();
		User seUser = (User)session.getAttribute(Const.SESSION_USER);
		List<String> titles = new ArrayList<String>();
		/*titles.add("编号");*/
		titles.add("登录名");
		titles.add("用户名");
		titles.add("角色");
		titles.add("状态");
		titles.add("描述");
		titles.add("最近登录");
		dataMap.put("titles", titles);
//		System.out.println(seUser.getLoginname()+seUser.getUsername());
//		seUser.setLoginname(seUser.getLoginname());
//		seUser.setUsername(seUser.getUsername());
		List<User> userList = userService.userSela(seUser);
//		userList.add(0, seUser);
		dataMap.put("userList", userList);
		UserExcelView erv = new UserExcelView();
		ModelAndView mv = new ModelAndView(erv,dataMap);
		return mv;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
