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
    int procesarTransaccion(Transaccion transaccion)throws PersistenciaException;
    void procesarTransferencia(int idTransaccion, String numCuentaDestino)throws PersistenciaException;
    void procesarRetiroSinCuenta(int idTransaccion, String folio, String contra )throws PersistenciaException;
    List<Transaccion> consultarTransacciones(String numCuenta)throws PersistenciaException;
    List<Transaccion> consultarTransaccionesPorRangoFechas(String numCuenta, Date fechaInicio, Date fechaFin)throws PersistenciaException;
    List<Transaccion> consultarTransaccionesPorTipo(String tipoTransaccion, String numCuenta)throws PersistenciaException;
    
}
