/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco.bancodominio;

import java.sql.Date;

/**
 *
 * @author luiis
 */
public class Cliente {
    private int id_cliente;
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private Date fecha_nacimiento;
    private int edad;
    private int id_domicilio;

    public Cliente(String nombre1, String apellidoPaterno, String apellidoMaterno, String fechaNacimiento, String direccion) {
    }

    public Cliente(int id_cliente, String nombre, String apellidoP, String apellidoM, Date fecha_nacimiento, int edad, int id_domicilio) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.fecha_nacimiento = fecha_nacimiento;
        this.edad = edad;
        this.id_domicilio = id_domicilio;
    }

    public Cliente(String nombre, String apellidoP, String apellidoM, Date fecha_nacimiento, int edad, int id_domicilio) {
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.fecha_nacimiento = fecha_nacimiento;
        this.edad = edad;
        this.id_domicilio = id_domicilio;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getId_domicilio() {
        return id_domicilio;
    }

    public void setId_domicilio(int id_domicilio) {
        this.id_domicilio = id_domicilio;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id_cliente;
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
        final Cliente other = (Cliente) obj;
        return this.id_cliente == other.id_cliente;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id_cliente=" + id_cliente + ", nombre=" + nombre + ", apellidoP=" + apellidoP + ", apellidoM=" + apellidoM + ", fecha_nacimiento=" + fecha_nacimiento + ", edad=" + edad + ", id_domicilio=" + id_domicilio + '}';
    }
    
}
