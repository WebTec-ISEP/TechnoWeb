package org.techweb.web;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.techweb.dao.ImageRepository;
import org.techweb.dao.OfferRepository;
import org.techweb.dao.UserRepository;
import org.techweb.entities.Image;
import org.techweb.entities.Offer;
import org.techweb.entities.User;

@Controller
public class ProfilController {
	@Autowired
	private UserRepository userDao;
	@Autowired
	private OfferRepository offerDao;
	@Autowired
	private ImageRepository imageDao;
	
	@RequestMapping(value = "/profil")
	public String profil(Model model, @RequestParam(name = "name", defaultValue = "") String name,@RequestParam(name = "ref", defaultValue = "") Long idOffer, HttpSession session) {
		String userName = (String)session.getAttribute("name");
		if(userName == null) {
			model.addAttribute("connected", "0");
		} else {
			model.addAttribute("connected", "1");
		}
		User user = userDao.findByName(name);
		model.addAttribute("user", user);
		model.addAttribute("idOffer", idOffer);
		
		List<Offer> offers = offerDao.findByOwnerAndValidation(user.getName(),false);
		List<String> imagesBase64String = new ArrayList();
		for(Offer offer:offers) {
			Image image = imageDao.findByOfferId(offer.getIdOffer()).get(0);
			String base64String = Base64.getEncoder().encodeToString(image.getImage());
			imagesBase64String.add(base64String);
		}
		model.addAttribute("offers", offers);
		model.addAttribute("images", imagesBase64String);
		
		return "profil";
	}
}
