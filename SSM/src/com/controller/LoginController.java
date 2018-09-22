package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.model.User;
import com.service.UserService;

@Controller
@RequestMapping("/user")
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	public String login(HttpServletRequest request, Model model) {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String result = userService.login(userName, password);
		if ("success".equals(result)) {
			HttpSession session = request.getSession();
			session.setAttribute("user.name", userName);
			List<User> userList = userService.listAllUsers();
			model.addAttribute("userList", userList);
			return "menu";
		}
		model.addAttribute("userName", userName);
		return "login";
	}
}