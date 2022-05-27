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
import org.techweb.dao.HouseRepository;
import org.techweb.dao.OfferRepository;
import org.techweb.dao.TradeRepository;
import org.techweb.dao.UserRepository;
import org.techweb.entities.House;
import org.techweb.entities.Offer;
import org.techweb.entities.Trade;
import org.techweb.entities.User;

@Controller
public class PersonalSpaceController {
	@Autowired
	private HouseRepository houseDao;
	@Autowired
	private OfferRepository offerDao;
	@Autowired
	private UserRepository userDao;
	@Autowired
	private TradeRepository tradeDao;
	
	@RequestMapping(value = "/personalSpace")
	public String personalSpace(Model model, @RequestParam(name = "name", defaultValue = "") String name,@RequestParam(name = "password", defaultValue = "") String password,HttpServletRequest request , HttpSession session) {	
		String userName = (String)session.getAttribute("name");
		if(userName == null) {
			User user = userDao.login(name,password);
			if (user == null) {
				return "redirect:/home";
			}
			request.getSession().setAttribute("name", name);
		}
		userName = (String)session.getAttribute("name");
		List<House> houses = houseDao.findByOwner(userName);
		model.addAttribute("houses", houses);
		List<Offer> offers = new ArrayList();
		List<Offer> validatedOffers = new ArrayList();
		for(House house:houses) {
			offers.addAll(offerDao.findByHouseIdAndValidation(house.getIdHouse(),false));
			validatedOffers.addAll(offerDao.findByHouseIdAndValidation(house.getIdHouse(),true));
		}
		model.addAttribute("offers", offers);
		List<Offer> acceptedOffers = new ArrayList();
		List<House> acceptedHouses = new ArrayList();
		for(Offer offer:validatedOffers) {
			Trade trade = tradeDao.findAcceptedOffer(offer.getIdOffer());
			Offer senderOffer = offerDao.getById(trade.getSenderOffer());
			Offer recipientOffer = offerDao.getById(trade.getRecipientOffer());
			String owner = houseDao.getById(senderOffer.getHouseId()).getOwner();
			if(owner.equals(userName)) {
				acceptedOffers.add(recipientOffer);
				acceptedHouses.add(houseDao.findById(recipientOffer.getHouseId()).get());
			} else {
				acceptedOffers.add(senderOffer);
				acceptedHouses.add(houseDao.findById(senderOffer.getHouseId()).get());
			}
		}
		model.addAttribute("acceptedOffers", acceptedOffers);
		model.addAttribute("acceptedHouses", acceptedHouses);
		return("personalSpace");
	}
	
	@RequestMapping(value = "/delete")
	public String delete(Model model, @RequestParam(name = "ref", defaultValue = "") Long offerId,HttpSession session) {
		offerDao.deleteById(offerId);
		return("redirect:/personalSpace");
	}
	

}
