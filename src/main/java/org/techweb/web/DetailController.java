package org.techweb.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.techweb.dao.OfferRepository;
import org.techweb.dao.TradeRepository;
import org.techweb.entities.Offer;
import org.techweb.entities.Trade;

@Controller
public class DetailController {
	@Autowired
	private OfferRepository offerDao;
	@Autowired
	private TradeRepository tradeDao;
	
	@RequestMapping(value = "/detail")
	public String detail(Model model, @RequestParam(name = "ref", defaultValue = "") Long idOffer) {
		Optional<Offer> offer = offerDao.findById(idOffer);
		if(offer.isPresent()) {
			model.addAttribute("offer", offer.get());
			List<Trade> tradesProposal = tradeDao.findTradeProposal(idOffer);
			List<Offer> offersProposal = new ArrayList();
			for(Trade trade:tradesProposal) {
				offersProposal.add(offerDao.getById(trade.getSenderOffer()));
			}
			model.addAttribute("offersProposal", offersProposal);
		}
		return "detail";
	}
	

}
