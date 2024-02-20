/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco.bancopersistencia.daos;

import banco.bancodominio.Retiro_sin_cuenta;
import banco.bancodominio.Transaccion;
import banco.bancodominio.Transferencia;
import banco.bancopersistencia.conexion.IConexion;
import banco.bancopersistencia.excepciones.PersistenciaException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
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
                CallableStatement comando = conexion.prepareCall(sentencia)) {
            int idTransaccion=0;
            
            comando.setBigDecimal(1, transaccion.getMonto());
            comando.setDate(2, transaccion.getFechaHoraCreacion());
            comando.setString(3, transaccion.getNumCuentaOrigen());
            comando.registerOutParameter(4, java.sql.Types.INTEGER);
            
            int res=comando.executeUpdate();
            if(res>0)
                idTransaccion=comando.getInt(4);
            return idTransaccion;
        }catch(SQLException e){
            LOG.log(Level.SEVERE,"algo salio mal",e.getMessage());
            throw new PersistenciaException("hubo un error al procesar la transaccion",e.getCause());
        }
    }

    @Override
    public void procesarTransferencia(int idTransaccion, String numCuentaDestino)throws PersistenciaException{
        String sentencia="call sp_procesarTransferencia(?,?)";
        
        try (//todos los recursos que se van a utilizar y se deben cerrar
                Connection conexion = this.conexion.crearConexion(); 
                CallableStatement comando = conexion.prepareCall(sentencia)) {
            
            comando.setInt(1, idTransaccion);
            comando.setString(2, numCuentaDestino);
            
            comando.executeUpdate();
        }catch(SQLException e){
            LOG.log(Level.SEVERE,"algo salio mal",e.getMessage());
            throw new PersistenciaException("hubo un error al procesar la transferencia",e.getCause());
        }
    }
    
    
    @Override
    public void procesarRetiroSinCuenta(int idTransaccion, String folio, String contra )throws PersistenciaException{
        String sentencia="call sp_procesarRetiro(?,?,?,?)";
        
        try (//todos los recursos que se van a utilizar y se deben cerrar
                Connection conexion = this.conexion.crearConexion(); 
                CallableStatement comando = conexion.prepareCall(sentencia)) {
            
            comando.setInt(1, idTransaccion);
            comando.setString(2, folio);
            comando.setString(3, "en proceso");
            comando.setString(4, contra);
            
            comando.executeUpdate();
        }catch(SQLException e){
            LOG.log(Level.SEVERE,"algo salio mal",e.getMessage());
            throw new PersistenciaException("hubo un error al procesar el retiro",e.getCause());
        }
    }
    
    @Override
    public List<Transaccion> consultarTransacciones(String numCuenta) throws PersistenciaException {
        String sentencia="call sp_consultarTransacciones(?)";
        List<Transaccion> transacciones=new ArrayList<>();
        try (//todos los recursos que se van a utilizar y se deben cerrar
                Connection conexion = this.conexion.crearConexion(); 
                PreparedStatement comando = conexion.prepareStatement(sentencia)) {
            comando.setString(1, numCuenta);
            
            
            ResultSet rs=comando.executeQuery();
            while(rs.next()){
                Transaccion transaccion=new Transaccion(rs.getBigDecimal("monto"),rs.getDate("fecha_hora"),
                rs.getString("num_cuenta"),null);
                transacciones.add(transaccion);
            }
            return transacciones;
        }catch(SQLException e){
            LOG.log(Level.SEVERE,"algo salio mal",e.getMessage());
            throw new PersistenciaException("hubo un error al consultar las transacciones",e.getCause());
        }
    }

    
    @Override
    public List<Transaccion> consultarTransaccionesPorRangoFechas(String numCuenta,Date fechaInicio, Date fechaFin) throws PersistenciaException {
        String sentencia="call sp_consultarTransaccionesPorRangoFechas(?,?,?)";
        List<Transaccion> transacciones=new ArrayList<>();
        try (//todos los recursos que se van a utilizar y se deben cerrar
                Connection conexion = this.conexion.crearConexion(); 
                PreparedStatement comando = conexion.prepareStatement(sentencia)) {
            comando.setString(1, numCuenta);
            comando.setDate(2, fechaInicio);
            comando.setDate(3, fechaFin);
            
            ResultSet rs=comando.executeQuery();
            while(rs.next()){
                Transaccion transaccion=null;
                if(rs.getString("tipo_transaccion").equals("transferencia")){
                    transaccion=new Transferencia(rs.getBigDecimal("monto"), rs.getDate("fecha_hora"),
                            rs.getString("num_cuenta"),rs.getString("destino_transferencia"));
                }else if(rs.getString("tipo_transaccion").equals("retiro sin cuenta")){
                    transaccion=new Retiro_sin_cuenta();//falta implementacion de esta clase
                }
                transacciones.add(transaccion);
            }
            return transacciones;
        }catch(SQLException e){
            LOG.log(Level.SEVERE,"algo salio mal",e.getMessage());
            throw new PersistenciaException("hubo un error al consultar las transacciones",e.getCause());
        }
    }

    @Override
    public List<Transaccion> consultarTransaccionesPorTipo(String tipoTransaccion, String numCuenta) throws PersistenciaException {
        String sentencia=null;
        if(tipoTransaccion.equals("transferencia"))
            sentencia="call sp_consultarTransferencias(?)";
        else if(tipoTransaccion.equals("retiro sin cuenta"))
            sentencia="call sp_consultarRetiros(?)";
        
        List<Transaccion> transacciones=new ArrayList<>();
        try (//todos los recursos que se van a utilizar y se deben cerrar
                Connection conexion = this.conexion.crearConexion(); 
                PreparedStatement comando = conexion.prepareStatement(sentencia)) {
            comando.setString(1, numCuenta);
            
            ResultSet rs=comando.executeQuery();
            while(rs.next()){
                Transaccion transaccion=null;
                if(tipoTransaccion.equals("transferencia")){
                    transaccion=new Transferencia(rs.getBigDecimal("monto"), rs.getDate("fecha_hora"),
                            rs.getString("num_cuenta"),rs.getString("destino transferencia"));
                }else if(tipoTransaccion.equals("retiro sin cuenta")){
                    transaccion=new Retiro_sin_cuenta();//falta implementacion de esta clase
                }
                transacciones.add(transaccion);
            }
            return transacciones;
        }catch(SQLException e){
            LOG.log(Level.SEVERE,"algo salio mal",e.getMessage());
            throw new PersistenciaException("hubo un error al consultar las transacciones por tipo",e.getCause());
        }
    }
    
}