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
    public Cuenta buscarCuentaPorNumero(String numCuenta) throws PersistenciaException;
    public List<Cuenta> listarCuentas() throws PersistenciaException;
    public void insertarCuenta(CuentaDTO cuenta) throws PersistenciaException;
    public void actualizarCuenta(Cuenta cuenta) throws PersistenciaException;

}
