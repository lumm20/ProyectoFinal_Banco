/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco.bancopresentacion.control;


import banco.bancodominio.Retiro_sin_cuenta;
import banco.bancopresentaciones.*;

/**
 *
 * @author luiis
 */
public class ControlPresentacion {
    private Retiro_Sin_Cuenta retiroSinCuenta; 
    
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
    
    
    public void despliegaRetiroSinCuenta() {
        retiroSinCuenta = new Retiro_Sin_Cuenta();
        retiroSinCuenta.setVisible(true);
        Retiro_sin_cuenta retiro = new Retiro_sin_cuenta();

        // Generar el folio y la contraseña aleatoria
        retiro.iniciarRetiro();

        // Obtener el folio y la contraseña generados
        int folio = retiro.getFolio();
        String contra = retiro.getContra();

        // Llamar al método mostrarFolioYContra() para mostrar el folio y la contraseña en el frame Retiro_Sin_Cuenta
        mostrarFolioYContra(folio, contra);
    }

    public void mostrarFolioYContra(int folio, String contra) {
        // Verificar si el frame Retiro_Sin_Cuenta está inicializado y visible
        if (retiroSinCuenta != null && retiroSinCuenta.isVisible()) {
            // Actualizar los labels txtFolio y txtContra con los valores proporcionados
            retiroSinCuenta.getTxtFolio().setText(String.valueOf(folio));
            retiroSinCuenta.getTxtContra().setText(contra);
        }
    }
}
