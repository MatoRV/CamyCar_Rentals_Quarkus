create table IF NOT EXISTS localidad (
id_localidad integer not null auto_increment,
nombre varchar(255) not null,
primary key (id_localidad)
);

create table IF NOT EXISTS tarifa_transporte (
id_tarifa_transporte integer not null auto_increment,
p14000 integer not null,
p4000 integer not null,
p9000 integer not null,
id_localidad integer not null,
primary key (id_tarifa_transporte)
);

