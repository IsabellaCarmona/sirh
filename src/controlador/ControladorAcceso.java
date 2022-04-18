/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Administrador;
import modelo.AdministradorDAO;
import modelo.Turnos;
import modelo.TurnosDAO;
import vista.FrmAccesoCuenta;
import vista.FrmInicio;
import vista.FrmMarcarTurno;

/**
 *
 * @author ISABELLA CARMONA C
 */
public class ControladorAcceso implements ActionListener {

    FrmAccesoCuenta frmcuenta;

    public ControladorAcceso(FrmAccesoCuenta frmcuenta) {
        this.frmcuenta = frmcuenta;

        this.frmcuenta.jBtIngresar.addActionListener(this);
        this.frmcuenta.jBtMarcarTurno.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == frmcuenta.jBtMarcarTurno) {

            FrmMarcarTurno fmarcar = new FrmMarcarTurno();
            Turnos turno = new Turnos();
            TurnosDAO tdao = new TurnosDAO();

            ControladorMarcarTurno control1 = new ControladorMarcarTurno(fmarcar, turno, tdao);

            fmarcar.setVisible(true);
            frmcuenta.setVisible(false);
        }

        if (e.getSource() == frmcuenta.jBtIngresar) {
            FrmInicio form1 = new FrmInicio();
            AdministradorDAO admindao = new AdministradorDAO();
            Administrador admin = new Administrador();

            ControladorInicio control = new ControladorInicio(form1, admin, admindao);
            frmcuenta.setVisible(false);
            form1.setVisible(true);
        }
    }

}
