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
import org.techweb.dao.UserRepository;
import org.techweb.entities.Offer;
import org.techweb.entities.User;

@Controller
public class PersonalSpaceController {
	@Autowired
	private OfferRepository offerDao;
	@Autowired
	private UserRepository userDao;
	
	@RequestMapping(value = "/personalSpace")
	public String personalSpace(Model model, @RequestParam(name = "name", defaultValue = "") String name,@RequestParam(name = "password", defaultValue = "") String password,HttpServletRequest request, HttpSession session) {
		
		User user = userDao.login(name,password);
		if (user == null) 
			{
			System.out.println("Failed to log in --> User : " + name + " Password: " + password);
			return "redirect:/home";
			}
		System.out.println("User : " + name + " Password: " + password);
		String userName = (String)session.getAttribute("name");
		if(userName == null) {
			request.getSession().setAttribute("name", name);
			List<Offer> offers = offerDao.findByOwner(name);
			model.addAttribute("offers", offers);
		} else {
			List<Offer> offers = offerDao.findByOwner(userName);
			model.addAttribute("offers", offers);
		}
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
