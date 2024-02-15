/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco.bancopersistencia.daos;

import banco.bancopersistencia.conexion.IConexion;
import banco.bancopersistencia.dtos.ClienteDTO;
import banco.bancopersistencia.excepciones.PersistenciaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author luiis
 */
public class ClienteDAO implements IClienteDAO{

    final IConexion conexion;
    private final static Logger LOG= Logger.getLogger(ClienteDAO.class.getName());
    
        public ClienteDAO(IConexion conexion){
        this.conexion=conexion;
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insertarCliente(ClienteDTO cliente) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizarCliente(ClienteDTO cliente) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
