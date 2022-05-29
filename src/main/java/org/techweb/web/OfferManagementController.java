package org.techweb.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Base64;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.techweb.dao.HouseRepository;
import org.techweb.dao.ImageRepository;
import org.techweb.dao.OfferRepository;
import org.techweb.entities.House;
import org.techweb.entities.Image;
import org.techweb.entities.Offer;
import org.techweb.entities.Tag;

@Controller
public class OfferManagementController {
	@Autowired
	private OfferRepository offerDao;
	@Autowired
	private HouseRepository houseDao;
	
	@RequestMapping(value = "/addOffer")
	public String add(Model model, HttpSession session) {
		List<House> houses = houseDao.findByOwner((String)session.getAttribute("name"));
		model.addAttribute("houses", houses);
		return ("offerManagement");
	}

	@RequestMapping(value = "/addOffer/submit")
	public String add(Model model,@RequestParam(name = "offerHouseId", defaultValue = "") String offerHouseId,
		@RequestParam(name = "offerBegin", defaultValue = "") String offerBegin,
		@RequestParam(name = "offerEnd", defaultValue = "") String offerEnd,
		@RequestParam(name = "offerId", defaultValue = "") String id,
			HttpServletRequest request) {
		
		String[] equipments = request.getParameterValues("equipments");
		String[] services = request.getParameterValues("services");
		String[] constraints = request.getParameterValues("constraints");
		
		Offer offer = new Offer();
		if(!id.equals("")) {
			offer = offerDao.getById(Long.valueOf(id));
		}
		offer.setHouseId(Long.valueOf(offerHouseId));
		offer.setBegin(offerBegin);
		offer.setEnd(offerEnd);
		offer.setEquipments(equipments);
		offer.setServices(services);
		offer.setConstraints(constraints);
		
		for(String e : equipments) {
			offer.addTags(new Tag("equipments",e));
		}

		for(String s : services) {
			offer.addTags(new Tag("services",s));
		}

		for(String c : constraints) {
			offer.addTags(new Tag("constraints",c));
		}
			
			
		offerDao.save(offer);
		return ("redirect:/personalSpace");
	}
	
	@RequestMapping(value = "/edit")
	public String edit(Model model, @RequestParam(name = "ref", defaultValue = "") Long idOffer, HttpSession session) {
		
		Optional<Offer> currentOffer = offerDao.findById(idOffer);
		if(currentOffer.isPresent()) {
			Offer getOffer = currentOffer.get();

			model.addAttribute("offer", getOffer);
			
			// make a string blob out of the values to be checked to then use jsp contains api
			List<String> checkList = new ArrayList<String>();
			checkList.addAll(Arrays.asList(getOffer.getEquipments()));
			checkList.addAll(Arrays.asList(getOffer.getServices()));
			checkList.addAll(Arrays.asList(getOffer.getConstraints()));
			model.addAttribute("checkList", checkList.toString());
		}
		
		List<House> houses = houseDao.findByOwner((String)session.getAttribute("name"));
		model.addAttribute("houses", houses);
		
		return("offerManagement");
	}

}
