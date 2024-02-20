/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco.bancopresentacion.control;

import banco.bancopresentacion.*;

/**
 *
 * @author luiis
 */
public class ControlPresentacion {
    private static int idCliente;
    private static int idDireccion;
    private static String numCuentaActual;
    
    public ControlPresentacion(){
    }
    
    public int[] obtenIds(){
        int[] ids={idCliente,idDireccion};
        return ids;
    }
    /**
     * posicion 0= id_cliente, 
     * posicion 1= id_direccion
     * @param ids array con los id del cliente y de la direccion
     */
    public void setIds(int[] ids){
        idCliente=ids[0];
        idDireccion=ids[1];
    }
    
    public void setNumCuenta(String numCuenta){
        numCuentaActual=numCuenta;
    }
    
    public String obtenNumCuentaActual(){
        return numCuentaActual;
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
    
    public void desplegarPerfilUsuario(){
        PerfilUsuario perfil=new PerfilUsuario();
    }
    
    public void desplegarTransferencia(){
        FrmTransferencia transferencia=new FrmTransferencia();
    }
    
    public void despliegaRetiroSinCuenta(){
        Retiro_Sin_Cuenta retiro=new Retiro_Sin_Cuenta();
    }
}
