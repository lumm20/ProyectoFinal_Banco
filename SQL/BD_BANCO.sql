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
    saldo VARCHAR(50),
    id_cliente int not null,
    foreign key (id_cliente) references Clientes,
    PRIMARY KEY (numero_de_cuenta)
);

-- CREATE TABLE Cliente_Cuentas (
--     id_cliente INT not null,
--     id_cuenta INT not null,
--     PRIMARY KEY (id_cliente, id_cuenta),
--     FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente),
--     FOREIGN KEY (id_cuenta) REFERENCES Cuentas(numero_de_cuenta)
-- );

CREATE TABLE Transacciones (
    num_cuenta varchar(10) not null,
    fecha_hora datetime not null,
    monto float not null,
    tipo_transaccion varchar(30) check("transferencia" or "retiro sin cuenta"),
    FOREIGN KEY (num_cuenta) REFERENCES Cuentas(numero_de_cuenta)
);

CREATE TABLE Transaccion_Transferencia (
    num_cuenta_origen varchar(10) not null,
    num_cuenta_destino varchar(10) not null,
    FOREIGN KEY (num_cuenta_origen) REFERENCES Transacciones(num_cuenta)
);

CREATE TABLE Usuario_SinCuenta (
    id_clienteSinCuenta INT not null auto_increment,
    folio VARCHAR(32) not null,
    contraseña VARCHAR(8) not null,
    PRIMARY KEY (id_clienteSinCuenta)
);

CREATE TABLE Transaccion_SinCuenta (
    num_cuenta_cliente varchar(10) not null,
    Id_clienteSinCuenta INT not null,
    estado varchar(20) check("no cobrado" or "cobrado" or "en proceso"),
    FOREIGN KEY (Id_clienteSinCuenta) REFERENCES Clientes_SinCuenta(id_clienteSinCuenta),
    FOREIGN KEY (num_cuenta_cliente) REFERENCES Transacciones(num_cuenta)
);

