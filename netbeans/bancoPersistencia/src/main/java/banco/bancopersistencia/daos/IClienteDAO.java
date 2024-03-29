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
    public Cliente buscarClientePorId(int id) throws PersistenciaException;
    public List<Cliente> listarClientes() throws PersistenciaException;
    public int insertarCliente(ClienteDTO cliente, String usuario, String contra) throws PersistenciaException;
    public void actualizarCliente(Cliente cliente) throws PersistenciaException;
    public int agregarDireccionCliente(String calle, String colonia, String codigo_postal, String numero) throws PersistenciaException;
    public void actualizarDireccionCliente(int idDireccion, String calle, String colonia, String codigo_postal, String numero) throws PersistenciaException;
    public int autenticar(String usuario, String contra)throws PersistenciaException;
    public int obtenerIdDireccion(int idCliente)throws PersistenciaException;
}
