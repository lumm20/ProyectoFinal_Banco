/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco.bancopresentacion.control;


import banco.bancopresentaciones.*;

/**
 *
 * @author luiis
 */
public class ControlPresentacion {
    public ControlPresentacion(){
    }
    
    public void despliegaInicio(){
        Inicial ini=new Inicial();
    }
    public void despliegaLogin(){
        Login login=new Login();
    }
    
    public void despliegaRegistro(){
        Registro registro=new Registro();
    }
    
    public void despliegaPrincipal(){
        Principal principal=new Principal();
    }
    
    public void despliegaRetiroSinCuenta(){
        Retiro_Sin_Cuenta retiro=new Retiro_Sin_Cuenta();
    }
}
