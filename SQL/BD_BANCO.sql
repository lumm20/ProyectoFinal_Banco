CREATE DATABASE BANCO;

USE BANCO;

CREATE TABLE Direccion_Cliente (
    id_direccion INT UNIQUE,
    calle VARCHAR(100),
    codigo_Postal VARCHAR(5),
    colonia VARCHAR(100),
    PRIMARY KEY (id_direccion)
);

CREATE TABLE Clientes (
    id_cliente INT UNIQUE,
    nombre VARCHAR(50),
    apellidoP VARCHAR(50),
    apellidoM VARCHAR(50),
    fecha_Nacimiento DATE,
    edad INT,
    codigo_Direccion INT UNIQUE,
    PRIMARY KEY (id_cliente),
    FOREIGN KEY (codigo_Direccion) REFERENCES Direccion_Cliente(id_direccion)
);

CREATE TABLE Cuentas (
    numero_de_cuenta INT UNIQUE,
    fecha_Inicio DATE,
    saldo VARCHAR(50),
    PRIMARY KEY (numero_de_cuenta)
);

CREATE TABLE Cliente_Cuentas (
    id_cliente INT UNIQUE,
    id_cuenta INT UNIQUE,
    PRIMARY KEY (id_cliente, id_cuenta),
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente),
    FOREIGN KEY (id_cuenta) REFERENCES Cuentas(numero_de_cuenta)
);

CREATE TABLE Transacciones (
    id_transaccion INT UNIQUE,
    id_cliente INT UNIQUE,
    PRIMARY KEY (id_transaccion),
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente)
);

CREATE TABLE Transaccion_Transferencia (
    id_transaccion INT UNIQUE,
    cuenta_origen INT,
    cuenta_destino INT,
    PRIMARY KEY (id_transaccion),
    FOREIGN KEY (id_transaccion) REFERENCES Transacciones(id_transaccion)
);

CREATE TABLE Clientes_SinCuenta (
    id_clienteSinCuenta INT UNIQUE,
    folio VARCHAR(32),
    contraseña VARCHAR(8),
    PRIMARY KEY (id_clienteSinCuenta)
);

CREATE TABLE Transaccion_SinCuenta (
    id_transaccion INT UNIQUE,
    Id_clienteSinCuenta INT UNIQUE,
    PRIMARY KEY (id_transaccion),
    FOREIGN KEY (Id_clienteSinCuenta) REFERENCES Clientes_SinCuenta(id_clienteSinCuenta),
    FOREIGN KEY (id_transaccion) REFERENCES Transacciones(id_transaccion)
);

