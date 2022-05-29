package org.techweb.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.techweb.dao.HouseRepository;
import org.techweb.dao.OfferRepository;
import org.techweb.dao.TradeRepository;
import org.techweb.entities.House;
import org.techweb.entities.Offer;
import org.techweb.entities.Trade;
import org.techweb.entities.User;

@Controller
public class TradeController {
	@Autowired
	private TradeRepository tradeDao;
	@Autowired
	private OfferRepository offerDao;
	@Autowired
	private HouseRepository houseDao;
	
	@RequestMapping(value = "/trade")
	public String trade(Model model, @RequestParam(name = "ref", defaultValue = "") Long idOffer, HttpSession session) {
		String userName = (String)session.getAttribute("name");
		if(userName == null) {
			model.addAttribute("connected", "0");
			return "redirect:/home";
		} else {
			model.addAttribute("connected", "1");
		}
		
		Optional<Offer> offer = offerDao.findById(idOffer);
		if(offer.isPresent()) {
			model.addAttribute("offer", offer.get());
			Optional<House> house = houseDao.findById(offer.get().getHouseId());
			model.addAttribute("house", house.get());
		}
		
		List<House> houses = houseDao.findByOwner(userName);
		List<Offer> offers = new ArrayList();
		List<Offer> validatedOffers = new ArrayList();
		for(House house:houses) {
			offers.addAll(offerDao.findByHouseIdAndValidation(house.getIdHouse(),false));
			validatedOffers.addAll(offerDao.findByHouseIdAndValidation(house.getIdHouse(),true));
		}
		model.addAttribute("houses",houses);
		model.addAttribute("offers", offers);
		return "trade";
	}
	
	@RequestMapping(value = "/tradeProposal")
	public String tradeProposal(Model model, @RequestParam(name = "idOffer", defaultValue = "") Long idOffer, @RequestParam(name = "selected", defaultValue = "") Long selected, HttpSession session) {
		Trade trade = new Trade(selected,idOffer);
		tradeDao.save(trade);
		return "redirect:/offer?ref="+idOffer;
	}
	
}