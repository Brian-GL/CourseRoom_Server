CREATE DATABASE  IF NOT EXISTS `courseroom_server` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `courseroom_server`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: courseroom_server
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `tb_metodos`
--

DROP TABLE IF EXISTS `tb_metodos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_metodos` (
  `Metodo` varchar(50) NOT NULL,
  `Activo` bit(1) NOT NULL,
  PRIMARY KEY (`Metodo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_metodos`
--

LOCK TABLES `tb_metodos` WRITE;
/*!40000 ALTER TABLE `tb_metodos` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_metodos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_tickets`
--

DROP TABLE IF EXISTS `tb_tickets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_tickets` (
  `IdTicket` int NOT NULL AUTO_INCREMENT,
  `Solicitud` varchar(100) NOT NULL,
  `Cliente` varchar(40) NOT NULL,
  `FechaSolicitud` varchar(50) NOT NULL,
  `Respuesta` varchar(100) DEFAULT NULL,
  `FechaRespuesta` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`IdTicket`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_tickets`
--

LOCK TABLES `tb_tickets` WRITE;
/*!40000 ALTER TABLE `tb_tickets` DISABLE KEYS */;
INSERT INTO `tb_tickets` VALUES (1,'Solicitud 1','Cliente 1','2022-01-30 09:40:45',NULL,NULL),(2,'Solicitud 2','Cliente 2','2022-01-30 09:45:45',NULL,NULL);
/*!40000 ALTER TABLE `tb_tickets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'courseroom_server'
--

--
-- Dumping routines for database 'courseroom_server'
--
/*!50003 DROP PROCEDURE IF EXISTS `sp_AgregarRespuesta` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_AgregarRespuesta`(
	IN _IdTicket INT, 
	IN _Respuesta VARCHAR(100),
    IN _FechaRespuesta VARCHAR(50),
    OUT Codigo INT
)
BEGIN

	DECLARE Cuenta INT;
	
	IF _Respuesta IS NULL THEN
		SELECT 0 AS "Codigo";
	ELSE 
		
		SET Cuenta = (SELECT COUNT(IdTicket) FROM Tb_Tickets WHERE IdTicket = _IdTicket);

		IF Cuenta = 1 THEN
		
			UPDATE TbTickets SET Respuesta = _Respuesta, 
			FechaRespuesta  = IF(_FechaRespuesta IS NULL,
            DATE_FORMAT(NOW(), '%d/%m/%y %T'),
            _FechaRespuesta)
			WHERE IdTicket = _IdTicket;
			
			SELECT 1 AS "Codigo";
			
		ELSE
			SELECT -1 AS "Codigo";
		END IF;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_AgregarSolicitud` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_AgregarSolicitud`(
	IN Solicitud VARCHAR(100),
	IN Cliente VARCHAR(40),
	IN Fecha_Solicitud VARCHAR(50),
    OUT Codigo INT
)
BEGIN

	IF Solicitud IS NULL OR Cliente IS NULL THEN
		SELECT -1 As 'Codigo';
	ELSE
    
		INSERT INTO Tb_Tickets(Solicitud, Cliente, FechaSolicitud)
		VALUES (Solicitud, Cliente, IF(Fecha_Solicitud IS NULL, 
        DATE_FORMAT(NOW(), '%d/%m/%y %T'), 
        Fecha_Solicitud));
		SELECT COUNT(IdTicket) AS "Codigo" FROM Tb_Tickets;
	END IF;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_ObtenerRespuestas` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ObtenerRespuestas`()
BEGIN

	SELECT IdTicket, Respuesta, Cliente, FechaRespuesta FROM Tb_Tickets 
    WHERE Respuesta IS NOT NULL AND FechaRespuesta IS NOT NULL
    ORDER BY FechaRespuesta DESC LIMIT 20;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_ObtenerSolicitudes` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ObtenerSolicitudes`()
BEGIN
	SELECT IdTicket, Solicitud, Cliente, FechaSolicitud FROM Tb_Tickets 
    WHERE Respuesta IS NULL AND FechaRespuesta IS NULL
    ORDER BY FechaSolicitud DESC LIMIT 20;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-30 22:08:31
