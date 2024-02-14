/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco.bancodominio;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author luiis
 */
public class Cuenta {
    private String num_cuenta;
    private String estado;
    private float saldo;
    private Date fecha_creacion;

    public Cuenta() {
    }

    public Cuenta(String num_cuenta, String estado, float saldo, Date fecha_creacion) {
        this.num_cuenta = num_cuenta;
        this.estado = estado;
        this.saldo = saldo;
        this.fecha_creacion = fecha_creacion;
    }

    public String getNum_cuenta() {
        return num_cuenta;
    }

    public void setNum_cuenta(String num_cuenta) {
        this.num_cuenta = num_cuenta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.num_cuenta);
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
        final Cuenta other = (Cuenta) obj;
        return Objects.equals(this.num_cuenta, other.num_cuenta);
    }

    @Override
    public String toString() {
        return "Cuenta{" + "num_cuenta=" + num_cuenta + ", estado=" + estado + ", saldo=" + saldo + ", fecha_creacion=" + fecha_creacion + '}';
    }
    
}
