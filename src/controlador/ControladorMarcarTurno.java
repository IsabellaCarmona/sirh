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

        }

        if (e.getSource() == fmarcar.jBtVolver) {

            FrmAccesoCuenta facceso = new FrmAccesoCuenta();

            ControladorAcceso control1 = new ControladorAcceso(facceso);

            fmarcar.setVisible(false);
            facceso.setVisible(true);
        }
    }

}
