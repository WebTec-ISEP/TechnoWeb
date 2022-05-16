package org.techweb.web;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.techweb.dao.ImageRepository;
import org.techweb.dao.OfferRepository;
import org.techweb.entities.Image;
import org.techweb.entities.Offer;

@Controller
public class HomeController {
	@Autowired
	private OfferRepository offerDao;
	@Autowired
	private ImageRepository imageDao;
	
	@RequestMapping(value = "/home")
	public String home(Model model, @RequestParam(name = "motCle", defaultValue = "") String mc, HttpSession session) {
		String userName = (String)session.getAttribute("name");
		if(userName == null) {
			model.addAttribute("connected", "0");
		} else {
			model.addAttribute("connected", "1");
		}
		List<Offer> offers = offerDao.findByName("%" + mc + "%");
		List<String> imagesBase64String = new ArrayList();
		for(Offer offer:offers) {
			Image image = imageDao.findByOfferId(offer.getIdOffer()).get(0);
			String base64String = Base64.getEncoder().encodeToString(image.getImage());
			imagesBase64String.add(base64String);
		}
		model.addAttribute("images", imagesBase64String);
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
