/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco.bancopersistencia.daos;

import banco.bancopersistencia.conexion.IConexion;
import banco.bancopersistencia.dtos.CuentaDTO;
import banco.bancopersistencia.excepciones.PersistenciaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    @Override
    public CuentaDTO buscarCuentaPorNumero(String numCuenta) throws PersistenciaException {

        return null;

    }

    @Override
    public List<CuentaDTO> listarCuentas() throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insertarCuenta(CuentaDTO cuenta) throws PersistenciaException {
        String consulta = "INSERT INTO Cuentas (numero_de_cuenta, fecha_inicio, saldo) VALUES (?, ?, ?)";

        try (Connection conexion = this.conexionBD.crearConexion(); PreparedStatement comandoSQL = conexion.prepareStatement(consulta)) {

            comandoSQL.setString(1, cuenta.getNum_cuenta());
            comandoSQL.setDate(2, cuenta.getFecha_creacion());
            comandoSQL.setFloat(3, cuenta.getSaldo());

            comandoSQL.executeUpdate();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error al insertar cuenta", ex);
            throw new PersistenciaException("Error al insertar cuenta", ex);
        }
    }

    @Override
    public void actualizarCuenta(CuentaDTO cuenta) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
