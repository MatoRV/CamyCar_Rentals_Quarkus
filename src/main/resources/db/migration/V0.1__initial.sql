create table IF NOT EXISTS usuario (id_usuario integer not null auto_increment,
contrasena varchar(255) not null,
dni_usuario varchar(255) not null unique,
nombre varchar(255) not null,
apellido1 varchar(255) not null,
apellido2 varchar(255) not null,
correo varchar(255) not null unique,
primary key (id_usuario)
);