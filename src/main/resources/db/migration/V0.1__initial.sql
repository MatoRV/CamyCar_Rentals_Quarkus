create table IF NOT EXISTS cliente (id_cliente integer not null auto_increment,
contrasena varchar(255) not null,
dni_cliente varchar(255) not null unique,
nombre varchar(255) not null,
usuario_cliente varchar(255) not null unique,
primary key (id_cliente)
);