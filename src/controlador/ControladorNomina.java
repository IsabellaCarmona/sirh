/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Arrays;
import javax.swing.JOptionPane;
import modelo.Salario;
import vista.FrmNomina;
import vista.FrmVerNomina;

/**
 *
 * @author ISABELLA CARMONA C
 */
public class ControladorNomina implements ActionListener {

    FrmNomina fnomina;
    Salario salario;
    FrmVerNomina fvernomina;

    public ControladorNomina(FrmNomina fnomina, Salario salario, FrmVerNomina fvernomina) {
        this.fnomina = fnomina;
        this.salario = salario;
        this.fvernomina = fvernomina;

        this.fnomina.jBtGuardar.addActionListener(this);
        this.fnomina.jBtEditar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == fnomina.jBtEditar) {

            DecimalFormat df = new DecimalFormat("0,000");

            int diasTrabajados = Integer.parseInt(fnomina.jTxDiasTrabajados.getText());

            String[] vectorNumero = (fnomina.jTxSalarioBase.getText()).split(" ");
            String numero = vectorNumero[1];

            vectorNumero = numero.split("\\.");

            int salarioBase = cambioDecimal(vectorNumero);

            double pagoPeriodo = salario.validacion(salarioBase, diasTrabajados);
            double auxTransp = salario.auxTransp(salarioBase);
            double auxD;

            if (auxTransp != 0) {
                auxD = (auxTransp / 30) * diasTrabajados;
            } else {
                auxD = 0;
            }

            double totalD = salario.totalD(salarioBase, diasTrabajados);
            double totalP = salario.totalPagar(salarioBase, diasTrabajados);
            double totalDeducciones = salario.deducciones(salarioBase, diasTrabajados);

            fnomina.jTxPagoQuincena.setText("$ " + String.valueOf(df.format(pagoPeriodo)));
            fnomina.jTxPagoAuxTransp.setText("$ " + String.valueOf(df.format(auxD)));

            if (!fnomina.jTxBonificacion.getText().equals("$ 0") || !fnomina.jTxBonificacion.getText().equals("$ ")) {
                vectorNumero = (fnomina.jTxBonificacion.getText()).split(" ");
                numero = vectorNumero[1];
                vectorNumero = numero.split("\\.");
                int bonificacion = cambioDecimal(vectorNumero);
                if (!fnomina.jTxBonificacion.getText().equals("$ 0")) {
                    fnomina.jTxBonificacion.setText("$ " + String.valueOf(df.format(bonificacion)));
                }

                totalP += bonificacion;
                totalD += bonificacion;
            }

            fnomina.jTxTotalDevengado.setText("$ " + String.valueOf(df.format(totalD)));

            if (!fnomina.jTxPrestamo.getText().equals("$ 0") || !fnomina.jTxPrestamo.getText().equals("$ ")) {
                vectorNumero = (fnomina.jTxPrestamo.getText()).split(" ");
                numero = vectorNumero[1];
                vectorNumero = numero.split("\\.");

                int prestamo = cambioDecimal(vectorNumero);
                if (!fnomina.jTxPrestamo.getText().equals("$ 0")) {
                    fnomina.jTxPrestamo.setText("$ " + String.valueOf(df.format(prestamo)));
                }
                totalP -= prestamo;

                vectorNumero = (fnomina.jTxTotalDeducciones.getText()).split(" ");
                numero = vectorNumero[1];
                vectorNumero = numero.split("\\.");
                totalDeducciones += prestamo;
                fnomina.jTxTotalDeducciones.setText("$ " + String.valueOf(df.format(totalDeducciones)));
            }
            fnomina.jTxNetoPagar.setText("$ " + String.valueOf(df.format(totalP)));
        }

        if (e.getSource() == fnomina.jBtGuardar) {

            int respuesta = JOptionPane.showConfirmDialog(fnomina, "¿Está seguro de guardar los cambios?\nPara que los cambios se vean reflejados en la tabla debe primero oprimir el botón 'Editar'",
                    "Fin Nómina", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {

                String doc = fnomina.jTxDocumento.getText();
                for (int i = 0; i < fvernomina.jTbNomina.getRowCount(); i++) {

                    if (fvernomina.jTbNomina.getValueAt(i, 1).equals(doc)) {
                        for (int j = 0; j < fvernomina.jTbNomina.getColumnCount(); j++) {

                            String diasTrabajados = fnomina.jTxDiasTrabajados.getText();
                            String pagoPeriodo = fnomina.jTxPagoQuincena.getText();
                            String pagoAuxTransp = fnomina.jTxPagoAuxTransp.getText();
                            String bonificacion = fnomina.jTxBonificacion.getText();
                            String totalD = fnomina.jTxTotalDevengado.getText();
                            String prestamo = fnomina.jTxPrestamo.getText();
                            String totalDeducciones = fnomina.jTxTotalDeducciones.getText();
                            String netoPagar = fnomina.jTxNetoPagar.getText();

                            fvernomina.jTbNomina.setValueAt(diasTrabajados, i, 5);
                            fvernomina.jTbNomina.setValueAt(pagoPeriodo, i, 6);
                            fvernomina.jTbNomina.setValueAt(pagoAuxTransp, i, 8);
                            fvernomina.jTbNomina.setValueAt(bonificacion, i, 9);
                            fvernomina.jTbNomina.setValueAt(totalD, i, 10);
                            fvernomina.jTbNomina.setValueAt(prestamo, i, 12);
                            fvernomina.jTbNomina.setValueAt(totalDeducciones, i, 13);
                            fvernomina.jTbNomina.setValueAt(netoPagar, i, 14);
                        }
                    }
                }
                fnomina.dispose();
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
