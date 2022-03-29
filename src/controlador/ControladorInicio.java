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
import javax.swing.Icon;
import javax.swing.ImageIcon;
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
        this.frminicio.jBtVisibilidadPsw.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == frminicio.jBtInicioSesion) {

            String usuario = frminicio.jTxUser.getText();
            String contrasena;
            if (frminicio.jTxPassVisible.getText().equals("")) {
                contrasena = frminicio.jPassword.getText();
            } else {
                contrasena = frminicio.jTxPassVisible.getText();
            }

            try {
                if (admindao.validarUsuario(usuario, contrasena)) {

                    Icon icono = new ImageIcon(getClass().getResource("/Imagenes/skills.png"));
                    JOptionPane.showMessageDialog(frminicio, "Bienvendo(a) al SIRH", "Bienvenido(a)", JOptionPane.PLAIN_MESSAGE, icono);

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

        if (e.getSource() == frminicio.jBtVisibilidadPsw) {

            if (frminicio.jTxPassVisible.getText().equals("")) {
                frminicio.jBtVisibilidadPsw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/visibility.png")));
                frminicio.jPassword.setVisible(false);
                frminicio.jTxPassVisible.setVisible(true);

                frminicio.jTxPassVisible.setText(frminicio.jPassword.getText());
            } else {
                frminicio.jBtVisibilidadPsw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/show.png")));
                frminicio.jPassword.setVisible(true);
                frminicio.jTxPassVisible.setVisible(false);

                frminicio.jPassword.setText(frminicio.jTxPassVisible.getText());
                frminicio.jTxPassVisible.setText("");
            }
        }
    }
}
