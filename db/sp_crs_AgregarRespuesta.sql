CREATE PROCEDURE `sp_crs_AgregarRespuesta` (
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
		VALUES (_Respuesta, _Cliente, 
        IF(_FechaRespuesta IS NULL OR 
        LENGTH(_FechaRespuesta) = 0, DATE_FORMAT(NOW(), '%d/%m/%y %T'), _FechaRespuesta));
        SELECT 1 AS "Codigo", 'OK' AS "Mensaje";
       
	END IF;
END