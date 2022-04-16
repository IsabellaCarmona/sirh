/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Turnos;
import modelo.TurnosDAO;
import vista.FrmTurnos;

/**
 *
 * @author Sala-406
 */
public class ControladorTurnos implements ActionListener {

    FrmTurnos fTurnos;
    Turnos turnos;
    TurnosDAO turnosdao;

    public ControladorTurnos(FrmTurnos fTurnos, Turnos turnos, TurnosDAO turnosdao) {

        this.fTurnos = fTurnos;
        this.turnos = turnos;
        this.turnosdao = turnosdao;

        this.fTurnos.BtGuardar.addActionListener(this);
        this.fTurnos.BtSalir.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == fTurnos.BtGuardar) {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String fecha = format.format(fTurnos.jDtFechaInicio.getDate());
            java.util.Date fechaN = null;
            try {
                fechaN = format.parse(fecha);
            } catch (ParseException ex) {
                Logger.getLogger(ControladorTurnos.class.getName()).log(Level.SEVERE, null, ex);
            }
            java.sql.Date fechaInicio = new java.sql.Date(fechaN.getTime());

            SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
            String fecha2 = format2.format(fTurnos.jDtFechaFin.getDate());
            java.util.Date fechaN2 = null;
            try {
                fechaN2 = format.parse(fecha2);
            } catch (ParseException ex) {
                Logger.getLogger(ControladorTurnos.class.getName()).log(Level.SEVERE, null, ex);
            }
            java.sql.Date fechaFin = new java.sql.Date(fechaN2.getTime());

            String horaI = fTurnos.jFTxHoraInicio.getText() + ":00";
            Time horaInicio = java.sql.Time.valueOf(horaI);

            String horaF = fTurnos.jFTxHoraFin.getText() + ":00";
            Time horaFin = java.sql.Time.valueOf(horaF);

            String id = fTurnos.jTxID.getText();

            turnos = new Turnos(fechaInicio, fechaFin, horaInicio, horaFin, id);

            if (turnosdao.asignarTurno(turnos)) {
                limpiarControles();
                JOptionPane.showMessageDialog(fTurnos, "Turno registrado");
            } else {
                JOptionPane.showMessageDialog(fTurnos, "Error al registrar el Turno", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
        if (e.getSource() == fTurnos.BtSalir) {
            int respuesta = JOptionPane.showConfirmDialog(fTurnos, "¿Está seguro de salir?", "Fin ingreso Turnos", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                fTurnos.dispose();
            }
        }

    }

    public void limpiarControles() {
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());

        fTurnos.jDtFechaInicio.setDate(null);
        fTurnos.jDtFechaFin.setDate(null);
        fTurnos.jFTxHoraInicio.setText("00:00");
        fTurnos.jFTxHoraFin.setText("00:00");

    }
}
