start transaction;
CREATE DATABASE  IF NOT EXISTS `acme-news` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `acme-news`;

create user 'acme-user'@'%' 
	identified by password '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577';

create user 'acme-manager'@'%' 
	identified by password '*FDB8CD304EB2317D10C95D797A4BD7492560F55F';


grant select, insert, update, delete 
	on `acme-news`.* to 'acme-user'@'%';

grant select, insert, update, delete, create, drop, references, index, alter, 
        create temporary tables, lock tables, create view, create routine, 
        alter routine, execute, trigger, show view
    on `acme-news`.* to 'acme-manager'@'%';
    
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: acme-news
-- ------------------------------------------------------
-- Server version	5.5.29

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
-- Table structure for table `actor`
--

DROP TABLE IF EXISTS `actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `apellidos` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `user_account` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_i7xei45auwq1f6vu25985riuh` (`user_account`),
  UNIQUE KEY `UK_awymvli3olnnumqow6wf060pa` (`email`),
  CONSTRAINT `FK_i7xei45auwq1f6vu25985riuh` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
/*!40000 ALTER TABLE `actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrador`
--

DROP TABLE IF EXISTS `administrador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrador` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `apellidos` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `user_account` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pnccftldbiin2uh8jqcclmheg` (`user_account`),
  UNIQUE KEY `UK_h121ki47maojpkmvdvqf87ybo` (`email`),
  CONSTRAINT `FK_pnccftldbiin2uh8jqcclmheg` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrador`
--

LOCK TABLES `administrador` WRITE;
/*!40000 ALTER TABLE `administrador` DISABLE KEYS */;
INSERT INTO `administrador` VALUES (1349,0,'admin1Apellidos','admin1@gmail.com','admin1Nombre',1331);
/*!40000 ALTER TABLE `administrador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agencia`
--

DROP TABLE IF EXISTS `agencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agencia` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `capacidad_disponible` int(11) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `importancia` bigint(20) DEFAULT NULL,
  `tasa` double NOT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `manager` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7fbx1ccbowqblo5n7o9kog5kd` (`manager`),
  CONSTRAINT `FK_7fbx1ccbowqblo5n7o9kog5kd` FOREIGN KEY (`manager`) REFERENCES `manager` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agencia`
--

LOCK TABLES `agencia` WRITE;
/*!40000 ALTER TABLE `agencia` DISABLE KEYS */;
INSERT INTO `agencia` VALUES (1367,0,0,'direccionAgencia1',700002315,3.2,'tituloAgencia1',1350),(1370,0,3,'direccionAgencia2',39,3.2,'tituloAgencia2',1350),(1372,0,1,'direccionAgencia3',0,4.2,'tituloAgencia3',1351);
/*!40000 ALTER TABLE `agencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agencia_eventos`
--

DROP TABLE IF EXISTS `agencia_eventos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agencia_eventos` (
  `agencia` int(11) NOT NULL,
  `eventos` int(11) NOT NULL,
  UNIQUE KEY `UK_n06q07ogkq6j8sc8fn6vv6v62` (`eventos`),
  KEY `FK_237epgesehiyyihr2l8dqhbl8` (`agencia`),
  CONSTRAINT `FK_237epgesehiyyihr2l8dqhbl8` FOREIGN KEY (`agencia`) REFERENCES `agencia` (`id`),
  CONSTRAINT `FK_n06q07ogkq6j8sc8fn6vv6v62` FOREIGN KEY (`eventos`) REFERENCES `evento` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agencia_eventos`
--

LOCK TABLES `agencia_eventos` WRITE;
/*!40000 ALTER TABLE `agencia_eventos` DISABLE KEYS */;
INSERT INTO `agencia_eventos` VALUES (1367,1368),(1367,1369),(1370,1371),(1372,1373);
/*!40000 ALTER TABLE `agencia_eventos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agencia_periodistas`
--

DROP TABLE IF EXISTS `agencia_periodistas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agencia_periodistas` (
  `agencia` int(11) NOT NULL,
  `periodistas` int(11) NOT NULL,
  UNIQUE KEY `UK_sgspsg1kx7b0v3p9j0qrm4h1r` (`periodistas`),
  KEY `FK_mmaskyssv8fo4fwhddpwkxe7j` (`agencia`),
  CONSTRAINT `FK_mmaskyssv8fo4fwhddpwkxe7j` FOREIGN KEY (`agencia`) REFERENCES `agencia` (`id`),
  CONSTRAINT `FK_sgspsg1kx7b0v3p9j0qrm4h1r` FOREIGN KEY (`periodistas`) REFERENCES `periodista` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agencia_periodistas`
--

LOCK TABLES `agencia_periodistas` WRITE;
/*!40000 ALTER TABLE `agencia_periodistas` DISABLE KEYS */;
INSERT INTO `agencia_periodistas` VALUES (1367,1355),(1367,1356),(1370,1357);
/*!40000 ALTER TABLE `agencia_periodistas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `banner`
--

DROP TABLE IF EXISTS `banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `banner` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banner`
--

LOCK TABLES `banner` WRITE;
/*!40000 ALTER TABLE `banner` DISABLE KEYS */;
INSERT INTO `banner` VALUES (1374,0,'http://www.daveallworthy.com/images/banner_3mx1m_AMS_banner.jpg'),(1375,0,'http://www.embertech.co.uk/wp-content/uploads/2013/02/header-banner.png'),(1376,0,'https://staff.brighton.ac.uk/is/media/PublishingImages/banner%20example%205.jpg');
/*!40000 ALTER TABLE `banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buscador`
--

DROP TABLE IF EXISTS `buscador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `buscador` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `categoria` int(11) DEFAULT NULL,
  `fecha_fin` datetime DEFAULT NULL,
  `fecha_inicio` datetime DEFAULT NULL,
  `palabra` varchar(255) DEFAULT NULL,
  `ultima_actualizacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buscador`
--

LOCK TABLES `buscador` WRITE;
/*!40000 ALTER TABLE `buscador` DISABLE KEYS */;
INSERT INTO `buscador` VALUES (1361,0,NULL,NULL,NULL,NULL,'2000-01-01 00:00:00'),(1362,0,NULL,NULL,NULL,NULL,'2000-01-01 00:00:00'),(1363,0,NULL,NULL,NULL,NULL,'2000-01-01 00:00:00'),(1364,0,NULL,NULL,NULL,NULL,'2000-01-01 00:00:00'),(1365,0,NULL,NULL,NULL,NULL,'2000-01-01 00:00:00'),(1366,0,NULL,NULL,NULL,NULL,'3000-01-01 00:00:00');
/*!40000 ALTER TABLE `buscador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buscador_noticias`
--

DROP TABLE IF EXISTS `buscador_noticias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `buscador_noticias` (
  `buscador` int(11) NOT NULL,
  `noticias` int(11) NOT NULL,
  KEY `FK_c8toy794yrv7f28et0oxgtp97` (`noticias`),
  KEY `FK_ejkn9dcd7701f83q91c42vxpu` (`buscador`),
  CONSTRAINT `FK_ejkn9dcd7701f83q91c42vxpu` FOREIGN KEY (`buscador`) REFERENCES `buscador` (`id`),
  CONSTRAINT `FK_c8toy794yrv7f28et0oxgtp97` FOREIGN KEY (`noticias`) REFERENCES `noticia` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buscador_noticias`
--

LOCK TABLES `buscador_noticias` WRITE;
/*!40000 ALTER TABLE `buscador_noticias` DISABLE KEYS */;
/*!40000 ALTER TABLE `buscador_noticias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comentario`
--

DROP TABLE IF EXISTS `comentario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comentario` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `evento` int(11) DEFAULT NULL,
  `informacion` int(11) DEFAULT NULL,
  `usuario` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m0fbo9e3fwpgjy3ag5p9m184f` (`evento`),
  KEY `FK_hlqy49x33ymvgjyew7q1jkldt` (`usuario`),
  CONSTRAINT `FK_hlqy49x33ymvgjyew7q1jkldt` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FK_m0fbo9e3fwpgjy3ag5p9m184f` FOREIGN KEY (`evento`) REFERENCES `evento` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comentario`
--

LOCK TABLES `comentario` WRITE;
/*!40000 ALTER TABLE `comentario` DISABLE KEYS */;
INSERT INTO `comentario` VALUES (1413,0,'menudos tolais, timofónica tenia que ser','2019-05-13','jajaja',NULL,1378,1396),(1414,0,'Deseando que llegue la fecha, MUSICA Y ALCOHOL!!!!','2019-05-13','Fiestaaaa',NULL,1368,1396),(1415,0,'descripcionComentario3','2019-05-13','tituloComentario3',NULL,1378,1398),(1416,0,'descripcionComentario4','2019-05-13','tituloComentario4',NULL,1381,1399),(1417,0,'descripcionComentario5','2019-05-13','tituloComentario5',NULL,1382,1399),(1418,0,'descripcionComentario6','2019-05-13','tituloComentario6',NULL,1382,1400),(1419,0,'descripcionComentario7','2019-05-13','tituloComentario7',NULL,1368,1400),(1420,0,'descripcionComentario8','2019-05-13','tituloComentario8',NULL,1369,1400);
/*!40000 ALTER TABLE `comentario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configurations`
--

DROP TABLE IF EXISTS `configurations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configurations` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `cache_time` int(11) DEFAULT NULL,
  `country_code` varchar(255) DEFAULT NULL,
  `english_message` varchar(255) DEFAULT NULL,
  `finder_max_result` int(11) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `spanish_message` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configurations`
--

LOCK TABLES `configurations` WRITE;
/*!40000 ALTER TABLE `configurations` DISABLE KEYS */;
INSERT INTO `configurations` VALUES (1395,0,1,'+34','Welcome to Acme News!',10,'https://i.imgur.com/JAsKMdd.jpg','¡Bienvenidos a Acme News!','Acme News Co., Inc.');
/*!40000 ALTER TABLE `configurations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configurations_spam_words`
--

DROP TABLE IF EXISTS `configurations_spam_words`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configurations_spam_words` (
  `configurations` int(11) NOT NULL,
  `spam_words` varchar(255) DEFAULT NULL,
  KEY `FK_1mwxou3h8fn5uxuwtsbhw7g1e` (`configurations`),
  CONSTRAINT `FK_1mwxou3h8fn5uxuwtsbhw7g1e` FOREIGN KEY (`configurations`) REFERENCES `configurations` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configurations_spam_words`
--

LOCK TABLES `configurations_spam_words` WRITE;
/*!40000 ALTER TABLE `configurations_spam_words` DISABLE KEYS */;
INSERT INTO `configurations_spam_words` VALUES (1395,'sex'),(1395,'viagra'),(1395,'cialis'),(1395,'one million'),(1395,'you\'ve been selected'),(1395,'Nigeria'),(1395,'sexo'),(1395,'un millón'),(1395,'ha sido seleccionado');
/*!40000 ALTER TABLE `configurations_spam_words` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evento`
--

DROP TABLE IF EXISTS `evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `evento` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `descripcion` longtext,
  `imagen` varchar(255) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `agencia` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_t2degca2t19h3wg4y2pwt32yh` (`agencia`),
  CONSTRAINT `FK_t2degca2t19h3wg4y2pwt32yh` FOREIGN KEY (`agencia`) REFERENCES `agencia` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evento`
--

LOCK TABLES `evento` WRITE;
/*!40000 ALTER TABLE `evento` DISABLE KEYS */;
INSERT INTO `evento` VALUES (1368,0,'Fiesta Salvaje en Sevilla, no te la pierdas. Los mejores DJs y las mejores gogos. Ven a la discoteca y disfruta de una noche loca.','http://tueventoenbogota.com/wp-content/uploads/2015/07/01_eventos_empresariales-1240x824.jpg','tituloEvento1','calle falsa 123','2020-01-01',1367),(1369,0,'descripcionEvento4.','http://www.myfico.com/Images/sample_overlay.gif','tituloEvento4','direccion4','2020-01-01',1367),(1371,0,'descripcionEvento2.','http://www.myfico.com/Images/sample_overlay.gif','tituloEvento2','direccion2','2020-01-01',1370),(1373,0,'descripcionEvento3.','http://www.myfico.com/Images/sample_overlay.gif','tituloEvento3','direccion3','2020-01-01',1372);
/*!40000 ALTER TABLE `evento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('domain_entity',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `informacion`
--

DROP TABLE IF EXISTS `informacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `informacion` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `descripcion` longtext,
  `imagen` varchar(255) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `informacion`
--

LOCK TABLES `informacion` WRITE;
/*!40000 ALTER TABLE `informacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `informacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `informacion_usuarios`
--

DROP TABLE IF EXISTS `informacion_usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `informacion_usuarios` (
  `informacion` int(11) NOT NULL,
  `usuarios` int(11) NOT NULL,
  KEY `FK_48o2fukrvc9ajp58ewygc9mou` (`usuarios`),
  CONSTRAINT `FK_48o2fukrvc9ajp58ewygc9mou` FOREIGN KEY (`usuarios`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `informacion_usuarios`
--

LOCK TABLES `informacion_usuarios` WRITE;
/*!40000 ALTER TABLE `informacion_usuarios` DISABLE KEYS */;
INSERT INTO `informacion_usuarios` VALUES (1368,1399),(1369,1397),(1371,1399),(1378,1396),(1379,1396),(1379,1397),(1381,1397),(1382,1399);
/*!40000 ALTER TABLE `informacion_usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manager` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `apellidos` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `user_account` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_7fkueol8lcnvrbgwmmmyvsytv` (`user_account`),
  UNIQUE KEY `UK_ch4c0h9mgdd2c5lategqkpsyi` (`email`),
  CONSTRAINT `FK_7fkueol8lcnvrbgwmmmyvsytv` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES (1350,0,'manager1Apellidos','manager1@gmail.com','manager1Nombre',1332),(1351,0,'manager2Apellidos','manager2@gmail.com','manager2Nombre',1339),(1352,0,'manager3Apellidos','manager3@gmail.com','manager3Nombre',1340);
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager_agencias`
--

DROP TABLE IF EXISTS `manager_agencias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manager_agencias` (
  `manager` int(11) NOT NULL,
  `agencias` int(11) NOT NULL,
  UNIQUE KEY `UK_3x534fdbfre7p1qsckg5h7kuo` (`agencias`),
  KEY `FK_j7yahc1xkynsoahv9y1hjmmab` (`manager`),
  CONSTRAINT `FK_j7yahc1xkynsoahv9y1hjmmab` FOREIGN KEY (`manager`) REFERENCES `manager` (`id`),
  CONSTRAINT `FK_3x534fdbfre7p1qsckg5h7kuo` FOREIGN KEY (`agencias`) REFERENCES `agencia` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager_agencias`
--

LOCK TABLES `manager_agencias` WRITE;
/*!40000 ALTER TABLE `manager_agencias` DISABLE KEYS */;
INSERT INTO `manager_agencias` VALUES (1350,1367),(1350,1370),(1351,1372);
/*!40000 ALTER TABLE `manager_agencias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mensaje`
--

DROP TABLE IF EXISTS `mensaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mensaje` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `momento` datetime DEFAULT NULL,
  `texto` varchar(255) DEFAULT NULL,
  `emisor` int(11) NOT NULL,
  `noticia` int(11) NOT NULL,
  `receptor` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_e0yp9rqmx7y7jy4fx74ee0fq4` (`emisor`),
  UNIQUE KEY `UK_m5r9vq2jwl3yplvuoke31gn1w` (`receptor`),
  KEY `FK_1c5dumoh33capqwryxa7kkcx7` (`noticia`),
  CONSTRAINT `FK_m5r9vq2jwl3yplvuoke31gn1w` FOREIGN KEY (`receptor`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FK_1c5dumoh33capqwryxa7kkcx7` FOREIGN KEY (`noticia`) REFERENCES `noticia` (`id`),
  CONSTRAINT `FK_e0yp9rqmx7y7jy4fx74ee0fq4` FOREIGN KEY (`emisor`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mensaje`
--

LOCK TABLES `mensaje` WRITE;
/*!40000 ALTER TABLE `mensaje` DISABLE KEYS */;
/*!40000 ALTER TABLE `mensaje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `moderador`
--

DROP TABLE IF EXISTS `moderador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `moderador` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `apellidos` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `user_account` int(11) NOT NULL,
  `paypal_email` varchar(255) DEFAULT NULL,
  `saldo_acumulado` double NOT NULL,
  `saldo_acumulado_total` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_isqgmui05dv4h10cu7ecnjns1` (`user_account`),
  UNIQUE KEY `UK_1mxi9cfnk7a889lei30fjhgpd` (`email`),
  UNIQUE KEY `UK_psytg2rgb4yhvk38cgdywxlil` (`paypal_email`),
  CONSTRAINT `FK_isqgmui05dv4h10cu7ecnjns1` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moderador`
--

LOCK TABLES `moderador` WRITE;
/*!40000 ALTER TABLE `moderador` DISABLE KEYS */;
INSERT INTO `moderador` VALUES (1353,0,'moderador1Apellidos','moderador1@gmail.com','moderador1Nombre',1334,'paypalCorreoModerador1@gmail.com',7.13,7.13),(1354,0,'moderador2Apellidos','moderador2@gmail.com','moderador2Nombre',1336,'paypalCorreoModerador2@gmail.com',4.13,50.23);
/*!40000 ALTER TABLE `moderador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `noticia`
--

DROP TABLE IF EXISTS `noticia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `noticia` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `descripcion` longtext,
  `imagen` varchar(255) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `categoria` int(11) DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `is_banned` bit(1) DEFAULT NULL,
  `numero_visitas` bigint(20) DEFAULT NULL,
  `video` varchar(255) DEFAULT NULL,
  `periodista` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_8aqp4m5gor8jstpabmwueni92` (`periodista`),
  CONSTRAINT `FK_8aqp4m5gor8jstpabmwueni92` FOREIGN KEY (`periodista`) REFERENCES `periodista` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `noticia`
--

LOCK TABLES `noticia` WRITE;
/*!40000 ALTER TABLE `noticia` DISABLE KEYS */;
INSERT INTO `noticia` VALUES (1378,0,'Varios ordenadores de la red interna de Telefónica se han visto afectados este viernes por un software malicioso -\'ransomware\'- que ha llevado a la compañía a solicitar a todos los empleados de su sede central en Madrid que apaguen sus ordenadores. Lo ocurrido ha llenado las redes sociales de chistes sobre la compañía. Recopilamos algunos de ellos: Twitter, por suerte, sigue funcionando','https://s3.amazonaws.com/poderpda/2017/05/movistar-hackeo.jpg','Hackeo Telefónica',5,2,'2019-05-12','\0',700002145,'https://www.youtube.com/embed/_5xU6TcPvvU',1355),(1379,0,'descripciónNoticia2','http://www.myfico.com/Images/sample_overlay.gif','noticia2',8,0,'2019-05-12','\0',50,'https://www.youtube.com/embed/a3ICNMQW7Ok',1355),(1380,0,'descripciónNoticia3','http://www.myfico.com/Images/sample_overlay.gif','noticia3',3,2,'2019-04-11','\0',51,'https://www.youtube.com/embed/a3ICNMQW7Ok',1355),(1381,0,'descripciónNoticia4','http://www.myfico.com/Images/sample_overlay.gif','noticia4',1,2,'2019-05-05','\0',40,'https://www.youtube.com/embed/a3ICNMQW7Ok',1356),(1382,0,'descripciónNoticia5','http://www.myfico.com/Images/sample_overlay.gif','noticia5',2,2,'2019-05-11','\0',29,'https://www.youtube.com/embed/a3ICNMQW7Ok',1356),(1383,0,'descripciónNoticia6','http://www.myfico.com/Images/sample_overlay.gif','noticia2',4,1,'2019-04-08','\0',39,'https://www.youtube.com/embed/a3ICNMQW7Ok',1357),(1384,0,'descripciónNoticia7','http://www.myfico.com/Images/sample_overlay.gif','noticia7',7,1,'2019-03-24','\0',47,'https://www.youtube.com/embed/a3ICNMQW7Ok',1358),(1385,0,'descripciónNoticia8','http://www.myfico.com/Images/sample_overlay.gif','noticia8',6,2,'2019-03-01','\0',200000,'https://www.youtube.com/embed/a3ICNMQW7Ok',1358),(1386,0,'descripciónNoticia8','http://www.myfico.com/Images/sample_overlay.gif','noticia9',6,2,'2019-04-04','\0',2000,'https://www.youtube.com/embed/a3ICNMQW7Ok',1358),(1387,0,'descripciónNoticia8','http://www.myfico.com/Images/sample_overlay.gif','noticia10',6,0,'2019-04-04','',2000,'https://www.youtube.com/embed/a3ICNMQW7Ok',1358);
/*!40000 ALTER TABLE `noticia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `noticia_noticias_relacionadas`
--

DROP TABLE IF EXISTS `noticia_noticias_relacionadas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `noticia_noticias_relacionadas` (
  `noticia` int(11) NOT NULL,
  `noticias_relacionadas` int(11) NOT NULL,
  KEY `FK_hyru0j87oaeik2itaq8nj2gcp` (`noticias_relacionadas`),
  KEY `FK_4skmjigexjdc6c9yvedb6gn18` (`noticia`),
  CONSTRAINT `FK_4skmjigexjdc6c9yvedb6gn18` FOREIGN KEY (`noticia`) REFERENCES `noticia` (`id`),
  CONSTRAINT `FK_hyru0j87oaeik2itaq8nj2gcp` FOREIGN KEY (`noticias_relacionadas`) REFERENCES `noticia` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `noticia_noticias_relacionadas`
--

LOCK TABLES `noticia_noticias_relacionadas` WRITE;
/*!40000 ALTER TABLE `noticia_noticias_relacionadas` DISABLE KEYS */;
INSERT INTO `noticia_noticias_relacionadas` VALUES (1378,1379),(1378,1380),(1379,1378),(1379,1380),(1379,1381),(1380,1378),(1380,1379),(1380,1382),(1381,1379),(1381,1385),(1382,1380),(1385,1381);
/*!40000 ALTER TABLE `noticia_noticias_relacionadas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `periodista`
--

DROP TABLE IF EXISTS `periodista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `periodista` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `apellidos` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `user_account` int(11) NOT NULL,
  `paypal_email` varchar(255) DEFAULT NULL,
  `saldo_acumulado` double NOT NULL,
  `saldo_acumulado_total` double NOT NULL,
  `foto` varchar(255) DEFAULT NULL,
  `is_banned` bit(1) DEFAULT NULL,
  `username_copy_for_ban` varchar(255) DEFAULT NULL,
  `agencia` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_jer1aau9915j2sixejuqp1c2b` (`user_account`),
  UNIQUE KEY `UK_7x89o5p88y9rx2xmh493ykdx0` (`email`),
  UNIQUE KEY `UK_51ru3qef79ppmoyd874h8cf5q` (`paypal_email`),
  KEY `FK_jiwe90x0njlm47i046ux6dwm4` (`agencia`),
  CONSTRAINT `FK_jer1aau9915j2sixejuqp1c2b` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`),
  CONSTRAINT `FK_jiwe90x0njlm47i046ux6dwm4` FOREIGN KEY (`agencia`) REFERENCES `agencia` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `periodista`
--

LOCK TABLES `periodista` WRITE;
/*!40000 ALTER TABLE `periodista` DISABLE KEYS */;
INSERT INTO `periodista` VALUES (1355,1,'periodista1Apellidos','periodista1@gmail.com','periodista1Nombre',1335,'paypalCorreoPeriodista1@gmail.com',20.13,50.23,'https://pbs.twimg.com/profile_images/2254029636/foto_carnet_reasonably_small.jpg','\0',NULL,1367),(1356,1,'periodista2Apellidos','periodista2@gmail.com','periodista2Nombre',1337,'paypalCorreoPeriodista2@gmail.com',11.33,150.23,'https://pbs.twimg.com/profile_images/1536193136/foto_carnet_Alexis_reasonably_small.jpg','\0',NULL,1367),(1357,1,'periodista3Apellidos','periodista3@gmail.com','periodista3Nombre',1338,'paypalCorreoPeriodista3@gmail.com',0.13,300.23,'https://pbs.twimg.com/profile_images/2162163885/Carnet_reasonably_small.jpg','\0',NULL,1372),(1358,0,'periodista4Apellidos','periodista4@gmail.com','periodista4Nombre',1341,'paypalCorreoPeriodista4@gmail.com',3,3,'https://pbs.twimg.com/profile_images/1423419535/foto_carnet_M__Angeles_reasonably_small.jpg','\0',NULL,NULL),(1359,0,'periodista5Apellidos','periodista5@gmail.com','periodista5Nombre',1346,'paypalCorreoPeriodista5@gmail.com',0,0,'https://pbs.twimg.com/profile_images/1423419535/foto_carnet_M__Angeles_reasonably_small.jpg','\0',NULL,NULL),(1360,0,'desconocido','desconocido@desconocido.com','desconocido',1348,'desconocido@desconocido.com',0,0,NULL,'\0',NULL,NULL);
/*!40000 ALTER TABLE `periodista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `premio`
--

DROP TABLE IF EXISTS `premio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `premio` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `precio` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `premio`
--

LOCK TABLES `premio` WRITE;
/*!40000 ALTER TABLE `premio` DISABLE KEYS */;
INSERT INTO `premio` VALUES (1392,0,'Bicicleta de paseo eléctrica, porque pedalear es de noobs..','http://www.ondabike.es/images/thumbs/0000224_450.jpeg','Bicicleta de paseo',1200),(1393,0,'descripcionPremio2','http://www.myfico.com/Images/sample_overlay.gif','NombrePremio2',1234),(1394,0,'descripcionPremio3','http://www.myfico.com/Images/sample_overlay.gif','NombrePremio3',150);
/*!40000 ALTER TABLE `premio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reporte`
--

DROP TABLE IF EXISTS `reporte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reporte` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `texto` varchar(255) DEFAULT NULL,
  `noticia` int(11) NOT NULL,
  `usuario` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7ymecgmoncoahie83gvlt2k4w` (`noticia`),
  KEY `FK_9sgc5k9grbntd6vlxu2ye7b74` (`usuario`),
  CONSTRAINT `FK_9sgc5k9grbntd6vlxu2ye7b74` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FK_7ymecgmoncoahie83gvlt2k4w` FOREIGN KEY (`noticia`) REFERENCES `noticia` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reporte`
--

LOCK TABLES `reporte` WRITE;
/*!40000 ALTER TABLE `reporte` DISABLE KEYS */;
INSERT INTO `reporte` VALUES (1402,0,'Este periodista es un trollaso, La noticia es mas fake que un gitano médico!',1378,1396),(1403,0,'textoReporte2',1379,1396),(1404,0,'textoreporte3',1380,1396),(1405,0,'textoreporte4',1378,1397),(1406,0,'textoreporte5',1379,1397),(1407,0,'textoreporte6',1380,1397),(1408,0,'textoreporte7',1378,1398),(1409,0,'textoreporte8',1379,1398),(1410,0,'textoReporte9',1380,1398),(1411,0,'textoreporte10',1382,1398),(1412,0,'textoreporte11',1382,1399);
/*!40000 ALTER TABLE `reporte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sorteo`
--

DROP TABLE IF EXISTS `sorteo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sorteo` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `fecha_inicio` datetime DEFAULT NULL,
  `fecha_vencimiento` datetime DEFAULT NULL,
  `ganador` varchar(255) DEFAULT NULL,
  `puntos_necesarios` int(11) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `premio` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_8va78rvxto1qifwfphn0p67cc` (`premio`),
  CONSTRAINT `FK_8va78rvxto1qifwfphn0p67cc` FOREIGN KEY (`premio`) REFERENCES `premio` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sorteo`
--

LOCK TABLES `sorteo` WRITE;
/*!40000 ALTER TABLE `sorteo` DISABLE KEYS */;
INSERT INTO `sorteo` VALUES (1388,1,'Sorteamos bicicleta de paseo.','2019-01-13 00:00:00','2019-07-18 00:00:00','Usuario 1, Email: usuario1@gmail.com.',20,'Sorteo Bicicleta',1392),(1389,1,'descripcionSorteo2.','2019-01-13 00:00:00','2019-07-18 00:00:00','',10,'Sorteo2',1392),(1390,1,'descripcionSorteo3.','2019-01-13 00:00:00','2019-08-18 00:00:00','',30,'Sorteo3',1392),(1391,1,'descripcionSorteo4.','2019-01-13 00:00:00','2019-07-01 00:00:00','',100,'Sorteo4',1393);
/*!40000 ALTER TABLE `sorteo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sorteo_usuarios`
--

DROP TABLE IF EXISTS `sorteo_usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sorteo_usuarios` (
  `sorteo` int(11) NOT NULL,
  `usuarios` int(11) NOT NULL,
  KEY `FK_brbs0nytiwm7aevr8vmb6i646` (`usuarios`),
  KEY `FK_45hf7bid5d7msum1t409k68av` (`sorteo`),
  CONSTRAINT `FK_45hf7bid5d7msum1t409k68av` FOREIGN KEY (`sorteo`) REFERENCES `sorteo` (`id`),
  CONSTRAINT `FK_brbs0nytiwm7aevr8vmb6i646` FOREIGN KEY (`usuarios`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sorteo_usuarios`
--

LOCK TABLES `sorteo_usuarios` WRITE;
/*!40000 ALTER TABLE `sorteo_usuarios` DISABLE KEYS */;
INSERT INTO `sorteo_usuarios` VALUES (1388,1396),(1388,1397),(1389,1396),(1389,1398),(1390,1397),(1390,1398),(1390,1399),(1390,1400),(1391,1397),(1391,1398),(1391,1399),(1391,1400);
/*!40000 ALTER TABLE `sorteo_usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tasa`
--

DROP TABLE IF EXISTS `tasa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tasa` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `coste_maestro` int(11) DEFAULT NULL,
  `coste_veterano` int(11) DEFAULT NULL,
  `puntos_maestro` int(11) DEFAULT NULL,
  `puntos_principiante` int(11) DEFAULT NULL,
  `puntos_veterano` int(11) DEFAULT NULL,
  `tasa_moderador` double DEFAULT NULL,
  `tasa_visita` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasa`
--

LOCK TABLES `tasa` WRITE;
/*!40000 ALTER TABLE `tasa` DISABLE KEYS */;
INSERT INTO `tasa` VALUES (1377,0,150,100,30,10,20,0.03,0.01);
/*!40000 ALTER TABLE `tasa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_castjbvpeeus0r8lbpehiu0e4` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (1331,0,'21232f297a57a5a743894a0e4a801fc3','admin'),(1332,0,'c240642ddef994358c96da82c0361a58','manager1'),(1333,0,'122b738600a0f74f7c331c0ef59bc34c','usuario1'),(1334,0,'20bca7bfb096355debce49667f9a5f0a','moderador1'),(1335,0,'e39fcda2d366c8fe1c4f6a59cd2c7e9b','periodista1'),(1336,0,'938a0ff623add3ee858cb8588c79c7c7','moderador2'),(1337,0,'54fa26bfd1c6cfb849aa5640b49994c1','periodista2'),(1338,0,'b7d37d4c180cc75b0f7721d160e583a6','periodista3'),(1339,0,'8df5127cd164b5bc2d2b78410a7eea0c','manager2'),(1340,0,'2d3a5db4a2a9717b43698520a8de57d0','manager3'),(1341,0,'fbe4decd39c98f391c23a7b4677b8e59','periodista4'),(1342,0,'2fb6c8d2f3842a5ceaa9bf320e649ff0','usuario2'),(1343,0,'5a54c609c08a0ab3f7f8eef1365bfda6','usuario3'),(1344,0,'0ddd0fbf933b170eb6d90987a67d0a5d','usuario4'),(1345,0,'0b65933df3421cf1bdf4ff082ffc8901','usuario5'),(1346,0,'ff58245b216e8b0c551b58ca57e51cb8','periodista5'),(1347,0,NULL,'UnknownUser'),(1348,0,NULL,'UnknownJournalist');
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account_authorities`
--

DROP TABLE IF EXISTS `user_account_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account_authorities` (
  `user_account` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_pao8cwh93fpccb0bx6ilq6gsl` (`user_account`),
  CONSTRAINT `FK_pao8cwh93fpccb0bx6ilq6gsl` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account_authorities`
--

LOCK TABLES `user_account_authorities` WRITE;
/*!40000 ALTER TABLE `user_account_authorities` DISABLE KEYS */;
INSERT INTO `user_account_authorities` VALUES (1331,'ADMIN'),(1332,'MANAGER'),(1333,'USUARIO'),(1334,'MODERADOR'),(1335,'PERIODISTA'),(1336,'MODERADOR'),(1337,'PERIODISTA'),(1338,'PERIODISTA'),(1339,'MANAGER'),(1340,'MANAGER'),(1341,'PERIODISTA'),(1342,'USUARIO'),(1343,'USUARIO'),(1344,'USUARIO'),(1345,'USUARIO'),(1346,'PERIODISTA'),(1347,'USUARIO'),(1348,'PERIODISTA');
/*!40000 ALTER TABLE `user_account_authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `apellidos` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `user_account` int(11) NOT NULL,
  `banned` bit(1) NOT NULL,
  `estatus` int(11) DEFAULT NULL,
  `puntos` int(11) DEFAULT NULL,
  `username_copy_for_ban` varchar(255) DEFAULT NULL,
  `buscador` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_93f30ftd3u5f88mddib5b99s1` (`buscador`),
  UNIQUE KEY `UK_6ysql3xef2exh5fnkrlwqew1q` (`user_account`),
  UNIQUE KEY `UK_5171l57faosmj8myawaucatdw` (`email`),
  CONSTRAINT `FK_6ysql3xef2exh5fnkrlwqew1q` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`),
  CONSTRAINT `FK_93f30ftd3u5f88mddib5b99s1` FOREIGN KEY (`buscador`) REFERENCES `buscador` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1396,0,'usuario1Apellidos','usuario1@gmail.com','usuario1Nombre',1333,'\0',0,2000,NULL,1361),(1397,0,'usuario2Apellidos','usuario2@gmail.com','usuario2Nombre',1342,'\0',1,5,NULL,1362),(1398,0,'usuario3Apellidos','usuario3@gmail.com','usuario3Nombre',1343,'\0',2,1000,NULL,1363),(1399,0,'usuario4Apellidos','usuario4@gmail.com','usuario4Nombre',1344,'\0',1,300,NULL,1364),(1400,0,'usuario5Apellidos','usuario5@gmail.com','usuario5Nombre',1345,'\0',0,0,NULL,1365),(1401,0,'desconocido','desconocido@desconocido.com','desconocido',1347,'\0',0,0,NULL,1366);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_informacion_compartida`
--

DROP TABLE IF EXISTS `usuario_informacion_compartida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario_informacion_compartida` (
  `usuario` int(11) NOT NULL,
  `informacion_compartida` int(11) NOT NULL,
  KEY `FK_44qqyo0cfrelnkqn1tri2ueh3` (`usuario`),
  CONSTRAINT `FK_44qqyo0cfrelnkqn1tri2ueh3` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_informacion_compartida`
--

LOCK TABLES `usuario_informacion_compartida` WRITE;
/*!40000 ALTER TABLE `usuario_informacion_compartida` DISABLE KEYS */;
INSERT INTO `usuario_informacion_compartida` VALUES (1396,1378),(1396,1379),(1397,1379),(1397,1381),(1397,1369),(1399,1382),(1399,1368),(1399,1371);
/*!40000 ALTER TABLE `usuario_informacion_compartida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_periodistas`
--

DROP TABLE IF EXISTS `usuario_periodistas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario_periodistas` (
  `usuario` int(11) NOT NULL,
  `periodistas` int(11) NOT NULL,
  KEY `FK_3kbevcv9tam7t3ckr9kiqhwwd` (`periodistas`),
  KEY `FK_7ueexer7cnvofgp4mp45hahrd` (`usuario`),
  CONSTRAINT `FK_7ueexer7cnvofgp4mp45hahrd` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FK_3kbevcv9tam7t3ckr9kiqhwwd` FOREIGN KEY (`periodistas`) REFERENCES `periodista` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_periodistas`
--

LOCK TABLES `usuario_periodistas` WRITE;
/*!40000 ALTER TABLE `usuario_periodistas` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario_periodistas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_seguidores`
--

DROP TABLE IF EXISTS `usuario_seguidores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario_seguidores` (
  `usuario` int(11) NOT NULL,
  `seguidores` int(11) NOT NULL,
  KEY `FK_9fdendwq5ymlafyv5ceuyq52g` (`seguidores`),
  KEY `FK_fnfdma1hd7vv0h40git30unva` (`usuario`),
  CONSTRAINT `FK_fnfdma1hd7vv0h40git30unva` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FK_9fdendwq5ymlafyv5ceuyq52g` FOREIGN KEY (`seguidores`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_seguidores`
--

LOCK TABLES `usuario_seguidores` WRITE;
/*!40000 ALTER TABLE `usuario_seguidores` DISABLE KEYS */;
INSERT INTO `usuario_seguidores` VALUES (1396,1397),(1396,1398),(1397,1399),(1397,1400),(1399,1400);
/*!40000 ALTER TABLE `usuario_seguidores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_siguiendo`
--

DROP TABLE IF EXISTS `usuario_siguiendo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario_siguiendo` (
  `usuario` int(11) NOT NULL,
  `siguiendo` int(11) NOT NULL,
  KEY `FK_2tr3ruvtegekv400ji1f3xalo` (`siguiendo`),
  KEY `FK_ddrb2vgxjdske92229810gt03` (`usuario`),
  CONSTRAINT `FK_ddrb2vgxjdske92229810gt03` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FK_2tr3ruvtegekv400ji1f3xalo` FOREIGN KEY (`siguiendo`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_siguiendo`
--

LOCK TABLES `usuario_siguiendo` WRITE;
/*!40000 ALTER TABLE `usuario_siguiendo` DISABLE KEYS */;
INSERT INTO `usuario_siguiendo` VALUES (1397,1396),(1398,1396),(1399,1397),(1400,1399),(1400,1397);
/*!40000 ALTER TABLE `usuario_siguiendo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_sorteos`
--

DROP TABLE IF EXISTS `usuario_sorteos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario_sorteos` (
  `usuario` int(11) NOT NULL,
  `sorteos` int(11) NOT NULL,
  KEY `FK_foxb7dljoo5bd07rdmuit3jtg` (`sorteos`),
  KEY `FK_osfg9ddjiup9ylvn4diaa5af4` (`usuario`),
  CONSTRAINT `FK_osfg9ddjiup9ylvn4diaa5af4` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FK_foxb7dljoo5bd07rdmuit3jtg` FOREIGN KEY (`sorteos`) REFERENCES `sorteo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_sorteos`
--

LOCK TABLES `usuario_sorteos` WRITE;
/*!40000 ALTER TABLE `usuario_sorteos` DISABLE KEYS */;
INSERT INTO `usuario_sorteos` VALUES (1396,1388),(1396,1389),(1397,1388),(1397,1390),(1398,1390),(1398,1389),(1398,1391),(1399,1390),(1400,1390);
/*!40000 ALTER TABLE `usuario_sorteos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-05 10:03:10
commit;