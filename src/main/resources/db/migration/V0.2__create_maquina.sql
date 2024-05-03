create table IF NOT EXISTS maquina (
id_maquina integer not null auto_increment,
capacidad_carga integer not null,
estado enum('D','N','A','M') not null,
fabricante varchar(255) not null,
modelo varchar(255) not null,
id_tipo_maquina integer not null,
primary key (id_maquina)
);

create table IF NOT EXISTS tipo_maquina(
    id_tipo_maquina INT not null auto_increment,
    nombre varchar(50) not null,
    primary key (id_tipo_maquina)
);
