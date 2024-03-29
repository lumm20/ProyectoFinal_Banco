/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco.bancopersistencia.daos;



import banco.bancodominio.Cliente;
import banco.bancopersistencia.conexion.IConexion;
import banco.bancopersistencia.dtos.ClienteDTO;
import banco.bancopersistencia.excepciones.PersistenciaException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
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
    public Cliente buscarClientePorId(int id) throws PersistenciaException {
        String sentencia = "SELECT * FROM Clientes WHERE id_cliente= ? ";
        Cliente clienteBuscado;
        try (//todos los recursos que se van a utilizar y se deben cerrar
                Connection conexion = this.conexion.crearConexion(); PreparedStatement comando = conexion.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);) {
            comando.setInt(1, id);

            ResultSet rs = comando.executeQuery();
            if (rs.next()) {
                clienteBuscado = new Cliente(rs.getInt("id_cliente"), rs.getString("nombre"), rs.getString("apellidoP"),
                        rs.getString("apellidoM"), rs.getDate("fecha_Nacimiento"), rs.getInt("edad"), rs.getInt("codigo_Direccion"));
                return clienteBuscado;
            } else {
                throw new PersistenciaException("no se encontro el cliente con el id especificado");
            }
        } catch (SQLException e) {
            throw new PersistenciaException("hubo un error al consultar el cliente: "+e.getMessage(),e.getCause());
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
            throw new PersistenciaException("hubo un error al obtener la lista de clientes: "+e.getMessage(),e.getCause());
        }
    }

    @Override
    public int insertarCliente(ClienteDTO cliente, String usuario, String contra) throws PersistenciaException {
        String sentencia = "call sp_agregar_cliente(?,?,?,?,?,?,?,?,?)";

        try (//todos los recursos que se van a utilizar y se deben cerrar
                Connection conexion = this.conexion.crearConexion(); 
                CallableStatement comando = conexion.prepareCall(sentencia);) {
            comando.setString(1, cliente.getNombre());
            comando.setString(2, cliente.getApellidoP());
            comando.setString(3, cliente.getApellidoM());
            comando.setDate(4, cliente.getFecha_nacimiento());
            comando.setInt(5, cliente.getEdad());
            comando.setString(6, usuario);
            comando.setString(7, contra);
            comando.registerOutParameter(8, java.sql.Types.VARCHAR);
            comando.registerOutParameter(9, java.sql.Types.INTEGER);
            
            comando.executeUpdate();
            String mensaje=comando.getString(8);
            if (mensaje==null) {
                int idCliente=comando.getInt(9);
                LOG.log(Level.INFO, "se agrego un cliente correctamente");
                return idCliente;
            }else
                LOG.log(Level.INFO, mensaje);
            return 0;
        } catch (SQLException e) {
            throw new PersistenciaException("hubo un error al agregar el cliente: "+e.getMessage(),e.getCause());
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
            throw new PersistenciaException("hubo un error al actualizar el cliente: "+e.getMessage(),e.getCause());
        }
    }

    @Override
    public int agregarDireccionCliente(String calle, String colonia, String codigo_postal, String numero) throws PersistenciaException {
        String sentencia="call sp_agregar_direccion(?,?,?,?,?)"
                + "";
        try (//todos los recursos que se van a utilizar y se deben cerrar
                Connection conexion = this.conexion.crearConexion(); 
                CallableStatement comando = conexion.prepareCall(sentencia);) {
            
            comando.setString(1, calle);
            comando.setString(2, codigo_postal);
            comando.setString(3, colonia);
            comando.setString(4, numero);
            comando.registerOutParameter(5, java.sql.Types.INTEGER);
            
            comando.executeUpdate();
            
            int idDireccion=comando.getInt(5);
            return idDireccion;
        }catch(SQLException e){
            throw new PersistenciaException("hubo un error al guardar la direccion del cliente: "+e.getMessage(),e.getCause());
        }
    }

    @Override
    public void actualizarDireccionCliente(int idDireccion, String calle, String colonia, String codigo_postal, String numero) throws PersistenciaException {
        String sentencia="call sp_actualizar_direccion(?,?,?,?,?)";
        try (//todos los recursos que se van a utilizar y se deben cerrar
                Connection conexion = this.conexion.crearConexion(); 
                CallableStatement comando = conexion.prepareCall(sentencia);) {
            
            if(calle==null)
                comando.setNull(1, java.sql.Types.NULL);
            else
                comando.setString(1, calle);
            if(codigo_postal==null)
                comando.setNull(2, java.sql.Types.NULL);
            else
                comando.setString(2, codigo_postal);
            if(colonia==null)
                comando.setNull(3, java.sql.Types.NULL);
            else 
                comando.setString(3, colonia);
            if(numero==null)
                comando.setNull(4, java.sql.Types.NULL);
            else
                comando.setString(4, numero);
            if(idDireccion==0)
                comando.setNull(5, java.sql.Types.NULL);
            else
                comando.setInt(5, idDireccion);
            
            comando.executeUpdate();
        }catch(SQLException e){
            throw new PersistenciaException("hubo un error al actualizar la direccion del cliente: "+e.getMessage(),e.getCause());
        }
    }

  
//    @Override
//    public String guardarUsuario(int idCliente, String usuario, String contra) throws PersistenciaException {
//        String sentencia="call sp_agregar_usuario(?,?,?)";
//        try (//todos los recursos que se van a utilizar y se deben cerrar
//                Connection conexion = this.conexion.crearConexion(); 
//                CallableStatement comando = conexion.prepareCall(sentencia);) {
//            comando.setInt(1, idCliente);
//            comando.setString(2, usuario);
//            comando.setString(3, contra);
//            
//            comando.executeUpdate();
//            
//            return this.getNumCuenta(idCliente);
//        }catch(SQLException e){
//            LOG.log(Level.SEVERE, "algo salio mal", e.getMessage());
//            throw new PersistenciaException("Hubo un error al agregar el usuario", e.getCause());
//        }
//    }
    
    
    @Override
    public int autenticar(String usuario, String contra)throws PersistenciaException{
        String sentencia="call sp_autenticar_usuario(?,?,?)";
        try (//todos los recursos que se van a utilizar y se deben cerrar
                Connection conexion = this.conexion.crearConexion(); 
                CallableStatement comando = conexion.prepareCall(sentencia);) {
            comando.setString(1, usuario);
            comando.setString(2, contra);
            comando.registerOutParameter(3, java.sql.Types.INTEGER);
            
            comando.executeUpdate();
            int idCliente=comando.getInt(3);
            return idCliente;
        }catch(SQLException e){
            throw new PersistenciaException("hubo un error al iniciar sesion: "+e.getMessage(),e.getCause());
        }
    }

    @Override
    public int obtenerIdDireccion(int idCliente) throws PersistenciaException {
        Cliente cliente;
        int idDireccion;
        cliente = this.buscarClientePorId(idCliente);
        idDireccion = cliente.getId_domicilio();
        return idDireccion;
    }
}
