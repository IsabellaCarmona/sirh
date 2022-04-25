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
import modelo.AsistenciaDAO;
import modelo.Empleado;
import modelo.EmpleadoDAO;
import modelo.Salario;
import vista.FrmNomina;
import vista.FrmVerNomina;
import vista.PruebaNomina;

/**
 *
 * @author ISABELLA CARMONA C
 */
public class ControladorVerNomina {

    Empleado empleado;
    EmpleadoDAO empldao;
    AsistenciaDAO asistdao = new AsistenciaDAO();
    Salario salario;
    DefaultTableModel modelo = new DefaultTableModel();
    FrmVerNomina fnomina;

    public ControladorVerNomina(Empleado empleado, EmpleadoDAO empldao, AsistenciaDAO asistdao, Salario salario, FrmVerNomina fnomina) {
        this.empleado = empleado;
        this.empldao = empldao;
        this.asistdao = asistdao;
        this.salario = salario;
        this.fnomina = fnomina;

        modelo.addColumn("Empleado");
        modelo.addColumn("Documento");
        modelo.addColumn("Cargo");
        modelo.addColumn("Periodo");
        modelo.addColumn("Salario Base");
        modelo.addColumn("Días trabajados");
        modelo.addColumn("Pago Periodo");
        modelo.addColumn("Auxilio Transp");
        modelo.addColumn("Pago Auxilio");
        modelo.addColumn("Bonificación");
        modelo.addColumn("Total Devengado");
        modelo.addColumn("Deducciones");
        modelo.addColumn("Préstamo");
        modelo.addColumn("Total deducciones");
        modelo.addColumn("Total Pagar");
        fnomina.jTbNomina.setModel(modelo);

        ArrayList<String> documentos = new ArrayList();
        ArrayList empl = null;

        try {
            documentos = empldao.traerDocumentos();
        } catch (SQLException ex) {
            Logger.getLogger(PruebaNomina.class.getName()).log(Level.SEVERE, null, ex);
        }

        int salarioBase = 0, diasTrabajados = 0;

        String[] datos = new String[15];
        String docu;
        DecimalFormat df = new DecimalFormat("00,000");
        for (int i = 0; i < documentos.size(); i++) {
            docu = documentos.get(i);

            try {
                salarioBase = empldao.traerSalarioBase(docu);
                diasTrabajados = salario.diasTrabajados(docu);
                empl = empldao.traerRegistros(docu);
            } catch (SQLException ex) {
                Logger.getLogger(PruebaNomina.class.getName()).log(Level.SEVERE, null, ex);
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
            String periodo = String.valueOf(LocalDate.now());
            double pagoPeriodo = salario.validacion(salarioBase, diasTrabajados);
            double auxTransp = salario.auxTransp(salarioBase);
            double auxD;
            if (auxTransp != 0) {
                auxD = (auxTransp / 30) * diasTrabajados;
            } else {
                auxD = 0;
            }

            double totalD = salario.totalD(salarioBase, diasTrabajados);
            String deducciones = "8%";
            double totalDeducciones = salario.deducciones(salarioBase, diasTrabajados);
            double totalP = salario.totalPagar(salarioBase, diasTrabajados);

            datos[0] = nombres;
            datos[1] = tipoID + " " + docu;
            datos[2] = cargo;
            datos[3] = periodo;
            datos[4] = String.valueOf(df.format(salarioBase));
            datos[5] = String.valueOf(diasTrabajados);
            datos[6] = String.valueOf(df.format(pagoPeriodo));
            datos[7] = String.valueOf(df.format(auxTransp));
            datos[8] = String.valueOf(df.format(auxD));
            datos[9] = String.valueOf(0);
            datos[10] = String.valueOf(df.format(totalD));
            datos[11] = deducciones;
            datos[12] = String.valueOf(0);
            datos[13] = String.valueOf(df.format(totalDeducciones));
            datos[14] = String.valueOf(df.format(totalP));

            modelo.addRow(datos);
        }
    }

}
