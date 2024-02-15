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
    public void insertarCliente(ClienteDTO cliente) throws PersistenciaException;
    public void actualizarCliente(ClienteDTO cliente) throws PersistenciaException;

}
