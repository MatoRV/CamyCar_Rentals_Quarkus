CREATE TABLE IF NOT EXISTS cliente(
    id_cliente int primary key auto_increment,
    nombre varchar(255) not null,
    nombre_usuario varchar(255) not null unique,
    contrasena varchar(255) not null,
    dni_cliente varchar(9) unique not null
);