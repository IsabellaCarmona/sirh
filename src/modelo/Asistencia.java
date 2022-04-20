/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author ISABELLA CARMONA C
 */
public class Asistencia {

    private int idAsistencia;
    private LocalDateTime fechaAsistencia;
    private String idEmpleado;

    public Asistencia() {
    }

    public Asistencia(LocalDateTime fechaAsistencia, String idEmpleado) {
        this.fechaAsistencia = fechaAsistencia;
        this.idEmpleado = idEmpleado;
    }

    public int getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(int idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public LocalDateTime getFechaAsistencia() {
        return fechaAsistencia;
    }

    public void setFechaAsistencia(LocalDateTime fechaAsistencia) {
        this.fechaAsistencia = fechaAsistencia;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

}
