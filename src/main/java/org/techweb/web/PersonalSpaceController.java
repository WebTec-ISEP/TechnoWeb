package org.techweb.web;

import java.util.List;

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
	public String personalSpace(Model model, @RequestParam(name = "name", defaultValue = "") String owner) {
		List<Offer> offers = offerDao.findByOwner(owner);
		model.addAttribute("offers", offers);
		return("personalSpace");
	}
	
	@RequestMapping(value = "/delete")
	public String delete(Model model, @RequestParam(name = "name", defaultValue = "") String owner, @RequestParam(name = "ref", defaultValue = "") Long offerId) {
		offerDao.deleteById(offerId);
		List<Offer> offers = offerDao.findByOwner(owner);
		model.addAttribute("offers", offers);
		return("personalSpace");
	}
}
