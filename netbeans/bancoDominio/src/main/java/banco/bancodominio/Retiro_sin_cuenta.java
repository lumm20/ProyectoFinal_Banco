/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco.bancodominio;

import java.util.Random;

/**
 *
 * @author luiis
 */
public class Retiro_sin_cuenta extends Transaccion{
    private int folio;
    private String estado;
    private String contra;
    private int folioUnico = 1000; 

    public Retiro_sin_cuenta() {
    }

    public Retiro_sin_cuenta(int folio, String estado, String contra) {
        this.folio = folio;
        this.estado = estado;
        this.contra = contra;
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public void iniciarRetiro() {
        // Generar un folio único
        this.folio = generarFolioUnico();

        // Generar una contraseña aleatoria de 8 dígitos
        this.contra = generarContrasenaAleatoria();

        // Se establece el estado inicial
        this.estado = "en proceso";
    }

    public void verificarEstado() {
        // Verificar el estado actual del retiro
        System.out.println("Estado del retiro: " + estado);
    }

    private int generarFolioUnico() {
        //Le suma 1 al folio anterior generado comenzando en 1000
        folioUnico++;
        return folioUnico;
    }

    private String generarContrasenaAleatoria() {
        // Generar una contraseña aleatoria de 8 dígitos
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
    @Override
    public String toString() {
        return "Retiro_Sin_cuenta{" + "folio=" + folio + ", estado=" + estado + ", contra=" + contra + '}';
    }

}
