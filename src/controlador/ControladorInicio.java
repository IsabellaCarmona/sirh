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
        this.frminicio.jBtCambiarPw.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == frminicio.jBtCambiarPw) {

            int x = 0;
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de querer cambiar su contraseña?", "Fin productos", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (respuesta == JOptionPane.YES_OPTION) {

                do {
                    String newPassword = JOptionPane.showInputDialog("Porfavor ingrese la nueva contraseña");

                    if (admindao.cambiarPassword(newPassword)) {
                        //limpiarControles();
                        JOptionPane.showMessageDialog(frminicio, "Contraseña actualizada con exito");
                        x = 0;
                    } else {
                        JOptionPane.showMessageDialog(frminicio, "Error al cambiar la contraseña");
                        x = 1;
                    }
                } while (x == 1);
            }

            if (e.getSource() == frminicio.jBtInicioSesion) {

                String usuario = frminicio.jTxUser.getText();
                String contrasena = frminicio.jPassword.getText();

                try {
                    if (admindao.validarUsuario(usuario, contrasena)) {
                        FrmPrincipal fmenu = new FrmPrincipal();

                        fmenu.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(frminicio, "Usuario y/o Contraseña incorrecto(s)");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorInicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

    }
}
