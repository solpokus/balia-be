/*
SQLyog Community v12.2.4 (64 bit)
MySQL - 8.0.33 : Database - db_admin_staging
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_admin_staging` /*!40100 DEFAULT CHARACTER SET utf8mb4*/ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `db_admin_staging`;

/*Table structure for table `m_about_us` */

DROP TABLE IF EXISTS `m_about_us`;

CREATE TABLE `m_about_us` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `short_description` varchar(100) DEFAULT NULL,
  `long_description` varchar(255) DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;

/*Data for the table `m_about_us` */

/*Table structure for table `m_articles` */

DROP TABLE IF EXISTS `m_articles`;

CREATE TABLE `m_articles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `short_description` varchar(100) DEFAULT NULL,
  `long_description` varchar(255) DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;

/*Data for the table `m_articles` */

/*Table structure for table `m_categories` */

DROP TABLE IF EXISTS `m_categories`;

CREATE TABLE `m_categories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) NOT NULL,
  `parent_id` int DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ;

/*Data for the table `m_categories` */

insert  into `m_categories`(`id`,`name`,`description`,`parent_id`,`created_by`,`created_date`,`last_modified_by`,`last_modified_date`) values 
(1,'Men Fashion','Description',NULL,'System','2025-01-22 06:04:28',NULL,NULL),
(2,'Women Fashion','Description',NULL,'System','2025-01-22 06:04:30',NULL,NULL),
(3,'Accessories','Description',NULL,'System','2025-01-22 06:04:31',NULL,NULL);

/*Table structure for table `m_product` */

DROP TABLE IF EXISTS `m_product`;

CREATE TABLE `m_product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `sku` varchar(50) NOT NULL,
  `price` int NOT NULL,
  `currency` int NOT NULL,
  `stock` int NOT NULL,
  `status` int NOT NULL,
  `color` varchar(100) DEFAULT NULL,
  `size` varchar(100) DEFAULT NULL,
  `weight` varchar(100) DEFAULT NULL,
  `weight_unit` varchar(100) DEFAULT NULL,
  `dimension_width` varchar(100) DEFAULT NULL,
  `dimension_length` varchar(100) DEFAULT NULL,
  `dimension_height` varchar(100) DEFAULT NULL,
  `short_description` varchar(100) DEFAULT NULL,
  `long_description` varchar(255) DEFAULT NULL,
  `amazon_url` varchar(255) DEFAULT NULL,
  `m_categories_id` bigint NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `video` varchar(255) DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cat_id` (`m_categories_id`),
  CONSTRAINT `fk_cat_id` FOREIGN KEY (`m_categories_id`) REFERENCES `m_categories` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;

/*Data for the table `m_product` */

/*Table structure for table `m_role` */

DROP TABLE IF EXISTS `m_role`;

CREATE TABLE `m_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ;

/*Data for the table `m_role` */

insert  into `m_role`(`id`,`name`) values 
(1,'ROLE_ADMIN'),
(2,'ROLE_USER');

/*Table structure for table `m_role_user` */

DROP TABLE IF EXISTS `m_role_user`;

CREATE TABLE `m_role_user` (
  `m_user_id` bigint NOT NULL,
  `m_role_id` bigint NOT NULL,
  KEY `fk_role_id` (`m_role_id`),
  KEY `fk_user_id` (`m_user_id`),
  CONSTRAINT `fk_role_id` FOREIGN KEY (`m_role_id`) REFERENCES `m_role` (`id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`m_user_id`) REFERENCES `m_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;

/*Data for the table `m_role_user` */

insert  into `m_role_user`(`m_user_id`,`m_role_id`) values 
(1,1),
(2,2);

/*Table structure for table `m_user` */

DROP TABLE IF EXISTS `m_user`;

CREATE TABLE `m_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `login` varchar(50) NOT NULL,
  `password_hash` varchar(60) NOT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(191) DEFAULT NULL,
  `image_url` varchar(256) DEFAULT NULL,
  `activated` int NOT NULL,
  `lang_key` varchar(10) DEFAULT NULL,
  `activation_key` varchar(20) DEFAULT NULL,
  `reset_key` varchar(20) DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` timestamp NULL DEFAULT NULL,
  `reset_date` timestamp NULL DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  `mobile_number` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ;

/*Data for the table `m_user` */

insert  into `m_user`(`id`,`login`,`password_hash`,`first_name`,`last_name`,`email`,`image_url`,`activated`,`lang_key`,`activation_key`,`reset_key`,`created_by`,`created_date`,`reset_date`,`last_modified_by`,`last_modified_date`,`mobile_number`) values 
(1,'user_admin','$2a$12$U3LsjwZM8dBoja.bSHwlj.gFfC31C0pVssXTEGsA5HDDwsSTF90Hy','User','admin','','',1,NULL,NULL,NULL,'SYSTEM',NULL,NULL,NULL,NULL,NULL),
(2,'user_user','$2a$12$U3LsjwZM8dBoja.bSHwlj.gFfC31C0pVssXTEGsA5HDDwsSTF90Hy','User','User','','',1,NULL,NULL,NULL,'SYSTEM',NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `system_parameter` */

DROP TABLE IF EXISTS `system_parameter`;

CREATE TABLE `system_parameter` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `parameter_name` varchar(255) DEFAULT NULL,
  `parameter_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `parameter_name` (`parameter_name`)
) ENGINE=InnoDB AUTO_INCREMENT=229 DEFAULT CHARSET=utf8mb4 ;

/*Data for the table `system_parameter` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
