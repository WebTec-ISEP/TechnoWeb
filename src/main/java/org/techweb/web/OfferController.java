package org.techweb.web;



import java.util.Optional;

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
	public String delete(Model model, @RequestParam(name = "ref", defaultValue = "") Long idOffer) {
		Optional<Offer> offer = offerDao.findById(idOffer);
		if(offer.isPresent()) {
			model.addAttribute("offer", offer.get());
		}
		return "offer";
	}
	
}
