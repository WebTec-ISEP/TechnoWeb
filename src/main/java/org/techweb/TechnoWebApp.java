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
		User user1 = new User("admin","123","mail1");
		User user2 = new User("toto","1234","mail2");
		User user3 = new User("titi","1234","mail3");
		User user4 = new User("root","1234","mail3");
		user1.setAdmin(true);
		UserRepository userDao = ctx.getBean(UserRepository.class);
		userDao.save(user1);
		userDao.save(user2);
		userDao.save(user3);
		userDao.save(user4);

		String[] equipments = new String[] {"microwave","bath"};
		String[] services = new String[] {"pet","plant"};
		String[] constraints = new String[] {"smoke","children"};
		
		HouseRepository houseDao = ctx.getBean(HouseRepository.class);
		
		House house1 = new House("beautiful house","74 rue lapatite, Paris, France","beautiful house","admin");
		House house2 = new House("regular house","2 rue de la montagne, britain, France","a great house located right near a mountain","toto");
		House house3 = new House("great house","2 CliffRow, london, UK","beautiful house","titi");
		House house4 = new House("ugly mansion","2 rue des sapins,Issy,France","beautiful house","root");
		House house5 = new House("box","2 rue des sapins,Issy,France","a box sized acomodation right in front of a mansion","root");
		houseDao.save(house1);
		houseDao.save(house2);
		houseDao.save(house3);
		houseDao.save(house4);
		houseDao.save(house5);

		Long house1Id = houseDao.findByOwner("admin").get(0).getIdHouse();
		Long house2Id = houseDao.findByOwner("toto").get(0).getIdHouse();
		Long house3Id = houseDao.findByOwner("titi").get(0).getIdHouse();
		Long house4Id = houseDao.findByOwner("root").get(0).getIdHouse();
		Long house5Id = houseDao.findByOwner("root").get(1).getIdHouse();
		OfferRepository offerDao = ctx.getBean(OfferRepository.class);
		

		Offer offer1 = new Offer(house1Id,"2022-05-27","2022-05-15",equipments,services,constraints);
		offer1.addTags(new Tag("equipments", "microwave"));
		offer1.addTags(new Tag("equipments", "bath"));
		Offer offer2 = new Offer(house2Id,"2022-02-01","2022-02-25",equipments,services,constraints);
		offer2.addTags(new Tag("services", "plant"));
		offer2.addTags(new Tag("constraints", "children"));
		Offer offer3 = new Offer(house3Id,"2022-05-10","2022-07-19", equipments,services,constraints);
		offer3.addTags(new Tag("services", "plant"));
		offer3.addTags(new Tag("constraints", "children"));
		Offer offer4 = new Offer(house4Id,"2022-02-27","2022-03-29",equipments,services,constraints);
		offer4.addTags(new Tag("equipments", "microwave"));
		offer4.addTags(new Tag("equipments", "bath"));	
		Offer offer5 = new Offer(house5Id,"2022-04-27","2022-06-25",equipments,services,constraints);
		offer5.addTags(new Tag("equipments", "microwave"));
		offer5.addTags(new Tag("services", "wifi"));
		offerDao.save(offer1);
		offerDao.save(offer2);
		offerDao.save(offer3);
		offerDao.save(offer4);
		offerDao.save(offer5);

		try {
			String path = "image";
			File image1 = new File(path+"1.jpg");
			System.out.println(image1.toString());
			byte[] byteArrayImage1 = Files.readAllBytes(image1.toPath());

			File image2 = new File(path+"2.jpg");
			byte[] byteArrayImage2 = Files.readAllBytes(image2.toPath());
			
			File imagecliff = new File("housecliff.jpg");
			byte[] byteArrayImageCliff = Files.readAllBytes(imagecliff.toPath());
			
			File image3 = new File(path+"3.jpg");
			byte[] byteArrayImage3 = Files.readAllBytes(image3.toPath());
			
			File MansionExtImageFile = new File("mansion.jpg");
			byte[] byteArrayMansionExtImage = Files.readAllBytes(MansionExtImageFile.toPath());
			File MansionbathImageFile = new File("mansionBath.jpg");
			byte[] byteArrayMansionbathImage = Files.readAllBytes(MansionbathImageFile.toPath());
			File MansionInsImageFile = new File("mansionInside.jpg");
			byte[] byteArrayMansionInsImage = Files.readAllBytes(MansionInsImageFile.toPath());
			
			File boxExtImageFile = new File("box.jpg");
			byte[] byteArrayBoxExtImage = Files.readAllBytes(boxExtImageFile.toPath());
			
			File boxInsImageFile = new File("boxInside.jpg");
			byte[] byteArrayBoxInsImage = Files.readAllBytes(boxInsImageFile.toPath());
			
			File boxmicrowaveImageFile = new File("boxmicrowaveaccomodation.jpg");
			byte[] byteArrayBoxMWImage = Files.readAllBytes(boxmicrowaveImageFile.toPath());
		
			ImageRepository imageDao = ctx.getBean(ImageRepository.class);
			imageDao.save(new Image(byteArrayImage2,offer1.getHouseId()));
			imageDao.save(new Image(byteArrayImage1,offer1.getHouseId()));
			imageDao.save(new Image(byteArrayImage3,offer1.getHouseId()));
			imageDao.save(new Image(byteArrayImageCliff,offer2.getHouseId()));
			imageDao.save(new Image(byteArrayImage1,offer3.getHouseId()));
			imageDao.save(new Image(byteArrayMansionExtImage,offer4.getHouseId()));
			imageDao.save(new Image(byteArrayMansionbathImage,offer4.getHouseId()));
			imageDao.save(new Image(byteArrayMansionInsImage,offer4.getHouseId()));
			imageDao.save(new Image(byteArrayBoxExtImage,offer5.getHouseId()));
			imageDao.save(new Image(byteArrayBoxInsImage,offer5.getHouseId()));
			imageDao.save(new Image(byteArrayBoxMWImage,offer5.getHouseId()));

		} catch(Exception e) {
			e.printStackTrace();
		}
		MessageRepository messageDao = ctx.getBean(MessageRepository.class);
		messageDao.save(new Message(user1.getName(),user2.getName(),123,"hello Mr toto, is your house close to public transport ?"));
		messageDao.save(new Message(user1.getName(),user3.getName(),123,"hello Mr titi, is your house still availible for trade ?"));
		messageDao.save(new Message(user2.getName(),user1.getName(),124,"yes, the metro is 5 min to the north"));
		messageDao.save(new Message(user3.getName(),user1.getName(),124,"of course Mr admin"));
		messageDao.save(new Message(user3.getName(),user1.getName(),125,"but i received multiples offers by now, you might want to make an offer soon if you are interested"));
		messageDao.save(new Message(user3.getName(),user1.getName(),126,"best regards."));
		messageDao.save(new Message(user1.getName(),user3.getName(),127,"ok, i'll get to it as soon as i can"));
		messageDao.save(new Message(user1.getName(),user2.getName(),128,"ok thanks."));
		messageDao.save(new Message(user2.getName(),user3.getName(),129,"hi, when your offer say no children, does it mean i can't bring mine or that there is not any comming with the house ?"));
		messageDao.save(new Message(user3.getName(),user2.getName(),130,"it means you can't bring your childrens..."));
		messageDao.save(new Message(user2.getName(),user3.getName(),131,"ok that make sense"));
		messageDao.save(new Message(user1.getName(),user4.getName(),132,"hello Mr root,i'm letting you know that box on the side of the road does not match our housing requirement, your offer will be remove in the near future"));
		messageDao.save(new Message(user1.getName(),user3.getName(),133,"bye"));
		messageDao.save(new Message(user3.getName(),user1.getName(),134,"bye"));
		System.out.println(
				"66%9mbUm9A6Z$X5X5$$Ia4a3$PO@qw8S6%9EhGONWBgRBWQBgQB&&OOGqmqGKmqK8K8UK8qhAKqKK0mmpdUd0O@NN@D8UG8b0GGK&BHWDbbKGq\n"+
				"XXXZw6wPAkXXa#eeV#VIIa4e#5w6wGMpmKM&WWRHgBWWgQg@Mdbdqpbpbd8p0UpUUGG0Um%6h9wb9q0qmdd8ddO&NNMGb966PS%Am@RWWDp0MO\n"+
				"Xk$55Zkk5$e#44aI43#eaI55Xk5kZE%%9Ah96KEEAwp8Phkkk$X$kkX5kX$X5Sk5k5k5$5kZZ$$5kX5X6k$%9$Z6m@MGPE9ASP9SMQWgHWRHN&\n"+
				"$5kX5Z55k4e434#3IaXkwk55$kXZk$ZZkZXk$5X5$$XX$5Zk$XZ$Z5$kZ$XZk$XZZ$ZX55kZk5X$$5$XXXXk$kXXZOM0wEhw8bODQNM@QBBQgQ\n"+
				"&OA6$k$XI3ee#5X$SKmA9k5kZ$e}[#5eci]jv]csZIvtkyiclIyi7YZZZ1771Z7[{z][{f1]xTfv[]5$X7xukXX5Z8Mmd0OM@@NDWB@NRRBRRQ\n"+
				"WRB@MK5Z#4IXwAK0GqO&WAZX5urfv/4kX>I$:[|/e;1<eiikn<x#cY$$vv$k([+iLs>ixy//{fv*{#F5v=a+yZkZ$bOMMMMOOM&NNDD@DRWBWg\n"+
				"WggQQBOPwSh0ppO&@QBHWPXZ2#+i=+?4$;aX;Vv1ii[v/x(7iTYr{1553v}]vV#o$X=Jkkv(LY}1z/Vi+r{<;YkZk8MMMMO&&D&D@N&D&DHRHg\n"+
				"NBQQ&WWgQDMDQBQRBRQWWw5kZeV4V3#XXXX5Xk5$ZZX5Zk55kk5kkk$kZkZ$Xk5Z5k$Z$5$$k$Z5kkZa43aI#$5k5GOMOOM@&NNNNNN&D&gRQH\n"+
				"&RBDGqDWQRggRQQQBgDMG95XZXX5$5ZZXk555XXXZkXZ$kPZ$ZZ5kZZ$k$ZXkXZk5k5ZXXXXXZXkkZ$$$$$55Z5khmMOMMMODDDN@@D&&N@DBR\n"+
				"gBW@@RgWBQWBRBWR@mp6%66E%%hSSG0Km8U0q9%PS9ES9%wdO8Uh6PwXZZXZS%wwAh0mpU88Kb0UpGqd0UmbKmKpbdmbOmMOM@&D&DDND&DNDW\n"+
				"WBRQHRQQRB&OMO@gWWDKhP%69E5ZZP9PhUdh9E%Aw9%w%PPSSqOdmw6wE$9PAGGmdqbOmppGmmqG0U0KGm8KUdmGbK0GUbpmMO@NDND&&@N&ND\n"+
				"WHRgHQ@&MMOqpU0pM@WHg&GE%A5Xk$X$%wAh%P9ZZZZk$XX$$9SbOOGG80KKG8KpUb880GmKdqdq8Gp0qGKGmUqpdGddKp800OOMN&&NNDD@&@\n"+
				"W&@OMOMOMOMdbUdm8mdMNDBH@0wZ5Z5kZ9h%EXXZX5$XX55XSE8mpUOMpqbmqmG0UGmUKGbK0G8Gq8GmbMO0G0q80XT([Fjj4$mMM@DNDOOMMM\n"+
				"mKpp0dqOOMOUOmMOM8qdm0ONNHPMGGGS%UqAZZX$ZES9%qpUppdqdpmdmMb8dbmp8UKKb$8bO0MbqpOhOMPOM9M#i.- .#-#   <c0MOMMMO&D\n"+
				"dmmEKUK0qKUo8UtI2yzq7wwijZ{%CYZczwAdSSKbK08qmGbK8bKKUKMOMMbMO00mbtF%iG71M{&MvMvIF}0M/a+` .-.``  `- - _sMOMOOM@\n"+
				"AE%6EE66Sw66KEZqPJV%FowIcxnM5qNk%DEOpGbmKGbmGGGbdPaVoCyyTT[c}Y]}]{;tY7sz71hpEOmkbqZ8G=``   `  #- ` -.#`(pMONDD\n"+
				"AA%%VyL/>>kE$kkXkX4##-.~   <l5OM&&WR&&uvT2ZPSUbmpv- #- -..`  ##..- `-.    (bqm80pbpb2  .# # `.#` # `#`#.>U@ND&\n"+
				"h69v. -- `^_`YXZZI#..-#-.    _kOMOOD&x.#!>*i1%bqbs ``--#`# #  `# --   -    V0iCYT213<`-#.##  `   =T    ##)MDN&\n"+
				"A66X^ ` #-` `.1hZ;-#  - `...# aU64Zk=-  # ._edppc  #. `.#. ##.   -#`.##--.#vd=3}]%7Y   ##  -#7YjZhG]`  ##`UON@\n"+
				"%dqAf-#. #.-#`~[<. -``.    -.-:6I1(-#` `-# YR@MO# `#` `,+++<+r#;><(/)vLY1FSUAJm8UKm+  - --`-apmUUGm|#`#`.-m0OO\n"+
				"qK8qdT-  `#`#. `. .` .#`-.. -`.u$< -## -.-LOD@pt --``#`.,_~_,^!^^ :+?)|>r]s)uFooe1i## - --#=?jFyyi~. -   vb0Kb\n"+
				"8bUmmp1#-.``#-`..` --`--. .-`- ;> . ...# vE9s= # .-# ` -``-- . `# `.  --.;1iz1iCv#:      ` ;~`   #.` `  [dbGpK\n"+
				"NNOO00Uv##.-.   .- -#.-`` -.- -`- .#- `:uGbMe .- -      `-   ## ` # # .# =fmMMUUUT ..``` #+a`-  `# -# `YdGUK0p\n"+
				"UUOOMOMOL`-  . `  `#.vx``.- .. `` ``- ^#OOMOMy ` `#-`  # `# #-.# -`~+#iiYZpMOOMOG:..-  .-#Tp<-.- -# `-.-remdKd\n"+
				"bfyynXbddv` --  ##.,j0U1  .--  `-`--`~empdmmOMV   #` #?tCn3XkIVk5PmMOOMOOOMOMOOdT` `---# <hAJxTYtT:`# . . iOMM\n"+
				"bdSPIuY1Ts~  # - -)qMOOOz` . .#` ## ^Z0pU8KKbU0v -` - sO&&N&DRB&@OMUmdbm800MMMMM>   . -`#]wS%qmqbdpj+# # `#]@D\n"+
				"Ubb0Uw6P6k+ #`# .[GK8qUOO4, .  .`.`~Xbd8pbpqdq%l  -`--iT11L7xY11Lii1XPqdbmqUGqpw -``-`--~5wSAEE9Ed8S%(#    `wM\n"+
				"K0G80mmqbG9Z#3CftTYYs$Gpb05^-`#`#-.y8UdpKKGd5v#` `# #.. `# -#  `.`.)kOMGq0KUGm0T``# -## }AS65eaZkZ$5Xs ``-  kO\n"+
				"0m8p80UbGdddKph99SEAkfY11zXk+_,+>+}dbbppq8qOk> #` -  -... #.# .  --?$DOMMOUqw6AL --.#  !Z%95VVkA6PEX]!     .PM\n"+
				"8qbqd00UdGGqqb0Kqbb9wSwP952TTTs$p8qMMdU8bONQRDv- --.` .#` #.-.!,<v[2dR&EZk8Om0An.. ``.#xEPXV2fTLi?+  . - - JMO\n"+
				"bqUm0U8Kpd8pUb8Gqd8bU0qSPS%6hA5zT1LT4U@OM@BQHgOJ><**//vvvlYf2e55ZAMgWBR&#yYT1ed<# `. -` ,  :++, `- # -```(XOMO\n"+
				"bddbUGb0dmpqdK0qpbUbbGK0bbGA%hPA66w5z{}eyRBBNmwZ3eC2nj2nfsjFVn3455G@RWQWqZXPh6h]^ #.`#   `.-## ``.. ##+xXmOMMO\n"+
				"OOMUbm0ddG0qKdb0pdK8GbmpmKpG86A%S%EEP6dMNQRNU6kke5kaf11Yt1TTC2ekX%dM@RHBgO%P9S8GSL#;: --.. # .`# .!/z$EhwpOMOM\n"+
				"MMOOMOGKKKbdUKmdpKbdpdGbb8pmb06AEhAEAwP&QHBMG0qGPZojfs1711ssu58KMO@NNDWHWWbUbUGp08b8031l[cLi[[7sukmdb6dmmGOOMO\n"+
				"MMMOMOMqmKb8GK080Kbb80dUmKdGG00hSwwE9hm&QRWNMZfYTtsCyY]vv1tf2sttskpND@gWWWNbb0d80888mdUmKdqm8m0dm0mqKdUdpGOMOM\n"+
				"OOOMOMMpKdGdGGqppqmK88ppKqKmbpb8A6%6E%GWBRNO3yn#o2JF#asifVZ$astFIFnEggHBRHQOK888pmdpUdKKdKG8KGmdUKq0UUpbb8U8Kp\n"+
				"MOOMO0M8q0dpbbqKGG8GbGddd8mU0UbUK0wwmqOBQWDXzj8POMXXk53ykbE$3X0Opbo#MQQQgBB&8K8U80K8bpGGdqbqGK0UGKK8GppKb0800q\n"+
				"MOOUqqqKUUUGKmp8GK8qqdUKqd8KdqbUpqbqU0bqDRmIoJnuojIk5XFsjM0%kV24Z3FnpDQWHHRDOU8UU08mpbbdUGd0mqdpqm8GU0qphGUKUp\n"+
				"OmUGK8qGbbdKK08K0dq0b0pGGbUKmmGU8KKb805uXOmaJ1YT11x1VIY[1SXjsFujFFnZ0@R&mPQWQMqK0qKGUKUG80KGmK0Ub0pUpdd8m0bdG0\n"+
				"UqmGdm0GU8mGUGqb0GppUKq0G0bdm0KUUGppGG$#y8d4y{ivivvT4ILxudXT[x7c1yo5MNNX4AHQgMKmqK0GUUbdqGm8pq0G8pdUmqKUK0EPSw\n"+
				"GmbdGmqKUK0UUKU80dbUG8ddmm0Udp8qpUpU8MO5yOMet1xiii/15fi/{$6c|(v7TtI9MWMVn&Wg@ObKq80Kpmd8KqKGdqmmm8GqGUd8b8SAh9\n"+
				"dmb8qddbUUqmd88Kbbqp080dUpp80GqbGKG8ODWDehMZysY}v??uaXz7J$I1?)i{fC5G@&AE6QROOOG88K0pMOMOGpUmqmm0bGqmKKbbqGAE%w\n"+
				"80GqmbGG0GU8qqpGbdUdpG8UUGUGb0K800dOgBQBBMD$uyyTlYYy34Yz5kFyY][YzewMQOPb&QQMOOOOMMMMMOMUqbMqUG0mb8M@gRDOUKA%6P\n"+
				"dp0Kq8qUdUbbqUmK0pppmGKmdm0dbdqdUbMgggWQBRHOZ2zYaXZEqIV5ZMOSk3sna5MNHgRggQWDOOMOMOOdU8G0q8DWNM0O@DHRBHBQR&UPES\n"+
				"qmdq8Up8GbdbbKmKK8pUdbb80KU8KKmdGMHBWgHQQBHRM4Cf43Iuu4nnVI5k54uukbWgBWHWBRQRWRBDDMMMOMONNDNRWQNRBQ@&NNN&NOUKUd\n"+
				"8UGmpbpGp0bUdGbpG0mmmdUbbb8p8mmqOgHRHWWgRWHQOazYssfsyystyu4nszta5OQHWHWWBRWWBQWBWB@D&NOMMNMNBNgRHDOOOD@NDDMMqq\n"+
				"0qbmmddUGbdUU080qKpKb0pKK8UbG8mM@RRQRQHWWHggOkjfzf1TzznItytz4VeZmRBBBQHRQRgQWgWWWBRWDMMNNDDN&RRWDNOMRRgHBWWQWB\n"+
				"dKqKqqpqmpU0qKbqmKmm8q8p8bKpdGM@HHQQWBBQRQRRR&Mpk3C1{]}YfJjk%MDNQBHQBQHBBQQHWQWBBR&DD&ONDRgWggBHDMMORQgQHQQHRQ\n"+
				"pUG0bbm8q0m0pU8qmbqUbG8qb8m8MOODWQHgRQBBBQQR%U&WMK%5j}iTISpODQgH&OHWBQRBHQBBQWgWRR@BHBNN&&D&NWDBBHDM@gQRWQRgQQ\n"+
				"bdUG8KbmbGbUGqdK0Gq0GKbdGG8pOMM&WHgQQgWWQQH&lIokODDOMwZGO@RQRRWOmO8HRHRQRRHHggHgWQBWRRHBHBWWgRHgBBBWRBRRggWHgQ\n"+
				"bbKpKGm0qbU0G0qmd8mGbq0GdKKOOMMRgWBRWQRQBQQEi(Y1CPOMD&@@&NQHHB@M8paHHWRggRQgRBBgRQWR&RQQHWgHgRW&gBgHQWQRHRHBgR\n"+
				"dGpq8GKUUKmKq88UdUdq8qG0dKmMMMMDQBRgHgWRRQH4/,~iy35ZA$XZ9K8KOGAAeTPQBBRgQHRgWgHQ&@HWHHWWBHgRRBQgHHQHHBgBRHgWBg\n"+
				"8b8dGd8m8m8q8GpmU8KmmUMOOON&&@RQHHRWQQgRBBBV+_- !vj#3Z5ASXS2IZfvifpBRHgWBHRHggRBRBgHWWgQHWWHQBBHBRHRQRQHWQRHQg\n"+
				"q8mMM@&DQW@&OMq0qMM&NN&QHQRQgQWgBRBgHWQBBgHG*_,^` _v2VXXY{3l/v}v{eOgRWBHQRWRRQWBBWNgHBBQRBHWWRBHHBWgBgQBRBWHRg\n"+
				"OMMMDBgBQWgWWD&@NQHHQRBBgRgQHBBBHWRgHWRgRgHRY+:,^:. <j2zlr/v/?1JjaDWHRRRQHQBBQRggRBHWQWBWBWBQg&RQBBRQWgWRgQgWQ\n"+
				"MM&&NgRHRHWHQQRWHg@BBBWRWRRWQWD&N@gRRgWBBWQBP#<+++>vT11[;<*tv*rl#ZHRRRQBHgWgQWBHHRRHBQgRgggHWHHRBQRWQBgQgWRRRg\n"+
				"&&NDNggRBHN@@D@N@D&NNN@@N@DRQ@DN@&D@gQHBHHQR@v*#(}[i]l|r<;XHk#+#/3DBggQggHQQWgBRWgQgHQHQQQWRHgBWWHHQHHBWHHBRgQ\n"+
				"@&&DBRgWH&@DH@MNO@OMOMMD@NDRgW@@DD@DDRHgBRWQWJi1lv7v#<!>/SRRQPx(r;s&RgBgHWQQRRWHBHWHHQgWQQHBgQWWgWQQBBQHWgWQgW\n"+
				"@DBQBBRW&D&DDNOMOOOMMOO@NN@&@g@NN&NNWBRHBQRQg0zv[i>,-,=uO&@Q6}LYlr<TMRHWQRQgWHHHHBRQBRBRHHQWHBgWQHQBWBggBQ&HWB\n"+
				"@NQQQHQ&&NN&&@DOOMOOOOOON@NN@DgD@&RWRQQgBRBHOsYL;:  :LUN9]rvL#?iJ1=>(AWBWRWBRgHRWBHBWRWRQQBRHgBBgHgBRgWQggBRWW\n"+
				"NNgRBBR@DD&DN@NMMMMMOMOMMD&D@&&BQBWgHgQBWR&#11)!. <v$Ao1=~_*2L=|]otv#=ARWHHRBQWWRRWHRgRBgHHWHHHWQgBWWBggBQHgRQ\n"+
				"&RBWQg&@N&&NN&@OOOOM&&&@NDD@&@&NgHBQWQWgB8zTi+,-^?e9uc/++r}yAd[*;/TCYvvdQRHBRgBQBRgQWRBBWggWWgHWWRgWBgRWHRWgRW\n"+
				"NRgRgND@&N&&&NDNM@@&NNN&@D&DDNDRgRBgWHBGtcY|,!>/y31(+<<=YIXJjAMY*r#|tyLYMgHHgRRRQRHWQWBWRQHRHgQQQBRWgWgRHQWWQg\n"+
				"BWRQ@&NDN@&@&QN&@NDNDN@@@NN&@&RggWHggDzv7}r,~)ff[r+:~*TbgBU5yTa%f=<<?TCljNQWHggWgBgQWHQHBBgHHRHQQWQQWHRRQRWWgH\n"+
				"QHHR&@&@&&@DDBN@@NDNDDDN&NN@&BQWRWHBMsTY)r:=TtL(>~+rsK&@BRQGjf1oZn?++r[JsVKgBQgRWRRQgQQgBHWBRHRHWQHWRWgQWgHHRR\n"+
				"HQR@&@&@D@D&@B@N&DN@@&&@&D@NgBWgRQB041/#+>iYT|=++*nOD&N@RgHO{]JJCIai*=/ieCykWgHHgQWRRgQWRgWWHQBHHWgHRBRRWRQRRR\n"+
				"Qgg&N&D&&@&NDR&DDN@D&NNDNDgRBWQHHRKJi==<^i[(r+;|(IgNNMODNgW@vryMauSkTv((iaTLaRBgHBWHBHWWQWBBBgBgHBHHggQRBRHQWB\n"+
				"QRR&@DD@DDNNDR&@ND@&N@&WRBWQgHWH&8t((#<#cv;rr/)T40BNMMpONgW@j*Gn[fkwdo1v|YClYSgQBQQgBHWWWgBBQHWWWQHWBHRgBBWQWQ\n"+
				"gWRDNND&NN&NDQD&&DD&RgBQRBQHQQBOZlvvi/iv?#>=)|CQM@BOMd9mDgOhVyOx/iu9MXt[/?YoCXSHBWRRRRWQgBgHQBWWgRRRQBQHQBQRQW\n"+
				"HHgNND@D@&&@DR&NNBHBHRgHHHHWgBpolcTlv?|#+<><_!bRWBM88bh#4T]i||bSv]]ToZkn{|/l1IhMHBHQHHRHRQBgQgRgQggBBWBQWggQRR\n"+
				"ROMOM%dODMMMOBQHHWBQBQQBBRQROkytY1li/(r+>^^^+/qK5JzyYv/|||((veRHmsxx[YtV5fiiTsjhNWBBWWB https://asciify.me BgW\n"+
				"NmOk9hkUMMwGmWHWHBQWHHgR@DDdCsty}77iii//)i]Y1lii??rrr==/iiYJOHQgBR9fiviTIwZuyCIIAMWQQWHBWRgWQBQWQRHBRBRWQgHgQW\n"
		
		);
	}

}
