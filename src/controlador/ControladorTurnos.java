/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        this.fTurnos.jCbTiposTurnos.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == fTurnos.jCbTiposTurnos) {
            //6:30am - 3:30pm 9 horas
            //7:00am - 4:00pm 9 horas
            //8:00am - 4:30pm 8 horas y media
            //9:00am - 5:30pm 8 horas y media

            String tipoTurno = (String) fTurnos.jCbTiposTurnos.getSelectedItem();

            if (tipoTurno.equalsIgnoreCase("Turno 1")) {

                fTurnos.jTxHoraInicio.setText("6:30 am");
                fTurnos.jTxHoraFin.setText("3:30 pm");

            } else if (tipoTurno.equalsIgnoreCase("Turno 2")) {

                fTurnos.jTxHoraInicio.setText("7:00 am");
                fTurnos.jTxHoraFin.setText("4:00 pm");

            } else if (tipoTurno.equalsIgnoreCase("Turno 3")) {

                fTurnos.jTxHoraInicio.setText("8:00 am");
                fTurnos.jTxHoraFin.setText("4:30 pm");

            } else if (tipoTurno.equalsIgnoreCase("Turno 4")) {

                fTurnos.jTxHoraInicio.setText("9:00 am");
                fTurnos.jTxHoraFin.setText("5:30 pm");

            } else {

                fTurnos.jTxHoraInicio.setText(" ");
                fTurnos.jTxHoraFin.setText(" ");

            }

        }
        if (e.getSource() == fTurnos.BtGuardar) {

        }

    }

}
