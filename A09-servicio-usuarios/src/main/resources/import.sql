INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES('nestor','12345',1,'nestor','ejemplo','nestor@abc.com');
INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES('admin','12345',1,'admin','ejemplo','admin@abc.com');

INSERT INTO `roles` (nombre) VALUES ('ROLE_USER');
INSERT INTO `roles` (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (1,1);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2,2);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2,1);
