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
import org.techweb.dao.HouseRepository;
import org.techweb.dao.ImageRepository;
import org.techweb.dao.OfferRepository;
import org.techweb.entities.House;
import org.techweb.entities.Image;
import org.techweb.entities.Offer;

@Controller
public class OfferController {
	@Autowired
	private OfferRepository offerDao;
	@Autowired
	private HouseRepository houseDao;
	@Autowired
	private ImageRepository imageDao;
	
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
			Optional<House> house = houseDao.findById(offer.get().getHouseId());
			model.addAttribute("house", house.get());
			model.addAttribute("offer", offer.get());
			List<Image> images = imageDao.findByHouseId(offer.get().getHouseId());
			List<String> imagesBase64String = new ArrayList();
			for(Image image:images) {
				byte[] imageInBytes = image.getImage();
				String base64String = Base64.getEncoder().encodeToString(imageInBytes);
				System.out.println(base64String);
				imagesBase64String.add(base64String);
			}
			model.addAttribute("images", imagesBase64String);
		}
		
		
		return "offer";
	}
	
}
