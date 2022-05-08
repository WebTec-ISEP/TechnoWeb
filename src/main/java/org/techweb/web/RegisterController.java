package org.techweb.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.techweb.dao.UserRepository;
import org.techweb.entities.Offer;
import org.techweb.entities.User;

@Controller
public class RegisterController {
	@Autowired
	private UserRepository userDao;
	
	@RequestMapping(value = "/register")
	public String register(HttpServletRequest request,@RequestParam(name = "name", defaultValue = "") String name,@RequestParam(name = "password", defaultValue = "") String password,@RequestParam(name = "email", defaultValue = "") String email) {
		//TODO verify user does not exist already
		if(!userDao.doesUserNameExist(name)) {
			return("Could not Register this username already exist");
		}

		User user = new User();
		user.setEmail(email);
		user.setName(name);
		user.setPassword(password);
		userDao.save(user);
		
		return("register");
	}
	
	@RequestMapping(value = "/forgotPassword")
	public String forgotPassword(HttpServletRequest request,@RequestParam String mail) {
		
	    //TODO send mail with password / or password change link
		return "forgotPassword";
	}
	
}
