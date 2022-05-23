package org.techweb;


import java.io.File;
import java.nio.file.Files;
import java.util.Base64;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.techweb.dao.ImageRepository;
import org.techweb.dao.MessageRepository;
import org.techweb.dao.OfferRepository;
import org.techweb.dao.UserRepository;
import org.techweb.entities.Image;
import org.techweb.entities.Message;
import org.techweb.entities.Offer;
import org.techweb.entities.User;

@SpringBootApplication
public class TechnoWebApp {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(TechnoWebApp.class, args);
		User user1 = new User("root","123","mail1");
		User user2 = new User("toto","1234","mail2");
		User user3 = new User("titi","1234","mail3");
		UserRepository userDao = ctx.getBean(UserRepository.class);
		userDao.save(user1);
		userDao.save(user2);
		userDao.save(user3);
		OfferRepository offerDao = ctx.getBean(OfferRepository.class);
		String[] equipments = new String[] {"microWave","bath"};
		String[] services = new String[] {"pet","plant"};
		String[] constraints = new String[] {"smoke","children"};
		Offer offer1 = new Offer("offer1","france",1,"jolie maison", user1.getName(),equipments,services,constraints);
		Offer offer2 = new Offer("offer2","france",1,"jolie maison", user2.getName(),equipments,services,constraints);
		Offer offer3 = new Offer("offer3","france",1,"jolie maison", user3.getName(),equipments,services,constraints);
		offerDao.save(offer1);
		offerDao.save(offer2);
		offerDao.save(offer3);
		offerDao.findAll().forEach(o->System.out.println(o.getName()));
		
		try {
			String path = "C:\\Users\\mdedr\\Desktop\\imageTechWeb\\image";
			File image1 = new File(path+"1.jpg");
			byte[] byteArrayImage1 = Files.readAllBytes(image1.toPath());
			
			File image2 = new File(path+"2.jpg");
			byte[] byteArrayImage2 = Files.readAllBytes(image2.toPath());
			
			File image3 = new File(path+"3.jpg");
			byte[] byteArrayImage3 = Files.readAllBytes(image3.toPath());
					
			ImageRepository imageDao = ctx.getBean(ImageRepository.class);
			imageDao.save(new Image(byteArrayImage2,offer1.getIdOffer()));
			imageDao.save(new Image(byteArrayImage1,offer1.getIdOffer()));
			imageDao.save(new Image(byteArrayImage3,offer1.getIdOffer()));
			imageDao.save(new Image(byteArrayImage1,offer2.getIdOffer()));
			imageDao.save(new Image(byteArrayImage1,offer3.getIdOffer()));
		} catch(Exception e) {
			e.printStackTrace();
		}
		MessageRepository messageDao = ctx.getBean(MessageRepository.class);
		messageDao.save(new Message(user1.getName(),user2.getName(),123,"hello world"));
		messageDao.save(new Message(user1.getName(),user3.getName(),123,"hello world"));
		messageDao.save(new Message(user2.getName(),user1.getName(),124,"hello world 2"));
		messageDao.save(new Message(user3.getName(),user1.getName(),124,"hello world 2"));
		messageDao.save(new Message(user3.getName(),user1.getName(),125,"hello world 3"));
		messageDao.save(new Message(user3.getName(),user1.getName(),126,"hello world 4"));
		messageDao.save(new Message(user3.getName(),user1.getName(),127,"hello world 5"));
		messageDao.save(new Message(user3.getName(),user1.getName(),128,"hello world 6"));
		messageDao.save(new Message(user3.getName(),user1.getName(),129,"hello world 7"));
		messageDao.save(new Message(user3.getName(),user1.getName(),130,"hello world 8"));
		messageDao.save(new Message(user3.getName(),user1.getName(),131,"hello world 9"));
		messageDao.save(new Message(user3.getName(),user1.getName(),132,"hello world, what's up bro, i want to send you a very long message!"));
		messageDao.save(new Message(user1.getName(),user3.getName(),133,"bye"));
		messageDao.save(new Message(user3.getName(),user1.getName(),134,"bye"));
	}

}
