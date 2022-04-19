/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Turnos;
import modelo.TurnosDAO;
import vista.FrmAccesoCuenta;
import vista.FrmMarcarTurno;

/**
 *
 * @author ISABELLA CARMONA C
 */
public class ControladorMarcarTurno implements ActionListener {

    FrmMarcarTurno fmarcar;
    Turnos turno;
    TurnosDAO tdao;

    public ControladorMarcarTurno(FrmMarcarTurno fmarcar, Turnos turno, TurnosDAO tdao) {
        this.fmarcar = fmarcar;
        this.turno = turno;
        this.tdao = tdao;

        fmarcar.jBtMarcarTurno.addActionListener(this);
        fmarcar.jBtVolver.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == fmarcar.jBtMarcarTurno) {

            String documento = fmarcar.jTxDocumento.getText();
            java.sql.Date diaActual = new java.sql.Date(new java.util.Date().getTime());
            //LocalDate diaActual = LocalDate.now();
            LocalTime horaActual = (LocalTime.now()).withNano(0);

            System.out.println("Dia Hoy: " + diaActual + "\nHora Actual: " + horaActual + "\nID: " + documento);

            ArrayList listaTurnos = null;
            try {
                listaTurnos = tdao.traerTurnos(documento);
            } catch (SQLException ex) {
                Logger.getLogger(ControladorMarcarTurno.class.getName()).log(Level.SEVERE, null, ex);
            }

            for (int i = 0; i < listaTurnos.size(); i++) {
                turno = (Turnos) listaTurnos.get(i);

                if (diaActual.after(turno.getFechaInicio()) && diaActual.before(turno.getFechaFin())
                        || diaActual.equals(turno.getFechaInicio()) || diaActual.equals(turno.getFechaFin())) {
                    System.out.println("Funciona");
                }
            }
        }

        if (e.getSource() == fmarcar.jBtVolver) {

            FrmAccesoCuenta facceso = new FrmAccesoCuenta();

            ControladorAcceso control1 = new ControladorAcceso(facceso);

            fmarcar.setVisible(false);
            facceso.setVisible(true);
        }
    }

}
