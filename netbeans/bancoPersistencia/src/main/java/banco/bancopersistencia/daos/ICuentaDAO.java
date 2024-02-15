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
<<<<<<< HEAD
=======
    
>>>>>>> 130fc9eb2e6c0bbf5c2f1a6cf516af24359011e7
}
