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
public class PruebaLogicaNomina {

    //https://es.stackoverflow.com/questions/288266/calcular-el-n%C3%BAmero-de-d%C3%ADas-entre-dos-fechas-sql-server
    //Sacar numero de dias entre 2 fechas sql
    public PruebaLogicaNomina() {
    }

    //TotalDevengado: Total a pagar cada quincena (sin salud ni pension)
    public double totalD(double salarioBase, double diasTrabajados) {

        double[] vectorV = validacion(salarioBase, diasTrabajados);

        double salarioD = vectorV[0];
        double auxD = vectorV[1];
        double totalDevengado = salarioD + auxD;

        return totalDevengado;
    }

    public double totalPagar(double salarioBase, double diasTrabajados) {

        double[] vector = validacion(salarioBase, diasTrabajados);
        double salario = vector[0];
        double totalPagar = salario - (salario * 0.08);
        return totalPagar;
    }

    //Cesantias: Dado anualmente a un fondo de cesantias cada 14 de febrero
    public double cesantias(double salarioBase, double diasTrabajados) {

        //Entradas salario Base, auxilio de Transporte
        int auxTransp = 117172;

        //Cesantias = ((salarioBase+AuxTransp+HorasExtras)/360)*DiasTrabajados
        double cesantias;

        cesantias = ((salarioBase + auxTransp) / 360) * diasTrabajados;

        return cesantias;
    }

    //Prima: Dado al empleado cada 6 meses (Junio y Diciembre) en quincenas
    public double prima(double diasTrabajados, double salarioBase) {

        //Prima = (1/2 salarioBase/180)*diasTrabajados
        double prima = ((salarioBase / 2) / 180) * diasTrabajados;
        return prima;
    }

    //Vacaciones: Se pagan cuando el trabajador cumple 1 año en la empresa y
    //el pago corresponde a 15 dias de trabajo (salarioBase/2)
    public double vacaciones(double salarioBase, double diasTrabajados) {

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
    public double interesCesantias(double salarioBase, double diasTrabajados) {

        double cesantias = cesantias(salarioBase, diasTrabajados);
        double intCesantias = cesantias * 0.12;

        return intCesantias;
    }

    public double[] validacion(double salarioBase, double diasTrabajados) {

        double salarioDevengado = 0;
        double auxTranspDevengado = 0;

        if (diasTrabajados == 13) {
            salarioDevengado = salarioBase / 2;
            if (salarioBase < 1000000 * 2) {
                auxTranspDevengado = 117172 / 2;
            }
        }
        double[] totalDevengado = {salarioDevengado, auxTranspDevengado};

        return totalDevengado;
    }
}
