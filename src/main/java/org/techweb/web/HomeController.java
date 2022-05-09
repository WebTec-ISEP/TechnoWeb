package org.techweb.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	public String home(Model model, @RequestParam(name = "motCle", defaultValue = "") String mc, HttpSession session) {
		String userName = (String)session.getAttribute("name");
		if(userName == null) {
			model.addAttribute("connected", "0");
		} else {
			model.addAttribute("connected", "1");
		}
		List<Offer> offers = offerDao.findByName("%" + mc + "%");
		model.addAttribute("offers", offers);
		model.addAttribute("motC", mc);
		return("home");
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request) {
	    HttpSession session = request.getSession(false);
	    if (session != null) {
	        session.invalidate();
	    }
	    request.setAttribute("connected", "0");
	    return "redirect:/home"; 
	}
	
}
