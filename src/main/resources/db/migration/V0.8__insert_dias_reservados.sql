create table IF NOT EXISTS dia_reservado (
id_dia_reservado integer not null auto_increment,
id_maquina integer not null,
dia date not null,
primary key (id_dia_reservado)
);