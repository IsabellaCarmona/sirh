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

            java.sql.Date fechaInicio = adquirirFecha(fTurnos.jDtFechaInicio.getDate());
            java.sql.Date fechaFin = adquirirFecha(fTurnos.jDtFechaFin.getDate());
            java.sql.Date diaDescanso = adquirirFecha(fTurnos.jDtDiaDescanso.getDate());

            String horaI = fTurnos.jFTxHoraInicio.getText() + ":00";
            Time horaInicio = java.sql.Time.valueOf(horaI);

            String horaF = fTurnos.jFTxHoraFin.getText() + ":00";
            Time horaFin = java.sql.Time.valueOf(horaF);

            String id = fTurnos.jTxID.getText();

            turnos = new Turnos(fechaInicio, fechaFin, diaDescanso, horaInicio, horaFin, id);

            if (turnosdao.asignarTurno(turnos)) {
                fTurnos.dispose();
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
        fTurnos.jDtFechaInicio.setDate(null);
        fTurnos.jDtFechaFin.setDate(null);
        fTurnos.jDtDiaDescanso.setDate(null);
        fTurnos.jFTxHoraInicio.setText("00:00");
        fTurnos.jFTxHoraFin.setText("00:00");

    }

    public java.sql.Date adquirirFecha(java.util.Date Fecha) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = format.format(Fecha);
        java.util.Date fechaN = null;
        try {
            fechaN = format.parse(fecha);
        } catch (ParseException ex) {
            System.err.println("Error en: \n" + ex);
        }
        java.sql.Date fechasql = new java.sql.Date(fechaN.getTime());
        return fechasql;
    }
}
