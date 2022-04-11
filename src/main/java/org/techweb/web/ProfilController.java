package org.techweb.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.techweb.dao.UserRepository;
import org.techweb.entities.User;

@Controller
public class ProfilController {
	@Autowired
	private UserRepository userDao;
	
	@RequestMapping(value = "/profil")
	public String profil(Model model, @RequestParam(name = "name", defaultValue = "") String userName,@RequestParam(name = "ref", defaultValue = "") Long idOffer) {
		User user = userDao.findByName(userName);
		model.addAttribute("user", user);
		model.addAttribute("idOffer", idOffer);
		return "profil";
	}
}
