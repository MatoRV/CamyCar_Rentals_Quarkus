CREATE TABLE Maquina IF NOT EXISTS(
    id_maquina INT PRIMARY KEY auto_increment,
    fabricante varchar(50) not null,
    modelo varchar(50) not null,
    capacidad_carga INT,
    id_tipo_maquina INT not null references Tipo_maquina(id_tipo_maquina)
);

CREATE TABLE Tipo_maquina IF NOT EXISTS(
    id_tipo_maquina INT PRIMARY KEY auto_increment,
    nombre varchar(50) not null
);