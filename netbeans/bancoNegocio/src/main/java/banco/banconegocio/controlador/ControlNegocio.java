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
            Date fecha_nacimiento, int id_direccion) throws NegocioException {
        //ClienteDTO cliente=new ClienteDTO(nombre,apellidoP,apellidoM,fecha_nacimiento,id_direccion);
        try {
            
        } catch (Exception e) {
        }
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void calcularEdad(Date fecha_nacimiento){
       // Date date=new Date
//        Calendar hoy=Calendar
    }
    
    @Override
    public void actualizarCliente(String nombre, String apellidoPaterno, String apellidoM,
            Date fecha_nacimiento) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Cuenta consultarCuentaEspecifica(String numCuenta) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Cuenta> consultarTodasCuentas() throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insertarCuenta(CuentaDTO cuenta) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizarSaldoCuenta(String num_cuenta, float saldo) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
