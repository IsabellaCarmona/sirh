/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Administrador;
import modelo.AdministradorDAO;
import vista.FrmInicio;
import vista.FrmPrincipal;

/**
 *
 * @author ISABELLA CARMONA C
 */
public class ControladorInicio implements ActionListener {

    FrmInicio frminicio;
    Administrador admin;
    AdministradorDAO admindao;

    public ControladorInicio(FrmInicio frminicio, Administrador admin, AdministradorDAO admindao) {
        this.frminicio = frminicio;
        this.admin = admin;
        this.admindao = admindao;

        //Escuchar los botones
        this.frminicio.jTxUser.addActionListener(this);
        this.frminicio.jPassword.addActionListener(this);
        this.frminicio.jBtInicioSesion.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == frminicio.jBtInicioSesion) {

            String usuario = frminicio.jTxUser.getText();
            String contraseña = frminicio.jPassword.getText();

            try {
                if (admindao.validarUsuario(usuario, contraseña)) {
                    admindao.abrirCuenta(usuario);
                    FrmPrincipal fmenu = new FrmPrincipal();

                    frminicio.setVisible(false);
                    fmenu.setVisible(true);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frminicio, "Usuario y/o Contraseña incorrecto(s)", "Error", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(ControladorInicio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
