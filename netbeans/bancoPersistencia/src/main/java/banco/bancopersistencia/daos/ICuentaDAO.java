/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package banco.bancopersistencia.daos;

import banco.bancopersistencia.dtos.CuentaDTO;
import banco.bancopersistencia.excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author luiis
 */
public interface ICuentaDAO {
    CuentaDTO buscarCuentaPorNumero(String numCuenta) throws PersistenciaException;
    List<CuentaDTO> listarCuentas() throws PersistenciaException;
    void insertarCuenta(CuentaDTO cuenta) throws PersistenciaException;
    void actualizarCuenta(CuentaDTO cuenta) throws PersistenciaException;
}
