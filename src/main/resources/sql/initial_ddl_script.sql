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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `author` */

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
  PRIMARY KEY (`entry_id`),
  KEY `fk_basketEntry_book` (`book_id`),
  KEY `fk_basketEntry_basket` (`basket_id`),
  CONSTRAINT `fk_basketEntry_basket` FOREIGN KEY (`basket_id`) REFERENCES `basket` (`basket_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_basketEntry_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `book` */

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

/*Table structure for table `bookimage` */

DROP TABLE IF EXISTS `bookimage`;

CREATE TABLE `bookimage` (
  `image_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `image_encoding` varchar(10000) NOT NULL,
  `image_url` varchar(1000) DEFAULT NULL,
  `book_id` bigint(20) NOT NULL,
  PRIMARY KEY (`image_id`),
  KEY `fk_bookimage_book` (`book_id`),
  CONSTRAINT `fk_bookimage_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `bookimage` */

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `city` */

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `comment_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `text` varchar(10000) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `book_id` bigint(20) NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `fk_comment_user` (`user_id`),
  KEY `fk_comment_book` (`book_id`),
  CONSTRAINT `fk_comment_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_comment_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `comment` */

/*Table structure for table `country` */

DROP TABLE IF EXISTS `country`;

CREATE TABLE `country` (
  `country_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `country_name` varchar(255) NOT NULL,
  `country_name_short` varbinary(255) NOT NULL,
  `description` varchar(10000) DEFAULT NULL,
  PRIMARY KEY (`country_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `country` */

/*Table structure for table `genre` */

DROP TABLE IF EXISTS `genre`;

CREATE TABLE `genre` (
  `genre_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `genre_name` varchar(255) NOT NULL,
  `description` varchar(10000) DEFAULT NULL,
  PRIMARY KEY (`genre_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `genre` */

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
  `image_encoding` varchar(10000) NOT NULL,
  `image_url` varchar(1000) DEFAULT NULL,
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
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UNIQUE_USERNAME` (`username`),
  KEY `fk_user_city` (`city_id`),
  CONSTRAINT `fk_user_city` FOREIGN KEY (`city_id`) REFERENCES `city` (`city_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `user` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
