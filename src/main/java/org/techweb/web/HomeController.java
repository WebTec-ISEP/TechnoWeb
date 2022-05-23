package org.techweb.web;

import java.util.ArrayList;
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
import org.techweb.dao.OfferRepository;
import org.techweb.dao.TagRepository;
import org.techweb.entities.Offer;

@Controller
public class HomeController {
	@Autowired
	private OfferRepository offerDao;
	
	@Autowired
	private TagRepository tagDao;
	
	@RequestMapping(value = "/home")
	public String home(Model model, @RequestParam(name = "motCle", defaultValue = "") String mc,@RequestParam(name = "tags", defaultValue = "") List<String> tags, HttpSession session) {
		String userName = (String)session.getAttribute("name");
		if(userName == null) {
			model.addAttribute("connected", "0");
		} else {
			model.addAttribute("connected", "1");
		}
		List<Offer> offers = new ArrayList<>();
		
		if(tags.size()>0) offers.addAll(removeOffersNotMatchingAllTags(tagDao.findOffersMatchingTags(tags), tags.size()));
		if(!mc.equals("")) offers.addAll(offerDao.findByName("%" + mc + "%")); 
		
		
		offers = removeDuplicates(offers);
		
		System.out.println(offers);
		
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
	
	public static <T> ArrayList<Offer> removeOffersNotMatchingAllTags(List<Offer> offers,int i)
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
