create table IF NOT EXISTS reserva (
id_reserva integer not null auto_increment,
direccion varchar(255) not null,
fecha_fin date not null,
fecha_inicio date not null,
id_usuario integer not null,
id_maquina integer not null,
primary key (id_reserva)
);
