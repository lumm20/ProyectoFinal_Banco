/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package banco.bancopersistencia.conexion.controladores;

import banco.bancodominio.Cliente;
import banco.bancodominio.Cuenta;
import banco.bancopersistencia.dtos.ClienteDTO;
import banco.bancopersistencia.dtos.CuentaDTO;
import banco.bancopersistencia.excepciones.PersistenciaException;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author luiis
 */
public interface IControlPersistencia {
    /**
     * Realiza una consulta de un cliente con el id especificado en el parametro
     *
     * @param id del cliente a buscar
     * @return el cliente obtenido
     * @throws PersistenciaException en caso de que no se haya encontrado el
     * cliente o si ocurre un error de base de datos al realizar la consulta
     */
    public Cliente buscarClientePorId(int id) throws PersistenciaException;

    /**
     * Realiza una consulta de todos los clientes registrados
     *
     * @return una lista de todos los clientes obtenidos
     * @throws PersistenciaException en caso de que no haya clientes registrados o 
     * en caso de que ocurra un error de base de datos al realizar la consulta
     */
    public List<Cliente> consultarClientes() throws PersistenciaException;

    /**
     * Agrega un registro de un cliente a la base de datos
     * @param cliente a registrar
     * @return el id del cliente agregado, 0 si no se pudo agregar
     * @throws PersistenciaException en caso de que ocurra un error de base de datos al agregar al cliente
     */
    public int insertarCliente(ClienteDTO cliente) throws PersistenciaException;

    /**
     * Agrega la direccion de un cliente a la base de datos
     * @param calle de la direccion
     * @param colonia de la direccion
     * @param codigo_postal del cliente
     * @param numero de la casa/departamento/edificio
     * @return el id auto-generado de la direccion
     * @throws PersistenciaException 
     */
    public int agregarDireccionCliente(String calle, String colonia, String codigo_postal, String numero)
            throws PersistenciaException;
    /**
     * Actualiza la informacion personal de un cliente, a excepcion de su id de cliente
     * @param cliente con la informacion a actualizar
     * @throws PersistenciaException en caso de que ocurra un error de base de datos al actualizar al cliente
     */
    public void actualizarCliente(Cliente cliente) throws PersistenciaException;
    
    /**
     * Realiza una consulta de una cuenta con el num_cuenta especificado en el parametro
     *
     * @param numCuenta de la cuenta a buscar
     * @return la cuenta obtenida
     * @throws PersistenciaException en caso de que no se haya encontrado la
     * cuenta o si ocurre un error de base de datos al realizar la consulta
     */
    public Cuenta consultarCuentaEspecifica(String numCuenta) throws PersistenciaException;
    
    /**
     * Realiza una consulta de todas las cuentas registradas
     *
     * @return una lista de todas las cuentas obtenidos
     * @throws PersistenciaException en caso de que no haya cuentas registradas o 
     * en caso de que ocurra un error de base de datos al realizar la consulta
     */
    public List<Cuenta> consultarTodasCuentas() throws PersistenciaException;
    
    /**
     * Agrega un registro de una cuenta a la base de datos
     * @param cuenta a registrar
     * @throws PersistenciaException en caso de que ocurra un error de base de datos al agregar la cuenta
     */
    public void insertarCuenta(CuentaDTO cuenta) throws PersistenciaException;
    
    /**
     * Actualiza el saldo de una cuenta
     * @param num_cuenta a actualizar
     * @param saldo a establecer en el registro de la cuenta
     * @throws PersistenciaException en caso de que ocurra un error de base de datos al actualizar el saldo
     */
    public void actualizarSaldoCuenta(String num_cuenta, BigDecimal saldo) throws PersistenciaException;
  /**
   * agrega un usuario a la tabla de usuarios
   * @param idCliente al que le pertenece el usuario
   * @param usuario a guardar
   * @param contra a guardar
   * @return el id del registro del usuario, 0 si no se registro
   * @throws PersistenciaException en caso de que ocurra un error de base de datos al agregar el usuario
   */
    public int guardarUsuario(int idCliente, String usuario, String contra)throws PersistenciaException;
}
