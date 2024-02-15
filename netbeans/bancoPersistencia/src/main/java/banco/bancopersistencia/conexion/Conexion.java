/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco.bancopersistencia.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luiis
 */
public class Conexion implements IConexion{

    private final String cadenaConexion;
    private final String usuario;
    private final String contra;

    private static final Logger LOG = Logger.getLogger(Conexion.class.getName());
    
    public Conexion(String cadenaConexion, String usuario, String contra) {
        this.cadenaConexion = cadenaConexion;
        this.usuario = usuario;
        this.contra = contra;
    }

    @Override
    public Connection crearConexion() throws SQLException {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(cadenaConexion, usuario, contra);
        } catch (SQLException ex) {
            throw new SQLException("Error al conectar con la base de datos", ex);
        }
        return conexion;
    }

    @Override
    public void cerrarConexion() throws SQLException {
        Connection conexion = DriverManager.getConnection(cadenaConexion, usuario, contra);
        try {
            conexion.close();
        } catch (Exception e) {
             LOG.log(Level.INFO, "Conexión cerrada ", cadenaConexion);
        }
    
    }
}
