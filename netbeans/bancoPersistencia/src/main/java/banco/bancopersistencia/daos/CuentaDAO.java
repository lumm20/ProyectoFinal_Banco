/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco.bancopersistencia.daos;


import banco.bancodominio.Cliente;
import banco.bancodominio.Cuenta;
import banco.bancopersistencia.conexion.IConexion;
import banco.bancopersistencia.dtos.CuentaDTO;
import banco.bancopersistencia.excepciones.PersistenciaException;
import java.math.BigDecimal;
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
public class CuentaDAO implements ICuentaDAO{

    final IConexion conexion;
    private final static Logger LOG= Logger.getLogger(CuentaDAO.class.getName());
    
    public CuentaDAO(IConexion conexion){
        this.conexion=conexion;
    }
    
    @Override
    public Cuenta buscarCuentaPorNumero(String numCuenta) throws PersistenciaException {
        String sentencia = "SELECT * FROM Cuentas WHERE numero_de_cuenta= ? ";
        Cuenta cuentaBuscada;
        try (//todos los recursos que se van a utilizar y se deben cerrar
                Connection conexion = this.conexion.crearConexion(); PreparedStatement comando = conexion.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);) {
            comando.setString(1, numCuenta);

            ResultSet rs = comando.executeQuery();
            if (rs.next()) {
                cuentaBuscada = new Cuenta(rs.getString("numero_de_cuenta"), rs.getString("estado"), rs.getBigDecimal("saldo"),
                        rs.getDate("fecha_inicio"));
                return cuentaBuscada;
            } else {
                throw new PersistenciaException("no se encontro la cuenta con el numero especificado");
            }
        } catch (SQLException e) {
            throw new PersistenciaException("hubo un error al consultar la cuenta",e.getCause());
        }
    }

    @Override
    public List<Cuenta> listarCuentas() throws PersistenciaException {
        String sentencia = "SELECT * FROM Cuentas";
        List<Cuenta> lista = new ArrayList<>();

        try (//todos los recursos que se van a utilizar y se deben cerrar
                Connection conexion = this.conexion.crearConexion(); PreparedStatement comando = conexion.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);) {
            ResultSet rs = comando.executeQuery();
            while (rs.next()) {
                Cuenta cuenta = new Cuenta(rs.getString("numero_de_cuenta"), rs.getString("estado"), rs.getBigDecimal("saldo"),
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
            throw new PersistenciaException("hubo un error al obtener la lista de cuentas",e.getCause());
        }
    }

    @Override
    public void insertarCuenta(CuentaDTO cuenta) throws PersistenciaException {
        String sentencia = "INSERT INTO Cuentas(numero_de_cuenta,estado, saldo, fecha_creacion) VALUES (?,?,?,?)";

        try (//todos los recursos que se van a utilizar y se deben cerrar
                Connection conexion = this.conexion.crearConexion(); PreparedStatement comando = conexion.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);) {
            
            comando.setString(1, cuenta.getNum_cuenta());
            comando.setString(2, cuenta.getEstado());
            comando.setBigDecimal(3, cuenta.getSaldo());
            comando.setDate(4, cuenta.getFecha_creacion());

            ResultSet rs = comando.getGeneratedKeys();
            if (rs.next()) {
                LOG.log(Level.INFO, "se agrego una cuenta correctamente");
            }
        } catch (SQLException e) {
            throw new PersistenciaException("hubo un error al agregar la cuenta",e.getCause());
        }
    }

    @Override
    public void cancelarCuenta(String numCuenta) throws PersistenciaException {
        String sentencia="UPDATE cuentas SET estado= 'cancelada' WHERE numero_de_cuenta= ?";
        
        try (//todos los recursos que se van a utilizar y se deben cerrar
                Connection conexion = this.conexion.crearConexion();
                PreparedStatement comando = conexion.prepareStatement(sentencia);) {
            comando.setString(1, numCuenta);
            
            comando.executeUpdate();
        }catch(SQLException e){
            throw new PersistenciaException("hubo un error al cancelar la cuenta",e.getCause());
        }
    }

    @Override
    public String getNumCuenta(int idCliente) throws PersistenciaException {
        String sentencia="SELECT numero_de_cuenta FROM Cuentas WHERE id_cliente = ? limit 1";
        try (//todos los recursos que se van a utilizar y se deben cerrar
                Connection conexion = this.conexion.crearConexion(); 
                PreparedStatement comando = conexion.prepareCall(sentencia);) {
            comando.setInt(1, idCliente);
            ResultSet rs=comando.executeQuery();
            if(rs.next())
                return rs.getString(1);
            return null;
        }catch(SQLException e){
            throw new PersistenciaException("hubo un error al obtener el numero de cuenta",e.getCause());
        }
    }
}