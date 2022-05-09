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
	public String register(Model model,@RequestParam(name = "name", defaultValue = "") String name,@RequestParam(name = "password", defaultValue = "") String password,@RequestParam(name = "verifyPassword", defaultValue = "") String verifyPassword, @RequestParam(name = "email", defaultValue = "") String email) {
		boolean error = false;
		if(userDao.doesUserNameExist(name)==1) {
			model.addAttribute("UserNameExist","1");
			error = true;
		}
		if(!password.equals(verifyPassword)) {
			model.addAttribute("PasswordMismatch","1");
			error = true;
		}
		if(error) {
			return("register");
		}
		
		User user = new User(name,password,email);
		userDao.save(user);
		
		return("home");
	}
}
