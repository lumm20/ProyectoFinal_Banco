#triggers
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
			set message_Text='Esa direccion ya esta registrada';
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
			set message_Text='Esa direccion ya esta registrada';
	end if;
end $$
delimiter ;
