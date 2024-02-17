/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco.bancopersistencia.dtos;

import java.sql.Date;

/**
 *
 * @author luiis
 */
public class TransferenciaDTO {
    private float monto;
    private Date fechaHoraCreacion;
    private String numCuentaOrigen;
    private String numCuentaDestino;

    public TransferenciaDTO() {
    }

    public TransferenciaDTO(float monto, Date fechaHoraCreacion, String numCuentaOrigen, String numCuentaDestino) {
        this.monto = monto;
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.numCuentaOrigen = numCuentaOrigen;
        this.numCuentaDestino = numCuentaDestino;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public Date getFechaHoraCreacion() {
        return fechaHoraCreacion;
    }

    public void setFechaHoraCreacion(Date fechaHoraCreacion) {
        this.fechaHoraCreacion = fechaHoraCreacion;
    }

    public String getNumCuentaOrigen() {
        return numCuentaOrigen;
    }

    public void setNumCuentaOrigen(String numCuentaOrigen) {
        this.numCuentaOrigen = numCuentaOrigen;
    }

    public String getNumCuentaDestino() {
        return numCuentaDestino;
    }

    public void setNumCuentaDestino(String numCuentaDestino) {
        this.numCuentaDestino = numCuentaDestino;
    }
    
    
}
