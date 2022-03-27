CREATE DATABASE  IF NOT EXISTS `courseroom_server` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `courseroom_server`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: courseroom_server
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
  `DireccionIP` varchar(256) DEFAULT NULL,
  `Fecha` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`IdRespuesta`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_respuestas`
--

LOCK TABLES `tb_respuestas` WRITE;
/*!40000 ALTER TABLE `tb_respuestas` DISABLE KEYS */;
INSERT INTO `tb_respuestas` VALUES (1,'Imagen Enviada Y Obtenida Desde: https://picsum.photos/500/700','C6CC0026-C2C2-11E8-B5F5-E86A64292601','177.245.217.103','sábado 26/03/22 21:03:05'),(2,'Estados Enviados','C6CC0026-C2C2-11E8-B5F5-E86A64292601','177.245.217.103','sábado 26/03/22 21:03:09'),(3,'Localidades Enviadas','C6CC0026-C2C2-11E8-B5F5-E86A64292601','177.245.217.103','sábado 26/03/22 21:03:10'),(4,'Imagen Enviada Y Obtenida Desde: https://picsum.photos/500/700','C6CC0026-C2C2-11E8-B5F5-E86A64292601','177.245.217.103','sábado 26/03/22 21:08:47'),(5,'Estados Enviados','C6CC0026-C2C2-11E8-B5F5-E86A64292601','177.245.217.103','sábado 26/03/22 21:08:50'),(6,'Localidades Enviadas','C6CC0026-C2C2-11E8-B5F5-E86A64292601','177.245.217.103','sábado 26/03/22 21:08:51'),(7,'Imagen Enviada Y Obtenida Desde: https://picsum.photos/500/700','C6CC0026-C2C2-11E8-B5F5-E86A64292601','177.245.217.103','sábado 26/03/22 21:12:51'),(8,'Estados Enviados','C6CC0026-C2C2-11E8-B5F5-E86A64292601','177.245.217.103','sábado 26/03/22 21:12:54'),(9,'Localidades Enviadas','C6CC0026-C2C2-11E8-B5F5-E86A64292601','177.245.217.103','sábado 26/03/22 21:12:54'),(10,'Imagen Enviada Y Obtenida Desde: https://picsum.photos/500/700','C6CC0026-C2C2-11E8-B5F5-E86A64292601','177.245.217.103','sábado 26/03/22 21:19:05'),(11,'Estados Enviados','C6CC0026-C2C2-11E8-B5F5-E86A64292601','177.245.217.103','sábado 26/03/22 21:19:07'),(12,'Localidades Enviadas','C6CC0026-C2C2-11E8-B5F5-E86A64292601','177.245.217.103','sábado 26/03/22 21:19:08'),(13,'Usuario Agregado Con ID 1','C6CC0026-C2C2-11E8-B5F5-E86A64292601','177.245.217.103','sábado 26/03/22 21:19:44'),(14,'Imagen Enviada Y Obtenida Desde: https://picsum.photos/500/700','C6CC0026-C2C2-11E8-B5F5-E86A64292601','177.245.217.103','sábado 26/03/22 21:23:07'),(15,'Estados Enviados','C6CC0026-C2C2-11E8-B5F5-E86A64292601','177.245.217.103','sábado 26/03/22 21:23:10'),(16,'Localidades Enviadas','C6CC0026-C2C2-11E8-B5F5-E86A64292601','177.245.217.103','sábado 26/03/22 21:23:10'),(17,'Usuario Agregado Con ID 2','C6CC0026-C2C2-11E8-B5F5-E86A64292601','177.245.217.103','sábado 26/03/22 21:24:18');
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
  `DireccionIP` varchar(256) DEFAULT NULL,
  `Fecha` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`IdSolicitud`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_solicitudes`
--

LOCK TABLES `tb_solicitudes` WRITE;
/*!40000 ALTER TABLE `tb_solicitudes` DISABLE KEYS */;
INSERT INTO `tb_solicitudes` VALUES (1,'Obtener Imagen Inicio Sesión','C6CC0026-C2C2-11E8-B5F5-E86A64292601','177.245.217.103','sábado 26/03/22 21:03:02'),(2,'Obtener Estados','C6CC0026-C2C2-11E8-B5F5-E86A64292601','177.245.217.103','sábado 26/03/22 21:03:09'),(3,'Obtener Localidades Del Estado AGUASCALIENTES','C6CC0026-C2C2-11E8-B5F5-E86A64292601','177.245.217.103','sábado 26/03/22 21:03:10'),(4,'Agregar Nuevo Usuario','C6CC0026-C2C2-11E8-B5F5-E86A64292601','177.245.217.103','sábado 26/03/22 21:03:37'),(5,'Obtener Imagen Inicio Sesión','C6CC0026-C2C2-11E8-B5F5-E86A64292601','177.245.217.103','sábado 26/03/22 21:08:44'),(6,'Obtener Estados','C6CC0026-C2C2-11E8-B5F5-E86A64292601','177.245.217.103','sábado 26/03/22 21:08:50'),(7,'Obtener Localidades Del Estado AGUASCALIENTES','C6CC0026-C2C2-11E8-B5F5-E86A64292601','177.245.217.103','sábado 26/03/22 21:08:51'),(8,'Agregar Nuevo Usuario','C6CC0026-C2C2-11E8-B5F5-E86A64292601','177.245.217.103','sábado 26/03/22 21:09:32'),(9,'Obtener Imagen Inicio Sesión','C6CC0026-C2C2-11E8-B5F5-E86A64292601','177.245.217.103','sábado 26/03/22 21:12:49'),(10,'Obtener Estados','C6CC0026-C2C2-11E8-B5F5-E86A64292601','177.245.217.103','sábado 26/03/22 21:12:53'),(11,'Obtener Localidades Del Estado AGUASCALIENTES','C6CC0026-C2C2-11E8-B5F5-E86A64292601','177.245.217.103','sábado 26/03/22 21:12:54'),(12,'Agregar Nuevo Usuario','C6CC0026-C2C2-11E8-B5F5-E86A64292601','177.245.217.103','sábado 26/03/22 21:13:22'),(13,'Obtener Imagen Inicio Sesión','C6CC0026-C2C2-11E8-B5F5-E86A64292601','177.245.217.103','sábado 26/03/22 21:19:02'),(14,'Obtener Estados','C6CC0026-C2C2-11E8-B5F5-E86A64292601','177.245.217.103','sábado 26/03/22 21:19:07'),(15,'Obtener Localidades Del Estado AGUASCALIENTES','C6CC0026-C2C2-11E8-B5F5-E86A64292601','177.245.217.103','sábado 26/03/22 21:19:08'),(16,'Agregar Nuevo Usuario','C6CC0026-C2C2-11E8-B5F5-E86A64292601','177.245.217.103','sábado 26/03/22 21:19:38'),(17,'Obtener Imagen Inicio Sesión','C6CC0026-C2C2-11E8-B5F5-E86A64292601','177.245.217.103','sábado 26/03/22 21:23:04'),(18,'Obtener Estados','C6CC0026-C2C2-11E8-B5F5-E86A64292601','177.245.217.103','sábado 26/03/22 21:23:09'),(19,'Obtener Localidades Del Estado AGUASCALIENTES','C6CC0026-C2C2-11E8-B5F5-E86A64292601','177.245.217.103','sábado 26/03/22 21:23:10'),(20,'Agregar Nuevo Usuario','C6CC0026-C2C2-11E8-B5F5-E86A64292601','177.245.217.103','sábado 26/03/22 21:23:39');
/*!40000 ALTER TABLE `tb_solicitudes` ENABLE KEYS */;
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
CREATE DEFINER=`courseroom_server`@`localhost` PROCEDURE `sp_AgregarRespuesta`(
	IN _Respuesta VARCHAR(100),
    IN _Cliente VARCHAR(45),
    IN _DireccionIP VARCHAR(256)
)
BEGIN
	IF _Respuesta IS NULL OR LENGTH(_Respuesta) = 0 
		OR  _Cliente IS NULL OR LENGTH(_Cliente) = 0
        OR  _DireccionIP IS NULL OR LENGTH(_DireccionIP) = 0 THEN
        SELECT -1 AS "Codigo", 'El Formato De Entrada De Algún Parámetro Es Incorrecto' AS "Mensaje";
	ELSE 
		SET lc_time_names = 'es_MX';
        INSERT INTO tb_respuestas(Respuesta, Cliente, DireccionIP, Fecha)
		VALUES (_Respuesta, _Cliente, _DireccionIP, CONCAT(DAYNAME(NOW()),' ',DATE_FORMAT(NOW(), '%d/%m/%y %T')));
        SELECT LAST_INSERT_ID() AS "Codigo", 'OK' AS "Mensaje";
       
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
CREATE DEFINER=`courseroom_server`@`localhost` PROCEDURE `sp_AgregarSolicitud`(
	IN _Solicitud VARCHAR(100),
    IN _Cliente VARCHAR(45),
    IN _DireccionIP VARCHAR(256)
)
BEGIN

	IF _Solicitud IS NULL OR LENGTH(_Solicitud) = 0 
		OR  _Cliente IS NULL OR LENGTH(_Cliente) = 0
        OR  _DireccionIP IS NULL OR LENGTH(_DireccionIP) = 0 THEN
        SELECT -1 AS "Codigo", 'El Formato De Entrada De Algún Parámetro Es Incorrecto' AS "Mensaje";
	ELSE 
		SET lc_time_names = 'es_MX';
        INSERT INTO tb_solicitudes(Solicitud, Cliente, DireccionIP, Fecha)
		VALUES (_Solicitud, _Cliente, _DireccionIP, CONCAT(DAYNAME(NOW()),' ',DATE_FORMAT(NOW(), '%d/%m/%y %T')));
        SELECT LAST_INSERT_ID() AS "Codigo", 'OK' AS "Mensaje";
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
CREATE DEFINER=`courseroom_server`@`localhost` PROCEDURE `sp_ObtenerRespuestas`()
BEGIN
	SELECT IdRespuesta, Respuesta, Cliente, DireccionIP, Fecha FROM tb_respuestas 
    ORDER BY IdRespuesta DESC LIMIT 250;
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
CREATE DEFINER=`courseroom_server`@`localhost` PROCEDURE `sp_ObtenerSolicitudes`()
BEGIN
	SELECT IdSolicitud, Solicitud, Cliente, DireccionIP, Fecha FROM tb_solicitudes 
    ORDER BY IdSolicitud DESC LIMIT 250;
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

-- Dump completed on 2022-03-26 21:26:22
