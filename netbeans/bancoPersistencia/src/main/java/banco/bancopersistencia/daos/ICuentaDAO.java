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
    /**
     * Se busca la cuenta de un cliente registrado por el numero de cuenta
     * @param numCuenta
     * @return 
     * @throws banco.bancopersistencia.excepciones.PersistenciaException
     */
    public Cuenta buscarCuentaPorNumero(String numCuenta) throws PersistenciaException;
    
    /**
     * Devuelve las cuentas almacenadas
     * @return 
     * @throws banco.bancopersistencia.excepciones.PersistenciaException
     */
    public List<Cuenta> listarCuentas() throws PersistenciaException;
    
    /**
     * Inserta una nueva cuenta en la base de datos
     * @param cuenta
     * @throws banco.bancopersistencia.excepciones.PersistenciaException
     */
    public void insertarCuenta(CuentaDTO cuenta) throws PersistenciaException;
    
    /**
     * Toma el número de cuenta y el nuevo saldo como parámetros, realiza la operación de actualización del saldo de la cuenta 
     * @param num_cuenta
     * @param saldo
     * @throws banco.bancopersistencia.excepciones.PersistenciaException
     */
    public void actualizarSaldoCuenta(String num_cuenta, BigDecimal saldo) throws PersistenciaException;

}