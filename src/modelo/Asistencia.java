/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author ISABELLA CARMONA C
 */
public class Asistencia {

    private int idAsistencia;
    private Date fechaAsistencia;
    private Time horaAsistencia;
    private String idEmpleado;

    public Asistencia() {
    }

    public Asistencia(Date fechaAsistencia, Time horaAsistencia, String idEmpleado) {
        this.fechaAsistencia = fechaAsistencia;
        this.horaAsistencia = horaAsistencia;
        this.idEmpleado = idEmpleado;
    }

    public int getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(int idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public Date getFechaAsistencia() {
        return fechaAsistencia;
    }

    public void setFechaAsistencia(Date fechaAsistencia) {
        this.fechaAsistencia = fechaAsistencia;
    }

    public Time getHoraAsistencia() {
        return horaAsistencia;
    }

    public void setHoraAsistencia(Time horaAsistencia) {
        this.horaAsistencia = horaAsistencia;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

}
