package org.techweb.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.techweb.dao.OfferRepository;
import org.techweb.dao.TradeRepository;
import org.techweb.dao.UserRepository;
import org.techweb.entities.Offer;
import org.techweb.entities.Trade;
import org.techweb.entities.User;

@Controller
public class PersonalSpaceController {
	@Autowired
	private OfferRepository offerDao;
	@Autowired
	private UserRepository userDao;
	@Autowired
	private TradeRepository tradeDao;
	
	@RequestMapping(value = "/personalSpace")
	public String personalSpace(Model model, @RequestParam(name = "name", defaultValue = "") String name,@RequestParam(name = "password", defaultValue = "") String password,HttpServletRequest request, HttpSession session) {	
		String userName = (String)session.getAttribute("name");
		if(userName == null) {
			User user = userDao.login(name,password);
			if (user == null) {
				System.out.println("Failed to log in --> User : " + name + " Password: " + password);
				return "redirect:/home";
			}
			request.getSession().setAttribute("name", name);
		}
		userName = (String)session.getAttribute("name");
		List<Offer> offers = offerDao.findByOwnerAndValidation(userName,false);
		model.addAttribute("offers", offers);
		List<Offer> validatedOffers = offerDao.findByOwnerAndValidation(userName,true);
		List<Offer> acceptedOffers = new ArrayList();
		for(Offer offer:validatedOffers) {
			Trade trade = tradeDao.findAcceptedOffer(offer.getIdOffer());
			Offer senderOffer = offerDao.getById(trade.getSenderOffer());
			Offer recipientOffer = offerDao.getById(trade.getRecipientOffer());
			if(senderOffer.getOwner().equals(userName)) {
				acceptedOffers.add(recipientOffer);
			} else {
				acceptedOffers.add(senderOffer);
			}
		}
		model.addAttribute("acceptedOffers", acceptedOffers);
		return("personalSpace");
	}
	
	@RequestMapping(value = "/delete")
	public String delete(Model model, @RequestParam(name = "ref", defaultValue = "") Long offerId,HttpSession session) {
		offerDao.deleteById(offerId);
		List<Offer> offers = offerDao.findByOwnerAndValidation((String)session.getAttribute("name"),false);
		model.addAttribute("offers", offers);
		return("redirect:/personalSpace");
	}
	

}
