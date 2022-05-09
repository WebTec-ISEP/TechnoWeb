package org.techweb.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.techweb.dao.ImageRepository;
import org.techweb.dao.OfferRepository;
import org.techweb.entities.Image;
import org.techweb.entities.Offer;

@Controller
public class OfferManagementController {
	
	@Autowired
	private ImageRepository imageDao;
	@Autowired
	private OfferRepository offerDao;


	@RequestMapping(value = "/addOffer")
	public String add(Model model,@RequestParam(name = "offerName", defaultValue = "") String offerName,
			@RequestParam(name = "offerLocation", defaultValue = "") String offerLocation,
			@RequestParam(name = "offerDuration", defaultValue = "") Long offerDuration,
			@RequestParam(name = "offerDescription", defaultValue = "") String offerDescription,
			HttpSession session) {
		if (!(offerName.equals(""))) {
			Offer offer = new Offer();
			offer.setName(offerName);
			offer.setLocation(offerLocation);
			offer.setDuration(offerDuration);
			offer.setDescription(offerDescription);


			offer.setOwner((String)session.getAttribute("name"));
			offerDao.save(offer);
			
		}
		return ("offerManagement");
	}
	
	@RequestMapping(value = "/addOffer/submit",method= RequestMethod.POST)
	public ResponseEntity<FileUploadResponse> add(/*Model model,@RequestParam(name = "offerName", defaultValue = "") String offerName,*/
			@RequestParam(name = "offerLocation", defaultValue = "") String offerLocation,/*
			@RequestParam(name = "offerDuration", defaultValue = "") Long offerDuration,
			@RequestParam(name = "offerDescription", defaultValue = "") String offerDescription,*/
			@RequestParam(name = "offerImages") MultipartFile[] images,
			HttpSession session) {

//		if (!(offerName.equals(""))) {
//			Offer offer = new Offer();
//			offer.setName(offerName);
//			offer.setLocation(offerLocation);
//			offer.setDuration(offerDuration);
//			offer.setDescription(offerDescription);


//			offer.setOwner((String)session.getAttribute("name"));
//			offerDao.save(offer);
//			try {


	            List<String> fileNames = new ArrayList<>();
	            
//	            long parentId = offer.getIdOffer();
	            // read and write the file to the local folder
	            Arrays.asList(images).stream().forEach(file -> {
	                byte[] bytes = new byte[0];
	                try {
	                	Image image = new Image(file.getInputStream().readAllBytes(),(long)1);
	                	System.out.println("i am existing");
	                	imageDao.save(image);
	                } catch (IOException e) {
//
	                }
	            });

	            return ResponseEntity.status(HttpStatus.OK)
	                    .body(new FileUploadResponse("Files uploaded successfully: " + fileNames));

//	        } catch (Exception e) {
//	            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
//	                    .body(new FileUploadResponse("Exception to upload files!"));
//	        }
		}

	
	@RequestMapping(value = "/edit")
	public String edit(Model model, @RequestParam(name = "ref", defaultValue = "") Long idOffer, 
			@RequestParam(name = "offerName", defaultValue = "") String offerName,
			@RequestParam(name = "offerLocation", defaultValue = "") String offerLocation,
			@RequestParam(name = "offerDuration", defaultValue = "") Long offerDuration,
			@RequestParam(name = "offerDescription", defaultValue = "") String offerDescription) {
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
