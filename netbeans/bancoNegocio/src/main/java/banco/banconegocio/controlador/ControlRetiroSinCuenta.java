/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco.banconegocio.controlador;

import banco.bancodominio.Retiro_sin_cuenta;

/**
 *
 * @author molin
 */
public class ControlRetiroSinCuenta implements IControlRetiroSinCuenta{

    @Override
    public Retiro_sin_cuenta iniciarRetiroSinCuenta() {
        //Inicia un retiro sin cuenta
           Retiro_sin_cuenta retiro = new Retiro_sin_cuenta();
           retiro.iniciarRetiro();
           return retiro;
           
    }
    

    @Override
    public void verificarEstadoRetiro() {
        //Verifica el estado de el retiro sin cuenta
        Retiro_sin_cuenta retiro = new Retiro_sin_cuenta();
        retiro.verificarEstado();
        
    }
    
    
}
