/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco.bancopersistencia.daos;


import banco.bancopersistencia.conexion.Conexion;

import banco.bancopersistencia.conexion.IConexion;
import banco.bancopersistencia.dtos.ClienteDTO;
import banco.bancopersistencia.excepciones.PersistenciaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import java.sql.Statement;
import java.util.List;

import java.util.logging.Logger;

/**
 *
 * @author luiis
 */
public class ClienteDAO implements IClienteDAO {


    

    private final IConexion conexionBD;

    private static final Logger LOG = Logger.getLogger(ClienteDAO.class.getName());

    // Constructor que acepta un objeto IConexion
    public ClienteDAO(IConexion conexionBD) {
        this.conexionBD = conexionBD;
    }

   
    
    @Override
    public ClienteDTO buscarClientePorId(int id) throws PersistenciaException {
        // Aquí implementa la lógica para buscar un cliente por su ID en la base de datos
        String consulta = "SELECT * FROM Clientes WHERE id_cliente = ?";

        try (Connection conexion = this.conexionBD.crearConexion(); PreparedStatement comandoSQL = conexion.prepareStatement(consulta);) {

            comandoSQL.setInt(1, id);
            ResultSet resultado = comandoSQL.executeQuery();

            if (resultado.next()) {
                // Crear un objeto ClienteDTO con los datos obtenidos de la consulta
                ClienteDTO clienteConsultado = new ClienteDTO(
                                                resultado.getString("nombre"),
                        resultado.getString("apellidoP"),
                        resultado.getString("apellidoM"),
                        resultado.getDate("fecha_Nacimiento"),
                        resultado.getInt("id_cliente"),
                        resultado.getInt("id_direccion")
                );
                return clienteConsultado;
            } else {
                // Si no se encuentra ningún cliente con el ID dado, lanzar una excepción
                throw new PersistenciaException("No se ha encontrado ningún cliente con el ID proporcionado");
            }
        } catch (SQLException ex) {
            // En caso de error, lanzar una excepción de persistencia
            LOG.log(Level.SEVERE, "Error al buscar cliente por ID", ex);
            throw new PersistenciaException("Error al buscar cliente por ID", ex);
        }
    

    @Override
    public ClienteDTO buscarClientePorId(int id) throws PersistenciaException {
        String sentencia="SELECT * FROM Clientes WHERE id_cliente= ? ";
        ClienteDTO clienteBuscado=null;
         try(//todos los recursos que se van a utilizar y se deben cerrar
                Connection conexion = this.conexion.crearConexion(); 
                 PreparedStatement comando = conexion.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);) {
             comando.setInt(1, id);
             
             ResultSet rs=comando.executeQuery();
             if(rs.next()){
                 clienteBuscado=new ClienteDTO(rs.getString("nombre"),rs.getString("apellidoP"),
                 rs.getString("apellidM"),rs.getDate("fecha_Nacimiento"),rs.getInt("edad"),rs.getInt("codigo_direccion"));
             }
         }catch(SQLException e){
             
         }
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    }

    @Override
    public List<ClienteDTO> listarClientes() throws PersistenciaException {
        List<ClienteDTO> clientes = new ArrayList<>();

        // Aquí implementa la lógica para listar todos los clientes de la base de datos
        String consulta = "SELECT * FROM Clientes";

        try (Connection conexion = this.conexionBD.crearConexion(); PreparedStatement comandoSQL = conexion.prepareStatement(consulta); ResultSet resultado = comandoSQL.executeQuery()) {

            while (resultado.next()) {
                ClienteDTO cliente = new ClienteDTO(
                        resultado.getString("nombre"),
                        resultado.getString("apellidoP"),
                        resultado.getString("apellidoM"),
                        resultado.getDate("fecha_Nacimiento"),
                        resultado.getInt("id_cliente"),
                        resultado.getInt("id_direccion")
                );
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error al listar clientes", ex);
            throw new PersistenciaException("Error al listar clientes", ex);
        }

        return clientes;
    }

    @Override
    public void insertarCliente(ClienteDTO cliente) throws PersistenciaException {
        String consulta = "INSERT INTO Clientes (nombre, apellidoP, apellidoM, fecha_Nacimiento, codigo_Direccion) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conexion = this.conexionBD.crearConexion(); PreparedStatement comandoSQL = conexion.prepareStatement(consulta)) {

            comandoSQL.setString(1, cliente.getNombre());
            comandoSQL.setString(2, cliente.getApellidoP());
            comandoSQL.setString(3, cliente.getApellidoM());
            comandoSQL.setDate(4, cliente.getFecha_nacimiento());
            comandoSQL.setInt(5, cliente.getId_direccion());
            comandoSQL.executeUpdate();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error al insertar cliente", ex);
            throw new PersistenciaException("Error al insertar cliente", ex);
        }
    }

    @Override
    public void actualizarCliente(ClienteDTO cliente) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
