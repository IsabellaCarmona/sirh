/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Empleado;
import modelo.EmpleadoDAO;
import modelo.Salario;
import vista.FrmLiquidacion;

/**
 *
 * @author ISABELLA CARMONA C
 */
public class ControladorLiquidacion implements ActionListener {

    FrmLiquidacion fliquidacion;
    Salario salario;
    Empleado empleado;
    EmpleadoDAO empldao;

    public ControladorLiquidacion(FrmLiquidacion fliquidacion, Salario salario, Empleado empleado, EmpleadoDAO empldao) {
        this.fliquidacion = fliquidacion;
        this.salario = salario;
        this.empleado = empleado;
        this.empldao = empldao;

        this.fliquidacion.jBtEditar.addActionListener(this);
        this.fliquidacion.jBtGenerarPDF.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == fliquidacion.jBtEditar) {

            DecimalFormat df = new DecimalFormat("0,000");

            String[] vectorNumero = (fliquidacion.jTxSalarioBase.getText()).split(" ");
            String numero = vectorNumero[1];

            vectorNumero = numero.split("\\.");

            vectorNumero = (fliquidacion.jTxTotalDevengado.getText()).split(" ");
            numero = vectorNumero[1];
            vectorNumero = numero.split("\\.");
            double totalP = cambioDecimal(vectorNumero);
            double totalDeducciones = 0;

            if (!fliquidacion.jTxConsumo.getText().equals("$ 0") || !fliquidacion.jTxConsumo.getText().equals("$ ")) {
                vectorNumero = (fliquidacion.jTxConsumo.getText()).split(" ");
                numero = vectorNumero[1];
                vectorNumero = numero.split("\\.");

                int consumo = cambioDecimal(vectorNumero);
                if (!fliquidacion.jTxConsumo.getText().equals("$ 0")) {
                    fliquidacion.jTxConsumo.setText("$ " + String.valueOf(df.format(consumo)));
                }
                totalP -= consumo;

                vectorNumero = (fliquidacion.jTxTotalDeducciones.getText()).split(" ");
                numero = vectorNumero[1];
                vectorNumero = numero.split("\\.");
                totalDeducciones += consumo;
                fliquidacion.jTxTotalDeducciones.setText("$ " + String.valueOf(df.format(totalDeducciones)));
            }
            fliquidacion.jTxNetoPagar.setText("$ " + String.valueOf(df.format(totalP)));

            if (!fliquidacion.jTxPrimaPagada.getText().equals("$ 0") || !fliquidacion.jTxPrimaPagada.getText().equals("$ ")) {
                vectorNumero = (fliquidacion.jTxPrimaPagada.getText()).split(" ");
                numero = vectorNumero[1];
                vectorNumero = numero.split("\\.");

                int primaPagada = cambioDecimal(vectorNumero);
                if (!fliquidacion.jTxPrimaPagada.getText().equals("$ 0")) {
                    fliquidacion.jTxPrimaPagada.setText("$ " + String.valueOf(df.format(primaPagada)));
                }
                totalP -= primaPagada;

                vectorNumero = (fliquidacion.jTxTotalDeducciones.getText()).split(" ");
                numero = vectorNumero[1];
                vectorNumero = numero.split("\\.");
                totalDeducciones += primaPagada;
                fliquidacion.jTxTotalDeducciones.setText("$ " + String.valueOf(df.format(totalDeducciones)));
            }
            fliquidacion.jTxNetoPagar.setText("$ " + String.valueOf(df.format(totalP)));
        }

        if (e.getSource() == fliquidacion.jBtGenerarPDF) {

            int respuesta = JOptionPane.showConfirmDialog(fliquidacion, "¿Está seguro de que desea generar el PDF?\n\n"
                    + "No se podrán hacer cambios en la nómina cuando se genere el PDF", "Fin Nómina", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                fliquidacion.jTxEmpleado.setEnabled(true);
                fliquidacion.jTxDocumento.setEnabled(true);
                fliquidacion.jTxCargo.setEnabled(true);
                fliquidacion.jTxSalarioBase.setEnabled(true);
                fliquidacion.jTxPrima.setEnabled(true);
                fliquidacion.jTxInteresesCesan.setEnabled(true);
                fliquidacion.jTxCesantias.setEnabled(true);
                fliquidacion.jTxVacaciones.setEnabled(true);
                fliquidacion.jTxTotalDevengado.setEnabled(true);
                fliquidacion.jTxNetoPagar.setEnabled(true);

                fliquidacion.jBtEditar.setVisible(false);
                fliquidacion.jBtGenerarPDF.setVisible(false);
                PrinterJob job = PrinterJob.getPrinterJob();
                job.setPrintable(fliquidacion);

                if (job.printDialog()) {
                    try {
                        job.print();
                    } catch (PrinterException ex) {
                        Logger.getLogger(FrmLiquidacion.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(fliquidacion, "PDF creado de forma exitosa.");
                    fliquidacion.jTxEmpleado.setEnabled(false);
                    fliquidacion.jTxDocumento.setEnabled(false);
                    fliquidacion.jTxCargo.setEnabled(false);
                    fliquidacion.jTxSalarioBase.setEnabled(false);
                    fliquidacion.jTxPrima.setEnabled(false);
                    fliquidacion.jTxInteresesCesan.setEnabled(false);
                    fliquidacion.jTxCesantias.setEnabled(false);
                    fliquidacion.jTxVacaciones.setEnabled(false);
                    fliquidacion.jTxTotalDevengado.setEnabled(false);
                    fliquidacion.jTxNetoPagar.setEnabled(false);
                    fliquidacion.jBtEditar.setVisible(true);
                    fliquidacion.jBtGenerarPDF.setVisible(true);
                    fliquidacion.dispose();
                } else {
                    JOptionPane.showMessageDialog(fliquidacion, "La impresión fue cancelada");
                }
            }
        }
    }

    public int cambioDecimal(String[] numero) {

        int numeroInt = 0;
        String numerito = "";
        for (int i = 0; i < Arrays.asList(numero).size(); i++) {

            numerito += Arrays.asList(numero).get(i);
        }
        numeroInt = Integer.parseInt(numerito);
        return numeroInt;
    }
}
