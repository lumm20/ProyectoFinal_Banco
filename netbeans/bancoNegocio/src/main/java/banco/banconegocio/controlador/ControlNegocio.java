/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco.banconegocio.controlador;

import banco.bancodominio.Cliente;
import banco.bancodominio.Cuenta;
import banco.banconegocio.excepciones.NegocioException;
import banco.bancopersistencia.conexion.controladores.ControlPersistencia;
import banco.bancopersistencia.dtos.ClienteDTO;
import banco.bancopersistencia.dtos.CuentaDTO;
import banco.bancopersistencia.excepciones.PersistenciaException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author luiis
 */
public class ControlNegocio implements IControlNegocio{

    ControlPersistencia controlPers= new ControlPersistencia();
    private static final Logger LOG =Logger.getLogger(ControlNegocio.class.getName());
    
    @Override
    public Cliente buscarClientePorId(int id) throws NegocioException {
        Cliente clienteBuscado;
        try{
            clienteBuscado=this.controlPers.buscarClientePorId(id);
            return clienteBuscado;
        }catch(PersistenciaException e){
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public List<Cliente> consultarClientes() throws NegocioException {
        List<Cliente> lista;
        try{
            lista=this.controlPers.consultarClientes();
            return lista;
        }catch(PersistenciaException e){
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public void insertarCliente(String nombre, String apellidoP, String apellidoM,
            String fecha_nacimiento, int id_direccion) throws NegocioException {
        Date fechaNac=Date.valueOf(fecha_nacimiento);
        int edad=this.calcularEdad(fechaNac);
        ClienteDTO cliente=new ClienteDTO(nombre,apellidoP,apellidoM,fechaNac,edad,id_direccion);
        try {
            this.controlPers.insertarCliente(cliente);
        } catch (PersistenciaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    private int calcularEdad(Date fecha_nacimiento){
        Calendar hoy=Calendar.getInstance();
        Calendar fechaNac=Calendar.getInstance();
        fechaNac.setTime(fecha_nacimiento);
        int edad =hoy.get(Calendar.YEAR)-fechaNac.get(Calendar.YEAR);
        if(hoy.get(Calendar.DAY_OF_YEAR)<fechaNac.get(Calendar.DAY_OF_YEAR))
            edad--;
        return edad;
    }
    
//    @Override
//    public void actualizarCliente(String nombre, String apellidoPaterno, String apellidoM,
//            String fecha_nacimiento) throws NegocioException {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

    @Override
    public Cuenta consultarCuentaEspecifica(String numCuenta) throws NegocioException {
        Cuenta cuentaCons;
        try {
            cuentaCons=this.controlPers.consultarCuentaEspecifica(numCuenta);
            return cuentaCons;
        } catch (PersistenciaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public List<Cuenta> consultarTodasCuentas() throws NegocioException {
        List<Cuenta> lista;
        try {
            lista=this.controlPers.consultarTodasCuentas();
            return lista;
        } catch (PersistenciaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public void insertarCuenta(String numCuenta,String fecha_creacion) throws NegocioException {
        Date fechaCreacion=Date.valueOf(fecha_creacion);
        CuentaDTO cuenta =new CuentaDTO(numCuenta, "activa", 0, fechaCreacion);
        try {
            this.controlPers.insertarCuenta(cuenta);
        } catch (PersistenciaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

//    @Override
//    public void actualizarSaldoCuenta(String num_cuenta, float saldo) throws NegocioException {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
    
}
