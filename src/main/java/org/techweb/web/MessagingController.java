package org.techweb.web;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.techweb.dao.MessageRepository;
import org.techweb.entities.Message;

@Controller
public class MessagingController {
	@Autowired
	private MessageRepository messageDao;
	
	@RequestMapping(value = "/messaging")
	public String messaging(Model model,@RequestParam(name = "message", defaultValue = "") String message, @RequestParam(name = "recipient", defaultValue = "") String recipient,@RequestParam(name = "user", defaultValue = "") String user, HttpSession session) {
		String userName = (String)session.getAttribute("name");
		if(userName == null) {
			return("redirect:/home");
		}
		
		if(!message.equals("")) {
			Date date = new Date();
			Timestamp timestamp = new Timestamp(date.getTime());
			messageDao.save(new Message(userName,recipient,timestamp.getTime(),message));
			model.addAttribute("select", recipient);
		} else if(!user.equals("")){
			model.addAttribute("select", user);
		}
		
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
		System.out.println(messages.containsKey(user));
		if(!messages.containsKey(user) && !user.equals("")) {
			messages.put(user, new ArrayList<Message>());
		}
		model.addAttribute("messages", messages);
		messages.forEach((key, value) -> System.out.println(key + ":" + value.stream().map(n -> String.valueOf(n.getContent()))
			      .collect(Collectors.joining("-", "{", "}"))));
		return("messaging");
	}
}
