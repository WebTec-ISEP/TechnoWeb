package org.techweb.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.techweb.dao.MessageRepository;
import org.techweb.entities.Message;
import org.techweb.entities.User;

@Controller
public class MessagingController {
	@Autowired
	private MessageRepository messageDao;
	
	@RequestMapping(value = "/messaging")
	public String messaging(Model model, @RequestParam(name = "user", defaultValue = "")String u) {
		List<Message> messages = messageDao.findByUser(u);
		model.addAttribute("messages", messages);
		model.addAttribute("owner", u);
		return("messaging");
	}
}
