/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import modelo.Empleado;
import modelo.EmpleadoDAO;
import modelo.Salario;
import modelo.SalarioDAO;
import vista.FrmVerPrima;

/**
 *
 * @author ISABELLA CARMONA C
 */
public class ControladorVerPrima {

    FrmVerPrima fprima;
    Salario salario;
    SalarioDAO saldao;
    DefaultTableModel modelo = new DefaultTableModel();
    Empleado empleado;
    EmpleadoDAO empldao;

    public ControladorVerPrima(FrmVerPrima fprima, Salario salario, SalarioDAO saldao) {
        this.fprima = fprima;
        this.salario = salario;
        this.saldao = saldao;

        modelo.addColumn("Empleado");
        modelo.addColumn("Documento");
        modelo.addColumn("Cargo");
        modelo.addColumn("Periodo");
        modelo.addColumn("Salario Base");
        modelo.addColumn("Prima Servicios");
        modelo.addColumn("Total Devengado");
        modelo.addColumn("Neto Pagar");

        ArrayList<String> documentos = new ArrayList();
        ArrayList empl = null;

        try {
            documentos = empldao.traerDocumentos();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorVerPrima.class.getName()).log(Level.SEVERE, null, ex);
        }

        int salarioBase = 0, diasTrabajados = 0;

        String[] datos = new String[8];
        String docu;
        DecimalFormat df = new DecimalFormat("0,000");

        for (int i = 0; i < documentos.size(); i++) {
            docu = documentos.get(i);

            try {
                salarioBase = empldao.traerSalarioBase(docu);
                empl = empldao.traerRegistros(docu);
            } catch (SQLException ex) {
                Logger.getLogger(ControladorVerPrima.class.getName()).log(Level.SEVERE, null, ex);
            }

            String nombres = "";
            String tipoID = "";
            String cargo = "";
            for (int j = 0; j < empl.size(); j++) {
                empleado = (Empleado) empl.get(j);
                nombres = empleado.getNombres() + " " + empleado.getApellidos();

                if (empleado.getTipoId().equals("Cédula de Ciudadanía")) {
                    tipoID = "CC";
                } else if (empleado.getTipoId().equals("Cédula de Extranjería")) {
                    tipoID = "CE";
                } else {
                    tipoID = empleado.getTipoId();
                }
                cargo = empleado.getCargo();
            }

            String fechaActual = String.valueOf(LocalDate.now());
            String mes = String.valueOf(fechaActual.charAt(5)) + String.valueOf(fechaActual.charAt(6));

            if (mes.equals("06")) {

            } else if (mes.equals("12")) {

            }
        }
    }

}
