/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package banco.bancopersistencia.daos;

/**
 *
 * @author molin
 */
import banco.bancodominio.Retiro_sin_cuenta;
import java.util.List;

public interface IRetiroSinCuentaDAO {
    // Método para guardar un retiro sin cuenta en la base de datos
    public boolean guardarRetiroSinCuenta(Retiro_sin_cuenta retiroSinCuenta);
    // Método para obtener un retiro sin cuenta por su folio
    public Retiro_sin_cuenta obtenerRetiroSinCuentaPorFolio(int folio);

    // Método para obtener todos los retiros sin cuenta de la base de datos
    public List<Retiro_sin_cuenta> obtenerTodosLosRetirosSinCuenta();
}
