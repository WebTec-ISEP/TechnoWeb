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
public class PersonalSpaceController {
	@Autowired
	private OfferRepository offerDao;
	
	@RequestMapping(value = "/personalSpace")
	public String personalSpace(Model model, @RequestParam(name = "name", defaultValue = "") String name,HttpServletRequest request, HttpSession session) {
		String userName = (String)session.getAttribute("name");
		if(userName == null) {
			request.getSession().setAttribute("name", name);
		}
		List<Offer> offers = offerDao.findByOwner(userName);
		model.addAttribute("offers", offers);
		return("personalSpace");
	}
	
	@RequestMapping(value = "/delete")
	public String delete(Model model, @RequestParam(name = "ref", defaultValue = "") Long offerId,HttpSession session) {
		offerDao.deleteById(offerId);
		List<Offer> offers = offerDao.findByOwner((String)session.getAttribute("name"));
		model.addAttribute("offers", offers);
		return("personalSpace");
	}
}
