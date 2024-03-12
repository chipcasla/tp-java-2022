CREATE DATABASE  IF NOT EXISTS `school_management` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `school_management`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: school_management
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `alumno`
--

DROP TABLE IF EXISTS `alumno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alumno` (
  `idAlumno` int unsigned NOT NULL,
  `legajo` int NOT NULL AUTO_INCREMENT,
  `regular` tinyint(1) NOT NULL,
  PRIMARY KEY (`idAlumno`),
  UNIQUE KEY `legajo_UNIQUE` (`legajo`),
  CONSTRAINT `id_idAlumno_fk` FOREIGN KEY (`idAlumno`) REFERENCES `persona` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=47808 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumno`
--

LOCK TABLES `alumno` WRITE;
/*!40000 ALTER TABLE `alumno` DISABLE KEYS */;
INSERT INTO `alumno` VALUES (1,47790,1),(2,23655,1),(4,47791,1),(9,47794,1),(18,47802,1),(19,47803,1),(35,47807,1);
/*!40000 ALTER TABLE `alumno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asignatura`
--

DROP TABLE IF EXISTS `asignatura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asignatura` (
  `idCurso` int NOT NULL,
  `idModalidad` int NOT NULL,
  `idMateria` int NOT NULL,
  `idProfesor` int unsigned DEFAULT NULL,
  `activo` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`idCurso`,`idModalidad`,`idMateria`),
  KEY `asignatura_id_materia_idx` (`idMateria`),
  KEY `asignatura_idProfesor_persona_fk_idx` (`idProfesor`),
  CONSTRAINT `asignatura_id_materia` FOREIGN KEY (`idMateria`) REFERENCES `materia` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `asignatura_idCurso_idModalidad_curso_modalidad_fk` FOREIGN KEY (`idCurso`, `idModalidad`) REFERENCES `curso_modalidad` (`idCurso`, `idModalidad`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `asignatura_idProfesor_persona_fk` FOREIGN KEY (`idProfesor`) REFERENCES `persona` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asignatura`
--

LOCK TABLES `asignatura` WRITE;
/*!40000 ALTER TABLE `asignatura` DISABLE KEYS */;
INSERT INTO `asignatura` VALUES (1,3,1,11,1),(1,3,2,NULL,1),(1,3,3,NULL,1),(1,3,4,11,1),(1,3,5,NULL,1),(1,3,6,NULL,1),(1,3,11,NULL,1),(1,3,16,NULL,1),(1,3,19,NULL,1),(1,4,1,11,1),(1,4,2,NULL,1),(1,4,3,NULL,1),(1,4,4,NULL,1),(1,4,5,NULL,1),(1,4,6,NULL,1),(1,4,11,NULL,1),(1,4,16,NULL,1),(1,4,19,NULL,1),(1,5,1,11,1),(1,5,2,NULL,1),(1,5,3,NULL,1),(1,5,4,NULL,1),(1,5,5,NULL,1),(1,5,6,NULL,1),(1,5,11,NULL,1),(1,5,16,NULL,1),(1,5,19,NULL,1),(1,7,1,21,1),(1,7,2,NULL,0),(1,7,3,NULL,0),(1,7,7,NULL,1),(1,7,9,20,1),(1,7,11,NULL,0),(1,7,15,NULL,1),(1,7,21,NULL,0),(1,7,24,NULL,0),(1,7,27,NULL,0),(1,7,28,23,1),(2,3,1,11,1),(2,3,2,NULL,1),(2,3,3,NULL,1),(2,3,4,NULL,1),(2,3,5,NULL,1),(2,3,7,NULL,1),(2,3,16,NULL,1),(2,3,17,NULL,1),(2,3,19,NULL,1),(2,3,21,NULL,1),(2,4,1,11,1),(2,4,2,NULL,1),(2,4,3,NULL,1),(2,4,4,NULL,1),(2,4,5,NULL,1),(2,4,7,NULL,1),(2,4,16,NULL,1),(2,4,17,NULL,1),(2,4,19,NULL,1),(2,4,21,NULL,1),(2,5,1,11,1),(2,5,2,NULL,1),(2,5,3,NULL,1),(2,5,4,NULL,1),(2,5,5,NULL,1),(2,5,7,NULL,1),(2,5,16,NULL,1),(2,5,17,NULL,1),(2,5,19,NULL,1),(2,5,21,NULL,1),(3,1,1,NULL,1),(3,1,2,NULL,1),(3,1,5,NULL,1),(3,1,7,NULL,1),(3,1,11,NULL,1),(3,1,12,NULL,1),(3,1,16,NULL,1),(3,1,17,NULL,1),(3,1,18,NULL,1),(3,1,20,NULL,1),(3,2,1,NULL,1),(3,2,2,NULL,1),(3,2,5,NULL,1),(3,2,6,NULL,1),(3,2,7,NULL,1),(3,2,11,NULL,1),(3,2,16,NULL,1),(3,2,17,NULL,1),(3,2,18,NULL,1),(3,2,20,NULL,1),(4,1,1,NULL,1),(4,1,2,NULL,1),(4,1,5,NULL,1),(4,1,6,NULL,1),(4,1,7,NULL,1),(4,1,12,NULL,1),(4,1,14,NULL,1),(4,1,16,NULL,1),(4,1,17,NULL,1),(4,1,18,NULL,1),(4,1,22,NULL,1),(4,2,1,NULL,1),(4,2,2,NULL,1),(4,2,5,NULL,1),(4,2,6,NULL,1),(4,2,7,NULL,1),(4,2,14,NULL,1),(4,2,15,NULL,1),(4,2,16,NULL,1),(4,2,17,NULL,1),(4,2,22,NULL,1),(4,2,23,NULL,1),(5,1,1,NULL,1),(5,1,2,NULL,1),(5,1,3,NULL,1),(5,1,5,NULL,1),(5,1,8,NULL,1),(5,1,9,NULL,1),(5,1,10,NULL,1),(5,1,11,NULL,1),(5,1,12,NULL,1),(5,1,13,NULL,1),(5,1,16,NULL,1),(5,2,1,NULL,1),(5,2,2,NULL,1),(5,2,3,NULL,1),(5,2,5,NULL,1),(5,2,7,NULL,1),(5,2,8,NULL,1),(5,2,9,NULL,1),(5,2,15,NULL,1),(5,2,16,NULL,1),(5,2,23,NULL,1),(5,2,24,NULL,1);
/*!40000 ALTER TABLE `asignatura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curso`
--

DROP TABLE IF EXISTS `curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `curso` (
  `id` int NOT NULL,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curso`
--

LOCK TABLES `curso` WRITE;
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
INSERT INTO `curso` VALUES (4,'Cuarto'),(1,'Primer'),(5,'Quinto'),(2,'Segundo'),(3,'Tercer');
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curso_modalidad`
--

DROP TABLE IF EXISTS `curso_modalidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `curso_modalidad` (
  `idCurso` int NOT NULL,
  `idModalidad` int NOT NULL,
  `activo` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`idCurso`,`idModalidad`),
  KEY `curso_modalidad_id_modalidad_fk_idx` (`idModalidad`),
  CONSTRAINT `curso_modalidad_id_curso_fk` FOREIGN KEY (`idCurso`) REFERENCES `curso` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `curso_modalidad_id_modalidad_fk` FOREIGN KEY (`idModalidad`) REFERENCES `modalidad` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curso_modalidad`
--

LOCK TABLES `curso_modalidad` WRITE;
/*!40000 ALTER TABLE `curso_modalidad` DISABLE KEYS */;
INSERT INTO `curso_modalidad` VALUES (1,3,1),(1,4,1),(1,5,1),(1,7,1),(1,16,0),(2,3,1),(2,4,1),(2,5,1),(3,1,1),(3,2,1),(4,1,1),(4,2,1),(5,1,1),(5,2,1);
/*!40000 ALTER TABLE `curso_modalidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inscripcion`
--

DROP TABLE IF EXISTS `inscripcion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inscripcion` (
  `idAlumno` int unsigned NOT NULL,
  `id_curso` int NOT NULL,
  `id_modalidad` int NOT NULL,
  `fechaInscripcion` datetime NOT NULL,
  PRIMARY KEY (`idAlumno`,`fechaInscripcion`),
  KEY `inscripcion_idCurso_idModalidad_curso_modalidad_fk_idx` (`id_curso`,`id_modalidad`),
  CONSTRAINT `inscripcion_id_alumno_fk` FOREIGN KEY (`idAlumno`) REFERENCES `persona` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `inscripcion_idCurso_idModalidad_curso_modalidad_fk` FOREIGN KEY (`id_curso`, `id_modalidad`) REFERENCES `curso_modalidad` (`idCurso`, `idModalidad`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inscripcion`
--

LOCK TABLES `inscripcion` WRITE;
/*!40000 ALTER TABLE `inscripcion` DISABLE KEYS */;
INSERT INTO `inscripcion` VALUES (1,1,3,'2015-03-30 00:00:00'),(2,1,3,'2014-03-30 00:00:00'),(4,1,3,'2022-12-14 00:00:00'),(35,1,3,'2024-03-12 00:00:00'),(1,2,3,'2021-12-13 00:00:00'),(2,2,3,'2015-03-20 00:00:00'),(19,2,4,'2023-01-24 00:00:00'),(1,3,1,'2022-12-31 00:00:00'),(2,3,1,'2016-03-10 00:00:00'),(18,3,2,'2023-01-18 00:00:00'),(2,4,1,'2017-03-01 00:00:00'),(1,4,2,'2024-03-12 00:00:00'),(2,5,1,'2018-02-10 00:00:00');
/*!40000 ALTER TABLE `inscripcion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materia`
--

DROP TABLE IF EXISTS `materia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materia` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materia`
--

LOCK TABLES `materia` WRITE;
/*!40000 ALTER TABLE `materia` DISABLE KEYS */;
INSERT INTO `materia` VALUES (1,'Matemática'),(2,'Lengua y Literatura'),(3,'Economía y Administración'),(4,'Formación Ética'),(5,'Inglés'),(6,'Geografía'),(7,'Historia'),(8,'Filosofía'),(9,'Derecho'),(10,'Salud y Ambiente'),(11,'Biología'),(12,'Química'),(13,'Ciencias de la Tierra'),(14,'Salud y Adolescencia'),(15,'Psicología'),(16,'Educación Física'),(17,'Educación Artística'),(18,'Física'),(19,'Tecnología'),(20,'Construcción de Ciudadanía'),(21,'Físico-química'),(22,'Programación'),(23,'Problemática Educativa'),(24,'Sociología'),(27,'Programación'),(28,'Identidad');
/*!40000 ALTER TABLE `materia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modalidad`
--

DROP TABLE IF EXISTS `modalidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `modalidad` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modalidad`
--

LOCK TABLES `modalidad` WRITE;
/*!40000 ALTER TABLE `modalidad` DISABLE KEYS */;
INSERT INTO `modalidad` VALUES (1,'Ciencias Naturales'),(2,'Ciencias Sociales y Humanidades'),(3,'A'),(4,'B'),(5,'C'),(7,'D'),(9,'Informatica'),(14,'E'),(16,'Tecnicas');
/*!40000 ALTER TABLE `modalidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nota`
--

DROP TABLE IF EXISTS `nota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nota` (
  `idAlumno` int unsigned NOT NULL,
  `idCurso` int NOT NULL,
  `idModalidad` int NOT NULL,
  `idMateria` int NOT NULL,
  `notaFinal` int DEFAULT NULL,
  `nota1` int DEFAULT NULL,
  `nota2` int DEFAULT NULL,
  `nota3` int DEFAULT NULL,
  PRIMARY KEY (`idAlumno`,`idCurso`,`idModalidad`,`idMateria`),
  KEY `nota_asignatura_fk_idx` (`idCurso`,`idModalidad`,`idMateria`) /*!80000 INVISIBLE */,
  CONSTRAINT `nota_asignatura_fk` FOREIGN KEY (`idCurso`, `idModalidad`, `idMateria`) REFERENCES `asignatura` (`idCurso`, `idModalidad`, `idMateria`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `nota_idAlumno_fk` FOREIGN KEY (`idAlumno`) REFERENCES `persona` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nota`
--

LOCK TABLES `nota` WRITE;
/*!40000 ALTER TABLE `nota` DISABLE KEYS */;
INSERT INTO `nota` VALUES (1,1,3,1,10,9,10,10),(1,1,3,2,10,9,10,10),(1,1,3,3,8,8,8,9),(1,1,3,4,8,8,8,8),(1,1,3,5,9,8,9,9),(1,1,3,6,8,8,8,8),(1,1,3,11,8,8,8,10),(1,1,3,16,9,9,9,9),(1,1,3,19,10,7,10,10),(1,2,3,1,10,10,10,10),(1,2,3,2,10,9,10,10),(1,2,3,3,9,9,9,9),(1,2,3,4,4,10,9,10),(1,2,3,5,4,9,9,10),(1,2,3,7,8,8,8,9),(1,2,3,16,9,7,7,10),(1,2,3,17,9,9,10,10),(1,2,3,19,10,9,9,10),(1,2,3,21,10,9,9,10),(1,3,1,1,7,NULL,NULL,NULL),(1,3,1,2,7,NULL,NULL,NULL),(1,3,1,5,4,NULL,NULL,NULL),(1,3,1,7,7,NULL,NULL,NULL),(1,3,1,11,7,NULL,NULL,NULL),(1,3,1,12,6,NULL,NULL,NULL),(1,3,1,16,8,NULL,NULL,NULL),(1,3,1,17,4,NULL,NULL,NULL),(1,3,1,18,7,NULL,NULL,NULL),(1,3,1,20,7,NULL,NULL,NULL),(2,1,3,1,6,5,6,6),(2,1,3,2,7,4,6,8),(2,1,3,3,6,6,6,6),(2,1,3,4,7,8,6,7),(2,1,3,5,8,8,8,8),(2,1,3,6,6,6,7,6),(2,1,3,11,7,7,10,6),(2,1,3,16,10,9,10,10),(2,1,3,19,9,6,10,9),(2,2,3,1,6,5,6,6),(2,2,3,2,5,4,6,8),(2,2,3,3,10,6,10,10),(2,2,3,4,7,8,6,7),(2,2,3,5,8,8,8,8),(2,2,3,7,6,6,7,6),(2,2,3,16,7,7,10,6),(2,2,3,17,10,9,10,10),(2,2,3,19,9,6,10,9),(2,2,3,21,9,6,10,9),(4,1,3,1,9,9,9,9);
/*!40000 ALTER TABLE `nota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persona` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `dni` varchar(20) DEFAULT NULL,
  `fechaNacimiento` date DEFAULT NULL,
  `mail` varchar(50) NOT NULL,
  `tel` varchar(50) DEFAULT NULL,
  `contrasena` varchar(50) NOT NULL,
  `tipo` varchar(50) DEFAULT NULL,
  `cuil` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `mail_UNIQUE` (`mail`),
  UNIQUE KEY `dni_UNIQUE` (`dni`),
  UNIQUE KEY `tel_UNIQUE` (`tel`),
  UNIQUE KEY `cuil_UNIQUE` (`cuil`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (1,'Alejandro','Foresi','43378663','2001-06-30','alef@g.com','3464510401','asd','alumno',NULL),(2,'Tiana','Diaz','42669885','2000-07-15','t@diaz.com','6060606060','asd','alumno',NULL),(3,'Normal','2','',NULL,'admin@g.com',NULL,'asd','admin',NULL),(4,'Mateo','Mazzia','44256301','2001-09-04','m@maz.com','4040404040','asd','alumno',NULL),(9,'Lorena','Dublin','44500500','2001-08-07','ld@g.com','2323090909','asd1','alumno',NULL),(11,'Analia Claudia','Cardinali','22222222','1974-01-17','analia@g.com','3464520202','asd1','profesor','10222222228'),(18,'Santiago','Charpentier','28900901','2006-09-28','chano@g.com','0411010101','asd1','alumno',NULL),(19,'Rebeca','Wein','45900320','2007-04-20','reb@g.com','3415670000','asd1','alumno',NULL),(20,'Ariel','Altamirano','23562356','1973-12-27','aalta@gmail.com','3658957441','as1','profesor','20235623568'),(21,'Ernesto','Saenz','26263526','1970-05-12','ernes@gmail.com','3265514255','as1','profesor','20262635268'),(23,'Roberto','Redford','20356653','1968-03-12','robert@gmail.com','6598758963','as1','profesor','20203566538'),(25,'Alfredo','Podar','23892376','1989-06-01','afp@gmail.com','2333565478','as3','profesor','89273849099'),(35,'Calvin','Candie','23562354','1987-07-10','candie@g.com','3256985254','as2','alumno',NULL);
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-12 18:34:04
