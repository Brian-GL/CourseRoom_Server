CREATE PROCEDURE `sp_crs_AgregarSolicitud`(
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
		VALUES (_Solicitud, _Cliente, 
        IF(_FechaSolicitud IS NULL OR 
        LENGTH(_FechaSolicitud) = 0, DATE_FORMAT(NOW(), '%d/%m/%y %T'), _FechaSolicitud));
        
		SELECT 1 AS "Codigo", 'OK' AS "Mensaje";
        
	END IF;

END