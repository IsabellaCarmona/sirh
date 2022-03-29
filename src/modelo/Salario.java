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
    private Empleado idEmpleado;
    private double pension;
    private double salud;
    private int SMMLV = 1000000; //Agregar BD
    private int auxTransp = 117172; //Agregar BD

    //Total a Pagar, salario base, aux. Transporte, pension
    public double calcularPago() {
        int salario = idEmpleado.getSalarioBase();
        double vlrDia = salario / 30;
        double quincena = vlrDia * diasTrabajados;

        return quincena;
    }

    public double subsidioTransporte() {

        double vlrAuxTranspDia = auxTransp / 30;
        double subTransp;
        if (idEmpleado.getSalarioBase() < SMMLV * 2) {
            subTransp = vlrAuxTranspDia * diasTrabajados;
        } else {
            subTransp = 0;
        }

        return subTransp;
    }

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

    public Empleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleado idEmpleado) {
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
