CREATE PROCEDURE `sp_crs_ObtenerRespuestas`()
BEGIN
	SELECT IdRespuesta, Respuesta, Cliente, FechaRespuesta FROM tb_respuestas 
    ORDER BY FechaRespuesta DESC LIMIT 50;
END