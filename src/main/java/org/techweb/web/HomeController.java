package org.techweb.web;

import java.util.ArrayList;
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
import org.techweb.dao.ImageRepository;
import org.techweb.dao.OfferRepository;
import org.techweb.dao.TagRepository;
import org.techweb.entities.Image;
import org.techweb.entities.Offer;

@Controller
public class HomeController {
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
			@RequestParam(name = "tags", defaultValue = "") List<String> tags,
			HttpSession session,
			HttpServletRequest request) {
		String userName = (String)session.getAttribute("name");
		String[] equipments = request.getParameterValues("equipments");
		if(equipments!=null) Collections.addAll(tags,equipments);
		String[] services = request.getParameterValues("services");
		if(equipments!=null) Collections.addAll(tags,services);
		String[] constraints = request.getParameterValues("constraints");
		if(equipments!=null) Collections.addAll(tags,constraints);
		
		if(userName == null) {
			model.addAttribute("connected", "0");
		} else {
			model.addAttribute("connected", "1");
		}
		List<Offer> offers = new ArrayList<>();
		if(mc.equals("")&&tags.size()<=0) {
			offers = offerDao.findByName("%" + mc + "%"); 
		}
		
		if(tags.size()>0) offers.addAll(removeOffersNotMatchingAllTags(tagDao.findOffersMatchingTags(tags), tags.size()));
		if(!mc.equals("")) offers.addAll(offerDao.findByName("%" + mc + "%")); 
		List<String> imagesBase64String = new ArrayList<>();
		for(Offer offer:offers) {
			Image image = imageDao.findByOfferId(offer.getIdOffer()).get(0);
			String base64String = Base64.getEncoder().encodeToString(image.getImage());
			imagesBase64String.add(base64String);
		}
		model.addAttribute("images", imagesBase64String);
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
	
	public static <T> List<T> removeDuplicates(List<T> list)
    {
        ArrayList<T> newList = new ArrayList<T>();
          for (T element : list) {
            if (!newList.contains(element)) {
  
                newList.add(element);
            }
        }
        return newList;
    }
}
