package org.techweb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.techweb.dao.OfferRepository;
import org.techweb.entities.Offer;

@Controller
public class OfferManagementController {
	@Autowired
	private OfferRepository offerDao;
	
	@RequestMapping(value = "/add")
	public String add(Model model,@RequestParam(name = "name", defaultValue = "") String name, 
			@RequestParam(name = "offerName", defaultValue = "") String offerName,
			@RequestParam(name = "offerLocation", defaultValue = "") String offerLocation,
			@RequestParam(name = "offerDuration", defaultValue = "") Long offerDuration,
			@RequestParam(name = "offerDescription", defaultValue = "") String offerDescription) {
		if (!(offerName.equals(""))) {
			Offer offer = new Offer();
			offer.setName(offerName);
			offer.setLocation(offerLocation);
			offer.setDuration(offerDuration);
			offer.setDescription(offerDescription);
			offer.setImages(new String[] {});
			offer.setOwner(name);
			offerDao.save(offer);
		}
		return ("offerManagement");
	}
	
	@RequestMapping(value = "/edit")
	public String edit(Model model, @RequestParam(name = "ref", defaultValue = "") Long idOffer, 
			@RequestParam(name = "offerName", defaultValue = "") String offerName,
			@RequestParam(name = "offerLocation", defaultValue = "") String offerLocation,
			@RequestParam(name = "offerDuration", defaultValue = "") Long offerDuration,
			@RequestParam(name = "offerDescription", defaultValue = "") String offerDescription) {
		Offer offer = offerDao.getById(idOffer);
		offer.setName(offerName);
		offer.setLocation(offerLocation);
		offer.setDuration(offerDuration);
		offer.setDescription(offerDescription);
		offerDao.save(offer);
		return("personalSpace");
	}

}
