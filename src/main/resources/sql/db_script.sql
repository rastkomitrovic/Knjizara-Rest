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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4;

/*Data for the table `author` */

insert  into `author`(`author_id`,`date_of_birth`,`first_name`,`middle_name`,`last_name`,`description`) values
(1,'1821-11-11','Fjodor','Mihajlovic','Dostojevski','Pisac iz Rusije'),
(3,'1564-04-26','Viljem','','Sekspir','Najpoznatiji engleski pisac'),
(4,'1890-09-15','Agata','','Kristi','Popularna engleski pisac'),
(5,'1965-07-31','Joanne','K','Rowling','Poznati pisac Hari Poter serije knjiga'),
(6,'1828-09-09','Lav','Nikolajevic','Tolstoj','Ruski pisac'),
(7,'1799-05-26','Aleksandar','Sergejevic','Puskin','Ruski pisac'),
(8,'1947-09-21','Stiven','Edvin','King','Americki pisac'),
(9,'1947-08-24','Paolo','Koeljo','de Souza','Brazilski pisac'),
(10,'1892-01-03','Dzon','Ronald Ruel','Tolkin','Engleski pisac'),
(11,'1892-10-09','Ivo','','Andric','Srpski Nobelovac'),
(12,'1749-08-28','Johan','Vulfgang','Gete','Nemacki pisac'),
(13,'1875-06-06','Tomas','Paul','Man','Nemacki pisac'),
(14,'1877-07-02','Herman','Karl','Hese','Svajcarski pisac'),
(15,'1891-05-15','Mihail','Afanasijevic','Bulgakov','Ruski pisac'),
(16,'1819-03-20','Nikolaj','Vasilijevic','Gogolj','Ruski pisac'),
(17,'1814-10-15','Mihail','Jurijevic','Ljermontov','Ruski pisac'),
(18,'1818-11-09','Ivan','Sergejevic','Turgenjev','Ruski pisac'),
(19,'1893-10-26','Milos','','Crnjanski','Srpski pisac'),
(20,'1921-12-29','Dobrica','','Cosic','Srpski pisac'),
(21,'1935-02-22','Danilo','','Kis','Srpski pisac'),
(22,'1930-02-04','Borislav','','Pekic','Srpski pisac'),
(23,'1910-04-26','Mehmed','Mesa','Selimovic','Srpski pisac');

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
                        `publisher_id` bigint(20) NOT NULL,
                        PRIMARY KEY (`book_id`),
                        KEY `fk_book_publisher` (`publisher_id`),
                        CONSTRAINT `fk_book_publisher` FOREIGN KEY (`publisher_id`) REFERENCES `publisher` (`publisher_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4;

/*Data for the table `book` */

insert  into `book`(`book_id`,`ISBN`,`book_name`,`description`,`price`,`stock`,`language`,`number_of_sold_copies`,`rating`,`publisher_id`) values
(1,'12143-141243','Zlocin i kazna',NULL,1500,100,'Srpski',30,10,1),
(2,'1234213-12434321','Braca Karamazovi 1',NULL,2000,300,'Srpski',60,10,2),
(3,'1234-152132','Braca Karamazovi 2',NULL,900,50,'Srpski',22,10,3),
(4,'1234432-5234124','Bedni ljudi',NULL,600,50,'Srpski',20,10,4),
(5,'542442252','Zapisi iz podzemlja',NULL,400,100,'Srpski',40,10,5),
(6,'123321132','Zli dusi 1',NULL,600,122,'Srpski',56,10,6),
(7,'2332413','Zli dusi 2',NULL,650,123,'Srpski',67,10,7),
(8,'13431413434','Dvojnik',NULL,900,240,'Srpski',120,10,8),
(9,'13321321','Zapisi iz mrtvog doma',NULL,789,100,'Srpski',40,10,1),
(10,'6789080988','Kockar',NULL,500,143,'Srpski',60,10,2),
(11,'143432545','Bele noci',NULL,700,156,'Srpski',89,10,3),
(12,'134341134341','Rat i mir',NULL,1500,123,'Srpski',40,6.4,4),
(13,'14313413431','Ana Karenjina 1',NULL,800,143,'Srpski',80,5.5,5),
(14,'14331413414','Ana Karenjina 2',NULL,1000,156,'Srpski',90,5.5,6),
(15,'15234231143','Hari Poter i kamen mudrosti',NULL,700,200,'Srpski',120,6.7,7),
(16,'134314314413','Hari Poter i dvorana tajni',NULL,800,200,'Srpski',140,6.7,8),
(17,'35213341134','Hari Poter i zatvorenik iz Azkabana',NULL,900,400,'Srpski',200,8.3,1),
(18,'235134134134','Hari Poter i vatreni pehar',NULL,900,100,'Srpski',34,7.4,2),
(19,'143314314314','Hari Poter i red feniksa',NULL,1000,400,'Srpski',300,8.5,3),
(20,'14134314341','Hari Poter i polukrvni princ',NULL,800,50,'Srpski',10,3.6,4),
(21,'141344311','Hari Poter i relikvije smrti',NULL,900,60,'Srpski',30,6.4,5),
(22,'124321431432','Ubistvo u Orijent ekspresu',NULL,1000,100,'Srpski',50,8.9,6),
(23,'14132413241234','Romeo i Julija',NULL,400,255,'Srpski',150,8.5,7),
(24,'12414114','Hamlet',NULL,500,143,'Srpski',100,6.7,8),
(25,'2431314341134','Evgenije Onjegin',NULL,1300,125,'Srpski',65,7.6,1),
(26,'213414241231324','Alhemicar',NULL,500,234,'Srpski',78,8.6,2),
(27,'212134142312','Gospodar prstenova: druzina prstena',NULL,700,35,'Srpski',10,7.8,3),
(28,'13123213213123','Gospodar prstenova: povratak kralja',NULL,800,45,'Srpski',20,7.5,4),
(29,'123123312','Gospodar prstenova: dve kule',NULL,750,50,'Srpski',30,8,5),
(30,'1525216121','Na Drini cuprija',NULL,850,178,'Srpski',68,9.3,6),
(31,'431314314314314','Prokleta avlija',NULL,560,100,'Srspki',46,9,7),
(32,'1413413','Travnicka hronika',NULL,500,45,'Srpski',20,8.9,8),
(33,'1414134132','Majstor i margarita',NULL,670,50,'Srpski',13,5.7,1),
(34,'678979979','Seobe',NULL,570,70,'Srpski',30,8.2,2),
(35,'213552132413','Psalm 44',NULL,700,80,'Srpski',23,7.6,3),
(36,'125513251256','Dervis i smrt',NULL,980,89,'Srpski',54,7.4,4),
(37,'135321512515','Tvrdjavav',NULL,1000,98,'Srpski',67,8.3,5);

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
(2,1),
(3,1),
(4,1),
(5,1),
(6,1),
(7,1),
(8,1),
(9,1),
(10,1),
(11,1),
(12,6),
(13,6),
(14,6),
(15,5),
(16,5),
(17,5),
(18,5),
(19,5),
(20,5),
(21,5),
(22,4),
(23,3),
(24,3),
(25,7),
(26,9),
(27,10),
(28,10),
(29,10),
(30,11),
(31,11),
(32,11),
(33,15),
(34,19),
(35,21),
(36,23),
(37,23);

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
(1,3),
(1,4),
(1,5),
(1,6),
(1,7),
(1,8),
(1,9),
(1,10),
(1,11),
(2,25),
(11,15),
(11,16),
(11,17),
(11,18),
(11,19),
(11,20),
(11,21),
(11,27),
(11,28),
(11,29),
(20,22),
(21,22),
(23,1),
(23,23),
(23,26),
(31,1),
(31,2),
(31,3),
(31,4),
(31,5),
(31,6),
(31,7),
(31,8),
(31,9),
(31,10),
(31,11),
(31,12),
(31,13),
(31,14),
(31,23),
(31,24),
(31,25),
(31,30),
(31,31),
(31,32),
(31,33),
(31,34),
(31,35),
(31,36),
(31,37),
(32,24),
(33,24),
(33,31),
(33,32),
(33,33),
(33,34),
(33,35),
(33,36),
(33,37),
(34,30),
(34,31),
(34,32),
(34,34),
(34,35),
(34,36),
(34,37);

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
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4;

/*Data for the table `bookimage` */

insert  into `bookimage`(`image_id`,`image_encoding`,`image_url`,`book_id`) values
(1,'','https://www.laguna.rs/_img/korice/4175/zlocin_i_kazna-fjodor_mihailovic_dostojevski_v.jpg',1),
(5,'','https://www.laguna.rs/_img/korice/4875/braca_karamazovi_i-fjodor_mihailovic_dostojevski_v.jpg',2),
(6,'','https://www.laguna.rs/_img/korice/4876/braca_karamazovi_ii-fjodor_mihailovic_dostojevski_v.jpg',3),
(7,'','https://www.delfi.rs/_img/artikli/2014/02/bedni_ljudi_vv.jpg',4),
(8,'','https://www.laguna.rs/_img/korice/4558/zapisi_iz_podzemlja-fjodor_mihailovic_dostojevski_v.jpg',5),
(9,'','https://www.delfi.rs/_img/artikli/2019/12/zli_dusi_1_vv.jpg',6),
(10,'','https://www.delfi.rs/_img/artikli/2019/12/zli_dusi_2_vv.jpg',7),
(11,'','https://www.delfi.rs/_img/artikli/2015/01/dvojnik_vv.jpg',8),
(13,'','https://www.knjizara.com/slika/knjiga.jpg',9),
(14,'','https://www.delfi.rs/_img/artikli/2018/06/kockar_vv.jpg',10),
(15,'','https://www.delfi.rs/_img/artikli/2015/01/bele_noci_vv-2.jpg',11),
(16,'','https://www.delfi.rs/_img/artikli/2019/01/rat_i_mir_vv.jpg',12),
(17,'','https://www.delfi.rs/_img/artikli/2020/04/ana_karenjina_vv.jpg',13),
(18,'','https://www.delfi.rs/_img/artikli/2020/04/ana_karenjina_vv.jpg',14),
(19,'','https://www.delfi.rs/_img/artikli/2019/02/hari_poter_i_kamen_mudrosti_vv.jpg',15),
(20,'','https://www.delfi.rs/_img/artikli/2017/09/hari_poter_i_dvorana_tajni_-_ilustrovano_vv.jpg',16),
(21,'','https://www.delfi.rs/_img/artikli/2019/02/hari_poter_i_zatvorenik_iz_askabana_vv.jpg',17),
(22,'','https://www.delfi.rs/_img/artikli/2019/02/hari_poter_i_vatreni_pehar_vv.jpg',18),
(23,'','https://www.delfi.rs/_img/artikli/2019/02/hari_poter_i_red_feniksa_vv.jpg',19),
(24,'','https://www.delfi.rs/_img/artikli/2019/02/hari_poter_i_polukrvni_princ_vv.jpg',20),
(25,'','https://www.delfi.rs/_img/artikli/2019/02/hari_poter_i_relikvije_smrti_vv.jpg',21),
(26,'','https://www.delfi.rs/_img/artikli/2017/10/ubistvo_u_orijent_ekspresu_vv.jpg',22),
(27,'','https://www.delfi.rs/_img/artikli/2019/12/romeo_i_julija_vv.jpg',23),
(28,'','https://www.delfi.rs/_img/artikli/knjige/44/vv/delfi_hamlet_vilijem_sekspir.jpg',24),
(29,'','https://www.delfi.rs/_img/artikli/2017/01/evgenije_onjegin_vv.jpg',25),
(30,'','https://www.delfi.rs/_img/artikli/2013/12/alhemicar_vv.jpg',26),
(31,'','https://www.delfi.rs/_img/artikli/2019/12/gospodar_prstenova_-_druzina_prstena_tvrd_povez_vv.jpg',27),
(32,'','https://www.delfi.rs/_img/artikli/2019/12/gospodar_prstenova_-_povratak_kralja_tvrd_povez_vv.jpg',28),
(33,'','https://www.delfi.rs/_img/artikli/2019/12/gospodar_prstenova_-_dve_kule_tvrd_povez_vv.jpg',29),
(34,'','https://www.delfi.rs/_img/artikli/2016/04/na_drini_cuprija_vv.jpg',30),
(35,'','https://www.delfi.rs/_img/artikli/2016/09/prokleta_avlija_vv.jpg',31),
(36,'','https://www.delfi.rs/_img/artikli/2017/04/travnicka_hronika_vv.jpg',32),
(39,'','https://www.delfi.rs/_img/artikli/2020/04/majstor_i_margarita_vv.jpg',33),
(40,'','https://www.delfi.rs/_img/artikli/2018/10/seobe_vv.jpg',34),
(41,'','https://www.delfi.rs/_img/artikli/2020/11/psalm_44_vv.jpg',35),
(42,'','https://www.delfi.rs/_img/artikli/2014/10/dervis_i_smrt_vv.jpg',36),
(43,'','https://www.delfi.rs/_img/artikli/2014/10/tvrdjava_vv.jpg',37);

/*Table structure for table `city` */

DROP TABLE IF EXISTS `city`;

CREATE TABLE `city` (
                        `city_id` bigint(20) NOT NULL AUTO_INCREMENT,
                        `city_name` varchar(255) NOT NULL,
                        `postal_code` varchar(255) NOT NULL,
                        `description` varchar(10000) DEFAULT NULL,
                        PRIMARY KEY (`city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

/*Data for the table `city` */

insert  into `city`(`city_id`,`city_name`,`postal_code`,`description`) values
(3,'Beograd','11000',NULL),
(4,'Novi Sad','21000',NULL),
(5,'Nis','18000',NULL),
(6,'Kragujevac','34000',NULL),
(7,'Kraljevo','36000',NULL),
(8,'Subotica','24000',NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4;

/*Data for the table `genre` */

insert  into `genre`(`genre_id`,`genre_name`,`description`) values
(1,'Realizam',NULL),
(2,'Romantizam',NULL),
(4,'Naturalizam',NULL),
(5,'Parnas',NULL),
(6,'Simbolizam',NULL),
(7,'Sentimentalizam',NULL),
(8,'Impresionizam',NULL),
(9,'Modernizam',NULL),
(10,'Postmodernizam',NULL),
(11,'Fantastika',NULL),
(12,'Naucna fantastika',NULL),
(13,'Istorija',NULL),
(14,'Biografija',NULL),
(15,'Autobiografija',NULL),
(16,'Enciklopedija',NULL),
(17,'Strucna literatura',NULL),
(18,'Nauka',NULL),
(19,'Horor',NULL),
(20,'Misterija',NULL),
(21,'Triler',NULL),
(22,'Akcija',NULL),
(23,'Drama',NULL),
(24,'Edukacija',NULL),
(25,'Filozofija',NULL),
(26,'Istorija',NULL),
(27,'Poezija',NULL),
(28,'Racionalizam',NULL),
(29,'Avangarda',NULL),
(31,'Klasicna Knjizevnost',NULL),
(32,'Tragedija',NULL),
(33,'Roman',NULL),
(34,'Domaci pisci',NULL);

/*Table structure for table `publisher` */

DROP TABLE IF EXISTS `publisher`;

CREATE TABLE `publisher` (
                             `publisher_id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `publisher_name` varchar(255) NOT NULL,
                             PRIMARY KEY (`publisher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

/*Data for the table `publisher` */

insert  into `publisher`(`publisher_id`,`publisher_name`) values
(1,'Dereta'),
(2,'Lento'),
(3,'NNK International'),
(4,'Laguna'),
(5,'Delfi'),
(6,'Vulkan'),
(7,'Gradac'),
(8,'Kosmos');

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
                         PRIMARY KEY (`store_id`),
                         KEY `fk_store_city` (`city_id`),
                         CONSTRAINT `fk_store_city` FOREIGN KEY (`city_id`) REFERENCES `city` (`city_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

/*Data for the table `store` */

insert  into `store`(`store_id`,`store_name`,`description`,`address`,`city_id`) values
(1,'Prodavnica Usce','Prodacniva u trznom centru Usce','Milentija Popovica 10',3),
(2,'Prodavnica Delta city','Prodavnica u trznom centru Delta city','Jurija Gagarina 50',3),
(3,'Prodavnica Subotica','Prodavnica u Subotici','Cara Dusana 15',8),
(5,'Prodavnica Novi Sad','Prodavnica u Novom Sadu','Svetozara Miletica 67',4),
(7,'Prodavnica Nis','Prodavnica u Nisu kod tvrdjave','Niska 54',5),
(9,'Prodavnica Kraljevo','Prodavnica u Kraljevu','Kraljevackih zrtava 42',7),
(10,'Prodavnica Kragujevac','Prodavnica u Kragujevcu','Kragujevacka 67',6);

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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

/*Data for the table `storeimage` */

insert  into `storeimage`(`image_id`,`image_encoding`,`image_url`,`store_id`) values
(1,'','https://www.rizzolibookstore.com/sites/rizzolibookstore.com/files/imagecache/page_top/desk.jpg',1),
(2,'','https://franchiseindia.s3.ap-south-1.amazonaws.com/uploads/content/edu/art/5bdda17e0732b.jpeg',2),
(3,'','http://pazbookbiz.com/wp-content/plugins/psThumb/timthumb.php?src=/wp-content/uploads/2017/03/111SOU08.jpg&w=522&h=282&zc=1',3),
(6,'','https://insideretail.asia/wp-content/uploads/2020/11/Hai-An-bookstore.jpg',5),
(7,'','https://insideretail.asia/wp-content/uploads/2020/11/Hai-An-bookstore-2.jpg',7),
(8,'','https://customer-service-us.com/wp-content/uploads/2020/07/online-book-store-photo.jpeg',9),
(9,'','https://www.rodneysbookstore.com/media/files/rodneysstorefront.png',10),
(10,'','https://www.firadelllibreprohibit.com/wp-content/uploads/2020/01/11-bookstores-6-three-lives-2.w710.h473.2x.jpg',1),
(11,'','https://static01.nyt.com/images/2017/05/11/t-magazine/bookstore-slide-2MCD/bookstore-slide-2MCD-superJumbo.jpg?quality=90&auto=webp',2),
(12,'','https://img.thedailybeast.com/image/upload/c_crop,d_placeholder_euli9k,h_675,w_1200,x_0,y_0/dpr_1.5/c_limit,w_1044/fl_lossy,q_auto/v1493058170/articles/2011/10/12/america-s-greatest-independent-bookst',3),
(13,'','https://citybook.pk/wp-content/uploads/2018/02/130422163934-indie-bookstores-ben-mcnally.jpg',5),
(14,'','https://www.texasobserver.org/wp-content/uploads/2020/04/bookstore-web-apr2020-flickr.png',7);

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
                        `role` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`user_id`),
                        UNIQUE KEY `UNIQUE_USERNAME` (`username`),
                        KEY `fk_user_city` (`city_id`),
                        CONSTRAINT `fk_user_city` FOREIGN KEY (`city_id`) REFERENCES `city` (`city_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `user` */

insert  into `user`(`user_id`,`username`,`password`,`name`,`last_name`,`email`,`phone`,`address`,`city_id`,`date_of_birth`,`role`) values
(2,'rastko','$2a$10$K2LeffN9msrgTRiEXrbcwOD6CkNnHtrQmynrV9lS4s/QXUUKSBv3i','Rastko','Mitrovic','rastkomitrovic@gmail.com','+381637413127','Lazarevacki drum 11',3,'1996-03-21','ADMIN'),
(3,'ogi','$2a$10$potcZuL5dy04rPmLbzfej.vZDXBMkz1PTLaI/vhhMApaQ/KDAv9zO','Ognjen','Andjelic','ognjen@gmail.com','+3815322332','Neka adresa 1',3,'1996-10-21','USER');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
