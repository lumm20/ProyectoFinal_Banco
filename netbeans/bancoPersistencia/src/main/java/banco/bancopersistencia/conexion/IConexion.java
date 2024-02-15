/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package banco.bancopersistencia.conexion;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author luiis
 */
public interface IConexion {
    /**
     * metodo para establecer la conexion con la base de datos
     * @return un objeto Connection que representa la conexion al url especificado
     * @throws SQLException si hubo un error al intentar conectarse a la base de datos
     */
    public Connection crearConexion() throws SQLException;
    public void cerrarConexion() throws SQLException;
}
