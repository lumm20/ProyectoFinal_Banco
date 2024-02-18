/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco.bancodominio;

<<<<<<< HEAD
=======
import java.math.BigDecimal;
>>>>>>> 208485a08e5a867a928b4baf0b034cb91e1b81ae
import java.sql.Date;

/**
 *
<<<<<<< HEAD
 * @author molin
 */
public class Transaccion {
    protected int idTransaccion;
    protected float monto;
=======
 * @author luiis
 */
public class Transaccion {
    protected int idTransaccion;
    protected BigDecimal monto;
>>>>>>> 208485a08e5a867a928b4baf0b034cb91e1b81ae
    protected Date fechaHoraCreacion;
    protected String numCuentaOrigen;
    protected String tipoTransaccion;

    public Transaccion() {
    }

<<<<<<< HEAD
    public Transaccion(int idTransaccion, float monto, Date fechaHoraCreacion, String numCuentaOrigen, String tipoTransaccion) {
=======
    public Transaccion(int idTransaccion, BigDecimal monto, Date fechaHoraCreacion, String numCuentaOrigen, String tipoTransaccion) {
>>>>>>> 208485a08e5a867a928b4baf0b034cb91e1b81ae
        this.idTransaccion = idTransaccion;
        this.monto = monto;
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.numCuentaOrigen = numCuentaOrigen;
        this.tipoTransaccion = tipoTransaccion;
    }

<<<<<<< HEAD
    public Transaccion(float monto, Date fechaHoraCreacion, String numCuentaOrigen, String tipoTransaccion) {
=======
    public Transaccion(BigDecimal monto, Date fechaHoraCreacion, String numCuentaOrigen, String tipoTransaccion) {
>>>>>>> 208485a08e5a867a928b4baf0b034cb91e1b81ae
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

<<<<<<< HEAD
    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
=======
    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
>>>>>>> 208485a08e5a867a928b4baf0b034cb91e1b81ae
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
<<<<<<< HEAD

=======
>>>>>>> 208485a08e5a867a928b4baf0b034cb91e1b81ae
