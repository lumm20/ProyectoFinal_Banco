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
public class ClienteDTO {
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private Date fecha_nacimiento;
    private int edad;
    private int id_direccion;

    public ClienteDTO() {
    }
    
    public ClienteDTO(String nombre, String apellidoP, String apellidoM, Date fecha_nacimiento,
            int edad, int id_direccion) {
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.fecha_nacimiento = fecha_nacimiento;
        this.edad = edad;
        this.id_direccion = id_direccion;
    }

    public ClienteDTO(String nombre, String apellidoP, String apellidoM, Date fecha_nacimiento, int edad) {
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.fecha_nacimiento = fecha_nacimiento;
        this.edad = edad;
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

    public int getId_direccion() {
        return id_direccion;
    }

    public void setId_direccion(int id_direccion) {
        this.id_direccion = id_direccion;
    }  
}

    