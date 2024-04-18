CREATE TABLE IF NOT EXISTS reserva(
    id_reserva int primary key auto_increment,
    id_maquina int not null references Maquina(id_maquina),
    id_cliente int not null references Cliente(id_cliente),
    fecha_inicio date not null,
    fecha_fin date not null
);