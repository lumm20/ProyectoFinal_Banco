/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco.bancodominio;

/**
 *
 * @author luiis
 */
public class Retiro_sin_cuenta extends Transaccion{
    private String folio;
    private String estado;
    private String contra;

    public Retiro_sin_cuenta() {
    }

    public Retiro_sin_cuenta(String folio, String estado, String contra) {
        this.folio = folio;
        this.estado = estado;
        this.contra = contra;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
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

    @Override
    public String toString() {
        return "Retiro_Sin_cuenta{" + "folio=" + folio + ", estado=" + estado + ", contra=" + contra + '}';
    }

}
