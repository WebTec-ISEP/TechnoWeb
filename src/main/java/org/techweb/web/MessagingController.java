package org.techweb.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.techweb.dao.MessageRepository;
import org.techweb.entities.Message;

@Controller
public class MessagingController {
	@Autowired
	private MessageRepository messageDao;
	
	@RequestMapping(value = "/messaging")
	public String messaging(Model model, HttpSession session) {
		String userName = (String)session.getAttribute("name");
		List<Message> messages = messageDao.findByUser(userName);
		model.addAttribute("messages", messages);
		List<String> listOfName = messageDao.findSenders(userName);
		listOfName.addAll(messageDao.findRecipients(userName));
		Set<String> setOfName = new HashSet<String>(listOfName);
		List<String> contacts = new ArrayList<String>(setOfName);
		model.addAttribute("contacts", contacts);
		return("messaging");
	}
}
