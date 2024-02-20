#----------------stored procedures------------------
#---------------------------------------------------
#consultar todas las transacciones
delimiter $$
create procedure sp_consultarTransacciones(in numCuenta varchar(10))
begin
	select t.id_transaccion, t.fecha_hora, t.monto, t.num_cuenta,
			tf.num_cuenta_destino as 'destino transferencia',
            tsc.contra as 'contraseña retiro', tsc.estado as 'estado retiro', tsc.folio as 'folio retiro'
    from transacciones t inner join transaccion_transferencia tf
    on t.id_transaccion=tf.id_transaccion inner join transaccion_sin_cuenta tsc
    on tf.id_transaccion=tsc.id_transaccion
    where t.num_cuenta=numCuenta
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
	select t.id_transaccion, t.fecha_hora, t.monto, t.tipo_transaccion, t.num_cuenta,
			tf.num_cuenta_destino as 'destino_transferencia',
            tsc.contra as 'contraseña_retiro', tsc.estado as 'estado_retiro', tsc.folio as 'folio_retiro'
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
	declare mensajeError varchar(255);
	#declarando manejador de excepciones	
	declare exit handler for sqlexception
    begin
		rollback;
        set mensajeError='hubo un error al procesar la transferencia';
        select mensajeError;
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
	declare mensajeError varchar(255);
	#declarando manejador de excepciones
	declare exit handler for sqlexception
    begin
		rollback;
        set mensajeError='hubo un error al procesar la transferencia';
        select mensajeError;
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
	declare mensajeError varchar(255);
	#declarando manejador de excepciones
	declare exit handler for sqlexception
    begin
		rollback;
        set mensajeError='hubo un error al procesar el retiro';
        select mensajeError;
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
#---------------------------------------------------
#agregar una direccion de un cliente nuevo
delimiter $$
create procedure sp_agregar_direccion(
	in calleD varchar(100),
    in codigoPostal varchar(5),
    in col varchar(100),
    in num varchar(5),
    out idDireccion int)
begin
	insert into direccion_cliente (calle, codigo_postal,colonia,numero)
    values (calleD, codigoPostal, col, num);
    
    set idDireccion=last_insert_id();
end $$
delimiter ;
#---------------------------------------------------
#actualiza una direccion
delimiter $$
create procedure sp_actualizar_direccion(
	in calleD varchar(100),
    in codigoPostal varchar(5),
    in col varchar(100),
    in num varchar(5),
    in idDireccion int)
begin
	#valida que los valores ingresados no sean null
    if idDireccion is null then
		signal sqlstate '45000'
			set message_text='Debe especificar el id de la direccion a actualizar';
	elseif idDireccion not in (select id_direccion from direccion_cliente) then
		signal sqlstate '45000'
			set message_text='No existe una direccion con el id especificado';
	else
		if calleD is not null then
			update direccion_cliente set calle=calleD where id_direccion=idDireccion;
		end if;
		if codigoPostal is not null then
			update direccion_cliente set codigo_Postal=codigoPostal where id_direccion=idDireccion;
		end if;
		if col is not null then
			update direccion_cliente set colonia=col where id_direccion=idDireccion;
		end if;
		if num is not null then
			update direccion_cliente set numero=num where id_direccion=idDireccion;
		end if;
    end if;
end $$
delimiter ;
#---------------------------------------------------
#agrega un cliente y su respectivo usuario
delimiter $$
create procedure sp_agregar_cliente(
	in nombreN varchar(50),
    in apellidoPN varchar(50),
    in apellidoMN varchar(50),
    in fechaNac date,
    in edadN int,
    in usuarioN varchar(20),
    in contraN varchar(10),
    out mensaje varchar(100),
    out idClienteN int)
begin
	declare mensajeError varchar(100);
	declare exit handler for sqlexception
    begin
		rollback;
        set mensajeError='hubo un error al agregar el cliente';
        set mensaje= mensajeError;
	end;
    
    start transaction;
    set autocommit=0;
    
    insert into clientes (nombre,apellidoP,apellidoM,fecha_Nacimiento,edad)
    values (nombreN,apellidoPN, apellidoMN, fechaNac,edadN);
    
    set @idCliente=last_insert_id();
    insert into usuarios_clientes values (@idCliente, usuarioN, contraN);
    
    set mensaje=null;
    set idClienteN=@idCliente;
    commit;
end $$
delimiter ;
#---------------------------------------------------
#actualiza un usuario
delimiter $$
create procedure sp_actualizar_cliente(
	in idCliente int,
    in nombreN varchar(50),
    in apellidoPN varchar(50),
    in apellidoMN varchar(50),
    in fechaNac date,
    in codigoDir int)
begin
	if idCliente=0 then
		signal sqlstate '45000' set message_text='Debe especificar un id';
    elseif idCliente not in (select id_cliente from clientes) then
		signal sqlstate '45000' set message_text='No hay un cliente registrado con ese id';
	else
		if nombreN is not null then
			update clientes set nombre=nombreN where id_cliente=idCliente;
		end if;
		if apellidoPN is not null then
			update clientes set apellidoP=apellidoPN where id_cliente=idCliente;
		end if;
		if apellidoMN is not null then
			update clientes set apellidoM=apellidoMN where id_cliente=idCliente;
		end if;
		if fechaNac is not null then
			update clientes set fecha_Nacimiento=fechaNac where id_cliente=idCliente;
		end if;
    end if;
end $$
delimiter ;

#---------------------------------------------------
#autenticacion de un usuario
delimiter $$
create procedure sp_autenticar_usuario(
	in usuarioB varchar(20),
    in contraB varchar(10),
    out idCliente int)
begin
	declare contraCorrecta varchar(10);
	if usuarioB in (select usuario from usuarios_clientes) then
		select contra into contraCorrecta from usuarios_clientes where usuario=usuarioB;
        if contraB=contraCorrecta then
			select id_cliente into idCliente from usuarios_clientes where usuario=usuarioB;
		else 
			signal sqlstate '45000' set message_text ='Ingreso una contraseña incorrecta';
		end if;
	else
		signal sqlstate '45000' set message_text ='Ingreso un usuario que no existe';
	end if;
end $$
delimiter ;
#---------------------------------------------------
#generar un deposito
delimiter $$
create procedure sp_procesar_deposito(
	in monto decimal(18,2),
    in numCuenta varchar(10))
begin
	call sp_calcularSaldo(numCuenta, monto, "sumar",@saldoNuevo);
	update cuentas set saldo=@saldoNuevo where numero_de_cuenta=numCuenta;
end $$
delimiter ;
#---------------------------------------------------
#generar una cuenta
delimiter $$
create procedure sp_crear_num_cuenta(out numCuenta varchar(100))
begin
	declare it int;
    declare numero varchar(100);
    declare rndm int;
    set it=1;
    set numero='';
    
    loop_generar_num:loop
		if it>10 then
			leave loop_generar_num;
		end if;
        select floor(1+rand()*9) into rndm;
        set numero=concat(numero,rndm);
        set it=it+1;
        iterate loop_generar_num;
	end loop;
    set numCuenta=numero;
end $$
delimiter ;