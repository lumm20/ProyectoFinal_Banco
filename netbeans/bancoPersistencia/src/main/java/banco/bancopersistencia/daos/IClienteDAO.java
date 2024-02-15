/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package banco.bancopersistencia.daos;

import banco.bancopersistencia.dtos.ClienteDTO;
import banco.bancopersistencia.excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author luiis
 */
public interface IClienteDAO {
    ClienteDTO buscarClientePorId(int id) throws PersistenciaException;
    List<ClienteDTO> listarClientes() throws PersistenciaException;
    void insertarCliente(ClienteDTO cliente) throws PersistenciaException;
    void actualizarCliente(ClienteDTO cliente) throws PersistenciaException;
<<<<<<< HEAD
=======
    
>>>>>>> 130fc9eb2e6c0bbf5c2f1a6cf516af24359011e7
}
