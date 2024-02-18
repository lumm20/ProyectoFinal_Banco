/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package banco.bancopersistencia.daos;

import banco.bancodominio.Cuenta;
import banco.bancopersistencia.dtos.CuentaDTO;
import banco.bancopersistencia.excepciones.PersistenciaException;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author luiis
 */
public interface ICuentaDAO {
    public Cuenta buscarCuentaPorNumero(String numCuenta) throws PersistenciaException;
    public List<Cuenta> listarCuentas() throws PersistenciaException;
    public void insertarCuenta(CuentaDTO cuenta) throws PersistenciaException;
<<<<<<< HEAD
    public void actualizarSaldoCuenta(String num_cuenta, float saldo) throws PersistenciaException;

=======
    public void actualizarSaldoCuenta(String num_cuenta, BigDecimal saldo) throws PersistenciaException;
>>>>>>> 208485a08e5a867a928b4baf0b034cb91e1b81ae
}
