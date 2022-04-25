/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author ISABELLA CARMONA C
 */
public class PruebaLogicaNomina {

    Empleado empleado;
    EmpleadoDAO empldao;
    AsistenciaDAO asistdao = new AsistenciaDAO();

    public PruebaLogicaNomina() {
    }

    public int diasTrabajados(String documento) throws SQLException {

        String fechaActual = String.valueOf(LocalDate.now());
        String dia = String.valueOf(fechaActual.charAt(8)) + String.valueOf(fechaActual.charAt(9));

        int registros;
        registros = asistdao.traerNroRegistros(documento);

        int diasTrabajados = 0;
        if (dia.equals("23") || dia.equals("15")) {

            if (registros % 2 == 0) {
                diasTrabajados = (registros / 2) + 2;
            } else {
                diasTrabajados = ((registros / 2) + 2) - 1;
            }
        }
        return diasTrabajados;
    }

    //TotalDevengado: Total a pagar cada quincena (sin salud ni pension)
    public double totalD(int salarioBase, int diasTrabajados) {

        double salarioD = validacion(salarioBase, diasTrabajados);
        double auxD = auxTransp(salarioBase);
        double totalDevengado = salarioD + auxD;

        return totalDevengado;
    }

    public double totalPagar(int salarioBase, int diasTrabajados) {

        double salario = validacion(salarioBase, diasTrabajados);

        double auxTransp = auxTransp(salarioBase);

        if (auxTransp != 0) {
            auxTransp = (auxTransp / 30) * diasTrabajados;
        }

        double totalPagar = salario - (salario * 0.08) + auxTransp;
        return totalPagar;
    }

    //Cesantias: Dado anualmente a un fondo de cesantias cada 14 de febrero
    public double cesantias(int salarioBase, int diasTrabajados) {

        //Entradas salario Base, auxilio de Transporte
        int auxTrans = auxTransp(salarioBase);

        //Cesantias = ((salarioBase+AuxTransp+HorasExtras)/360)*DiasTrabajados
        double cesantias;

        cesantias = ((salarioBase + auxTrans) / 360) * diasTrabajados;

        return cesantias;
    }

    //Prima: Dado al empleado cada 6 meses (Junio y Diciembre) en quincenas
    public double prima(int diasTrabajados, int salarioBase) {

        //Prima = (1/2 salarioBase/180)*diasTrabajados
        double prima = ((salarioBase / 2) / 180) * diasTrabajados;
        return prima;
    }

    //Vacaciones: Se pagan cuando el trabajador cumple 1 año en la empresa y
    //el pago corresponde a 15 dias de trabajo (salarioBase/2)
    public double vacaciones(int salarioBase, int diasTrabajados) {

        double vacaciones;
        if (diasTrabajados == 360) {
            vacaciones = salarioBase / 2;
        } else {
            vacaciones = ((salarioBase / 2) * diasTrabajados) / 360;
        }

        return vacaciones;
    }

    //Intereses a Cesantias: Se pagan al trabajador anualmente sobre el
    //12% totales de las censantias cada 31 de Enero
    public double interesCesantias(int salarioBase, int diasTrabajados) {

        double cesantias = cesantias(salarioBase, diasTrabajados);
        double intCesantias = cesantias * 0.12;

        return intCesantias;
    }

    public int auxTransp(int salarioBase) {

        int auxTransp = 0;
        if (salarioBase < 1000000 * 2) {
            auxTransp = 117172;
        }

        return auxTransp;
    }

    public double validacion(int salarioBase, int diasTrabajados) {

        double salarioDevengado = 0;

        if (diasTrabajados == 13) {
            salarioDevengado = salarioBase / 2;
        } else {
            salarioDevengado = (salarioBase / 30) * diasTrabajados;
        }

        double totalDevengado = salarioDevengado;

        return totalDevengado;
    }
}
