/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package banco.bancopersistencia.daos;

import banco.bancodominio.Cliente;
import banco.bancopersistencia.dtos.ClienteDTO;
import banco.bancopersistencia.excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author luiis
 */
public interface IClienteDAO {
    
    /**
     * Este metodo busca un cliente en la base de datos por su id y devuelve el cliente, 
     * si no se encuentra se lanzara una exception.
     * @param id
     * @return 
     * @throws banco.bancopersistencia.excepciones.PersistenciaException
     */
    public Cliente buscarClientePorId(int id) throws PersistenciaException;
    
    /**
     * Este metodo devuelve todos los clientes ingresados en la base de datos
     * @return 
     * @throws banco.bancopersistencia.excepciones.PersistenciaException
     */
    public List<Cliente> listarClientes() throws PersistenciaException;
    
    /**
     * Inserta un nuevo cliente en el sistema, es decir, 
     * toma los datos del nuevo cliente(ClienteDTO) y los guarda en la base de datos.
     * @param cliente
     * @return 
     * @throws banco.bancopersistencia.excepciones.PersistenciaException
     */
    public int insertarCliente(ClienteDTO cliente) throws PersistenciaException;
    
    /**
     * Este metodo toma la informacion actualizada del cliente y la almacena en la base de datos
     * @param cliente
     * @throws banco.bancopersistencia.excepciones.PersistenciaException
     */
    public void actualizarCliente(Cliente cliente) throws PersistenciaException;
    
    /**
     * Este metodo toma como parametros los atributos de la direccion y los almacena en un cliente, 
     * es decir, se genera el id de la direccion agregada y el id se almacena en el cliente
     * @param calle
     * @param colonia
     * @param codigo_postal
     * @param numero
     * @return 
     * @throws banco.bancopersistencia.excepciones.PersistenciaException
     */
    public int agregarDireccionCliente(String calle, String colonia, String codigo_postal, String numero) throws PersistenciaException;
    
    /**
     * Se toma la información actualizada de la direccion y se vuelve a almacenar en la base de datos
     * @param idDireccion
     * @param calle
     * @param colonia
     * @param codigo_postal
     * @param numero
     * @throws banco.bancopersistencia.excepciones.PersistenciaException
     */
    public void actualizarDireccionCliente(int idDireccion, String calle, String colonia, String codigo_postal, String numero) throws PersistenciaException;
    
    /**
     * Guarda la informacion del usuario asociado con un cliente y la almacena
     * @param idCliente
     * @param usuario
     * @param contra
     * @return 
     * @throws banco.bancopersistencia.excepciones.PersistenciaException
     */
    public int guardarUsuario(int idCliente , String usuario, String contra)throws PersistenciaException;
    
}
