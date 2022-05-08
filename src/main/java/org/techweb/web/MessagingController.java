package org.techweb.web;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
		
		List<String> listOfName = messageDao.findSenders(userName);
		listOfName.addAll(messageDao.findRecipients(userName));
		Set<String> setOfName = new HashSet<String>(listOfName);
		List<String> contacts = new ArrayList<String>(setOfName);
		
		Map<String,List<Message>> messages = new HashMap();
		for(String contact:contacts) {
			List<Message> receivedMessages = messageDao.findMessageWithSenderAndRecipient(contact, userName);
			List<Message> sentMessages = messageDao.findMessageWithSenderAndRecipient(userName, contact);
			List<Message> messagesList = new ArrayList();
			messagesList.addAll(receivedMessages);
			messagesList.addAll(sentMessages);
			messagesList = messagesList.stream().sorted(Comparator.comparingLong(Message::getTimeStamp)).collect(Collectors.toList());
			messages.put(contact, messagesList);
		}
		
		model.addAttribute("messages", messages);
		messages.forEach((key, value) -> System.out.println(key + ":" + value));
		return("messaging");
	}
}
