package org.techweb.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.techweb.dao.HouseRepository;
import org.techweb.dao.ImageRepository;
import org.techweb.dao.OfferRepository;
import org.techweb.dao.TagRepository;
import org.techweb.entities.House;
import org.techweb.entities.Image;
import org.techweb.entities.Offer;

@Controller
public class HomeController {
	@Autowired
	private	HouseRepository houseDao;
	@Autowired
	private OfferRepository offerDao;
	@Autowired
	private ImageRepository imageDao;
	@Autowired
	private TagRepository tagDao;

	@RequestMapping(value = "/home")
	public String home(
			Model model,
			@RequestParam(name = "motCle", defaultValue = "") String mc,
			HttpSession session,
			HttpServletRequest request) {
		List<String> tags = new ArrayList<String>();
		String userName = (String)session.getAttribute("name");
		
		String[] equipments = request.getParameterValues("equipments");

		if(equipments!=null && equipments.length !=0 ) {
			System.out.println(Arrays.toString(equipments));
			Collections.addAll(tags,equipments);
		}
		String[] services = request.getParameterValues("services");

		if(services!=null && services.length !=0){
			System.out.println(Arrays.toString(services));
			Collections.addAll(tags,services);
		}
		String[] constraints = request.getParameterValues("constraints");

		if(constraints!=null && constraints.length !=0) {
			System.out.println(Arrays.toString(constraints));
			Collections.addAll(tags,constraints);
		}

		if(userName == null) {
			model.addAttribute("connected", "0");
		} else {
			model.addAttribute("connected", "1");
		}
		
		List<Offer> offers = new ArrayList<Offer>();
		List<House> houses = new ArrayList<House>();
		
		houses = houseDao.findAll();
		
		if(!mc.equals("")) {
			houses = houseDao.findByName("%" + mc + "%");
			for(House house:houses) {
				offers.addAll(offerDao.findByHouseId(house.getIdHouse()));
			}
		} else {
			for(House house:houses) {
				offers.addAll(offerDao.findByHouseId(house.getIdHouse()));
			}
		}

		if(tags.size()>0){
			List<Offer> transitionList = new ArrayList<Offer>();
			List<Offer> machingTagOffers = removeOffersNotMatchingAllTags(tagDao.findOffersMatchingTags(tags), tags.size());
			for(Offer machingTagOffer:machingTagOffers) {
				for(Offer offer:offers) {
					if(machingTagOffer.getIdOffer()==offer.getIdOffer()) {
						transitionList.add(offer);
					}
				}
			}
			offers = transitionList;
		}
		
		List<String> imagesBase64String = new ArrayList<String>();
		for(Offer offer:offers) {
			Image image = imageDao.findByHouseId(offer.getHouseId()).get(0);
			String base64String = Base64.getEncoder().encodeToString(image.getImage());
			imagesBase64String.add(base64String);
		}
		model.addAttribute("images", imagesBase64String);
		model.addAttribute("houses", houses);
		model.addAttribute("offers", offers);
		model.addAttribute("motC", mc);
		return("home");
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		request.setAttribute("connected", "0");
		return "redirect:/home"; 
	}

	public static ArrayList<Offer> removeOffersNotMatchingAllTags(List<Offer> offers,int i)
	{
		Map<Offer, Long> counts = 
				offers.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
		for(Offer offerKey : counts.keySet()) {
			if(counts.get(offerKey)<i) {
				counts.remove(offerKey);
			}
		}
		return new ArrayList<Offer>(counts.keySet());
	}

}
