/*
SQLyog Community v13.1.5  (64 bit)
MySQL - 10.4.11-MariaDB : Database - knjizararest
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`knjizararest` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `knjizararest`;

/*Table structure for table `author` */

DROP TABLE IF EXISTS `author`;

CREATE TABLE `author` (
                          `author_id` bigint(20) NOT NULL AUTO_INCREMENT,
                          `date_of_birth` date NOT NULL,
                          `first_name` varchar(255) NOT NULL,
                          `middle_name` varchar(255) DEFAULT NULL,
                          `last_name` varchar(255) NOT NULL,
                          `description` varchar(255) DEFAULT NULL,
                          PRIMARY KEY (`author_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `author` */

insert  into `author`(`author_id`,`date_of_birth`,`first_name`,`middle_name`,`last_name`,`description`) values
(1,'1810-01-30','Fjodor','Mihajlovic','Dostojevski','Pisac iz Rusije');

/*Table structure for table `basket` */

DROP TABLE IF EXISTS `basket`;

CREATE TABLE `basket` (
                          `basket_id` bigint(20) NOT NULL AUTO_INCREMENT,
                          `user_id` bigint(20) NOT NULL,
                          PRIMARY KEY (`basket_id`),
                          KEY `fk_basket_user` (`user_id`),
                          CONSTRAINT `fk_basket_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `basket` */

/*Table structure for table `basket_entry` */

DROP TABLE IF EXISTS `basket_entry`;

CREATE TABLE `basket_entry` (
                                `entry_id` bigint(20) NOT NULL AUTO_INCREMENT,
                                `book_id` bigint(20) NOT NULL,
                                `quantity` bigint(20) NOT NULL,
                                `basket_id` bigint(20) NOT NULL,
                                `active` tinyint(1) NOT NULL,
                                PRIMARY KEY (`entry_id`),
                                KEY `fk_basketEntry_book` (`book_id`),
                                KEY `fk_basketEntry_basket` (`basket_id`),
                                CONSTRAINT `fk_basketEntry_basket` FOREIGN KEY (`basket_id`) REFERENCES `basket` (`basket_id`) ON DELETE CASCADE,
                                CONSTRAINT `fk_basketEntry_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `basket_entry` */

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
                        `book_id` bigint(20) NOT NULL AUTO_INCREMENT,
                        `ISBN` varchar(255) NOT NULL,
                        `book_name` varchar(255) NOT NULL,
                        `description` varchar(10000) DEFAULT NULL,
                        `price` double NOT NULL,
                        `stock` bigint(20) NOT NULL,
                        `language` varchar(255) NOT NULL,
                        `number_of_sold_copies` bigint(20) NOT NULL,
                        `rating` float NOT NULL,
                        PRIMARY KEY (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `book` */

insert  into `book`(`book_id`,`ISBN`,`book_name`,`description`,`price`,`stock`,`language`,`number_of_sold_copies`,`rating`) values
(1,'12143-141243','Zlocin i kazna',NULL,1500,100,'Srpski',30,10),
(2,'1234213-12434321','Braca Karamazovi 1',NULL,2000,300,'Srpski',60,10),
(3,'1234-152132','Braca Karamazovi 2',NULL,900,50,'Srpski',22,10);

/*Table structure for table `book_author` */

DROP TABLE IF EXISTS `book_author`;

CREATE TABLE `book_author` (
                               `book_id` bigint(20) NOT NULL,
                               `author_id` bigint(20) NOT NULL,
                               PRIMARY KEY (`book_id`,`author_id`),
                               KEY `fk_bookAuthor_author` (`author_id`),
                               CONSTRAINT `fk_bookAuthor_author` FOREIGN KEY (`author_id`) REFERENCES `author` (`author_id`) ON DELETE CASCADE,
                               CONSTRAINT `fk_bookAuthor_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `book_author` */

insert  into `book_author`(`book_id`,`author_id`) values
(1,1),
(2,1);

/*Table structure for table `book_genre` */

DROP TABLE IF EXISTS `book_genre`;

CREATE TABLE `book_genre` (
                              `genre_id` bigint(20) NOT NULL,
                              `book_id` bigint(20) NOT NULL,
                              PRIMARY KEY (`genre_id`,`book_id`),
                              KEY `fk_bookGenre_book` (`book_id`),
                              CONSTRAINT `fk_bookGenre_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE CASCADE,
                              CONSTRAINT `fk_bookGenre_genre` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`genre_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `book_genre` */

insert  into `book_genre`(`genre_id`,`book_id`) values
(1,1),
(1,2),
(1,3);

/*Table structure for table `bookimage` */

DROP TABLE IF EXISTS `bookimage`;

CREATE TABLE `bookimage` (
                             `image_id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `image_encoding` varchar(100) NOT NULL,
                             `image_url` varchar(200) DEFAULT NULL,
                             `book_id` bigint(20) NOT NULL,
                             PRIMARY KEY (`image_id`),
                             KEY `fk_bookimage_book` (`book_id`),
                             CONSTRAINT `fk_bookimage_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `bookimage` */

insert  into `bookimage`(`image_id`,`image_encoding`,`image_url`,`book_id`) values
(1,'','https://www.laguna.rs/_img/korice/4175/zlocin_i_kazna-fjodor_mihailovic_dostojevski_v.jpg',1),
(4,'','https://www.delfi.rs/_img/artikli/2017/02/zlocin_i_kazna_vv.jpg',1);

/*Table structure for table `city` */

DROP TABLE IF EXISTS `city`;

CREATE TABLE `city` (
                        `city_id` bigint(20) NOT NULL AUTO_INCREMENT,
                        `city_name` varchar(255) NOT NULL,
                        `postal_code` varchar(255) NOT NULL,
                        `description` varchar(10000) DEFAULT NULL,
                        `country_id` bigint(20) NOT NULL,
                        PRIMARY KEY (`city_id`),
                        KEY `fk_city_country` (`country_id`),
                        CONSTRAINT `fk_city_country` FOREIGN KEY (`country_id`) REFERENCES `country` (`country_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `city` */

insert  into `city`(`city_id`,`city_name`,`postal_code`,`description`,`country_id`) values
(3,'Beograd','11000',NULL,9);

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
                           `comment_id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `text` varchar(10000) NOT NULL,
                           `rating` float NOT NULL,
                           `user_id` bigint(20) NOT NULL,
                           `book_id` bigint(20) NOT NULL,
                           PRIMARY KEY (`comment_id`),
                           KEY `fk_comment_user` (`user_id`),
                           KEY `fk_comment_book` (`book_id`),
                           CONSTRAINT `fk_comment_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE CASCADE,
                           CONSTRAINT `fk_comment_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `comment` */

insert  into `comment`(`comment_id`,`text`,`rating`,`user_id`,`book_id`) values
(1,'Grmi knjiga',10,1,1),
(2,'Grmi knjiga',10,1,2),
(3,'Grmi knjiga',10,1,3);

/*Table structure for table `country` */

DROP TABLE IF EXISTS `country`;

CREATE TABLE `country` (
                           `country_id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `country_name` varchar(255) NOT NULL,
                           `country_name_short` varbinary(255) NOT NULL,
                           `description` varchar(10000) DEFAULT NULL,
                           PRIMARY KEY (`country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

/*Data for the table `country` */

insert  into `country`(`country_id`,`country_name`,`country_name_short`,`description`) values
(9,'Srbija','SRB',NULL);

/*Table structure for table `genre` */

DROP TABLE IF EXISTS `genre`;

CREATE TABLE `genre` (
                         `genre_id` bigint(20) NOT NULL AUTO_INCREMENT,
                         `genre_name` varchar(255) NOT NULL,
                         `description` varchar(10000) DEFAULT NULL,
                         PRIMARY KEY (`genre_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `genre` */

insert  into `genre`(`genre_id`,`genre_name`,`description`) values
(1,'Realizam',NULL);

/*Table structure for table `reservation` */

DROP TABLE IF EXISTS `reservation`;

CREATE TABLE `reservation` (
                               `reservation_id` bigint(20) NOT NULL AUTO_INCREMENT,
                               `status` varchar(255) NOT NULL,
                               `entry_id` bigint(20) NOT NULL,
                               PRIMARY KEY (`reservation_id`),
                               UNIQUE KEY `UNIQUE_ENTRY_ID` (`entry_id`),
                               CONSTRAINT `fk_reservation_basketEntry` FOREIGN KEY (`entry_id`) REFERENCES `basket_entry` (`entry_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `reservation` */

/*Table structure for table `store` */

DROP TABLE IF EXISTS `store`;

CREATE TABLE `store` (
                         `store_id` bigint(20) NOT NULL AUTO_INCREMENT,
                         `store_name` varchar(255) NOT NULL,
                         `description` varchar(10000) DEFAULT NULL,
                         `address` varchar(255) NOT NULL,
                         `city_id` bigint(20) NOT NULL,
                         `image_id` bigint(20) DEFAULT NULL,
                         PRIMARY KEY (`store_id`),
                         KEY `fk_store_image` (`image_id`),
                         KEY `fk_store_city` (`city_id`),
                         CONSTRAINT `fk_store_city` FOREIGN KEY (`city_id`) REFERENCES `city` (`city_id`) ON DELETE CASCADE,
                         CONSTRAINT `fk_store_image` FOREIGN KEY (`image_id`) REFERENCES `storeimage` (`image_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `store` */

/*Table structure for table `storeimage` */

DROP TABLE IF EXISTS `storeimage`;

CREATE TABLE `storeimage` (
                              `image_id` bigint(20) NOT NULL AUTO_INCREMENT,
                              `image_encoding` varchar(100) NOT NULL,
                              `image_url` varchar(200) DEFAULT NULL,
                              `store_id` bigint(20) NOT NULL,
                              PRIMARY KEY (`image_id`),
                              KEY `fk_storeImage_sotre` (`store_id`),
                              CONSTRAINT `fk_storeImage_sotre` FOREIGN KEY (`store_id`) REFERENCES `store` (`store_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `storeimage` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
                        `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
                        `username` varchar(255) NOT NULL,
                        `password` varchar(255) NOT NULL,
                        `name` varchar(255) NOT NULL,
                        `last_name` varchar(255) NOT NULL,
                        `email` varchar(255) NOT NULL,
                        `phone` varchar(255) NOT NULL,
                        `address` varchar(255) NOT NULL,
                        `city_id` bigint(20) NOT NULL,
                        `date_of_birth` date NOT NULL,
                        PRIMARY KEY (`user_id`),
                        UNIQUE KEY `UNIQUE_USERNAME` (`username`),
                        KEY `fk_user_city` (`city_id`),
                        CONSTRAINT `fk_user_city` FOREIGN KEY (`city_id`) REFERENCES `city` (`city_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `user` */

insert  into `user`(`user_id`,`username`,`password`,`name`,`last_name`,`email`,`phone`,`address`,`city_id`,`date_of_birth`) values
(1,'pera','{noop}pera','Pera','Peric','pera@gmail.com','0641234567','Pere Velimirovica 10',3,'1991-02-28');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
