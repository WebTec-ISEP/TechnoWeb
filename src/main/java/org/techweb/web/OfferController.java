package org.techweb.web;



import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.techweb.dao.OfferRepository;
import org.techweb.entities.Offer;

@Controller
public class OfferController {
	@Autowired
	private OfferRepository offerDao;
	
	@RequestMapping(value = "/offer")
	public String delete(Model model, @RequestParam(name = "ref", defaultValue = "") Long idOffer, HttpSession session) {
		String userName = (String)session.getAttribute("name");
		if(userName == null) {
			model.addAttribute("connected", "0");
		} else {
			model.addAttribute("connected", "1");
		}
		
		
		Optional<Offer> offer = offerDao.findById(idOffer);
		if(offer.isPresent()) {
			model.addAttribute("offer", offer.get());
		}
		
		
		return "offer";
	}
	
}
