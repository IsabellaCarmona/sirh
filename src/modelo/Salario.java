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
    private float horasTrabajadas;
    private float valorHora;
    private Empleado idEmpleado;
    private float porcARL;
    private float porcEPS;

    public Salario() {
    }

    public int getIdSalario() {
        return idSalario;
    }

    public void setIdSalario(int idSalario) {
        this.idSalario = idSalario;
    }

    public float getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(float horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
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

    public float getPorcARL() {
        return porcARL;
    }

    public void setPorcARL(float porcARL) {
        this.porcARL = porcARL;
    }

    public float getPorcEPS() {
        return porcEPS;
    }

    public void setPorcEPS(float porcEPS) {
        this.porcEPS = porcEPS;
    }

}
