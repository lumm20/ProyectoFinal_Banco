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
public class CuentaDTO {
    private String num_cuenta;
    private String estado;
    private float saldo;
    private Date fecha_creacion;

    public CuentaDTO() {
    }

    public CuentaDTO(String num_cuenta, String estado, float saldo, Date fecha_creacion) {
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
    
    
}