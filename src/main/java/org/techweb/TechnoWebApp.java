package org.techweb;


import java.io.File;
import java.nio.file.Files;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.techweb.dao.HouseRepository;
import org.techweb.dao.ImageRepository;
import org.techweb.dao.MessageRepository;
import org.techweb.dao.OfferRepository;
import org.techweb.dao.UserRepository;
import org.techweb.entities.House;
import org.techweb.entities.Image;
import org.techweb.entities.Message;
import org.techweb.entities.Offer;
import org.techweb.entities.Tag;
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

		Set<Tag> tags = new HashSet<Tag>();

		String[] equipments = new String[] {"microwave","bath"};
		String[] services = new String[] {"pet","plant"};
		String[] constraints = new String[] {"smoke","children"};
		
		HouseRepository houseDao = ctx.getBean(HouseRepository.class);
		
		House house1 = new House("house1","france","beautiful house","root");
		House house2 = new House("house2","france","beautiful house","toto");
		House house3 = new House("house3","france","beautiful house","titi");
		House house4 = new House("house4","france","beautiful house","root");
		houseDao.save(house1);
		houseDao.save(house2);
		houseDao.save(house3);
		houseDao.save(house4);

		Long house1Id = houseDao.findByOwner("root").get(0).getIdHouse();
		Long house2Id = houseDao.findByOwner("root").get(1).getIdHouse();
		Long house3Id = houseDao.findByOwner("titi").get(0).getIdHouse();
		Long house4Id = houseDao.findByOwner("toto").get(0).getIdHouse();
		OfferRepository offerDao = ctx.getBean(OfferRepository.class);
		

		Offer offer1 = new Offer(house1Id,"2022-05-27","2022-05-29",equipments,services,constraints);
		offer1.addTags(new Tag("equipments", "microwave"));
		offer1.addTags(new Tag("equipments", "bath"));
		Offer offer2 = new Offer(house2Id,"2022-05-27","2022-05-29",equipments,services,constraints);
		offer2.addTags(new Tag("services", "plant"));
		offer2.addTags(new Tag("constraints", "children"));
		Offer offer3 = new Offer(house3Id,"2022-05-27","2022-05-29", equipments,services,constraints);
		offer3.addTags(new Tag("services", "plant"));
		offer3.addTags(new Tag("constraints", "children"));
		Offer offer4 = new Offer(house4Id,"2022-05-27","2022-05-29",equipments,services,constraints);
		offer4.addTags(new Tag("equipments", "microwave"));
		offer4.addTags(new Tag("equipments", "bath"));	
		offerDao.save(offer1);
		offerDao.save(offer2);
		offerDao.save(offer3);
		offerDao.save(offer4);

		try {
			String path = "image";
			File image1 = new File(path+"1.jpg");
			System.out.println(image1.toString());
			byte[] byteArrayImage1 = Files.readAllBytes(image1.toPath());

			File image2 = new File(path+"2.jpg");
			byte[] byteArrayImage2 = Files.readAllBytes(image2.toPath());

			File image3 = new File(path+"3.jpg");
			byte[] byteArrayImage3 = Files.readAllBytes(image3.toPath());

			ImageRepository imageDao = ctx.getBean(ImageRepository.class);
			imageDao.save(new Image(byteArrayImage2,offer1.getHouseId()));
			imageDao.save(new Image(byteArrayImage1,offer1.getHouseId()));
			imageDao.save(new Image(byteArrayImage3,offer1.getHouseId()));
			imageDao.save(new Image(byteArrayImage1,offer2.getHouseId()));
			imageDao.save(new Image(byteArrayImage1,offer3.getHouseId()));
			imageDao.save(new Image(byteArrayImage1,offer4.getHouseId()));
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
