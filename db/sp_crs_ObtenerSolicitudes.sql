CREATE PROCEDURE `sp_crs_ObtenerSolicitudes`()
BEGIN
	SELECT IdSolicitud, Solicitud, Cliente, FechaSolicitud FROM tb_solicitudes 
    ORDER BY FechaSolicitud DESC LIMIT 50;
END