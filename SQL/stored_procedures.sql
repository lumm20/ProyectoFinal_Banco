#----------------stored procedures------------------
#---------------------------------------------------
#consultar todas las transacciones
delimiter $$
create procedure sp_consultarTransacciones(in numCuenta varchar(10))
begin
	select t.id_transaccion, t.fecha_hora, t.monto,
			tf.num_cuenta_destino as 'destino transferencia',
            tsc.contra as 'contraseña retiro', tsc.estado as 'estado retiro', tsc.folio as 'folio retiro'
    from transacciones t inner join transaccion_transferencia tf
    on t.id_transaccion=tf.id_transaccion inner join transaccion_sin_cuenta tsc
    on tf.id_transaccion=tsc.id_transaccion
    where num_cuenta=numCuenta
    order by t.fecha_hora desc;
end $$
delimiter ;
#---------------------------------------------------
#consultar todas las transacciones por rango de fechas
delimiter $$
create procedure sp_consultarTransaccionesPorRangoFechas(in numCuenta varchar(10), 
	in fecha_inicio datetime,
    in fecha_fin datetime)
begin
	select t.id_transaccion, t.fecha_hora, t.monto,
			tf.num_cuenta_destino as 'destino transferencia',
            tsc.contra as 'contraseña retiro', tsc.estado as 'estado retiro', tsc.folio as 'folio retiro'
    from transacciones t inner join transaccion_transferencia tf
    on t.id_transaccion=tf.id_transaccion inner join transaccion_sin_cuenta tsc
    on tf.id_transaccion=tsc.id_transaccion
    where (num_cuenta=numCuenta) and (fecha_hora between fecha_inicio and fecha_fin)
    order by fecha_hora desc;
end $$
delimiter ;
#---------------------------------------------------
#consultar todos los retiros sin cuenta
delimiter $$
create procedure sp_consultarRetiros(in numCuenta varchar(10))
begin
	select t.id_transaccion, t.fecha_hora, t.monto,
            tsc.contra as 'contraseña retiro', tsc.estado as 'estado retiro', tsc.folio as 'folio retiro'
    from transacciones t inner join transaccion_sin_cuenta tsc
    on t.id_transaccion=tsc.id_transaccion
    where num_cuenta=numCuenta
    order by t.fecha_hora desc;
end $$
delimiter ;
#---------------------------------------------------
#consultar todas las transferencias
delimiter $$
create procedure sp_consultarTransferencias(in numCuenta varchar(10))
begin
	select t.id_transaccion, t.num_cuenta, t.fecha_hora, t.monto,
			tf.num_cuenta_destino as 'destino transferencia'
    from transacciones t inner join transaccion_transferencia tf
    on t.id_transaccion=tf.id_transaccion
    where t.num_cuenta=numCuenta
    order by t.fecha_hora desc;
end $$
delimiter ;
#---------------------------------------------------
#calcula el saldo nuevo a establecer en una cuenta a partir de una transaccion 
delimiter $$
create procedure sp_calcularSaldo(
	in numCuenta varchar(10),
    in monto decimal(18,2),
    in tipoOperacion varchar(10),
    out saldoNuevo decimal(18,2))
begin
	declare saldoActual decimal(18,2);
    select saldo into saldoActual from cuentas where numero_de_cuenta=numCuenta;
    
    if tipoOperacion= "sumar" then
		set saldoNuevo=saldoActual+monto;
    elseif tipoOperacion = "restar" then
		set saldoNuevo=saldoActual-monto;
	end if;
end $$
delimiter ;
#---------------------------------------------------
#procesar una transaccion
delimiter $$
create procedure sp_procesarTransaccion(
	in monto decimal(18,2),
    in fechaHora datetime,
    in numCuentaOrigen varchar(10),
    out idTransaccion int)
begin 
#declarando manejador de excepciones
	declare exit handler for sqlexception
    begin
		rollback;
	end;
    
    start transaction;
    set autocommit=0;
    #agrega el registro de la transaccion de tipo transferencia
    insert into transacciones (num_cuenta, fecha_hora, monto)
    values (numCuentaOrigen, fechaHora, monto);
    
    set idTransaccion=last_insert_id();
    
    commit;
end $$
delimiter ;
#---------------------------------------------------
#procesar una transferencia
delimiter $$
create procedure sp_procesarTransferencia(
	in idTransaccion int,
    in numCuentaDestino varchar(10))
begin
#declarando manejador de excepciones
	declare exit handler for sqlexception
    begin
		rollback;
	end;
    
    start transaction;
    set autocommit=0;
    
    #agrega la transferencia con el id de la transaccion
    insert into transaccion_transferencia values (numCuentaDestino, idTransaccion);
    
    #actualiza la columna 'tipo_transaccion' en la tabla transacciones en el registro con el id del parametro
    update transacciones set tipo_transaccion="transferencia" where id_transaccion=idTransaccion;
    
    #----set @saldoCuentaOrigen=(select saldo from cuentas where numero_de_cuenta=numCuentaOrigen);
    
    #hace el calculo del saldo nuevo de la cuenta origen, es decir, se le resta el monto
    #de la transferencia a su saldo
    call sp_calcularSaldo(numCuentaOrigen, monto,"restar",@saldoNuevo);
    #actualiza el saldo de la cuenta origen en el registro de la tabla de cuentas
    update cuentas set saldo=@saldoNuevo where numero_de_cuenta=numCuentaOrigen;
    #hace el calculo del saldo nuevo de la cuenta destino, es decir, se le suma el monto
    #de la transferencia a su saldo
    call sp_calcularSaldo(numCuentaDestino, monto,"sumar",@saldoNuevo);
    #actualiza el saldo de la cuenta origen en el registro de la tabla de cuentas
    update cuentas set saldo=@saldoNuevo where numero_de_cuenta=numCuentaDestino;
    
    commit;
end $$
delimiter ;
#---------------------------------------------------
#procesar un retiro sin cuenta
delimiter $$
create procedure sp_procesarRetiro(
	in idTransaccion int,
    in folio varchar(32),
    in estado varchar(10),
    in contra varchar(8))
begin
#declarando manejador de excepciones
	declare exit handler for sqlexception
    begin
		rollback;
	end;
    
    start transaction;
    set autocommit=0;
    
    #agrega el retiro con el id de la transaccion
    insert into transaccion_sin_cuenta values (idTransaccion, folio, estado,contra);
    
    #actualiza la columna 'tipo_transaccion' en la tabla transacciones en el registro con el id del parametro
    update transacciones set tipo_transaccion="retiro sin cuenta" where id_transaccion=idTransaccion;
    
    #----set @saldoCuentaOrigen=(select saldo from cuentas where numero_de_cuenta=numCuentaOrigen);
    
    #hace el calculo del saldo nuevo de la cuenta origen, es decir, se le resta el monto
    #del retiro a su saldo
    call sp_calcularSaldo(numCuentaOrigen, monto,"restar",@saldoNuevo);
    #actualiza el saldo de la cuenta origen en el registro de la tabla de cuentas
    update cuentas set saldo=@saldoNuevo where numero_de_cuenta=numCuentaOrigen;
    
    commit;
end $$
delimiter ;