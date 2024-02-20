/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package banco.bancopersistencia.conexion.controladores;

import banco.bancodominio.Cliente;
import banco.bancodominio.Cuenta;
import banco.bancodominio.Transaccion;
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
     * @param usuario con el que se identificara el cliente
     * @param contra respectiva a su usuario
     * @return el id del cliente agregado, 0 si no se pudo agregar
     * @throws PersistenciaException en caso de que ocurra un error de base de datos al agregar al cliente
     */
    public int insertarCliente(ClienteDTO cliente, String usuario, String contra) throws PersistenciaException;

    /**
     * Obtiene el ultimo numero de cuenta registrado en la tabla de cuentas cuyo id de cliente asociado
     * coincida con el id del parametro
     * @param idCliente del cual se quiere obtener su mas reciente numero de cuenta registrado 
     * @return el numero de cuenta obtenido
     * @throws PersistenciaException en caso de que ocurra un error de base de datos al obtener el numero de cuenta
     */
    public String obtenerNumCuenta(int idCliente)throws PersistenciaException;
    
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
     * Actualiza la informacion personal de un cliente, a excepcion del id de cliente
     * @param cliente con la informacion a actualizar
     * @throws PersistenciaException en caso de que ocurra un error de base de datos al actualizar al cliente
     */
    public void actualizarCliente(Cliente cliente) throws PersistenciaException;
    /**
     * Actualiza la direccion de un cliente en la base de datos, a excepcion del id de direccion
     * @param idDireccion del registro en la base de datos
     * @param calle a actualizar
     * @param colonia a actualizar
     * @param codigo_postal a actualizar
     * @param numero a actualizar
     * @throws PersistenciaException en caso de que ocurra un error de base de datos al actualizar la direccion
     */
    public void actualizarDireccion(int idDireccion, String calle, String colonia, String codigo_postal, String numero)
            throws PersistenciaException;
    
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
     * Cancela una cuenta de un cliente, actualizando su estado de 'activa' a 'cancelada' en la base de datos
     * @param numCuenta a cancelar
     * @throws PersistenciaException en caso de que ocurra un error de base de datos al cancelar la cuenta
     */
    public void cancelarCuenta(String numCuenta) throws PersistenciaException;
    
    /**
     * guarda un registro de una transaccion en la base de datos
     * @param transaccion a guardar en la tabla de la base de datos
     * @return el id de la transaccion
     * @throws PersistenciaException en caso de que ocurra un error de base de datos al procesar la transaccion
     */
    public int procesarTransaccion(Transaccion transaccion)throws PersistenciaException;
    /**
     * guarda un registro de una transferencia a partir de un registro de una transaccion en la base de datos
     * @param idTransaccion de la transaccion
     * @param numCuentaDestino de la transferencia
     * @throws PersistenciaException en caso de que ocurra un error de base de datos al procesar la transferencia
     */
    public void procesarTransferencia(int idTransaccion, String numCuentaDestino)throws PersistenciaException;
    /**
     * guarda un registro de un retiro sin cuenta a partir de un registro de una transaccion en la base de datos
     * @param idTransaccion de la transaccion
     * @param folio del retiro sin cuenta
     * @param contra a utilizar para el retiro
     * @throws PersistenciaException en caso de que ocurra un error de base de datos al procesar el retiro
     */
    public void procesarRetiroSinCuenta(int idTransaccion, String folio, String contra )throws PersistenciaException;
    /**
     * inicia sesion con el usuario y clave del cliente
     * @param usuario con el que se registro el cliente
     * @param contra que establecio al momento del registro
     * @return el id del cliente si se inicio correctamente
     * @throws PersistenciaException  que ocurra un error de base de datos al iniciar sesion
     */
    public int autenticarUsuario(String usuario, String contra)throws PersistenciaException;
    /**
     * obtiene el id de la direccion del cliente especificado en el parametro
     * @param idCliente del cual se quiere recuperar el id de direccion
     * @return el id de la direccion obtenida
     * @throws PersistenciaException en caso de que ocurra un error de base de datos al obtener el id de la direccion
     */
    public int obtenerIdDireccion(int idCliente)throws PersistenciaException;
            
}
