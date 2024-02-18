/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package banco.bancopersistencia.daos;

import banco.bancodominio.Transaccion;
import banco.bancopersistencia.excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author luiis
 */
public interface ITransaccionDAO {
    int procesarTransaccion(Transaccion transaccion)throws PersistenciaException;
    List<Transaccion> consultarTransacciones()throws PersistenciaException;
    Transaccion consultarTransaccionPorId(int id_transaccion)throws PersistenciaException;
    List<Transaccion> consultarTransaccionesPorRangoFechas()throws PersistenciaException;
    List<Transaccion> consultarTransaccionesPorTipo(String tipoTransaccion)throws PersistenciaException;
    
}
