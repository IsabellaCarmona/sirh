/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import modelo.AsistenciaDAO;
import modelo.Empleado;
import modelo.EmpleadoDAO;
import modelo.Salario;
import vista.FrmLiquidacion;
import vista.FrmVerNomina;

/**
 *
 * @author ISABELLA CARMONA C
 */
public class ControladorVerNomina implements ActionListener {

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

        this.fnomina.jBtSalir.addActionListener(this);
        this.fnomina.jBtGenerarPdf.addActionListener(this);

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

        TableColumnModel columnModel = fnomina.jTbNomina.getColumnModel();

        columnModel.getColumn(0).setPreferredWidth(605); //Empleado
        columnModel.getColumn(1).setPreferredWidth(300); //Documento
        columnModel.getColumn(2).setPreferredWidth(250); //Cargo
        columnModel.getColumn(3).setPreferredWidth(210); //Periodo
        columnModel.getColumn(4).setPreferredWidth(230); //Salario Base
        columnModel.getColumn(5).setPreferredWidth(250); //Días trabajados
        columnModel.getColumn(6).setPreferredWidth(220); //Pago Periodo
        columnModel.getColumn(7).setPreferredWidth(220); //Auxilio Transp
        columnModel.getColumn(8).setPreferredWidth(200); //Pago Auxilio
        columnModel.getColumn(9).setPreferredWidth(190); //Bonificación
        columnModel.getColumn(10).setPreferredWidth(260); //Total Devengado
        columnModel.getColumn(11).setPreferredWidth(220); //Deducciones
        columnModel.getColumn(12).setPreferredWidth(220); //Préstamo
        columnModel.getColumn(13).setPreferredWidth(280); //Total deducciones
        columnModel.getColumn(14).setPreferredWidth(203); //Total Pagar

        ArrayList<String> documentos = new ArrayList();
        ArrayList empl = null;

        try {
            documentos = empldao.traerDocumentos();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorVerNomina.class.getName()).log(Level.SEVERE, null, ex);
        }

        int salarioBase = 0, diasTrabajados = 0;

        String[] datos = new String[15];
        String docu;
        DecimalFormat df = new DecimalFormat("0,000");
        for (int i = 0; i < documentos.size(); i++) {
            docu = documentos.get(i);

            try {
                salarioBase = empldao.traerSalarioBase(docu);
                diasTrabajados = salario.diasTrabajados(docu);
                empl = empldao.traerRegistros(docu);
            } catch (SQLException ex) {
                Logger.getLogger(ControladorVerNomina.class.getName()).log(Level.SEVERE, null, ex);
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
            datos[4] = "$ " + String.valueOf(df.format(salarioBase));
            datos[5] = String.valueOf(diasTrabajados);
            datos[6] = "$ " + String.valueOf(df.format(pagoPeriodo));
            datos[7] = "$ " + String.valueOf(df.format(auxTransp));
            datos[8] = "$ " + String.valueOf(df.format(auxD));
            datos[9] = "$ " + String.valueOf(0);
            datos[10] = "$ " + String.valueOf(df.format(totalD));
            datos[11] = deducciones;
            datos[12] = "$ " + String.valueOf(0);
            datos[13] = "$ " + String.valueOf(df.format(totalDeducciones));
            datos[14] = "$ " + String.valueOf(df.format(totalP));

            modelo.addRow(datos);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == fnomina.jBtSalir) {
            int respuesta = JOptionPane.showConfirmDialog(fnomina, "¿Está seguro de salir?", "Fin Reporte Nómina", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                fnomina.dispose();
            }
        }

        if (e.getSource() == fnomina.jBtGenerarPdf) {

            PrinterJob job = PrinterJob.getPrinterJob();
            PageFormat preformat = job.defaultPage();
            preformat.setOrientation(PageFormat.LANDSCAPE);
            PageFormat postformat = job.pageDialog(preformat);
            job.setPrintable(fnomina);

            if (job.printDialog()) {
                try {
                    job.print();
                } catch (PrinterException ex) {
                    Logger.getLogger(FrmLiquidacion.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                JOptionPane.showMessageDialog(fnomina, "La impresión fue cancelada");
            }
        }
    }

}
