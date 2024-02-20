#triggers
#---------------------------------------------------
#validaciones antes de procesar una transaccion
delimiter //
create trigger tr_antes_procesar_transaccion
before insert on transacciones
for each row
begin
	declare saldoDisponible decimal(18,2);
    declare numCuenta varchar(10);
    declare msjError varchar(255);
    
    select numero_de_cuenta into numCuenta from cuentas where numero_de_cuenta= NEW.num_cuenta;
    
    if numCuenta is null then
		signal sqlstate '45000' 
		set message_text='La cuenta que ingreso no esta registrada';
	else
		select saldo into saldoDisponible from cuentas where numero_de_cuenta=NEW.num_cuenta;
		if saldoDisponible < NEW.monto then
			signal sqlstate '45000' 
            set message_text='No cuenta con el saldo suficiente para procesar la transaccion';
		end if;
	end if;
end //
delimiter ;
drop trigger tr_antes_procesar_transaccion;
#---------------------------------------------------
#validaciones antes de procesar una transferencia
delimiter $$
create trigger tr_antes_procesar_transferencia
before insert on transaccion_transferencia
for each row
begin
	declare idTran int;
    declare numCuentaDestino varchar(10);
    declare msjError varchar(255);
    
    select id_transaccion into idTran from transacciones where id_transaccion=new.id_transaccion;
    select numero_de_cuenta into numCuentaDestino from cuentas where numero_de_cuenta=new.num_cuenta_destino;
    
	if idTran is null then
		signal sqlstate '45000'
		set message_text='La transaccion no esta registrada';
	end if;
	if numCuentaDestino is null then
		signal sqlstate '45000'
		set message_text='La cuenta destino de la transferencia no esta registrada';
	end if;
end $$
delimiter ;
#---------------------------------------------------
#validaciones antes de procesar un retiro
delimiter $$
create trigger tr_antes_procesar_retiro
before insert on transaccion_sin_cuenta
for each row
begin
	declare idTran int;
    
    select id_transaccion into idTran from transacciones where id_transaccion=new.id_transaccion;
    
	if idTran is null then
		signal sqlstate '45000'
		set message_text='La transaccion no esta registrada';
	end if;
end $$
delimiter ;
#---------------------------------------------------
#validaciones antes de actualizar una cuenta
delimiter $$
create trigger tr_antes_actualizar_cuenta
before update on cuentas
for each row
begin
	#valida que el saldo a actualizar no sea un numero negativo
	if new.saldo <0.00 then
		signal sqlstate '45000' 
			set message_text='El saldo no puede ser menor a 0';
	end if;
    #valida que no se cancele una cuenta que aun tenga dinero
    if new.estado = "cancelada" and old.saldo>0.00 then
		signal sqlstate '45000' 
			set message_text='No puede cancelar una cuenta con saldo en ella';
	end if;
end $$
delimiter ;
	
#---------------------------------------------------
#validaciones antes de agregar una direccion
delimiter $$
create trigger tr_antes_agregar_direccion
before insert on direccion_cliente
for each row 
begin
	declare direccion varchar(255);
    set direccion= concat(new.calle,new.codigo_Postal,new.colonia, new.numero);
    
    if direccion in (select concat(calle,codigo_Postal,colonia,numero) from direccion_cliente) then
		signal sqlstate '45000'
			set message_Text='Ya existe una direccion registrada con esa informacion';
	end if;
end $$
delimiter ;
#---------------------------------------------------
#validaciones antes de actualizar una direccion
delimiter $$
create trigger tr_antes_actualizar_direccion
before update on direccion_cliente
for each row 
begin
	declare direccion varchar(255);
    set direccion= concat(new.calle,new.codigo_Postal,new.colonia, new.numero);
    
    if direccion in (select concat(calle,codigo_Postal,colonia,numero) from direccion_cliente) then
		signal sqlstate '45000'
			set message_Text='Ya existe una direccion registrada con esa informacion';
	end if;
end $$
delimiter ;
#---------------------------------------------------
#validaciones antes de agregar un cliente
delimiter $$
create trigger tr_antes_agregar_cliente
before insert on clientes
for each row 
begin
	declare cliente varchar(255);
    set cliente= concat(new.nombre,new.apellidoP,new.apellidoM, new.fecha_Nacimiento);
    #si ya hay un cliente registrado con la misma informacion
    if cliente in (select concat(nombre,apellidoP,apellidoM,fecha_Nacimiento) from clientes) then
		signal sqlstate '45000'
			set message_Text='Ya existe un cliente registrado con esa informacion';
	end if;
    #si la edad del cliente es menor a 18
    if new.edad<18 then
		signal sqlstate '45000'
			set message_text='No se puede registrar un cliente menor de edad';
	end if;
    #si se ingresa una edad mayor a 100
    if new.edad>100 then
		signal sqlstate '45000'
			set message_text='Edad excedente';
	end if;
end $$
delimiter ;
#---------------------------------------------------
#validaciones antes de agregar un cliente
delimiter $$
create trigger tr_antes_agregar_usuario
before insert on usuarios_clientes
for each row 
begin
	if new.usuario in (select usuario from usuarios_clientes) then
		signal sqlstate '45000' set message_text='Ese nombre de usuario ya esta registrado';
	end if;
end $$
delimiter ;    
