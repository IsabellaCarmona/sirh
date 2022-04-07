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
    public int totalD(int salarioBase, int diasTrabajados) {

        int[] vectorV = validacion(salarioBase, diasTrabajados);

        int salarioD = vectorV[0];
        int auxD = vectorV[1];
        int totalDevengado = salarioD + auxD;

        return totalDevengado;
    }

    public double totalPagar(int salarioBase, int diasTrabajados) {

        int[] vector = validacion(salarioBase, diasTrabajados);
        int salario = vector[0];
        double totalPagar = salario - (salario * 0.08);
        return totalPagar;
    }

    //Cesantias: Dado anualmente a un fondo de cesantias cada 14 de febrero
    public double cesantias(int salarioBase, int diasTrabajados) {

        //Entradas salario Base, auxilio de Transporte
        int auxTransp = 117172;

        //Cesantias = ((salarioBase+AuxTransp+HorasExtras)/360)*DiasTrabajados
        double cesantias;

        cesantias = ((salarioBase + auxTransp) / 360) * diasTrabajados;

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

    public int[] validacion(int salarioBase, int diasTrabajados) {

        int salarioDevengado = 0;
        int auxTranspDevengado = 0;

        if (diasTrabajados == 13) {
            salarioDevengado = salarioBase / 2;
            if (salarioBase < 1000000 * 2) {
                auxTranspDevengado = 117172 / 2;
            }
        }
        int[] totalDevengado = {salarioDevengado, auxTranspDevengado};

        return totalDevengado;
    }
}
