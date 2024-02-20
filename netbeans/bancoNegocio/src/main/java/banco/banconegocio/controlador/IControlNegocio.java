/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package banco.banconegocio.controlador;

import banco.bancodominio.Cliente;
import banco.bancodominio.Cuenta;
import banco.banconegocio.excepciones.NegocioException;

import java.util.List;

/**
 *
 * @author luiis
 */
public interface IControlNegocio {
    
    /**
     * Realiza una consulta de un cliente con el id especificado en el parametro
     *
     * @param id del cliente a buscar
     * @return el cliente obtenido
     * @throws NegocioException en caso de que no se haya encontrado el
     * cliente o si ocurre un error de base de datos al realizar la consulta
     */
    public Cliente buscarClientePorId(int id) throws NegocioException;

    /**
     * Realiza una consulta de todos los clientes registrados
     *
     * @return una lista de todos los clientes obtenidos
     * @throws NegocioException en caso de que no haya clientes registrados o 
     * en caso de que ocurra un error de base de datos al realizar la consulta
     */
    public List<Cliente> consultarClientes() throws NegocioException;

    /**
     * Agrega un registro de un cliente a la base de datos
     * @param nombre del cliente
     * @param apellidoPaterno del cliente
     * @param apellidoM del cliente
     * @param fecha_nacimiento del cliente
     * @param id_direccion del registro de la direccion en la base de datos
     * @return el id del cliente agregado, 0 si no se agrego
     * @throws NegocioException en caso de que ocurra un error de base de datos al agregar al cliente
     */
    public int insertarCliente(String nombre, String apellidoPaterno, String apellidoM,
            String fecha_nacimiento, int id_direccion) throws NegocioException;

    /**
     * Agrega la direccion de un cliente en la base de datos
     * @param calle del domicilio
     * @param colonia del domicilio
     * @param codigo_postal del domicilio
     * @param numero de la casa/departamento/edificio
     * @return el id auto-generado de la direccion
     * @throws NegocioException en caso de que ocurra un error de base de datos al agregar la direccion 
     */
    public int agregarDireccionCliente(String calle, String colonia, String codigo_postal, String numero)
            throws NegocioException;
    
//    /**
//     * Actualiza la informacion personal de un cliente, a excepcion de su id de cliente
//     * @param nombre del cliente
//     * @param apellidoPaterno del cliente
//     * @param apellidoM del cliente
//     * @param fecha_nacimiento del cliente
//     * @throws NegocioException en caso de que ocurra un error de base de datos al actualizar al cliente
//     */
//    public void actualizarCliente(String nombre, String apellidoPaterno, String apellidoM,
//            String fecha_nacimiento) throws NegocioException;
//    
    /**
     * Realiza una consulta de una cuenta con el num_cuenta especificado en el parametro
     *
     * @param numCuenta de la cuenta a buscar
     * @return la cuenta obtenida
     * @throws NegocioException en caso de que no se haya encontrado la
     * cuenta o si ocurre un error de base de datos al realizar la consulta
     */
    public Cuenta consultarCuentaEspecifica(String numCuenta) throws NegocioException;
    
    /**
     * Realiza una consulta de todas las cuentas registradas
     *
     * @return una lista de todas las cuentas obtenidos
     * @throws NegocioException en caso de que no haya cuentas registradas o 
     * en caso de que ocurra un error de base de datos al realizar la consulta
     */
    public List<Cuenta> consultarTodasCuentas() throws NegocioException;
    
    /**
     * Agrega un registro de una cuenta a la base de datos con saldo inicial 0.0 y estado activo
     * @param numCuenta de la cuenta a registrar
     * @param fecha_creacion de la cuenta
     * @throws NegocioException en caso de que ocurra un error de base de datos al agregar la cuenta
     */
    public void insertarCuenta(String numCuenta, String fecha_creacion) throws NegocioException;
    
//    /**
//     * Actualiza el saldo de una cuenta
//     * @param num_cuenta a actualizar
//     * @param saldo a establecer en el registro de la cuenta
//     * @throws NegocioException en caso de que ocurra un error de base de datos al actualizar el saldo
//     */
//    public void actualizarSaldoCuenta(String num_cuenta, float saldo) throws NegocioException;

    /**
     * guarda un usuario en la base de datos
     * @param idCliente al que le pertenece el usuario
     * @param usuario a guardar
     * @param contra a guardar
     * @return el id del registro del usuario, 0 si no se guardo
     * @throws NegocioException en caso de que ocurra un error de base de datos al agregar el usuario
     */
    public int guardarUsuario(int idCliente, String usuario, String contra)throws NegocioException;
}