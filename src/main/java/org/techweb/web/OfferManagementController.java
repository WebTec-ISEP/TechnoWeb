package org.techweb.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.techweb.dao.ImageRepository;
import org.techweb.dao.OfferRepository;
import org.techweb.entities.Image;
import org.techweb.entities.Offer;
import org.techweb.entities.Tag;

@Controller
public class OfferManagementController {
	
	@Autowired
	private ImageRepository imageDao;
	@Autowired
	private OfferRepository offerDao;
	
	@RequestMapping(value = "/addOffer")
	public String add(Model model) {
		return ("offerManagement");
	}

	@RequestMapping(value = "/addOffer/submit")
	public String add(Model model,@RequestParam(name = "offerName", defaultValue = "") String offerName,
			@RequestParam(name = "offerLocation", defaultValue = "") String offerLocation,
			@RequestParam(name = "offerDuration", defaultValue = "") Long offerDuration,
			@RequestParam(name = "offerDescription", defaultValue = "") String offerDescription,
			@RequestParam(name = "offerImages") MultipartFile[] files,
			HttpServletRequest request,
			HttpSession session) {
		if (!(offerName.equals(""))) {
			String[] equipments = request.getParameterValues("equipments");
			String[] services = request.getParameterValues("services");
			String[] constraints = request.getParameterValues("constraints");
			String owner = (String)session.getAttribute("name");
			Offer offer = new Offer();
			offer.setName(offerName);
			offer.setLocation(offerLocation);
			offer.setDuration(offerDuration);
			offer.setDescription(offerDescription);
			offer.setOwner(owner);
			offer.setEquipments(equipments);
			offer.setServices(services);
			offer.setConstraints(constraints);
			//equip
			for(String e : equipments) {
				offer.addTags(new Tag("equipments",e));
			}
			//serv
			for(String s : services) {
				offer.addTags(new Tag("services",s));
			}
			//Constraints
			for(String c : constraints) {
				offer.addTags(new Tag("constraints",c));
			}
			
			
			offerDao.save(offer);
			
			for(MultipartFile file:files) {
				Image image;
				try {
					image = new Image(file.getBytes(),offerDao.findByOwnerAndName(owner, offerName));
					imageDao.save(image);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		return ("offerManagement");
	}
	
	@RequestMapping(value = "/edit")
	public String edit(Model model, @RequestParam(name = "ref", defaultValue = "") Long idOffer, 
			@RequestParam(name = "offerName", defaultValue = "") String offerName,
			@RequestParam(name = "offerLocation", defaultValue = "") String offerLocation,
			@RequestParam(name = "offerDuration", defaultValue = "") Long offerDuration,
			@RequestParam(name = "offerDescription", defaultValue = "") String offerDescription) {
		
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
			
			List<Image> images = imageDao.findByOfferId(idOffer);
			List<String> imagesBase64String = new ArrayList<String>();
			for(Image image:images) {
				byte[] imageInBytes = image.getImage();
				System.out.println(Arrays.toString(imageInBytes));
				String base64String = Base64.getEncoder().encodeToString(imageInBytes);
				System.out.println(base64String);
				imagesBase64String.add(base64String);
			}
		}
		
		if (!(offerName.equals(""))) {
			Offer offer = offerDao.getById(idOffer);
			offer.setName(offerName);
			offer.setLocation(offerLocation);
			offer.setDuration(offerDuration);
			offer.setDescription(offerDescription);
			offerDao.save(offer);
		}
		return("offerManagement");
	}

}
