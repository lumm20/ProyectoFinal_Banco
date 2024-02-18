/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco.bancopersistencia.daos;

<<<<<<< HEAD
import banco.bancodominio.Cliente;
import banco.bancodominio.Cuenta;
=======
>>>>>>> d7e93302e5094450e4cff2abbb77baca94698af7
import banco.bancopersistencia.conexion.IConexion;
import banco.bancopersistencia.dtos.CuentaDTO;
import banco.bancopersistencia.excepciones.PersistenciaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
<<<<<<< HEAD
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
=======
import java.sql.SQLException;
>>>>>>> d7e93302e5094450e4cff2abbb77baca94698af7
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luiis
 */
public class CuentaDAO implements ICuentaDAO {

    private final IConexion conexionBD;

    private static final Logger LOG = Logger.getLogger(CuentaDAO.class.getName());

    public CuentaDAO(IConexion conexionBD) {
        this.conexionBD = conexionBD;
    }

    final IConexion conexion;
    private final static Logger LOG= Logger.getLogger(CuentaDAO.class.getName());
    
    public CuentaDAO(IConexion conexion){
        this.conexion=conexion;
    }
    
    @Override
<<<<<<< HEAD
    public Cuenta buscarCuentaPorNumero(String numCuenta) throws PersistenciaException {
        String sentencia = "SELECT * FROM Cuentas WHERE numero_de_cuenta= ? ";
        Cuenta cuentaBuscada;
        try (//todos los recursos que se van a utilizar y se deben cerrar
                Connection conexion = this.conexion.crearConexion(); PreparedStatement comando = conexion.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);) {
            comando.setString(1, numCuenta);

            ResultSet rs = comando.executeQuery();
            if (rs.next()) {
                cuentaBuscada = new Cuenta(rs.getString("numero_de_cuenta"), rs.getString("estado"), rs.getFloat("saldo"),
                        rs.getDate("fecha_Nacimiento"));
                return cuentaBuscada;
            } else {
                throw new PersistenciaException("no se encontro la cuenta con el numero especificado");
            }
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "algo salio mal: " + e.getMessage(), e.getCause());
            throw new PersistenciaException("hubo un error al consultar la cuenta");
        }
=======
    public CuentaDTO buscarCuentaPorNumero(String numCuenta) throws PersistenciaException {

        return null;

>>>>>>> d7e93302e5094450e4cff2abbb77baca94698af7
    }

    @Override
    public List<Cuenta> listarCuentas() throws PersistenciaException {
        String sentencia = "SELECT * FROM Cuentas";
        List<Cuenta> lista = new ArrayList<>();

        try (//todos los recursos que se van a utilizar y se deben cerrar
                Connection conexion = this.conexion.crearConexion(); PreparedStatement comando = conexion.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);) {
            ResultSet rs = comando.executeQuery();
            while (rs.next()) {
                Cuenta cuenta = new Cuenta(rs.getString("numero_de_cuenta"), rs.getString("estado"), rs.getFloat("saldo"),
                        rs.getDate("fecha_Nacimiento"));
                lista.add(cuenta);
            }
            if (!lista.isEmpty()) {
                LOG.log(Level.INFO, "se encontraron {0} cuentas", lista.size());
                return lista;
            } else {
                throw new PersistenciaException("no hay ninguna cuenta registrada");
            }

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "algo salio mal: " + e.getMessage(), e.getCause());
            throw new PersistenciaException("hubo un error al obtener la lista de cuentas");
        }
    }

    @Override
    public void insertarCuenta(CuentaDTO cuenta) throws PersistenciaException {
<<<<<<< HEAD
        String sentencia = "INSERT INTO Cuentas(estado, saldo, fecha_creacion) VALUES (?,?,?)";

        try (//todos los recursos que se van a utilizar y se deben cerrar
                Connection conexion = this.conexion.crearConexion(); PreparedStatement comando = conexion.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);) {
            comando.setString(1, cuenta.getEstado());
            comando.setFloat(2, cuenta.getSaldo());
            comando.setDate(3, cuenta.getFecha_creacion());

            ResultSet rs = comando.getGeneratedKeys();
            Cuenta cuentaAgregada;
            if (rs.next()) {
                cuentaAgregada = new Cuenta(rs.getString("numero_de_cuenta"), rs.getString("estado"), rs.getFloat("saldo"),
                        rs.getDate("fecha_Nacimiento"));
                LOG.log(Level.INFO, "se agrego una cuenta correctamente.\n Cuenta agregada: ", cuentaAgregada.toString());
            }
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "algo salio mal: " + e.getMessage(), e.getCause());
            throw new PersistenciaException("hubo un error al agregar la cuenta");
=======
        String consulta = "INSERT INTO Cuentas (numero_de_cuenta, fecha_inicio, saldo) VALUES (?, ?, ?)";

        try (Connection conexion = this.conexionBD.crearConexion(); PreparedStatement comandoSQL = conexion.prepareStatement(consulta)) {

            comandoSQL.setString(1, cuenta.getNum_cuenta());
            comandoSQL.setDate(2, cuenta.getFecha_creacion());
            comandoSQL.setFloat(3, cuenta.getSaldo());

            comandoSQL.executeUpdate();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error al insertar cuenta", ex);
            throw new PersistenciaException("Error al insertar cuenta", ex);
>>>>>>> d7e93302e5094450e4cff2abbb77baca94698af7
        }
    }

    @Override
<<<<<<< HEAD
    public void actualizarCuenta(CuentaDTO cuenta) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
<<<<<<< HEAD
=======
    public void actualizarSaldoCuenta(String num_cuenta, float saldo) throws PersistenciaException {
        String sentencia = "UPDATE Cuentas SET saldo = ?"
                + "WHERE numero_de_cuenta=?";

        try (//todos los recursos que se van a utilizar y se deben cerrar
                Connection conexion = this.conexion.crearConexion(); PreparedStatement comando = conexion.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);) {
            comando.setFloat(1, saldo);
            comando.setString(2, num_cuenta);

            int res = comando.executeUpdate();
            if (res > 0) {
                LOG.log(Level.INFO, "se actualizo el saldo de la cuenta " + num_cuenta +" correctamente.\n Saldo actual: ", saldo);
            }
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "algo salio mal: " + e.getMessage(), e.getCause());
            throw new PersistenciaException("hubo un error al actualizar la cuenta");
        }
    }

>>>>>>> rama-luisa
=======

    @Override
    public void actualizarSaldoCuenta(String num_cuenta, float saldo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
>>>>>>> d7e93302e5094450e4cff2abbb77baca94698af7
}
