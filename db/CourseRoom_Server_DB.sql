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
-- Table structure for table `tb_respuestas`
--

DROP TABLE IF EXISTS `tb_respuestas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_respuestas` (
  `IdRespuesta` int NOT NULL AUTO_INCREMENT,
  `Respuesta` varchar(256) NOT NULL,
  `Cliente` varchar(80) NOT NULL,
  `FechaRespuesta` varchar(100) NOT NULL,
  PRIMARY KEY (`IdRespuesta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_respuestas`
--

LOCK TABLES `tb_respuestas` WRITE;
/*!40000 ALTER TABLE `tb_respuestas` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_respuestas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_solicitudes`
--

DROP TABLE IF EXISTS `tb_solicitudes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_solicitudes` (
  `IdSolicitud` int NOT NULL AUTO_INCREMENT,
  `Solicitud` varchar(200) NOT NULL,
  `Cliente` varchar(80) NOT NULL,
  `FechaSolicitud` varchar(100) NOT NULL,
  PRIMARY KEY (`IdSolicitud`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_solicitudes`
--

LOCK TABLES `tb_solicitudes` WRITE;
/*!40000 ALTER TABLE `tb_solicitudes` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_solicitudes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'courseroom_server'
--

--
-- Dumping routines for database 'courseroom_server'
--
/*!50003 DROP PROCEDURE IF EXISTS `sp_crs_AgregarRespuesta` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_crs_AgregarRespuesta`(
	IN _Respuesta VARCHAR(100),
    IN _Cliente VARCHAR(45),
    IN _FechaRespuesta VARCHAR(75)
)
BEGIN

	IF _Respuesta IS NULL OR LENGTH(_Respuesta) = 0 
		OR  _Cliente IS NULL OR LENGTH(_Cliente) = 0  THEN
        SELECT -1 AS "Codigo", 'La Respuesta O El Cliente Tienen Valores Nulos' AS "Mensaje";
	ELSE 
		
        INSERT INTO tb_respuestas(Respuesta, Cliente, FechaRespuesta)
		VALUES (_Respuesta, _Cliente, IF(_FechaRespuesta IS NULL OR LENGTH(_FechaRespuesta) = 0, DATE_FORMAT(NOW(), '%d/%m/%y %T'), _FechaRespuesta));
        SELECT LAST_INSERT_ID() AS "Codigo", 'OK' AS "Mensaje";
       
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_crs_AgregarSolicitud` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_crs_AgregarSolicitud`(
	IN _Solicitud VARCHAR(100),
	IN _Cliente VARCHAR(45),
	IN _FechaSolicitud VARCHAR(75)
)
BEGIN

	IF _Solicitud IS NULL OR LENGTH(_Solicitud) = 0 
		OR  _Cliente IS NULL OR LENGTH(_Cliente) = 0  THEN
        SELECT -1 AS "Codigo", 'La Solicitud O El Cliente Tienen Valores Nulos' AS "Mensaje";
	ELSE
    
		INSERT INTO tb_solicitudes(Solicitud, Cliente, FechaSolicitud)
		VALUES (_Solicitud, _Cliente, IF(_FechaSolicitud IS NULL OR LENGTH(_FechaSolicitud) = 0, DATE_FORMAT(NOW(), '%d/%m/%y %T'), _FechaSolicitud));
		SELECT LAST_INSERT_ID() AS "Codigo", 'OK' AS "Mensaje";
        
	END IF;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_crs_ObtenerRespuestas` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_crs_ObtenerRespuestas`()
BEGIN
	SELECT IdRespuesta, Respuesta, Cliente, FechaRespuesta FROM tb_respuestas 
    ORDER BY IdRespuesta DESC LIMIT 200;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_crs_ObtenerSolicitudes` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_crs_ObtenerSolicitudes`()
BEGIN
	SELECT IdSolicitud, Solicitud, Cliente, FechaSolicitud FROM tb_solicitudes 
    ORDER BY IdSolicitud DESC LIMIT 200;
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

-- Dump completed on 2022-03-19 18:18:00
