/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package banco.banconegocio.controlador;

import banco.bancodominio.Cliente;
import banco.bancodominio.Cuenta;
import banco.bancodominio.Transaccion;
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
     * @param usuario con el que se identificara el cliente
     * @param contra respectiva a su usuario
     * @return el id del cliente agregado, 0 si no se agrego
     * @throws NegocioException en caso de que ocurra un error de base de datos al agregar al cliente
     */
    public int insertarCliente(String nombre, String apellidoPaterno, String apellidoM,
            String fecha_nacimiento,  String usuario, String contra) throws NegocioException;

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
    
    /**
     * Actualiza la informacion personal de un cliente, a excepcion de su id de cliente
     * @param idCliente del cliente
     * @param nombre del cliente
     * @param apellidoPaterno del cliente
     * @param apellidoM del cliente
     * @param fecha_nacimiento del cliente
     * @param idDireccion de la direccion del cliente
     * @throws NegocioException en caso de que ocurra un error de base de datos al actualizar al cliente
     */
    public void actualizarCliente(int idCliente, String nombre, String apellidoPaterno, String apellidoM,
            String fecha_nacimiento, int idDireccion) throws NegocioException;
    
    /**
     * Actualiza la direccion de un cliente en la base de datos, a excepcion del id de direccion
     * @param idDireccion del registro en la base de datos
     * @param calle a actualizar
     * @param colonia a actualizar
     * @param codigo_postal a actualizar
     * @param numero a actualizar
     * @throws NegocioException en caso de que ocurra un error de base de datos al actualizar la direccion
     */
    public void actualizarDireccionCliente(int idDireccion, String calle, String colonia, String codigo_postal, String numero)
            throws NegocioException;
    
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
     * Obtiene el ultimo numero de cuenta registrado en la tabla de cuentas cuyo id de cliente asociado
     * coincida con el id del parametro
     * @param idCliente del cual se quiere obtener su mas reciente numero de cuenta registrado 
     * @return el numero de cuenta obtenido
     * @throws NegocioException en caso de que ocurra un error de base de datos al obtener el numero de cuenta
     */
    public String obtenerNumCuenta(int idCliente)throws NegocioException;
    
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
     * @throws NegocioException en caso de que ocurra un error de base de datos al agregar la cuenta
     */
    public void insertarCuenta(String numCuenta) throws NegocioException;

     /**
     * Cancela una cuenta de un cliente, actualizando su estado de 'activa' a 'cancelada' en la base de datos
     * @param numCuenta a cancelar
     * @throws NegocioException en caso de que ocurra un error de base de datos al cancelar la cuenta
     */
    public void cancelarCuenta(String numCuenta) throws NegocioException;
    
    /**
     * guarda un registro de una transaccion en la base de datos
     * @param transaccion a guardar en la tabla de la base de datos
     * @return el id de la transaccion
     * @throws NegocioException en caso de que ocurra un error de base de datos al procesar la transaccion
     */
    public int procesarTransaccion(Transaccion transaccion)throws NegocioException;
    /**
     * guarda un registro de una transferencia a partir de un registro de una transaccion en la base de datos
     * @param idTransaccion de la transaccion
     * @param numCuentaDestino de la transferencia
     * @throws NegocioException en caso de que ocurra un error de base de datos al procesar la transferencia
     */
    public void procesarTransferencia(int idTransaccion, String numCuentaDestino)throws NegocioException;
    /**
     * guarda un registro de un retiro sin cuenta a partir de un registro de una transaccion en la base de datos
     * @param idTransaccion de la transaccion
     * @param folio del retiro sin cuenta
     * @param contra a utilizar para el retiro
     * @throws NegocioException en caso de que ocurra un error de base de datos al procesar el retiro
     */
    public void procesarRetiroSinCuenta(int idTransaccion, String folio, String contra )throws NegocioException;
    
    /**
     * inicia sesion con el usuario y clave del cliente
     * @param usuario con el que se registro el cliente
     * @param contra que establecio al momento del registro
     * @return el id del cliente si se inicio correctamente
     * @throws NegocioException en caso de que ocurra el usuario-contrasena 
     * que se ingresaron fueron incorrectas, o si ocurre un error de base de datos al iniciar sesion 
     */
    public int autenticarUsuario(String usuario, String contra)throws NegocioException;
    /**
     * obtiene el id de la direccion del cliente especificado en el parametro
     * @param idCliente del cual se quiere recuperar el id de direccion
     * @return el id de la direccion obtenida
     * @throws NegocioException en caso de que ocurra un error de base de datos al obtener el id de la direccion
     */
    public int obtenerIdDireccionCliente(int idCliente)throws NegocioException;
}