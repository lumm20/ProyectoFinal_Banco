/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco.bancopersistencia.daos;

import banco.bancodominio.Transaccion;
import banco.bancopersistencia.conexion.IConexion;
import banco.bancopersistencia.excepciones.PersistenciaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author luiis
 */
public class TransaccionDAO implements ITransaccionDAO{

    final IConexion conexion;
    private final static Logger LOG= Logger.getLogger(TransaccionDAO.class.getName());
    
    public TransaccionDAO(IConexion conexion){
        this.conexion=conexion;
    }
    
    
    @Override
    public int procesarTransaccion(Transaccion transaccion) throws PersistenciaException {
        String sentencia="call sp_procesarTransaccion(?,?,?,?)";
        
        try (//todos los recursos que se van a utilizar y se deben cerrar
                Connection conexion = this.conexion.crearConexion(); 
                PreparedStatement comando = conexion.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);) {
            comando.setBigDecimal(0, transaccion.getMonto());
            return 0;
        }catch(SQLException e){
            throw new PersistenciaException("");
        }
    }

    @Override
    public List<Transaccion> consultarTransacciones() throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Transaccion consultarTransaccionPorId(int id_transaccion) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Transaccion> consultarTransaccionesPorRangoFechas() throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Transaccion> consultarTransaccionesPorTipo(String tipoTransaccion) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
