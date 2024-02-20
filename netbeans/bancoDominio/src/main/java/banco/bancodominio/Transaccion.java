/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco.bancodominio;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author luiis
 */

public class Transaccion {
    protected int idTransaccion;
    protected BigDecimal monto;
    protected Date fechaHoraCreacion;
    protected String numCuentaOrigen;
    protected String tipoTransaccion;

    public Transaccion() {
    }


    public Transaccion(int idTransaccion, BigDecimal monto, Date fechaHoraCreacion, String numCuentaOrigen, String tipoTransaccion) {

        this.idTransaccion = idTransaccion;
        this.monto = monto;
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.numCuentaOrigen = numCuentaOrigen;
        this.tipoTransaccion = tipoTransaccion;
    }
    public Transaccion(BigDecimal monto, Date fechaHoraCreacion, String numCuentaOrigen, String tipoTransaccion) {

        this.monto = monto;
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.numCuentaOrigen = numCuentaOrigen;
        this.tipoTransaccion = tipoTransaccion;
    }

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {

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

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.idTransaccion;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Transaccion other = (Transaccion) obj;
        return this.idTransaccion == other.idTransaccion;
    }

    @Override
    public String toString() {
        return "Transaccion{" + "idTransaccion=" + idTransaccion + ", monto=" + monto + ", fechaHoraCreacion=" + fechaHoraCreacion + ", numCuentaOrigen=" + numCuentaOrigen + ", tipoTransaccion=" + tipoTransaccion + '}';
    }
    
}

