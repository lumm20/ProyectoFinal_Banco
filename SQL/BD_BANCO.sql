CREATE DATABASE BANCO;

USE BANCO;

CREATE TABLE Direccion_Cliente (
    id_direccion INT not null auto_increment,
    calle VARCHAR(100) not null,
    codigo_Postal VARCHAR(5) not null,
    colonia VARCHAR(100),
    numero varchar(5),
    PRIMARY KEY (id_direccion)
);

CREATE TABLE Clientes (
    id_cliente INT not null auto_increment,
    nombre VARCHAR(50) not null,
    apellidoP VARCHAR(50) not null,
    apellidoM VARCHAR(50),
    fecha_Nacimiento DATE not null,
    codigo_Direccion INT,
    PRIMARY KEY (id_cliente),
    FOREIGN KEY (codigo_Direccion) REFERENCES Direccion_Cliente(id_direccion)
);

CREATE TABLE Cuentas (
    numero_de_cuenta varchar(10) not null,
    fecha_inicio DATE not null,
    saldo float not null,
    estado varchar(10) not null check(estado in("activa","cancelada")),
    id_cliente int not null,
    foreign key (id_cliente) references Clientes(id_cliente),
    PRIMARY KEY (numero_de_cuenta)
);

CREATE TABLE Transacciones (
	id_transaccion int primary key not null auto_increment,
    num_cuenta varchar(10) not null,
    fecha_hora datetime not null,
    monto float not null,
    tipo_transaccion varchar(30) check(tipo_transaccion in("transferencia","retiro sin cuenta")),
    FOREIGN KEY (num_cuenta) REFERENCES Cuentas(numero_de_cuenta)
);

CREATE TABLE Transaccion_Transferencia (
	id_transaccion int not null,
    num_cuenta_destino varchar(10),
    FOREIGN KEY (id_transaccion) REFERENCES Transacciones(id_transaccion)
);

CREATE TABLE Transaccion_sin_cuenta (
    id_transaccion int not null,
    folio varchar(32) not null,
    estado varchar(20) check(estado in("no cobrado" or "cobrado" or "en proceso")),
    contra varchar(8) not null,
    FOREIGN KEY (id_transaccion) REFERENCES Transacciones(id_transaccion)
);


show tables;
