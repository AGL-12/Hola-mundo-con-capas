/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Alexander
 */
public class Usuario {

    private String nombre;
    private String contrasena;
    private String email;
    private boolean titulao;
    private String sexo;
    private Date fechaNace;
    private int cp;

    public Usuario(String nombre, String contrasena, String email, boolean titulao, String sexo, Date fechaNace, int cp) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.email = email;
        this.titulao = titulao;
        this.sexo = sexo;
        this.fechaNace = fechaNace;
        this.cp = cp;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isTitulao() {
        return titulao;
    }

    public void setTitulao(boolean titulao) {
        this.titulao = titulao;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFechaNace() {
        return fechaNace;
    }

    public void setFechaNace(Date fechaNace) {
        this.fechaNace = fechaNace;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", contrasena=" + contrasena + ", email=" + email + ", titulao=" + titulao + ", sexo=" + sexo + ", fechaNace=" + fechaNace + ", cp=" + cp + '}';
    }

}
