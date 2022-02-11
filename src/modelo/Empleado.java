/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;

/**
 *
 * @author ISABELLA CARMONA C
 */
public class Empleado {

    private String tipoId;
    private String cedula;
    private String nombres;
    private String apellidos;
    private Date fechaNacimiento;
    private String telefono;
    private String direccion;
    private String cargo;
    private String rh;
    private String eps;
    private String arl;
    private int salarioBase;

    public Empleado() {
    }

    public Empleado(String tipoId, String cedula, String nombres, String apellidos) {
        this.tipoId = tipoId;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    public Empleado(String tipoId, String cedula, String nombres, String apellidos, Date fechaNacimiento,
            String telefono, String direccion, String cargo, String rh, String eps, String arl, int salarioBase) {
        this.tipoId = tipoId;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.direccion = direccion;
        this.cargo = cargo;
        this.rh = rh;
        this.eps = eps;
        this.arl = arl;
        this.salarioBase = salarioBase;
    }

    public boolean empiezaPor(String inicio) {
        if (inicio.length() > cedula.length()) {
            return false;
        }

        for (int i = 0; i < inicio.length(); i++) {
            if (inicio.charAt(i) != cedula.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    public String getTipoId() {
        return tipoId;
    }

    public void setTipoId(String tipoId) {
        this.tipoId = tipoId;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getRh() {
        return rh;
    }

    public void setRh(String rh) {
        this.rh = rh;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public String getArl() {
        return arl;
    }

    public void setArl(String arl) {
        this.arl = arl;
    }

    public int getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(int salarioBase) {
        this.salarioBase = salarioBase;
    }

}
