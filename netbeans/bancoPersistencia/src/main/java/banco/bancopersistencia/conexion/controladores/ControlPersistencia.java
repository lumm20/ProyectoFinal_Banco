/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco.bancopersistencia.conexion.controladores;

import banco.bancodominio.Cliente;
import banco.bancodominio.Cuenta;
import banco.bancopersistencia.conexion.Conexion;
import banco.bancopersistencia.conexion.IConexion;
import banco.bancopersistencia.daos.ClienteDAO;
import banco.bancopersistencia.daos.CuentaDAO;
import banco.bancopersistencia.daos.IClienteDAO;
import banco.bancopersistencia.daos.ICuentaDAO;
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
    String contra = "esme2304";
    IConexion conexionBD = new Conexion(url+"/"+nombreBD, usuario, contra);
    IClienteDAO clienteDAO=new ClienteDAO(conexionBD);
    ICuentaDAO cuentaDAO= new CuentaDAO(conexionBD);
    
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
    public void insertarCliente(ClienteDTO cliente) throws PersistenciaException {
        this.clienteDAO.insertarCliente(cliente);
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
    public void actualizarSaldoCuenta(String num_cuenta, float saldo) throws PersistenciaException {
        this.cuentaDAO.actualizarSaldoCuenta(num_cuenta, saldo);
    }
    
}

