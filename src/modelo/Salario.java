/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author ISABELLA CARMONA C
 */
public class Salario {

    private int idSalario;
    private int diasTrabajados; //Cambiar en BD
    private Date fechaCorte;
    private String idEmpleado;

    public Salario() {
    }

    public Salario(int diasTrabajados, Date fechaCorte, String idEmpleado) {
        this.diasTrabajados = diasTrabajados;
        this.fechaCorte = fechaCorte;
        this.idEmpleado = idEmpleado;
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

    public Date getFechaCorte() {
        return fechaCorte;
    }

    public void setFechaCorte(Date fechaCorte) {
        this.fechaCorte = fechaCorte;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int diasTrabajados(String documento) throws SQLException {

        String fechaActual = String.valueOf(LocalDate.now());
        String dia = String.valueOf(fechaActual.charAt(8)) + String.valueOf(fechaActual.charAt(9));

        AsistenciaDAO asistdao = new AsistenciaDAO();
        int registros;
        registros = asistdao.traerNroRegistros(documento);

        int diasTrabajados = 0;
        if (dia.equals("03") || dia.equals("15")) {

            if (registros % 2 == 0) {
                diasTrabajados = (registros / 2) + 2;
            } else {
                diasTrabajados = ((registros / 2) + 2) - 1;
            }
        }
        return diasTrabajados;
    }

    //TotalDevengado: Total a pagar cada quincena (sin salud ni pension)
    public int totalD(int salarioBase, int diasTrabajados) {

        int salarioD = validacion(salarioBase, diasTrabajados);
        int auxTransp = auxTransp(salarioBase);
        if (auxTransp != 0) {
            auxTransp = (auxTransp / 30) * diasTrabajados;
        }

        int totalDevengado = salarioD + auxTransp;

        return totalDevengado;
    }

    public double deducciones(double salarioBase, int diasTrabajados) {

        double salario = validacion(salarioBase, diasTrabajados);

        double deducciones = salario * 0.08;

        return deducciones;
    }

    public int totalPagar(double salarioBase, int diasTrabajados) {

        double salario = validacion(salarioBase, diasTrabajados);

        int auxTransp = auxTransp(salarioBase);

        if (auxTransp != 0) {
            auxTransp = (auxTransp / 30) * diasTrabajados;
        }

        int totalPagar = (int) (salario - (salario * 0.08) + auxTransp);
        return totalPagar;
    }

    //Cesantias: Dado anualmente a un fondo de cesantias cada 14 de febrero
    public int cesantias(int salarioBase, int diasTrabajados) {

        //Entradas salario Base, auxilio de Transporte
        int auxTrans = auxTransp(salarioBase);

        //Cesantias = ((salarioBase+AuxTransp+HorasExtras)/360)*DiasTrabajados
        int cesantias;

        cesantias = ((salarioBase + auxTrans) / 360) * diasTrabajados;

        return cesantias;
    }

    //Prima: Dado al empleado cada 6 meses (Junio y Diciembre) en quincenas
    public int prima(int diasTrabajados, int salarioBase) {

        //Prima = (1/2 salarioBase/180)*diasTrabajados
        int prima = ((salarioBase / 2) / 180) * diasTrabajados;
        return prima;
    }

    //Vacaciones: Se pagan cuando el trabajador cumple 1 año en la empresa y
    //el pago corresponde a 15 dias de trabajo (salarioBase/2)
    public int vacaciones(int salarioBase, int diasTrabajados) {

        int vacaciones;
        if (diasTrabajados == 360) {
            vacaciones = salarioBase / 2;
        } else {
            vacaciones = ((salarioBase / 2) * diasTrabajados) / 360;
        }

        return vacaciones;
    }

    //Intereses a Cesantias: Se pagan al trabajador anualmente sobre el
    //12% totales de las censantias cada 31 de Enero
    public int interesCesantias(int salarioBase, int diasTrabajados) {

        int cesantias = cesantias(salarioBase, diasTrabajados);
        int intCesantias = (int) (cesantias * 0.12);

        return intCesantias;
    }

    public int auxTransp(double salarioBase) {

        int auxTransp = 0;
        if (salarioBase <= 1000000 * 2) {
            auxTransp = 117172;
        }

        return auxTransp;
    }

    public int validacion(double salarioBase, int diasTrabajados) {

        int salarioDevengado;
        double vlrDia = (salarioBase / 30);

        salarioDevengado = (int) (vlrDia * diasTrabajados);

        int totalDevengado = salarioDevengado;

        return totalDevengado;
    }

}
