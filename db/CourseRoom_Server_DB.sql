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
-- Table structure for table `tb_courseroom`
--

DROP TABLE IF EXISTS `tb_courseroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_courseroom` (
  `Tabla` varchar(50) NOT NULL,
  `CantidadRegistros` int NOT NULL DEFAULT '0',
  `FechaActualizacion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Tabla`),
  UNIQUE KEY `Tabla_Courseroom_UNIQUE` (`Tabla`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_courseroom`
--

LOCK TABLES `tb_courseroom` WRITE;
/*!40000 ALTER TABLE `tb_courseroom` DISABLE KEYS */;
INSERT INTO `tb_courseroom` VALUES ('TB_PRUEBA',10,'01/02/22 19:47:24');
/*!40000 ALTER TABLE `tb_courseroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_metodos`
--

DROP TABLE IF EXISTS `tb_metodos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_metodos` (
  `Metodo` varchar(50) NOT NULL,
  PRIMARY KEY (`Metodo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_metodos`
--

LOCK TABLES `tb_metodos` WRITE;
/*!40000 ALTER TABLE `tb_metodos` DISABLE KEYS */;
INSERT INTO `tb_metodos` VALUES ('METODO 1'),('METODO 2');
/*!40000 ALTER TABLE `tb_metodos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_respuestas`
--

DROP TABLE IF EXISTS `tb_respuestas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_respuestas` (
  `IdRespuesta` int NOT NULL AUTO_INCREMENT,
  `Respuesta` varchar(100) NOT NULL,
  `Cliente` varchar(45) NOT NULL,
  `FechaRespuesta` varchar(75) NOT NULL,
  PRIMARY KEY (`IdRespuesta`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COMMENT='Tabla para guardar las respuestas realizadas del servidor hacia los clientes';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_respuestas`
--

LOCK TABLES `tb_respuestas` WRITE;
/*!40000 ALTER TABLE `tb_respuestas` DISABLE KEYS */;
INSERT INTO `tb_respuestas` VALUES (1,'IMAGEN ENVIADA Y OBTENIDA DESDE HTTPS://PICSUM.PHOTOS/500/700','C6CC0026-C2C2-11E8-B5F5-E86A64292601','lunes 14/02/2022 08:44:17 p. m.'),(2,'IMAGEN ENVIADA Y OBTENIDA DESDE: \nHTTPS://PICSUM.PHOTOS/500/700','C6CC0026-C2C2-11E8-B5F5-E86A64292601','lunes 14/02/2022 08:45:32 p. m.'),(3,'IMAGEN ENVIADA Y OBTENIDA DESDE: HTTPS://PICSUM.PHOTOS/500/700','C6CC0026-C2C2-11E8-B5F5-E86A64292601','lunes 14/02/2022 08:46:21 p. m.'),(4,'FECHA & HORA: [2022, 2, 14, 20, 47, 27]','C6CC0026-C2C2-11E8-B5F5-E86A64292601','lunes 14/02/2022 08:47:27 p. m.');
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
  `Solicitud` varchar(100) NOT NULL,
  `Cliente` varchar(45) NOT NULL,
  `FechaSolicitud` varchar(75) NOT NULL,
  PRIMARY KEY (`IdSolicitud`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COMMENT='Tabla para guardar las solicitudes de los clientes hacia el servidor';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_solicitudes`
--

LOCK TABLES `tb_solicitudes` WRITE;
/*!40000 ALTER TABLE `tb_solicitudes` DISABLE KEYS */;
INSERT INTO `tb_solicitudes` VALUES (1,'OBTENER IMAGEN INICIO SESIÓN','C6CC0026-C2C2-11E8-B5F5-E86A64292601','lunes 14/02/2022 08:44:15 p. m.'),(2,'OBTENER IMAGEN INICIO SESIÓN','C6CC0026-C2C2-11E8-B5F5-E86A64292601','lunes 14/02/2022 08:45:30 p. m.'),(3,'OBTENER IMAGEN INICIO SESIÓN','C6CC0026-C2C2-11E8-B5F5-E86A64292601','lunes 14/02/2022 08:46:18 p. m.'),(4,'OBTENER FECHA & HORA DEL SERVIDOR','C6CC0026-C2C2-11E8-B5F5-E86A64292601','lunes 14/02/2022 08:47:27 p. m.');
/*!40000 ALTER TABLE `tb_solicitudes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'courseroom_server'
--

--
-- Dumping routines for database 'courseroom_server'
--
/*!50003 DROP PROCEDURE IF EXISTS `sp_ActualizarCantidadRegistrosTablaCourseroom` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ActualizarCantidadRegistrosTablaCourseroom`(
	IN _Tabla VARCHAR(50),
    IN _CantidadRegistros INT,
    OUT Codigo BIT,
    OUT Mensaje VARCHAR(100)
)
BEGIN

	IF _Tabla IS NULL OR LENGTH(_Tabla) = 0 OR 
    _CantidadRegistros IS NULL THEN
		SELECT 0 AS "Codigo", 'La Tabla Es Nula O La Cantidad De Registros' AS "Mensaje";
	ELSE
		
        IF  EXISTS (SELECT Tabla FROM tb_courseroom WHERE Tabla = _Tabla) THEN
        
			IF _CantidadRegistros < 0 THEN
				SELECT 0 AS "Codigo", 'La Cantidad De Registros Es Menor A Cero' AS "Mensaje";
            ELSE 
				UPDATE tb_courseroom  SET CantidadRegistros = _CantidadRegistros
                WHERE Tabla = _Tabla;
                SELECT 1 AS "Codigo", 'OK' AS "Mensaje";
            END IF;
		ELSE
			SELECT 0 AS "Codigo", 'La Tabla No Se Encuentra Registrada' AS "Mensaje";
        END IF;
        
    
    END IF;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_ActualizarFechaActualizacionTablaCourseRoom` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ActualizarFechaActualizacionTablaCourseRoom`(
	IN _Tabla VARCHAR(50),
    IN _FechaActualizacion VARCHAR(50),
    OUT Codigo BIT,
    OUT Mensaje VARCHAR(100)
)
BEGIN

	IF _Tabla IS NULL OR LENGTH(_Tabla) = 0  THEN
		SELECT 0 AS "Codigo", 'La Tabla Es Nula' AS "Mensaje";
	ELSE
		
        IF  EXISTS (SELECT Tabla FROM tb_courseroom WHERE Tabla = _Tabla) THEN
        
			UPDATE tb_courseroom  SET FechaActualizacion  = UPPER(IF(_FechaActualizacion IS NULL OR LENGTH(_FechaActualizacion) = 0 ,
            DATE_FORMAT(NOW(), '%d/%m/%y %T'),
            _FechaActualizacion))
			WHERE Tabla = _Tabla;
			SELECT 1 AS "Codigo", 'OK' AS "Mensaje";
            
		ELSE
			SELECT 0 AS "Codigo", 'La Tabla No Se Encuentra Registrada' AS "Mensaje";
        END IF;
        
    
    END IF;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_AgregarCourseRoom` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_AgregarCourseRoom`(
	IN _Tabla VARCHAR(50),
    OUT Codigo BIT,
    OUT Mensaje VARCHAR(100)
)
BEGIN

	IF _Tabla IS NULL THEN
		SELECT 0 AS "Codigo", 'La Tabla Es Nula' AS "Mensaje";
	ELSE
		
        IF NOT EXISTS (SELECT Tabla FROM tb_courseroom WHERE Tabla = _Tabla) THEN
			INSERT INTO tb_courseroom(Tabla) VALUES(UPPER(_Tabla));
            SELECT 1 AS "Codigo", 'OK' AS Mensaje;
		ELSE
			SELECT 0 AS "Codigo", 'La Tabla Ya Se Encuentra Registrada' AS "Mensaje";
        END IF;
        
    
    END IF;
    

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_AgregarMetodo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_AgregarMetodo`(
	IN _Metodo VARCHAR(50),
	OUT Codigo BIT,
    OUT Mensaje VARCHAR(100)
)
BEGIN

	IF _Metodo IS NULL OR LENGTH(_Metodo) = 0  THEN
		SELECT 0 AS "Codigo", 'El Metodo Es Nulo' AS Mensaje;
    ELSE
		
        IF EXISTS (SELECT Metodo FROM tb_metodos WHERE Metodo = _Metodo) THEN
			SELECT 0 AS "Codigo", 'El Metodo Ya Se Encuentra Registrado' AS Mensaje;
		ELSE
			INSERT INTO tb_metodos(Metodo) VALUES (UPPER(_Metodo));
            SELECT 1 AS "Codigo", 'OK' AS Mensaje;
        END IF;
    END IF;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
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
	IN _Respuesta VARCHAR(100),
    IN _Cliente VARCHAR(45),
    IN _FechaRespuesta VARCHAR(75)
)
BEGIN

	IF _Respuesta IS NULL OR LENGTH(_Respuesta) = 0 
		OR  _Cliente IS NULL OR LENGTH(_Cliente) = 0  THEN
        SELECT 0 AS "Codigo", 'La Respuesta O El Cliente Tienen Valores Nulos' AS "Mensaje";
	ELSE 
		
        INSERT INTO tb_respuestas(Respuesta, Cliente, FechaRespuesta)
		VALUES (UPPER(_Respuesta), UPPER(_Cliente), 
        IF(_FechaRespuesta IS NULL OR 
        LENGTH(_FechaRespuesta) = 0, DATE_FORMAT(NOW(), '%y/%m/%d %T'), _FechaRespuesta));
        SELECT 1 AS "Codigo", 'OK' AS "Mensaje";
       
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
	IN _Solicitud VARCHAR(100),
	IN _Cliente VARCHAR(45),
	IN _FechaSolicitud VARCHAR(75)
)
BEGIN

	IF _Solicitud IS NULL OR LENGTH(_Solicitud) = 0 
		OR  _Cliente IS NULL OR LENGTH(_Cliente) = 0  THEN
        SELECT 0 AS "Codigo", 'La Solicitud O El Cliente Tienen Valores Nulos' AS "Mensaje";
	ELSE
    
		INSERT INTO tb_solicitudes(Solicitud, Cliente, FechaSolicitud)
		VALUES (UPPER(_Solicitud), UPPER(_Cliente), 
        IF(_FechaSolicitud IS NULL OR 
        LENGTH(_FechaSolicitud) = 0, DATE_FORMAT(NOW(), '%y/%m/%d %T'), _FechaSolicitud));
        
		SELECT 1 AS "Codigo", 'OK' AS "Mensaje";
        
	END IF;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_AgregarTablaCourseRoom` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_AgregarTablaCourseRoom`(
	IN _Tabla VARCHAR(50),
    OUT Codigo BIT,
    OUT Mensaje VARCHAR(100)
)
BEGIN

	IF _Tabla IS NULL OR LENGTH(_Tabla) = 0  THEN
		SELECT 0 AS "Codigo", 'La Tabla Es Nula' AS "Mensaje";
	ELSE
		
        IF NOT EXISTS (SELECT Tabla FROM tb_courseroom WHERE Tabla = _Tabla) THEN
			INSERT INTO tb_courseroom(Tabla) VALUES(UPPER(_Tabla));
            SELECT 1 AS "Codigo", 'OK' AS Mensaje;
		ELSE
			SELECT 0 AS "Codigo", 'La Tabla Ya Se Encuentra Registrada' AS "Mensaje";
        END IF;
        
    
    END IF;
    

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_ObtenerMetodos` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ObtenerMetodos`()
BEGIN
	SELECT *FROM tb_metodos;
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

	SELECT IdRespuesta, Respuesta, Cliente, FechaRespuesta FROM tb_respuestas 
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
	SELECT IdSolicitud, Solicitud, Cliente, FechaSolicitud FROM tb_solicitudes 
    ORDER BY FechaSolicitud DESC LIMIT 20;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_ObtenerTablasCourseRoom` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ObtenerTablasCourseRoom`()
BEGIN
	SELECT Tabla, CantidadRegistros, 
    IF(FechaActualizacion IS NULL, 'NO ACTUALIZADO', FechaActualizacion) AS "FechaActualizacion" 
    FROM tb_courseroom;
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

-- Dump completed on 2022-02-14 20:48:41
