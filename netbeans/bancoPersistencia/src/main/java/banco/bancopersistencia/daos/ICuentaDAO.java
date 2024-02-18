/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package banco.bancopersistencia.daos;

import banco.bancodominio.Cuenta;
import banco.bancopersistencia.dtos.CuentaDTO;
import banco.bancopersistencia.excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author luiis
 */
public interface ICuentaDAO {
<<<<<<< HEAD
    CuentaDTO buscarCuentaPorNumero(String numCuenta) throws PersistenciaException;
    List<CuentaDTO> listarCuentas() throws PersistenciaException;
    void insertarCuenta(CuentaDTO cuenta) throws PersistenciaException;
    void actualizarCuenta(CuentaDTO cuenta) throws PersistenciaException;
<<<<<<< HEAD
=======
    public Cuenta buscarCuentaPorNumero(String numCuenta) throws PersistenciaException;
    public List<Cuenta> listarCuentas() throws PersistenciaException;
    public void insertarCuenta(CuentaDTO cuenta) throws PersistenciaException;
    public void actualizarSaldoCuenta(String num_cuenta, float saldo) throws PersistenciaException;
>>>>>>> rama-luisa
=======
    void actualizarSaldoCuenta(String num_cuenta, float saldo);
>>>>>>> d7e93302e5094450e4cff2abbb77baca94698af7

}
