package org.techweb.web;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.techweb.dao.ImageRepository;
import org.techweb.dao.OfferRepository;
import org.techweb.dao.TradeRepository;
import org.techweb.entities.Image;
import org.techweb.entities.Offer;
import org.techweb.entities.Trade;

@Controller
public class DetailController {
	@Autowired
	private OfferRepository offerDao;
	@Autowired
	private TradeRepository tradeDao;
	@Autowired
	private ImageRepository imageDao;
	
	@RequestMapping(value = "/detail")
	public String detail(Model model, @RequestParam(name = "ref", defaultValue = "") Long idOffer, @RequestParam(name = "previous", defaultValue = "") Long previous,HttpSession session) {
		Optional<Offer> offer = offerDao.findById(idOffer);
		if(offer.isPresent()) {
			Offer getOffer = offer.get();
			model.addAttribute("offer", getOffer);
			List<Image> images = imageDao.findByOfferId(idOffer);
			List<String> imagesBase64String = new ArrayList();
			for(Image image:images) {
				byte[] imageInBytes = image.getImage();
				String base64String = Base64.getEncoder().encodeToString(imageInBytes);
				System.out.println(base64String);
				imagesBase64String.add(base64String);
			}
			model.addAttribute("images", imagesBase64String);
			String userName = (String)session.getAttribute("name");
			if(getOffer.getOwner().equals(userName)) {
				List<Trade> tradesProposal = tradeDao.findPendingTradeProposal(idOffer);
				List<Offer> offersProposal = new ArrayList();
				for(Trade trade:tradesProposal) {
					offersProposal.add(offerDao.getById(trade.getSenderOffer()));
				}
				model.addAttribute("offersProposal", offersProposal);
				
				List<Trade> tradesProposed = tradeDao.findPendingTradeProposed(idOffer);
				List<Offer> offersProposed = new ArrayList();
				for(Trade trade:tradesProposed) {
					offersProposed.add(offerDao.getById(trade.getRecipientOffer()));
				}
				model.addAttribute("offersProposed", offersProposed);
			}
			model.addAttribute("previous", previous);
		}
		return "detail";
	}
	
	@RequestMapping(value = "/respond")
	public String respond(Model model, @RequestParam(name = "accept", defaultValue = "") Long acceptId, @RequestParam(name = "refuse", defaultValue = "") Long refuseId, @RequestParam(name = "idOffer", defaultValue = "") Long idOffer, HttpSession session) {
		if(acceptId != null) {
			Trade trade = tradeDao.findBySenderAndRecipientOffers(acceptId, idOffer);
			trade.setValidate(true);
			List<Trade> canceledTrades = tradeDao.findTradesForOffer(idOffer);
			for(Trade t:canceledTrades) {
				tradeDao.delete(t);
			}
			tradeDao.save(trade);
			Offer senderOffer = offerDao.getById(acceptId);
			senderOffer.setValidate(true);
			offerDao.save(senderOffer);
			Offer recipientOffer = offerDao.getById(idOffer);
			recipientOffer.setValidate(true);
			offerDao.save(recipientOffer);
		} else if(refuseId != null) {
			Trade trade = tradeDao.findBySenderAndRecipientOffers(refuseId, idOffer);
			tradeDao.delete(trade);
		}
		return "redirect:/detail?ref="+idOffer;
	}
	

}
