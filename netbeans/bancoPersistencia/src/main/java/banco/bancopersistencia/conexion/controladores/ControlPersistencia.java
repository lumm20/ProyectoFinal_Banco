/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco.bancopersistencia.conexion.controladores;

import banco.bancodominio.Cliente;
import banco.bancodominio.Cuenta;
import banco.bancodominio.Transaccion;
import banco.bancopersistencia.conexion.Conexion;
import banco.bancopersistencia.conexion.IConexion;
import banco.bancopersistencia.daos.ClienteDAO;
import banco.bancopersistencia.daos.CuentaDAO;
import banco.bancopersistencia.daos.IClienteDAO;
import banco.bancopersistencia.daos.ICuentaDAO;
import banco.bancopersistencia.daos.ITransaccionDAO;
import banco.bancopersistencia.daos.TransaccionDAO;
import banco.bancopersistencia.dtos.ClienteDTO;
import banco.bancopersistencia.dtos.CuentaDTO;
import banco.bancopersistencia.excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author luiis
 */
public class ControlPersistencia implements IControlPersistencia{


    String url="jdbc:mysql://127.0.0.1:3306";
    String nombreBD="banco";
    String usuario = "root";
    String contra = "moeLISa:22_03";
    IConexion conexionBD = new Conexion(url+"/"+nombreBD, usuario, contra);
    IClienteDAO clienteDAO=new ClienteDAO(conexionBD);
    ICuentaDAO cuentaDAO= new CuentaDAO(conexionBD);
    ITransaccionDAO transaccionDAO=new TransaccionDAO(conexionBD);
    
    @Override
    public Cliente buscarClientePorId(int id) throws PersistenciaException {
        Cliente cliente=this.clienteDAO.buscarClientePorId(id);
        return cliente;
    }

    @Override
    public List<Cliente> consultarClientes() throws PersistenciaException {
        List<Cliente> lista=this.clienteDAO.listarClientes();
        return lista;
    }

    @Override
    public int insertarCliente(ClienteDTO cliente,String usuario, String contra) throws PersistenciaException {
        int id=this.clienteDAO.insertarCliente(cliente,usuario,contra);
        return id;
    }
    
    @Override
    public int agregarDireccionCliente(String calle, String colonia, String codigo_postal, String numero)
            throws PersistenciaException{
        int codigo_direccion=this.clienteDAO.agregarDireccionCliente(calle, colonia, codigo_postal, numero);
        return codigo_direccion;
    }
    
    @Override
    public void actualizarCliente(Cliente cliente) throws PersistenciaException {
        this.clienteDAO.actualizarCliente(cliente);
    }

    
    @Override
    public Cuenta consultarCuentaEspecifica(String numCuenta) throws PersistenciaException {
        Cuenta cuenta=this.cuentaDAO.buscarCuentaPorNumero(numCuenta);
        return cuenta;
    }

    @Override
    public List<Cuenta> consultarTodasCuentas() throws PersistenciaException {
        List<Cuenta> lista=this.cuentaDAO.listarCuentas();
        return lista;
    }

    @Override
    public void insertarCuenta(CuentaDTO cuenta) throws PersistenciaException {
        this.cuentaDAO.insertarCuenta(cuenta);
    }

    @Override 
    public void cancelarCuenta(String numCuenta)throws PersistenciaException{
        this.cuentaDAO.cancelarCuenta(numCuenta);
    }

    @Override
    public int procesarTransaccion(Transaccion transaccion) throws PersistenciaException {
        int idTransaccion=this.transaccionDAO.procesarTransaccion(transaccion);
        return idTransaccion;
    }

    @Override
    public void procesarTransferencia(int idTransaccion, String numCuentaDestino) throws PersistenciaException {
        this.transaccionDAO.procesarTransferencia(idTransaccion, numCuentaDestino);
    }

    @Override
    public void procesarRetiroSinCuenta(int idTransaccion, String folio, String contra) throws PersistenciaException {
        this.transaccionDAO.procesarRetiroSinCuenta(idTransaccion, folio, contra);
    }

    @Override
    public void actualizarDireccion(int idDireccion, String calle, String colonia, String codigo_postal, String numero) 
            throws PersistenciaException {
        this.clienteDAO.actualizarDireccionCliente(idDireccion, calle, colonia, codigo_postal, numero);
    }

    @Override
    public int autenticarUsuario(String usuario, String contra) throws PersistenciaException {
        int idCliente=this.clienteDAO.autenticar(usuario, contra);
        return idCliente;
    }

    @Override
    public int obtenerIdDireccion(int idCliente) throws PersistenciaException {
        int idDireccion=this.clienteDAO.obtenerIdDireccion(idCliente);
        return idDireccion;
    }

    @Override
    public String obtenerNumCuenta(int idCliente) throws PersistenciaException {
        String numero=this.cuentaDAO.getNumCuenta(idCliente);
        return numero;
    }

}
