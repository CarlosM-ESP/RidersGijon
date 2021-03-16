CREATE DATABASE  IF NOT EXISTS `ridersgijon` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ridersgijon`;
-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ridersgijon
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido` (
  `id_pedido` int NOT NULL AUTO_INCREMENT,
  `comentarios` varchar(255) DEFAULT NULL,
  `dir_destinatario` varchar(255) DEFAULT NULL,
  `dir_remitente` varchar(255) DEFAULT NULL,
  `fecha_pedido` date DEFAULT NULL,
  `fecha_entregado` date DEFAULT NULL,
  `nombre_destinatario` varchar(255) DEFAULT NULL,
  `nombre_remitente` varchar(255) DEFAULT NULL,
  `status` int NOT NULL,
  `telefono_destinatario` varchar(255) DEFAULT NULL,
  `telefono_remitente` varchar(255) DEFAULT NULL,
  `cliente_id_user` int DEFAULT NULL,
  `rider_id_user` int DEFAULT NULL,
  PRIMARY KEY (`id_pedido`),
  KEY `FKlu0gu2dlpnf1le9uwjae7er7a` (`cliente_id_user`),
  KEY `FK2s2burxhqvijf2mfw73eoqjw2` (`rider_id_user`),
  CONSTRAINT `FK2s2burxhqvijf2mfw73eoqjw2` FOREIGN KEY (`rider_id_user`) REFERENCES `user` (`id_user`),
  CONSTRAINT `FKlu0gu2dlpnf1le9uwjae7er7a` FOREIGN KEY (`cliente_id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` VALUES (1,'Prueba Pedido 1','C/ Uría, nº 13, 4ºF','C/ Muñeca cañí, nº 3','2021-01-11','2021-01-12','Ana Menéndez','Pedro Perez',0,'984779988','985445588',1,2),(2,'Prueba Pedido 2','C/ Main, nº 23, 5ºA','C/ Pelota, nº 13','2021-01-12','2021-01-13','Juan Muñoz','Pablo Suarez',1,'984669988','985555588',2,3),(3,'Prueba Pedido 3','C/ Principado, nº 8, 1ºA','C/ Principal, nº 21','2020-01-14','2021-01-14','Eva Pisto','Clara Alonso',2,'984559988','985665588',3,1),(4,'Prueba Pedido 3','C/ Principado, nº 8, 1ºA','C/ Principal, nº 21','2020-01-14','2021-01-14','Eva Pisto','Clara Alonso1',2,'984559988','985665588',18,35),(5,'Prueba Pedido 3','C/ Principado, nº 8, 1ºA','C/ Principal, nº 21','2020-01-14','2021-01-14','Eva Pisto','Clara Alonso2',2,'984559988','985665588',18,35),(6,'Prueba Pedido 3','C/ Principado, nº 8, 1ºA','C/ Principal, nº 21','2020-01-14','2021-01-14','Eva Pisto','Clara Alonso3',2,'984559988','985665588',18,35),(7,'Prueba Pedido 3','C/ Principado, nº 8, 1ºA','C/ Principal, nº 21','2020-01-14','2021-01-14','Eva Pisto','Clara Alonso4',2,'984559988','985665588',18,35),(8,'Prueba Pedido 3','C/ Principado, nº 8, 1ºA','C/ Principal, nº 21','2020-01-14','2021-01-14','Eva Pisto','Clara Alonso5',2,'984559988','985665588',18,35),(9,'Prueba Pedido 3','C/ Principado, nº 8, 1ºA','C/ Principal, nº 21','2020-01-14','2021-01-14','Eva Pisto','Clara Alonso6',2,'984559988','985665588',18,35),(10,'Prueba Pedido 2','C/ Main, nº 23, 5ºA','C/ Pelota, nº 13','2021-01-12','2021-01-13','Juan Muñoz','Pablo1',1,'984669988','985555588',19,36),(11,'Prueba Pedido 2','C/ Main, nº 23, 5ºA','C/ Pelota, nº 13','2021-01-12','2021-01-13','Juan Muñoz','Pablo2',1,'984669988','985555588',19,36),(12,'Prueba Pedido 2','C/ Main, nº 23, 5ºA','C/ Pelota, nº 13','2021-01-12','2021-01-13','Juan Muñoz','Pablo3',1,'984669988','985555588',19,36),(13,'Prueba Pedido 2','C/ Main, nº 23, 5ºA','C/ Pelota, nº 13','2021-01-12','2021-01-13','Juan Muñoz','Pablo4',1,'984669988','985555588',19,36),(14,'Prueba Pedido 2','C/ Main, nº 23, 5ºA','C/ Pelota, nº 13','2021-01-12','2021-01-13','Juan Muñoz','Pablo5',1,'984669988','985555588',19,36),(15,'Prueba Pedido 2','C/ Main, nº 23, 5ºA','C/ Pelota, nº 13','2021-01-12','2021-01-13','Juan Muñoz','Pablo6',1,'984669988','985555588',19,36),(16,'Prueba Pedido 1','C/ Uría, nº 13, 4ºF','C/ Muñeca cañí, nº 3','2021-01-11','2021-01-12','Ana Menéndez','Pedro1',0,'984779988','985445588',20,37),(17,'Prueba Pedido 1','C/ Uría, nº 13, 4ºF','C/ Muñeca cañí, nº 3','2021-01-11','2021-01-12','Ana Menéndez','Pedro2',0,'984779988','985445588',20,37),(18,'Prueba Pedido 1','C/ Uría, nº 13, 4ºF','C/ Muñeca cañí, nº 3','2021-01-11','2021-01-12','Ana Menéndez','Pedro3',0,'984779988','985445588',20,37),(19,'Prueba Pedido 1','C/ Uría, nº 13, 4ºF','C/ Muñeca cañí, nº 3','2021-01-11','2021-01-12','Ana Menéndez','Pedro4',0,'984779988','985445588',20,37),(20,'Prueba Pedido 1','C/ Uría, nº 13, 4ºF','C/ Muñeca cañí, nº 3','2021-01-11','2021-01-12','Ana Menéndez','Pedro5',0,'984779988','985445588',20,37),(21,'Prueba Pedido 1','C/ Uría, nº 13, 4ºF','C/ Muñeca cañí, nº 3','2021-01-11','2021-01-12','Ana Menéndez','Pedro6',0,'984779988','985445588',20,37);
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `idrol` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idrol`),
  UNIQUE KEY `UK_43kr6s7bts1wqfv43f7jd87kp` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_CLIENT'),(3,'ROLE_RIDER');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id_user` int NOT NULL AUTO_INCREMENT,
  `apellido1` varchar(255) DEFAULT NULL,
  `apellido2` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_active` bit(1) NOT NULL,
  `matricula` varchar(255) DEFAULT NULL,
  `nick` varchar(255) DEFAULT NULL,
  `nif` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `user_type` varchar(255) DEFAULT NULL,
  `vehiculo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Menendez','Martinez','C/ Luna, nº 23, 3º izda','carlosm1@carlosm.com',_binary '','','CarlosM1','09444888N','Carlos','$2a$10$NuiHJOc8HVG6yDMU8Jx5euLKpi9varb55/vYFu21uhIj9YcqIsngm','666555444','ADMIN',''),(2,'Menendez','Martinez','C/ Luna, nº 23, 3º izda','carlosm2@carlosm.com',_binary '','','CarlosM2','09444888N','Carlos','$2a$10$P/OnWCy.4kU5G7Sgo5gWpO8iZTdqpfoPaQ7ggGuPctFNF26GQW88i','666555444','ADMIN',''),(3,'Menendez','Martinez','C/ Luna, nº 23, 3º izda','carlosm3@carlosm.com',_binary '','','CarlosM3','09444888N','Carlos','$2a$10$YlvqbdXS5V7dc6dxESrHserlpRB6NCjiheKlrA9zTNfUwvbGNLarW','666555444','ADMIN',''),(4,'Menendez','Martinez','C/ Luna, nº 23, 3º izda','1234',_binary '','','1234','09444888N','Carlos','$2a$10$AGxj8wUxfFdxO1yhJUq5xe7XZx7v74S2aUdQZQ/h16hCGbx83EIFe','666555444','ADMIN',''),(5,'Menendez','Martinez','C/ Luna, nº 23, 3º izda','carlosm4@carlosm.com',_binary '','','CarlosM4','09444888N','Carlos','$2a$10$yMuFAiMdEW2Gzw98KGDC9./9Q8R3UIcszCzdDlsW.kgB8nZ7Swboy','666555444','ADMIN',''),(6,'Menendez','Martinez','C/ Luna, nº 23, 3º izda','carlosm5@carlosm.com',_binary '','','CarlosM5','09444888N','Carlos','$2a$10$sesrLxIc7TphSHgd/8yjoup2DqM2B9JTyhM4ZqKUSGZuBPAk8nGQ2','666555444','ADMIN',''),(7,'Menendez','Martinez','C/ Luna, nº 23, 3º izda','carlosm6@carlosm.com',_binary '','','CarlosM6','09444888N','Carlos','$2a$10$aXzwrslGro/NUoqRTdC/IuxUz4Yelz7bD2mJl611rveMb2/jGmxne','666555444','ADMIN',''),(8,'Menendez','Martinez','C/ Luna, nº 23, 3º izda','carlosm7@carlosm.com',_binary '','','CarlosM7','09444888N','Carlos','$2a$10$LivztQrfJ21opzdcphwliujctaOJYmR5WtArl.L81twYwueXHq1Pa','666555444','ADMIN',''),(9,'Menendez','Martinez','C/ Luna, nº 23, 3º izda','carlosm8@carlosm.com',_binary '','','CarlosM8','09444888N','Carlos','$2a$10$oIzYmacpf5RHx6DVGtsC7OkiyPIT2sH5nrtEDtPAR.In6hqGtpdNy','666555444','ADMIN',''),(10,'Menendez','Martinez','C/ Luna, nº 23, 3º izda','carlosm9@carlosm.com',_binary '','','CarlosM9','09444888N','Carlos','$2a$10$qb3KLosx1QNjBe.uCQ4ere7w3Z7uxJMg/DnOse0eU2RnIkdQktqDS','666555444','ADMIN',''),(11,'Menendez','Martinez','C/ Luna, nº 23, 3º izda','carlosm10@carlosm.com',_binary '','','CarlosM10','09444888N','Carlos','$2a$10$uhZw4PxLnrzQ4zMG90fLYOqLaCoyY.sOEdNKk.5dG4cGcBtsr5L8C','666555444','ADMIN',''),(12,'Menendez','Martinez','C/ Luna, nº 23, 3º izda','carlosm11@carlosm.com',_binary '','','CarlosM11','09444888N','Carlos','$2a$10$ct2S5LEN/5LDNydqVOfWZer3EzdBFnPZsiageyXNma06.1FlHVGsi','666555444','ADMIN',''),(13,'Menendez','Martinez','C/ Luna, nº 23, 3º izda','carlosm12@carlosm.com',_binary '','','CarlosM12','09444888N','Carlos','$2a$10$Hq3Dgd1X4DOn34L8nuuXQ.0CTdLjGLwjganniEGoEiJmAGH47cswa','666555444','ADMIN',''),(14,'Menendez','Martinez','C/ Luna, nº 23, 3º izda','carlosm13@carlosm.com',_binary '','','CarlosM13','09444888N','Carlos','$2a$10$B43BtEDN9SXROz36wkSI8OLmyG.1s2tYWl9g9Tre2onWgtkXrlOrq','666555444','ADMIN',''),(15,'Menendez','Martinez','C/ Luna, nº 23, 3º izda','carlosm14@carlosm.com',_binary '','','CarlosM14','09444888N','Carlos','$2a$10$v2Vi1vjHnMYI0b2kmHNFOedXgEcgzvW5KS.VXtOi5mXBDVb7FaeSG','666555444','ADMIN',''),(16,'Menendez','Martinez','C/ Luna, nº 23, 3º izda','carlosm15@carlosm.com',_binary '','','CarlosM15','09444888N','Carlos','$2a$10$5Mf7g2Ge8PV60r4VvHhxqulUg9GOBIY8ahzTTKdZOA6CAyThWQG0K','666555444','ADMIN',''),(17,'Menendez','Martinez','C/ Luna, nº 23, 3º izda','carlosm16@carlosm.com',_binary '','','CarlosM16','09444888N','Carlos','$2a$10$kRhJTXU0eAws65f55pmIcOy26U8sppanqCfWV9j8c2MEtoNpISAAW','666555444','ADMIN',''),(18,'Suarez','Alonso','C/ Pelota, nº 12 3º izda','4567',_binary '','','4567','09555888N','Luis','$2a$10$LYs78/UtbQYuWSXoA35vD.OCCHRFcmRLGr3Yrtt5G2HSq7FeYkZRO','777555444','CLIENT',''),(19,'Suarez','Alonso','C/ Pelota, nº 12 3º izda','luism1@luism.com',_binary '','','JuanM1','09555888N','Luis','$2a$10$Wplb4sndylPRb/I4GZmsTeewM8SZLLIHJoQhNf.FawXhRmx4ihdAK','777555444','CLIENT',''),(20,'Suarez','Alonso','C/ Pelota, nº 12 3º izda','luism2@luism.com',_binary '','','JuanM2','09555888N','Luis','$2a$10$EBA3/p3POKr3g/YcLehhUegSOM0GlF/Wyva2m5WfwDufzVcaYxvI2','777555444','CLIENT',''),(21,'Suarez','Alonso','C/ Pelota, nº 12 3º izda','luism3@luism.com',_binary '','','JuanM3','09555888N','Luis','$2a$10$TTPXC2BIjNjNGyRPFtyiJucFGpUreEY0zEIhK2vJvGUVWzUkysdFG','777555444','CLIENT',''),(22,'Suarez','Alonso','C/ Pelota, nº 12 3º izda','luism4@luism.com',_binary '','','JuanM4','09555888N','Luis','$2a$10$ls3lpIov8Z86CQvg7hkrGecUZVz.SVk579DaJXdUdUT5pNuVCh9CO','777555444','CLIENT',''),(23,'Suarez','Alonso','C/ Pelota, nº 12 3º izda','luism5@luism.com',_binary '','','JuanM5','09555888N','Luis','$2a$10$CSCCg1ykm1y1YS434JUi2epsSBuHjlvuBEcg0QnsIpajMsAZcqwr2','777555444','CLIENT',''),(24,'Suarez','Alonso','C/ Pelota, nº 12 3º izda','luism6@luism.com',_binary '','','JuanM6','09555888N','Luis','$2a$10$VKgYRh9iMeiTqKc.YPJbbON8jlHOMTt.8nTSfov7tE6AHCUBDhweS','777555444','CLIENT',''),(25,'Suarez','Alonso','C/ Pelota, nº 12 3º izda','luism7@luism.com',_binary '','','JuanM7','09555888N','Luis','$2a$10$xbZGmcZU2AArwG2YIWUrA.4wyC92lHVIK0WMDMMXR9tuXs/evI73K','777555444','CLIENT',''),(26,'Suarez','Alonso','C/ Pelota, nº 12 3º izda','luism8@luism.com',_binary '','','JuanM8','09555888N','Luis','$2a$10$5peJvMYEbf/3K6zbsss5iuenluJvVbpoLizIHeFRpfkkLjDefPa4C','777555444','CLIENT',''),(27,'Suarez','Alonso','C/ Pelota, nº 12 3º izda','luism9@luism.com',_binary '','','JuanM9','09555888N','Luis','$2a$10$YeVXVPBjcuyOKCpvnZYgKe1u.01qQI8qUvcxCtWWO33uPHnh7GgxS','777555444','CLIENT',''),(28,'Suarez','Alonso','C/ Pelota, nº 12 3º izda','luism10@luism.com',_binary '','','JuanM10','09555888N','Luis','$2a$10$V2eMtM9Un/qVfa48xxEVzOjUaxk9FWd4.T9EXHWhpZhyUeJr1nbVW','777555444','CLIENT',''),(29,'Suarez','Alonso','C/ Pelota, nº 12 3º izda','luism11@luism.com',_binary '','','JuanM11','09555888N','Luis','$2a$10$YyyOZNfLmCniP/NCLDldP.aY6fbtP0J5UegfRgleSAfyMgUThUFoq','777555444','CLIENT',''),(30,'Suarez','Alonso','C/ Pelota, nº 12 3º izda','luism12@luism.com',_binary '','','JuanM12','09555888N','Luis','$2a$10$MErcjRLAm1WwA9GxjnfXxON6l4oMFouJtvvmM/J7L2LOPL7fBjdje','777555444','CLIENT',''),(31,'Suarez','Alonso','C/ Pelota, nº 12 3º izda','luism13@luism.com',_binary '','','JuanM13','09555888N','Luis','$2a$10$MAzqsdF/IZl102eVB.k3WOLVld0yasMneW2c8FRhwNiO41IUXt1Qm','777555444','CLIENT',''),(32,'Suarez','Alonso','C/ Pelota, nº 12 3º izda','luism14@luism.com',_binary '','','JuanM14','09555888N','Luis','$2a$10$dHbIaRK4gWfBxzhbuau.4.JE6rW.5Fro5BgpOZj.xRGx.0Nkug6fC','777555444','CLIENT',''),(33,'Suarez','Alonso','C/ Pelota, nº 12 3º izda','luism15@luism.com',_binary '','','JuanM15','09555888N','Luis','$2a$10$NKtTq.J6xOmPL37A5Tz7TOj2WLTB5n4Mf3DbrHgkcYJ3XQkroV4Li','777555444','CLIENT',''),(34,'Suarez','Alonso','C/ Pelota, nº 12 3º izda','luism16@luism.com',_binary '','','JuanM16','09555888N','Luis','$2a$10$zzJKOmRXoH8bLCNsbyGGIeOMLuBGSCf3OWObs3pl5bA0vUoj67FPq','777555444','CLIENT',''),(35,'Pérez','Caso','C/ Alegría Ñoña, nº 1 1º','8900',_binary '','1234DDR','8900','10555888N','Pedro','$2a$10$pSblKkqy6gPh3pwuCg61uu6ERkt3DeyF4W2uGFmIL08f3U5gmR5iq','111222333','RIDER','COCHE'),(36,'Pérez','Caso','C/ Alegría Ñoña, nº 1 1º','pedrom1@pedrom.com',_binary '','1234DDR','PedroM1','10555888N','Pedro','$2a$10$Sbgc4zv5LMGfVHkcglH5euXFGOJCCn.Ty4TqS06CcspzUK5aEvu1y','111222333','RIDER','COCHE'),(37,'Pérez','Caso','C/ Alegría Ñoña, nº 1 1º','pedrom2@pedrom.com',_binary '','1234DDR','PedroM2','10555888N','Pedro','$2a$10$ztAQYZiiQ33OWemg4XyLPOcrQbT5ktYk9nowz/dcx4ISZTfANp5GG','111222333','RIDER','COCHE'),(38,'Pérez','Caso','C/ Alegría Ñoña, nº 1 1º','pedrom@3pedrom.com',_binary '','1234DDR','PedroM3','10555888N','Pedro','$2a$10$PsauniMvILyhDFrLc9k3v.b6XKNbw6IL2cLnzksH05qh7/yXEWMBe','111222333','RIDER','COCHE'),(39,'Pérez','Caso','C/ Alegría Ñoña, nº 1 1º','pedrom4@pedrom.com',_binary '','1234DDR','PedroM4','10555888N','Pedro','$2a$10$WTrfKOK0OsvMBngbtPVkW.7xD3jz5AcxU9HRvI7ftvs8NRfPITd6S','111222333','RIDER','COCHE'),(40,'Pérez','Caso','C/ Alegría Ñoña, nº 1 1º','pedrom5@pedrom.com',_binary '','1234DDR','PedroM5','10555888N','Pedro','$2a$10$641wYsopMuB0JaTvQQEyQ.FemLMO6VOjZJuSI89BhKrHaB5BV/uGu','111222333','RIDER','COCHE'),(41,'Pérez','Caso','C/ Alegría Ñoña, nº 1 1º','pedrom6@pedrom.com',_binary '','1234DDR','PedroM6','10555888N','Pedro','$2a$10$PT5w.Tkzn.4fTEvlkRpY.ODgEkcUdXb27zKGKpm2jV5BKFHiMxJQS','111222333','RIDER','COCHE'),(42,'Pérez','Caso','C/ Alegría Ñoña, nº 1 1º','pedrom7@pedrom.com',_binary '','1234DDR','PedroM7','10555888N','Pedro','$2a$10$mbx.0Iz8ZPNwxzm9gKenkOyq8ExC80xVvNX9OEon9k4ib0Co8WWIe','111222333','RIDER','COCHE'),(43,'Pérez','Caso','C/ Alegría Ñoña, nº 1 1º','pedrom8@pedrom.com',_binary '','1234DDR','PedroM8','10555888N','Pedro','$2a$10$yAYJVEBdwv/qqgRT7zmVfu3UiP..9KqaotMfXcZoFATcEGTPweQM.','111222333','RIDER','COCHE'),(44,'Pérez','Caso','C/ Alegría Ñoña, nº 1 1º','pedrom9@pedrom.com',_binary '','1234DDR','PedroM9','10555888N','Pedro','$2a$10$2BLHfdmpKMfM7.xw566RUeYsFfF0ciZhWRuxUMc7wjESJfUMOvpyq','111222333','RIDER','COCHE'),(45,'Pérez','Caso','C/ Alegría Ñoña, nº 1 1º','pedrom10@pedrom.com',_binary '','1234DDR','PedroM10','10555888N','Pedro','$2a$10$SXsyNA19bdSJOBsHVD.wc.NTy7mxOpb109iaWzfCHRr6bDh5vAnWO','111222333','RIDER','COCHE'),(46,'Pérez','Caso','C/ Alegría Ñoña, nº 1 1º','pedrom11@pedrom.com',_binary '','1234DDR','PedroM11','10555888N','Pedro','$2a$10$h6k.l.4HPHA3x8ldRsmNHuQUpGQWZy6EHEOwFgvOw8hKJnAYgT6ei','111222333','RIDER','COCHE'),(47,'Pérez','Caso','C/ Alegría Ñoña, nº 1 1º','pedrom12@pedrom.com',_binary '','1234DDR','PedroM12','10555888N','Pedro','$2a$10$RAPhMSIIl6SQjXVlgrUoWOgRsQqzLMacGRLc79.mNj.Y/4lb.nfsG','111222333','RIDER','COCHE'),(48,'Pérez','Caso','C/ Alegría Ñoña, nº 1 1º','pedrom13@pedrom.com',_binary '','1234DDR','PedroM13','10555888N','Pedro','$2a$10$In6X1iQnoLUsaSruRZg5Y.X5H8z.wKuvRn1rENRaL5DauqLPdKI86','111222333','RIDER','COCHE'),(49,'Pérez','Caso','C/ Alegría Ñoña, nº 1 1º','pedrom14@pedrom.com',_binary '','1234DDR','PedroM14','10555888N','Pedro','$2a$10$at.gOx9ANK7bItOkOYQPlOq/pqVAFBADTD7OlHKV1dM9oTYyd3.gC','111222333','RIDER','COCHE'),(50,'Pérez','Caso','C/ Alegría Ñoña, nº 1 1º','pedrom15@pedrom.com',_binary '','1234DDR','PedroM15','10555888N','Pedro','$2a$10$1xCoGCZwxK1XocuTPTX5p.3aIyoG9siHnzLhqyKoqKrJjrAYrHMJW','111222333','RIDER','COCHE'),(51,'Pérez','Caso','C/ Alegría Ñoña, nº 1 1º','pedrom16@pedrom.com',_binary '','1234DDR','PedroM16','10555888N','Pedro','$2a$10$9Taz1rGHKg4CZC02l.SuruafpqZey6yHL/YS90u8qC3hcyMl3pcG2','111222333','RIDER','COCHE');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userrol`
--

DROP TABLE IF EXISTS `userrol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userrol` (
  `iduserrol` int NOT NULL AUTO_INCREMENT,
  `idrol` int DEFAULT NULL,
  `id_user` int DEFAULT NULL,
  PRIMARY KEY (`iduserrol`),
  KEY `FK3enep7x3t0lx6liuy2p0fxlks` (`idrol`),
  KEY `FKdihujlq00mbv6q1m5p9km2ca3` (`id_user`),
  CONSTRAINT `FK3enep7x3t0lx6liuy2p0fxlks` FOREIGN KEY (`idrol`) REFERENCES `rol` (`idrol`),
  CONSTRAINT `FKdihujlq00mbv6q1m5p9km2ca3` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userrol`
--

LOCK TABLES `userrol` WRITE;
/*!40000 ALTER TABLE `userrol` DISABLE KEYS */;
INSERT INTO `userrol` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),(7,1,7),(8,1,8),(9,1,9),(10,1,10),(11,1,11),(12,1,12),(13,1,13),(14,1,14),(15,1,15),(16,1,16),(17,1,17),(18,2,18),(19,2,19),(20,2,20),(21,2,21),(22,2,22),(23,2,23),(24,2,24),(25,2,25),(26,2,26),(27,2,27),(28,2,28),(29,2,29),(30,2,30),(31,2,31),(32,2,32),(33,2,33),(34,2,34),(35,3,35),(36,3,36),(37,3,37),(38,3,38),(39,3,39),(40,3,40),(41,3,41),(42,3,42),(43,3,43),(44,3,44),(45,3,45),(46,3,46),(47,3,47),(48,3,48),(49,3,49),(50,3,50),(51,3,51);
/*!40000 ALTER TABLE `userrol` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-16 12:04:28
