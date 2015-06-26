CREATE DATABASE  IF NOT EXISTS `mystudydb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mystudydb`;
-- MySQL dump 10.13  Distrib 5.5.43, for debian-linux-gnu (i686)
--
-- Host: 127.0.0.1    Database: mystudydb
-- ------------------------------------------------------
-- Server version	5.5.43-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questions` (
  `idQ` int(11) NOT NULL AUTO_INCREMENT,
  `qText` varchar(50) NOT NULL,
  `qTopicId` int(11) NOT NULL,
  `qCorrectAnswer` varchar(45) NOT NULL,
  `qAnswer2` varchar(45) DEFAULT NULL,
  `qAnswer3` varchar(45) DEFAULT NULL,
  `qAnswer4` varchar(45) DEFAULT NULL,
  `qAnswer5` varchar(45) DEFAULT NULL,
  `qTutorId` int(11) NOT NULL,
  `qLanguage` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`idQ`),
  KEY `fk_questions_tutors1_idx` (`qTutorId`),
  KEY `fk_questions_topics1_idx` (`qTopicId`),
  CONSTRAINT `fk_questions_topics1` FOREIGN KEY (`qTopicId`) REFERENCES `topics` (`idtopics`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_questions_tutors1` FOREIGN KEY (`qTutorId`) REFERENCES `tutors` (`idtutors`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (1,'2+2 equals?',1,'4','5','5.5','3','2',1,NULL),(2,'Zn это?',2,'Цинк','Цирконий','Калий','Сера',NULL,1,NULL),(3,'Sqrt of 100',3,'10','5','1','0','100',1,'en'),(5,'Тестовый вопрос',2,'Бывает как обычно','mi-mi-mi','нет ничего','Show must go on',NULL,1,'ru'),(6,'Интеграл это?',1,'Операция обратная дифференцированнию','Такой значок','Кто здесь?','Множество функций','креатив',1,'ru'),(7,'Силитра это',2,'Соль','Щелочь','Кислота','Органическое вещество','',2,'ru');
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `results`
--

DROP TABLE IF EXISTS `results`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `results` (
  `idEntity` int(11) NOT NULL AUTO_INCREMENT,
  `StudId` int(11) NOT NULL,
  `TopicId` int(11) DEFAULT NULL,
  `Mark` decimal(2,0) DEFAULT NULL,
  PRIMARY KEY (`idEntity`),
  KEY `fk_results_students1_idx` (`StudId`),
  KEY `fk_results_topics1_idx` (`TopicId`),
  CONSTRAINT `fk_results_students1` FOREIGN KEY (`StudId`) REFERENCES `students` (`idstudents`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_results_topics1` FOREIGN KEY (`TopicId`) REFERENCES `topics` (`idtopics`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `results`
--

LOCK TABLES `results` WRITE;
/*!40000 ALTER TABLE `results` DISABLE KEYS */;
INSERT INTO `results` VALUES (1,3,1,5),(2,3,3,5),(11,3,1,2),(12,3,2,2),(13,4,1,2),(14,3,1,1),(15,3,2,0),(16,3,1,0),(17,3,1,1),(18,3,1,2),(19,3,2,2),(20,5,1,2),(21,3,2,0),(22,6,1,2),(23,6,2,2),(24,6,3,1);
/*!40000 ALTER TABLE `results` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `students` (
  `idstudents` int(11) NOT NULL AUTO_INCREMENT,
  `studName` varchar(45) NOT NULL,
  `studSurname` varchar(45) DEFAULT NULL,
  `studLogin` varchar(45) NOT NULL,
  `studPassword` varchar(45) DEFAULT NULL,
  `email` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`idstudents`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (1,'Ivan','Ivanov','iivanov','test',NULL),(3,'James','Brown','james','JAMES','james@brown.com'),(4,'Vanya','Сидоренко','student','test','i@i.ua'),(5,'Galya','','galya','galya','galya@i.ua'),(6,'test','test','test','test','ste');
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topics`
--

DROP TABLE IF EXISTS `topics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topics` (
  `idtopics` int(11) NOT NULL AUTO_INCREMENT,
  `topicName` varchar(45) NOT NULL,
  `topicDesc` varchar(45) DEFAULT NULL,
  `topicLanguage` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`idtopics`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topics`
--

LOCK TABLES `topics` WRITE;
/*!40000 ALTER TABLE `topics` DISABLE KEYS */;
INSERT INTO `topics` VALUES (1,'Алгебра','Теория чисел',NULL),(2,'Химия','',NULL),(3,'Math','Mathematics studies','en');
/*!40000 ALTER TABLE `topics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tutors`
--

DROP TABLE IF EXISTS `tutors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tutors` (
  `idtutors` int(11) NOT NULL AUTO_INCREMENT,
  `tName` varchar(45) NOT NULL,
  `tSurname` varchar(45) DEFAULT NULL,
  `tLogin` varchar(45) NOT NULL,
  `tPassword` varchar(45) DEFAULT NULL,
  `email` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`idtutors`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tutors`
--

LOCK TABLES `tutors` WRITE;
/*!40000 ALTER TABLE `tutors` DISABLE KEYS */;
INSERT INTO `tutors` VALUES (1,'Илья','Сидоров','isidorov','test','test@test.ua'),(2,'Alex','Тест','alex','test','art@i.ua'),(3,'dd','dd','dd','dd','dd');
/*!40000 ALTER TABLE `tutors` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-06-26 14:47:19
