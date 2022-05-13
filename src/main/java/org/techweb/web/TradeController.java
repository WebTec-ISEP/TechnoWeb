package org.techweb.web;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.techweb.dao.OfferRepository;
import org.techweb.dao.TradeRepository;
import org.techweb.entities.Offer;
import org.techweb.entities.Trade;
import org.techweb.entities.User;

@Controller
public class TradeController {
	@Autowired
	private TradeRepository tradeDao;
	@Autowired
	private OfferRepository offerDao;
	
	@RequestMapping(value = "/trade")
	public String trade(Model model, @RequestParam(name = "ref", defaultValue = "") Long idOffer, HttpSession session) {
		Optional<Offer> offer = offerDao.findById(idOffer);
		if(offer.isPresent()) {
			model.addAttribute("offer", offer.get());
		}
		String userName = (String)session.getAttribute("name");
		List<Offer> offers = offerDao.findByOwner(userName);
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