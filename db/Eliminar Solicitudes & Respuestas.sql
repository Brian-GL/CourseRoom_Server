-- Eliminar solicitudes y respuestas:
DELETE FROM tb_solicitudes WHERE IdSolicitud > 0;
DELETE FROM tb_respuestas WHERE IdRespuesta > 0;

ALTER TABLE tb_solicitudes AUTO_INCREMENT = 1;
ALTER TABLE tb_respuestas AUTO_INCREMENT = 1;
