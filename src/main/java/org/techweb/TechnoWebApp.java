package org.techweb;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.techweb.dao.MessageRepository;
import org.techweb.dao.OfferRepository;
import org.techweb.dao.UserRepository;
import org.techweb.entities.Message;
import org.techweb.entities.Offer;
import org.techweb.entities.User;

@SpringBootApplication
public class TechnoWebApp {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(TechnoWebApp.class, args);
		User user1 = new User("root","123");
		User user2 = new User("toto","1234");
		UserRepository userDao = ctx.getBean(UserRepository.class);
		userDao.save(user1);
		userDao.save(user2);
		OfferRepository offerDao = ctx.getBean(OfferRepository.class);
		offerDao.save(new Offer("offer1",new String[] {"i1","i2","i3"},"france",1,"jolie maison", user1.getName()));
		offerDao.findAll().forEach(o->System.out.println(o.getName()));
		
		MessageRepository messageDao = ctx.getBean(MessageRepository.class);
		messageDao.save(new Message(user1.getName(),user2.getName(),123,"hello world"));
	}

}
