/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco.banconegocio;

import banco.bancodominio.Cliente;
import banco.bancopersistencia.daos.ClienteDAO;
import banco.bancopersistencia.dtos.ClienteDTO;
import banco.bancopersistencia.excepciones.PersistenciaException;

/**
 *
 * @author molin
 */
public class clienteRegistro {
    private final ClienteDAO clienteDAO;

    public clienteRegistro(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    // Método para registrar un cliente en la base de datos
    public void registrarCliente(Cliente cliente) throws PersistenciaException {
        // Convertir el objeto Cliente al formato ClienteDTO esperado por ClienteDAO
        ClienteDTO clienteDTO = convertirAClienteDTO(cliente);

        // Insertar el cliente en la base de datos utilizando ClienteDAO
        clienteDAO.insertarCliente(clienteDTO);
    }

    // Método privado para convertir un Cliente a un ClienteDTO
    private ClienteDTO convertirAClienteDTO(Cliente cliente) {
        // Aquí implementa la lógica para convertir un Cliente a un ClienteDTO
        // Puedes crear un nuevo objeto ClienteDTO y asignarle los valores del Cliente
        ClienteDTO clienteDTO = new ClienteDTO(
        cliente.getNombre(),
        cliente.getApellidoP(),
        cliente.getApellidoM(),
        cliente.getFecha_nacimiento(),
        cliente.getEdad(), // Asegúrate de tener el método getIdCliente() en la clase Cliente
        cliente.getId_domicilio() // Asegúrate de tener el método getIdDireccion() en la clase Cliente
    );
        // Asigna los demás atributos según sea necesario

        return clienteDTO;
    }
}
