package org.techweb.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.techweb.dao.OfferRepository;
import org.techweb.entities.Offer;

@Controller
public class HomeController {
	@Autowired
	private OfferRepository offerDao;
	
	@RequestMapping(value = "/home")
	public String home(Model model, @RequestParam(name = "motCle", defaultValue = "") String mc) {
		List<Offer> offers = offerDao.findByName("%" + mc + "%");
		model.addAttribute("offers", offers);
		model.addAttribute("motC", mc);
		return("home");
	}
	
	@RequestMapping(value = "/register")
	public String register() {
		return("register");
	}
	
}
