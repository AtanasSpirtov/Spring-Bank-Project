-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: transactionmanager
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `transaction_amount` decimal(19,2) DEFAULT NULL,
  `transaction_time` time DEFAULT NULL,
  `transaction_type` varchar(255) DEFAULT NULL,
  `recipient_account_id` bigint DEFAULT NULL,
  `source_account_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK317ucrmhliy3ids31kpr1673o` (`recipient_account_id`),
  KEY `FK25e716ukpqahttjt6c487lrer` (`source_account_id`),
  CONSTRAINT `FK25e716ukpqahttjt6c487lrer` FOREIGN KEY (`source_account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FK317ucrmhliy3ids31kpr1673o` FOREIGN KEY (`recipient_account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,30.00,'11:01:06','Debit',2,1),(2,30.00,'11:01:06','Credit',1,2),(3,20.00,'11:01:10','Debit',2,1),(4,20.00,'11:01:10','Credit',1,2),(5,10.00,'11:01:13','Debit',2,1),(6,10.00,'11:01:13','Credit',1,2),(7,10.00,'13:47:18','Debit',2,2),(8,10.00,'13:47:18','Credit',2,2),(9,10.00,'11:49:04','Debit',2,1),(10,10.00,'11:49:04','Credit',1,2),(15,10.00,'15:09:17','Debit',4,1),(16,10.00,'15:09:17','Credit',1,4);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-08 15:49:26
