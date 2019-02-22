/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.25-0ubuntu0.16.04.2 : Database - socNetwork
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`socNetwork` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `socNetwork`;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `userType` enum('USER','ADMIN') NOT NULL,
  `user_img` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`surname`,`email`,`password`,`userType`,`user_img`) values (7,'roza','roza','roza','roza','USER','1550833318030_52556950_2200565099979901_8511680463960539136_o.jpg'),(8,'admin','admin','admin','admin','USER','1550833339435_51221341_1012222692321439_8264313351555776512_o.jpg'),(9,'test','test','test','test','USER','1550833339435_51221341_1012222692321439_8264313351555776512_o.jpg'),(11,'rozchka','roza','roza','roza','USER','1550852792380_48323976_2284295648261553_1128876933359075328_n.jpg'),(12,'Rozul','roza','roza','roza','USER','1550852950681_1.jpg');

/*Table structure for table `userMessage` */

DROP TABLE IF EXISTS `userMessage`;

CREATE TABLE `userMessage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `userName` varchar(255) NOT NULL,
  `friendId` int(11) NOT NULL,
  `friendName` varchar(255) NOT NULL,
  `sms` text,
  `smsDate` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `userMessage` */

insert  into `userMessage`(`id`,`userId`,`userName`,`friendId`,`friendName`,`sms`,`smsDate`) values (3,7,'roza',8,'admin','test','2019-02-22 15:29:49'),(4,8,'admin',7,'roza','testul','2019-02-22 15:30:39'),(5,9,'test',8,'admin','barev','2019-02-21 16:28:05'),(7,8,'admin',9,'test','Barev Vonc es?','2019-02-22 10:56:04');

/*Table structure for table `user_request` */

DROP TABLE IF EXISTS `user_request`;

CREATE TABLE `user_request` (
  `user_id` int(11) NOT NULL,
  `friend_id` int(11) NOT NULL,
  `request` enum('CANCEL','ACCEPT','SEND') NOT NULL,
  PRIMARY KEY (`user_id`,`friend_id`),
  CONSTRAINT `user_request_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user_request` */

insert  into `user_request`(`user_id`,`friend_id`,`request`) values (7,8,'ACCEPT'),(7,9,'ACCEPT'),(8,7,'ACCEPT'),(8,9,'ACCEPT'),(8,10,'ACCEPT'),(9,8,'ACCEPT');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
