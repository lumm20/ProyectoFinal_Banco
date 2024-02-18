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
<<<<<<< HEAD
    public Cliente buscarClientePorId(int id) throws PersistenciaException;
    public List<Cliente> listarClientes() throws PersistenciaException;
    public void insertarCliente(ClienteDTO cliente) throws PersistenciaException;
    public void actualizarCliente(Cliente cliente) throws PersistenciaException;
    public int agregarDireccionCliente(String calle, String colonia, String codigo_postal, String numero) throws PersistenciaException;
=======

    Cliente buscarClientePorId(int id) throws PersistenciaException;
    List<Cliente> listarClientes() throws PersistenciaException;
    void insertarCliente(ClienteDTO cliente) throws PersistenciaException;
    public void actualizarCliente(Cliente cliente) throws PersistenciaException;
    public int agregarDireccionCliente(String calle, String colonia, String codigo_postal, String numero) throws PersistenciaException;

>>>>>>> 208485a08e5a867a928b4baf0b034cb91e1b81ae
}
