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
import modelo.Salario;
import vista.FrmNomina;

/**
 *
 * @author ISABELLA CARMONA C
 */
public class ControladorNomina implements ActionListener {

    FrmNomina fnomina;
    Salario salario;

    public ControladorNomina(FrmNomina fnomina, Salario salario) {
        this.fnomina = fnomina;
        this.salario = salario;

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

            fnomina.jTxPagoQuincena.setText("$ " + String.valueOf(df.format(pagoPeriodo)));
            fnomina.jTxPagoAuxTransp.setText("$ " + String.valueOf(df.format(auxD)));
            fnomina.jTxTotalDevengado.setText("$ " + String.valueOf(df.format(totalD)));

            if (!fnomina.jTxBonificacion.getText().equals("$ 0") || !fnomina.jTxBonificacion.getText().equals("$ ")) {
                vectorNumero = (fnomina.jTxBonificacion.getText()).split(" ");
                numero = vectorNumero[1];
                vectorNumero = numero.split("\\.");
                int bonificacion = cambioDecimal(vectorNumero);
                fnomina.jTxBonificacion.setText("$ " + String.valueOf(df.format(bonificacion)));
                totalP += bonificacion;
            }

            if (!fnomina.jTxPrestamo.getText().equals("$ 0") || !fnomina.jTxPrestamo.getText().equals("$ ")) {
                vectorNumero = (fnomina.jTxPrestamo.getText()).split(" ");
                numero = vectorNumero[1];
                vectorNumero = numero.split("\\.");

                int prestamo = cambioDecimal(vectorNumero);
                fnomina.jTxPrestamo.setText("$ " + String.valueOf(df.format(prestamo)));
                totalP += prestamo;
            }
            fnomina.jTxNetoPagar.setText("$ " + String.valueOf(df.format(totalP)));
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
