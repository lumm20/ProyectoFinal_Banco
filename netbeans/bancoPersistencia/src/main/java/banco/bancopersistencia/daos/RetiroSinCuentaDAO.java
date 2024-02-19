/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco.bancopersistencia.daos;

import banco.bancodominio.Retiro_sin_cuenta;
import banco.bancopersistencia.conexion.IConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author molin
 */
public class RetiroSinCuentaDAO implements IRetiroSinCuentaDAO {

    final IConexion conexionBD;
    private final static Logger LOG = Logger.getLogger(CuentaDAO.class.getName());

    public RetiroSinCuentaDAO(IConexion conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public boolean guardarRetiroSinCuenta(Retiro_sin_cuenta retiro) {
        try (Connection conexion = conexionBD.crearConexion();
             PreparedStatement statement = conexion.prepareStatement("INSERT INTO retiros_sin_cuenta (folio, estado, contra) VALUES (?, ?, ?)")) {
            statement.setInt(1, retiro.getFolio());
            statement.setString(2, retiro.getEstado());
            statement.setString(3, retiro.getContra());
            statement.executeUpdate();
            return true; // Si llegamos aquí, el guardado fue exitoso
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error al guardar el retiro sin cuenta", ex);
            return false; // Si ocurre alguna excepción, el guardado falla
        }
    }

    @Override
    public Retiro_sin_cuenta obtenerRetiroSinCuentaPorFolio(int folio) {
        Retiro_sin_cuenta retiro = null;
        try (Connection conexion = conexionBD.crearConexion();
             PreparedStatement statement = conexion.prepareStatement("SELECT * FROM retiros_sin_cuenta WHERE folio = ?")) {
            statement.setInt(1, folio);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    retiro = new Retiro_sin_cuenta(
                            resultSet.getInt("folio"),
                            resultSet.getString("estado"),
                            resultSet.getString("contra")
                    );
                }
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error al obtener el retiro sin cuenta por folio", ex);
        }
        return retiro;
    }

    @Override
    public List<Retiro_sin_cuenta> obtenerTodosLosRetirosSinCuenta() {
        List<Retiro_sin_cuenta> retiros = new ArrayList<>();
        try (Connection conexion = conexionBD.crearConexion();
             PreparedStatement statement = conexion.prepareStatement("SELECT * FROM retiros_sin_cuenta");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Retiro_sin_cuenta retiro = new Retiro_sin_cuenta(
                        resultSet.getInt("folio"),
                        resultSet.getString("estado"),
                        resultSet.getString("contra")
                );
                retiros.add(retiro);
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error al obtener todos los retiros sin cuenta", ex);
        }
        return retiros;
    }
}