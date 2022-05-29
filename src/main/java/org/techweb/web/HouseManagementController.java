package org.techweb.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.techweb.dao.ImageRepository;
import org.techweb.entities.House;
import org.techweb.entities.Image;
import org.techweb.entities.Offer;
import org.techweb.entities.Tag;
import org.techweb.dao.HouseRepository;

@Controller
public class HouseManagementController {
	@Autowired
	private ImageRepository imageDao;
	@Autowired
	private HouseRepository houseDao;
	
	@RequestMapping(value = "/house")
	public String house(Model model) {
		return ("houseManagement");
	}

	@RequestMapping(value = "/house/add")
	public String add(Model model,@RequestParam(name = "houseName", defaultValue = "") String name,
		@RequestParam(name = "houseLocation", defaultValue = "") String location,
		@RequestParam(name = "houseDescription", defaultValue = "") String description,
		@RequestParam(name = "houseImages") MultipartFile[] files,
		@RequestParam(name = "houseId", defaultValue = "") String id,
		HttpSession session) {

		String owner = (String)session.getAttribute("name");
			
		House house = new House();
		if(!id.equals("")) {
			Optional<House> currentHouse = houseDao.findById(Long.valueOf(id));
			if(currentHouse.isPresent()) {
				house = currentHouse.get();
			}
		}
		
		house.setName(name);
		house.setLocation(location);
		house.setDescription(description);
		house.setOwner(owner);
			
		houseDao.save(house);
			
		for(MultipartFile file:files) {
			Image image;
			try {
				image = new Image(file.getBytes(),houseDao.findIdByOwnerAndName(owner, name));
				imageDao.save(image);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ("redirect:/personalSpace");
	}
	
	@RequestMapping(value = "/house/edit")
	public String edit(Model model, @RequestParam(name = "ref", defaultValue = "") Long idHouse) {
		
		Optional<House> currentHouse = houseDao.findById(idHouse);
		if(currentHouse.isPresent()) {
			House getHouse = currentHouse.get();	
			model.addAttribute("house", getHouse);
		}
		
		return("houseManagement");
	}
	
	@RequestMapping(value = "house/delete")
	public String delete(Model model, @RequestParam(name = "ref", defaultValue = "") Long houseId,HttpSession session) {
		houseDao.deleteById(houseId);
		return("redirect:/personalSpace");
	}
}
