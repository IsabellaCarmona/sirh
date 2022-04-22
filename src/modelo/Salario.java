/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author ISABELLA CARMONA C
 */
public class Salario {

    private int idSalario;
    private int diasTrabajados; //Cambiar en BD
    private float valorHora;
    private String idEmpleado;
    private double pension;
    private double salud;
    private int SMMLV = 1000000; //Agregar BD
    private int auxTransp = 117172; //Agregar BD

    public Salario() {
    }

    public int getIdSalario() {
        return idSalario;
    }

    public void setIdSalario(int idSalario) {
        this.idSalario = idSalario;
    }

    public int getDiasTrabajados() {
        return diasTrabajados;
    }

    public void setDiasTrabajados(int diasTrabajados) {
        this.diasTrabajados = diasTrabajados;
    }

    public float getValorHora() {
        return valorHora;
    }

    public void setValorHora(float valorHora) {
        this.valorHora = valorHora;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public double getPension() {
        return pension;
    }

    public void setPension(double pension) {
        this.pension = pension;
    }

    public double getSalud() {
        return salud;
    }

    public void setSalud(double salud) {
        this.salud = salud;
    }

}
