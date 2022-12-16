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
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `legajo` int unsigned DEFAULT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `dni` varchar(20) DEFAULT NULL,
  `fechaNacimiento` date DEFAULT NULL,
  `mail` varchar(50) NOT NULL,
  `tel` varchar(50) DEFAULT NULL,
  `regular` tinyint(1) DEFAULT NULL,
  `contrasena` varchar(50) NOT NULL,
  `tipo` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `mail_UNIQUE` (`mail`),
  UNIQUE KEY `dni_UNIQUE` (`dni`),
  UNIQUE KEY `tel_UNIQUE` (`tel`),
  UNIQUE KEY `legajo_UNIQUE` (`legajo`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumno`
--

LOCK TABLES `alumno` WRITE;
/*!40000 ALTER TABLE `alumno` DISABLE KEYS */;
INSERT INTO `alumno` VALUES (1,47790,'Alejandro','Foresi','43378663','2001-06-30','alef@g.com','3464510101',1,'asd','alumno'),(2,23655,'Tiana','Diaz','42669885','2000-07-15','t@diaz.com','6060606060',1,'asd','alumno'),(3,NULL,'Usuario','Administrador','',NULL,'admin@g.com',NULL,NULL,'asd','admin'),(4,47791,'Mateo','Mazzia','44256301','2001-09-04','m@maz.com','4040404040',1,'asd','alumno'),(6,47792,'juan','sanchez','33378663','2001-06-30','js@g.com','1010101011',0,'asd1','alumno'),(9,47794,'Lucia','Molina','44500500','2001-08-07','lm@g.com','2323090909',1,'asd1','alumno'),(10,47795,'Marina','Melo','40900233','1999-09-09','mm@g.com','2345454545',1,'asd1','alumno');
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
  `idProfesor` int DEFAULT NULL,
  PRIMARY KEY (`idCurso`,`idModalidad`,`idMateria`),
  KEY `asignatura_id_materia_idx` (`idMateria`),
  KEY `asignatura_id_profesor_idx` (`idProfesor`),
  CONSTRAINT `asignatura_id_materia` FOREIGN KEY (`idMateria`) REFERENCES `materia` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `asignatura_id_profesor` FOREIGN KEY (`idProfesor`) REFERENCES `profesor` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `asignatura_idCurso_idModalidad_curso_modalidad_fk` FOREIGN KEY (`idCurso`, `idModalidad`) REFERENCES `curso_modalidad` (`idCurso`, `idModalidad`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asignatura`
--

LOCK TABLES `asignatura` WRITE;
/*!40000 ALTER TABLE `asignatura` DISABLE KEYS */;
INSERT INTO `asignatura` VALUES (1,3,1,NULL),(1,3,2,NULL),(1,3,3,NULL),(1,3,4,NULL),(1,3,5,NULL),(1,3,6,NULL),(1,3,11,NULL),(1,3,16,NULL),(1,3,19,NULL),(2,3,1,NULL),(2,3,2,NULL),(2,3,3,NULL),(2,3,4,NULL),(2,3,5,NULL),(2,3,7,NULL),(2,3,16,NULL),(2,3,17,NULL),(2,3,19,NULL),(2,3,21,NULL),(3,1,1,NULL),(3,1,2,NULL),(3,1,5,NULL),(3,1,7,NULL),(3,1,11,NULL),(3,1,12,NULL),(3,1,16,NULL),(3,1,17,NULL),(3,1,18,NULL),(3,1,20,NULL),(3,2,1,NULL),(3,2,2,NULL),(3,2,5,NULL),(3,2,6,NULL),(3,2,7,NULL),(3,2,11,NULL),(3,2,16,NULL),(3,2,17,NULL),(3,2,18,NULL),(3,2,20,NULL),(4,1,1,NULL),(4,1,2,NULL),(4,1,5,NULL),(4,1,6,NULL),(4,1,7,NULL),(4,1,12,NULL),(4,1,14,NULL),(4,1,16,NULL),(4,1,17,NULL),(4,1,18,NULL),(4,1,22,NULL),(4,2,1,NULL),(4,2,2,NULL),(4,2,5,NULL),(4,2,6,NULL),(4,2,7,NULL),(4,2,14,NULL),(4,2,15,NULL),(4,2,16,NULL),(4,2,17,NULL),(4,2,22,NULL),(4,2,23,NULL),(5,1,1,NULL),(5,1,2,NULL),(5,1,3,NULL),(5,1,5,NULL),(5,1,8,NULL),(5,1,9,NULL),(5,1,10,NULL),(5,1,11,NULL),(5,1,12,NULL),(5,1,13,NULL),(5,1,16,NULL),(5,2,1,NULL),(5,2,2,NULL),(5,2,3,NULL),(5,2,5,NULL),(5,2,7,NULL),(5,2,8,NULL),(5,2,9,NULL),(5,2,15,NULL),(5,2,16,NULL),(5,2,23,NULL),(5,2,24,NULL);
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
INSERT INTO `curso_modalidad` VALUES (3,1),(4,1),(5,1),(3,2),(4,2),(5,2),(1,3),(2,3);
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
  CONSTRAINT `inscripcion_id_alumno_fk` FOREIGN KEY (`idAlumno`) REFERENCES `alumno` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `inscripcion_idCurso_idModalidad_curso_modalidad_fk` FOREIGN KEY (`id_curso`, `id_modalidad`) REFERENCES `curso_modalidad` (`idCurso`, `idModalidad`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inscripcion`
--

LOCK TABLES `inscripcion` WRITE;
/*!40000 ALTER TABLE `inscripcion` DISABLE KEYS */;
INSERT INTO `inscripcion` VALUES (1,1,3,'2015-03-30 00:00:00'),(2,1,3,'2014-03-30 00:00:00'),(4,1,3,'2022-12-14 00:00:00'),(1,2,3,'2021-12-13 00:00:00'),(2,2,3,'2015-03-20 00:00:00'),(1,3,1,'2022-12-14 00:00:00'),(2,3,1,'2016-03-10 00:00:00'),(2,4,1,'2017-03-01 00:00:00'),(2,5,1,'2018-02-10 00:00:00');
/*!40000 ALTER TABLE `inscripcion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materia`
--

DROP TABLE IF EXISTS `materia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materia` (
  `id` int NOT NULL,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materia`
--

LOCK TABLES `materia` WRITE;
/*!40000 ALTER TABLE `materia` DISABLE KEYS */;
INSERT INTO `materia` VALUES (1,'Matemática'),(2,'Lengua y Literatura'),(3,'Economía y Administración'),(4,'Formación Ética'),(5,'Inglés'),(6,'Geografía'),(7,'Historia'),(8,'Filosofía'),(9,'Derecho'),(10,'Salud y Ambiente'),(11,'Biología'),(12,'Química'),(13,'Ciencias de la Tierra'),(14,'Salud y Adolescencia'),(15,'Psicología'),(16,'Educación Física'),(17,'Educación Artística'),(18,'Física'),(19,'Tecnología'),(20,'Construcción de Ciudadanía'),(21,'Físico-química'),(22,'Programación'),(23,'Problemática Educativa'),(24,'Sociología');
/*!40000 ALTER TABLE `materia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modalidad`
--

DROP TABLE IF EXISTS `modalidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `modalidad` (
  `id` int NOT NULL,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modalidad`
--

LOCK TABLES `modalidad` WRITE;
/*!40000 ALTER TABLE `modalidad` DISABLE KEYS */;
INSERT INTO `modalidad` VALUES (1,'Ciencias Naturales'),(2,'Ciencias Sociales y Humanidades'),(3,'Básica');
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
  CONSTRAINT `nota_idAlumno_fk` FOREIGN KEY (`idAlumno`) REFERENCES `alumno` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nota`
--

LOCK TABLES `nota` WRITE;
/*!40000 ALTER TABLE `nota` DISABLE KEYS */;
INSERT INTO `nota` VALUES (1,1,3,1,10,9,10,10),(1,1,3,2,10,9,10,10),(1,1,3,3,8,8,8,9),(1,1,3,4,8,8,8,8),(1,1,3,5,9,8,9,9),(1,1,3,6,8,8,8,8),(1,1,3,11,8,8,8,10),(1,1,3,16,9,9,9,9),(1,1,3,19,10,7,10,10),(1,2,3,1,10,10,10,10),(1,2,3,2,10,9,10,10),(1,2,3,3,9,9,9,9),(1,2,3,4,4,10,9,10),(1,2,3,5,4,9,9,10),(1,2,3,7,8,8,8,9),(1,2,3,16,9,7,7,10),(1,2,3,17,9,9,10,10),(1,2,3,19,10,9,9,10),(1,2,3,21,10,9,9,10),(2,1,3,1,6,5,6,6),(2,1,3,2,7,4,6,8),(2,1,3,3,6,6,6,6),(2,1,3,4,7,8,6,7),(2,1,3,5,8,8,8,8),(2,1,3,6,6,6,7,6),(2,1,3,11,7,7,10,6),(2,1,3,16,10,9,10,10),(2,1,3,19,9,6,10,9),(2,2,3,1,6,5,6,6),(2,2,3,2,5,4,6,8),(2,2,3,3,10,6,10,10),(2,2,3,4,7,8,6,7),(2,2,3,5,8,8,8,8),(2,2,3,7,6,6,7,6),(2,2,3,16,7,7,10,6),(2,2,3,17,10,9,10,10),(2,2,3,19,9,6,10,9),(2,2,3,21,9,6,10,9),(4,1,3,1,9,9,9,9);
/*!40000 ALTER TABLE `nota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profesor`
--

DROP TABLE IF EXISTS `profesor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profesor` (
  `id` int NOT NULL,
  `cuil` varchar(20) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `mail` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cuil_UNIQUE` (`cuil`),
  UNIQUE KEY `mail_UNIQUE` (`mail`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profesor`
--

LOCK TABLES `profesor` WRITE;
/*!40000 ALTER TABLE `profesor` DISABLE KEYS */;
/*!40000 ALTER TABLE `profesor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-16  9:48:26
