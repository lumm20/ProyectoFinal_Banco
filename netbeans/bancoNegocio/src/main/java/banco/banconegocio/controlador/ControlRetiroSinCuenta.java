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
    public void iniciarRetiroSinCuenta() {
        //Inicia un retiro sin cuenta
           Retiro_sin_cuenta retiro = new Retiro_sin_cuenta();
           retiro.iniciarRetiro();
           System.out.println("Retiro Sin cuenta iniciado: Folio " + retiro.getFolio());
    }

    @Override
    public void verificarEstadoRetiro() {
        //Verifica el estado de el retiro sin cuenta
        Retiro_sin_cuenta retiro = new Retiro_sin_cuenta();
        retiro.verificarEstado();
        
    }
    
    
}
