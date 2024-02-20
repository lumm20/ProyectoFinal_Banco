/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package banco.bancopersistencia.daos;

import banco.bancodominio.Transaccion;
import banco.bancopersistencia.excepciones.PersistenciaException;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author luiis
 */
public interface ITransaccionDAO {
    /**
     * Este método procesa una transacción bancaria
     * @param transaccion
     * @return 
     * @throws banco.bancopersistencia.excepciones.PersistenciaException
     */
    int procesarTransaccion(Transaccion transaccion)throws PersistenciaException;
    /**
     * Este método procesa una transferencia bancaria a una cuenta destino específica
     * @param idTransaccion
     * @param numCuentaDestino
     * @throws banco.bancopersistencia.excepciones.PersistenciaException
     */
    void procesarTransferencia(int idTransaccion, String numCuentaDestino)throws PersistenciaException;
    /**
     * Este método procesa un retiro bancario sin la necesidad de una cuenta asociada
     * @param idTransaccion
     * @param folio
     * @param contra
     * @throws banco.bancopersistencia.excepciones.PersistenciaException
     */
    void procesarRetiroSinCuenta(int idTransaccion, String folio, String contra )throws PersistenciaException;
    /**
     * Este método consulta las transacciones asociadas a un número de cuenta específico
     * @param numCuenta
     * @return 
     * @throws banco.bancopersistencia.excepciones.PersistenciaException
     */
    List<Transaccion> consultarTransacciones(String numCuenta)throws PersistenciaException;
    /**
     * Este método consulta las transacciones asociadas a un número de cuenta específico dentro de un rango de fechas dado
     * @param numCuenta
     * @param fechaInicio
     * @param fechaFin
     * @return 
     * @throws banco.bancopersistencia.excepciones.PersistenciaException
     */
    List<Transaccion> consultarTransaccionesPorRangoFechas(String numCuenta, Date fechaInicio, Date fechaFin)throws PersistenciaException;
    /**
     * Este método consulta las transacciones de un tipo específico asociadas a un número de cuenta dado
     * @param tipoTransaccion
     * @param numCuenta
     * @return 
     * @throws banco.bancopersistencia.excepciones.PersistenciaException
     */
    List<Transaccion> consultarTransaccionesPorTipo(String tipoTransaccion, String numCuenta)throws PersistenciaException;
    
}
