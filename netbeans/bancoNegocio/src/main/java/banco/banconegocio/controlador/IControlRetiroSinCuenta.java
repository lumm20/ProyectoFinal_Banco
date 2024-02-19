/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package banco.banconegocio.controlador;

import banco.bancodominio.Retiro_sin_cuenta;

/**
 *
 * @author molin
 */
public interface IControlRetiroSinCuenta {
    // Método para iniciar un retiro sin cuenta
    public Retiro_sin_cuenta iniciarRetiroSinCuenta();

    // Método para verificar el estado de un retiro sin cuenta
    public void verificarEstadoRetiro();
    
 
}
