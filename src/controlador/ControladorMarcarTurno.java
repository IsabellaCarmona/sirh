/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Asistencia;
import modelo.AsistenciaDAO;
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
    Asistencia asist;
    AsistenciaDAO asisDao;

    public ControladorMarcarTurno(FrmMarcarTurno fmarcar, Turnos turno, TurnosDAO tdao, Asistencia asist, AsistenciaDAO asisDao) {
        this.fmarcar = fmarcar;
        this.turno = turno;
        this.tdao = tdao;
        this.asist = asist;
        this.asisDao = asisDao;

        this.fmarcar.jBtMarcarTurno.addActionListener(this);
        this.fmarcar.jBtVolver.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == fmarcar.jBtMarcarTurno) {

            String documento = fmarcar.jTxDocumento.getText();
            java.sql.Date diaActual = new java.sql.Date(new java.util.Date().getTime());
            LocalDateTime locaDate = LocalDateTime.now();
            int horas = locaDate.getHour();
            int minutos = locaDate.getMinute();
            int segundos = locaDate.getSecond();

            Time horaActual = java.sql.Time.valueOf(horas + ":" + minutos + ":" + segundos);

            ArrayList listaTurnos = null;

            try {
                listaTurnos = tdao.traerTurnos(documento);
                if (listaTurnos == null) {
                    limpiarControles();
                    JOptionPane.showMessageDialog(fmarcar, "Este n??mero de documentos no se encuentra en la base de datos,\nPor favor ingrese un n??mero de documento v??lido");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ControladorMarcarTurno.class.getName()).log(Level.SEVERE, null, ex);
            }

            for (int i = 0; i < listaTurnos.size(); i++) {
                turno = (Turnos) listaTurnos.get(i);

                if (diaActual.after(turno.getFechaInicio()) && diaActual.before(turno.getFechaFin())
                        || diaActual.equals(turno.getFechaInicio()) || diaActual.equals(turno.getFechaFin())) {

                    asist = new Asistencia(diaActual, horaActual, documento);

                    if (asisDao.guardarDatos(asist)) {
                        limpiarControles();
                        JOptionPane.showMessageDialog(fmarcar, "Asistencia registrada");
                    } else {
                        JOptionPane.showMessageDialog(fmarcar, "Error al marcar la asistencia");
                    }
                } else {
                    int resp = JOptionPane.showConfirmDialog(fmarcar, "Este n??mero de documento no posee turno el d??a de hoy,\n\n??Desea registrar turno?", "Confirmar turno", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                    if (resp == JOptionPane.YES_OPTION) {
                        asist = new Asistencia(diaActual, horaActual, documento);

                        if (asisDao.guardarDatos(asist)) {
                            limpiarControles();
                            JOptionPane.showMessageDialog(fmarcar, "Asistencia registrada");
                        } else {
                            JOptionPane.showMessageDialog(fmarcar, "Error al marcar la asistencia");
                        }
                    } else {
                        limpiarControles();
                        JOptionPane.showMessageDialog(fmarcar, "Por favor ingrese otro n??mero de documento");
                    }
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

    public void limpiarControles() {
        fmarcar.jTxDocumento.setText("");
    }
}
