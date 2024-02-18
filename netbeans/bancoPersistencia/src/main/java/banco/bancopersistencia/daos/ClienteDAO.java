/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco.bancopersistencia.daos;

<<<<<<< HEAD
<<<<<<< HEAD

import banco.bancopersistencia.conexion.Conexion;

=======
import banco.bancodominio.Cliente;
>>>>>>> rama-luisa
=======
>>>>>>> d7e93302e5094450e4cff2abbb77baca94698af7
import banco.bancopersistencia.conexion.IConexion;
import banco.bancopersistencia.dtos.ClienteDTO;
import banco.bancopersistencia.excepciones.PersistenciaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
<<<<<<< HEAD

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
<<<<<<< HEAD

=======
import java.util.logging.Level;
>>>>>>> rama-luisa
=======
import java.util.List;
>>>>>>> d7e93302e5094450e4cff2abbb77baca94698af7
import java.util.logging.Logger;

/**
 *
 * @author luiis
 */
public class ClienteDAO implements IClienteDAO {

<<<<<<< HEAD

    
<<<<<<< HEAD

=======
>>>>>>> d7e93302e5094450e4cff2abbb77baca94698af7
    private final IConexion conexionBD;

    private static final Logger LOG = Logger.getLogger(ClienteDAO.class.getName());

<<<<<<< HEAD
    // Constructor que acepta un objeto IConexion
    public ClienteDAO(IConexion conexionBD) {
        this.conexionBD = conexionBD;
=======
    public ClienteDAO(IConexion conexion){
        this.conexion=conexion;
>>>>>>> rama-luisa
    }

   
=======
>>>>>>> d7e93302e5094450e4cff2abbb77baca94698af7
    

    
    // Constructor que acepta un objeto IConexion
    
    public ClienteDAO(IConexion conexionBD) {
    this.conexionBD = conexionBD;
}
    

    @Override
    public ClienteDTO buscarClientePorId(int id) throws PersistenciaException {
        // Aquí implementa la lógica para buscar un cliente por su ID en la base de datos
        String consulta = "SELECT * FROM Clientes WHERE id_cliente = ?";

        try (
                Connection conexion = this.conexionBD.crearConexion(); PreparedStatement comandoSQL = conexion.prepareStatement(consulta);) {

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
<<<<<<< HEAD
    

    @Override
<<<<<<< HEAD
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

=======
>>>>>>> d7e93302e5094450e4cff2abbb77baca94698af7
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
=======
    public Cliente buscarClientePorId(int id) throws PersistenciaException {
        String sentencia = "SELECT * FROM Clientes WHERE id_cliente= ? ";
        Cliente clienteBuscado;
        try (//todos los recursos que se van a utilizar y se deben cerrar
                Connection conexion = this.conexion.crearConexion(); PreparedStatement comando = conexion.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);) {
            comando.setInt(1, id);

            ResultSet rs = comando.executeQuery();
            if (rs.next()) {
                clienteBuscado = new Cliente(rs.getInt("id_cliente"), rs.getString("nombre"), rs.getString("apellidoP"),
                        rs.getString("apellidM"), rs.getDate("fecha_Nacimiento"), rs.getInt("edad"), rs.getInt("codigo_direccion"));
                return clienteBuscado;
            } else {
                throw new PersistenciaException("no se encontro el cliente con el id especificado");
            }
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "algo salio mal", e.getMessage());
            throw new PersistenciaException("hubo un error al consultar el cliente",e.getCause());
        }
    }

    @Override
    public List<Cliente> listarClientes() throws PersistenciaException {
        String sentencia = "SELECT * FROM Clientes";
        List<Cliente> lista = new ArrayList<>();

        try (//todos los recursos que se van a utilizar y se deben cerrar
                Connection conexion = this.conexion.crearConexion(); 
                PreparedStatement comando = conexion.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);) {
            ResultSet rs = comando.executeQuery();
            while (rs.next()) {
                Cliente clienteCons = new Cliente(rs.getInt("id_cliente"), rs.getString("nombre"),
                        rs.getString("apellidoP"), rs.getString("apellidoM"), rs.getDate("fecha_nacimiento"),
                        rs.getInt("edad"), rs.getInt("codigo_direccion"));
                lista.add(clienteCons);
            }
            if (!lista.isEmpty()) {
                LOG.log(Level.INFO, "se encontraron {0} clientes", lista.size());
                return lista;
            } else {
                throw new PersistenciaException("no hay ningun cliente registrado");
            }

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "algo salio mal",e.getMessage());
            throw new PersistenciaException("hubo un error al obtener la lista de clientes",e.getCause());
        }
>>>>>>> rama-luisa
    }

    @Override
    public void insertarCliente(ClienteDTO cliente) throws PersistenciaException {
<<<<<<< HEAD
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
=======
        String sentencia = "INSERT INTO Clientes(nombre, apellidoP,apellidoM,fecha_nacimiento,"
                + "edad,codigo_direccion) VALUES (?,?,?,?,?,?)";

        try (//todos los recursos que se van a utilizar y se deben cerrar
                Connection conexion = this.conexion.crearConexion(); 
                PreparedStatement comando = conexion.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);) {
            comando.setString(1, cliente.getNombre());
            comando.setString(2, cliente.getApellidoP());
            comando.setString(3, cliente.getApellidoM());
            comando.setDate(4, cliente.getFecha_nacimiento());
            comando.setInt(5, cliente.getEdad());
            comando.setInt(6, cliente.getId_direccion());
            
            int res=comando.executeUpdate();
            
            if (res>0) {
                LOG.log(Level.INFO, "se agrego un cliente correctamente");
            }else
                throw new PersistenciaException("no se pudo agregar al cliente");
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "algo salio mal", e.getMessage());
            throw new PersistenciaException("hubo un error al agregar el cliente", e.getCause());
>>>>>>> rama-luisa
        }
    }

    @Override
    public void actualizarCliente(Cliente cliente) throws PersistenciaException {
        String sentencia = "UPDATE Clientes SET nombre = ?, apellidoP = ?, apellidoM = ?, fecha_nacimiento = ?,"
                + "edad = ? WHERE id_cliente= ?";

        try (//todos los recursos que se van a utilizar y se deben cerrar
                Connection conexion = this.conexion.crearConexion(); 
                PreparedStatement comando = conexion.prepareStatement(sentencia);) {
            comando.setString(1, cliente.getNombre());
            comando.setString(2, cliente.getApellidoP());
            comando.setString(3, cliente.getApellidoM());
            comando.setDate(4, cliente.getFecha_nacimiento());
            comando.setInt(5, cliente.getEdad());
            comando.setInt(6, cliente.getId_cliente());

            int res = comando.executeUpdate();
            if (res > 0) {
                LOG.log(Level.INFO, "se actualizo un cliente correctamente.\n Cliente actualizado: ", cliente.toString());
            }
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "algo salio mal", e.getMessage());
            throw new PersistenciaException("hubo un error al actualizar el cliente",e.getCause());
        }
    }

<<<<<<< HEAD
=======
    @Override
    public int agregarDireccionCliente(String calle, String colonia, String codigo_postal, String numero) throws PersistenciaException {
        String sentencia="INSERT INTO Direccion_Cliente(calle,codigo_postal,colonia,numero)"
                + "VALUES (?,?,?,?)";
        try (//todos los recursos que se van a utilizar y se deben cerrar
                Connection conexion = this.conexion.crearConexion(); 
                PreparedStatement comando = conexion.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);) {
            comando.setString(1, calle);
            comando.setString(2, codigo_postal);
            comando.setString(3, colonia);
            comando.setString(4, numero);
            
            comando.executeUpdate();
            int codigo_direccion=this.consultarUltimaDireccionAgregada();
            return codigo_direccion;
        }catch(SQLException e){
            LOG.log(Level.SEVERE, "algo salio mal", e.getMessage());
            throw new PersistenciaException("hubo un error al guardar la direccion del cliente", e.getCause());
        }
    }
    
    private int consultarUltimaDireccionAgregada()throws SQLException{
        String sentencia="SELECT max(id_direccion) as id FROM Direccion_Cliente";
        try (//todos los recursos que se van a utilizar y se deben cerrar
                Connection conexion = this.conexion.crearConexion(); 
                PreparedStatement comando = conexion.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);) {
            
            ResultSet rs=comando.executeQuery();
            int codigo_direccion=1;
            if(rs.next()){
                codigo_direccion=rs.getInt("id");
            }
            return codigo_direccion;
        }catch(SQLException e){
            throw new SQLException(e);
        }
    }
>>>>>>> rama-luisa
}
