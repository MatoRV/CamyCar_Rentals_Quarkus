--- Cliente ---
INSERT INTO cliente (id_cliente,contrasena,dni_cliente,nombre,usuario_cliente) VALUES (1,'1234','11111111C','Fernando Alonso','33');
INSERT INTO cliente (id_cliente,contrasena,dni_cliente,nombre,usuario_cliente) VALUES (2,'1234','22222222C','Rodolfo elfofo','fofo');
INSERT INTO cliente (id_cliente,contrasena,dni_cliente,nombre,usuario_cliente) VALUES (3,'1234','33333333C','Juan el donjuan','donjuan');
INSERT INTO cliente (id_cliente,contrasena,dni_cliente,nombre,usuario_cliente) VALUES (4,'1234','44444444C','Martín Martín','Martin');

-- Tipo maquina --
INSERT INTO Tipo_maquina (id_tipo_maquina,nombre) VALUES (1,'Torito');
INSERT INTO Tipo_maquina (id_tipo_maquina,nombre) VALUES (2,'Elevadora');
INSERT INTO Tipo_maquina (id_tipo_maquina,nombre) VALUES (3,'Carretilla');

-- maquina --
INSERT INTO maquina (id_maquina,capacidad_carga,estado,fabricante,modelo,id_tipo_maquina) VALUES (1,2500,'D','F1','M2',1);
INSERT INTO maquina (id_maquina,capacidad_carga,estado,fabricante,modelo,id_tipo_maquina) VALUES (2,1500,'D','F1','M3',3);
INSERT INTO maquina (id_maquina,capacidad_carga,estado,fabricante,modelo,id_tipo_maquina) VALUES (3,1000,'D','F2','M1',2);
INSERT INTO maquina (id_maquina,capacidad_carga,estado,fabricante,modelo,id_tipo_maquina) VALUES (4,2000,'D','F1','M1',1);