package com.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.app.entity.User;
import com.app.mapper.UserMapper;
import com.app.service.UserService;


public class UserServiceImpl implements UserService {

	private UserMapper userMapper;
	
	public User getUserById(Integer userId) {
		return userMapper.getUserById(userId);
	}

	public boolean insertUser(User user) {
		String userName = user.getLoginname();
		int count = userMapper.getCountByName(userName);
		if(count>0){
			return false;
		}else{
			userMapper.insertUser(user);
			return true;
		}
	}

	public List<User> listUser(User user){
		List<User> list=userMapper.listUserByUser(user);
		return list;
	}

	public void updateUser(User user) {
		userMapper.updateUser(user);
	}

	public void updateUserBaseInfo(User user){
		userMapper.updateUserBaseInfo(user);
	}
	
	public void updateUserRights(User user){
		userMapper.updateUserRights(user);
	}
	
	public User getUserByNameAndPwd(String loginname, String password) {
		User user = new User();
		user.setLoginname(loginname);
		user.setPassword(password);
		return userMapper.getUserInfo(user);
	}
	
	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public void deleteUser(int userId){
		userMapper.deleteUser(userId);
	}

	public User getUserAndRoleById(Integer userId) {
		return userMapper.getUserAndRoleById(userId);
	}

	public void updateLastLogin(User user) {
		userMapper.updateLastLogin(user);
	}

	public List<User> listAllUser() {
		return userMapper.listAllUser();
	}

	@Override
	public void updateUsers(User user) {
		userMapper.updateUsers(user);
	}

	@Override
	public List<User> userSel(int userId) {
		List<User> resultList = new ArrayList<User>();
		List<User> allChildList = userMapper.userSel(userId);
		getUserByCreate(allChildList,resultList);
		
		return resultList;
	}	
	
	
	private void getUserByCreate(List<User> users,List<User> resultList){
		
		for(int i=0;i<users.size();i++){
			resultList.add(users.get(i));
			List<User> childListAll =userMapper.userSel(users.get(i).getUserId());
			if(childListAll.size()>0){
				//递归调用
				getUserByCreate(childListAll,resultList);
			}
		}
	}

	@Override
	public List<User> userSela(User user) {
		// TODO Auto-generated method stub
		return userMapper.userSela(user);
	}

}
