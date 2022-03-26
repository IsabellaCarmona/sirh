/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import modelo.Administrador;
import modelo.AdministradorDAO;
import vista.FrmEditarPerfil;

/**
 *
 * @author 57322
 */
public class ControladorEditarPerfil implements ActionListener {

    FrmEditarPerfil frmeditarperfil;
    Administrador admin;
    AdministradorDAO admindao;

    public ControladorEditarPerfil() {
    }

    public ControladorEditarPerfil(FrmEditarPerfil frmEditarPerfil, Administrador admin, AdministradorDAO admindao) {
        this.frmeditarperfil = frmEditarPerfil;
        this.admin = admin;
        this.admindao = admindao;

        this.frmeditarperfil.jBtEditar.addActionListener(this);
        this.frmeditarperfil.jBtSalir.addActionListener(this);
        this.frmeditarperfil.jBtVisibilidadPsw.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == frmeditarperfil.jBtEditar) {

            int respuesta = JOptionPane.showConfirmDialog(frmeditarperfil, "¿Esta seguro de querer editar los campos?",
                    "Fin productos", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (respuesta == JOptionPane.YES_OPTION) {

                try {
                    admin = admindao.traerDatos();
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorEditarPerfil.class.getName()).log(Level.SEVERE, null, ex);
                }

                String doc = admin.getDocumento();
                String user = frmeditarperfil.jTxNewUser.getText();
                String password;

                if (frmeditarperfil.jTxPassVisible.getText().equals("")) {
                    password = frmeditarperfil.jPFNewPassword.getText();
                } else {
                    password = frmeditarperfil.jTxPassVisible.getText();
                }

                Administrador admin1 = new Administrador(user, password, doc);

                if (admindao.actualizarUsuario(admin1)) {
                    JOptionPane.showMessageDialog(frmeditarperfil, "Usuario y/o Contraseña editados de forma exitosa");
                } else {
                    JOptionPane.showMessageDialog(frmeditarperfil, "Error al editar el perfil",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if (e.getSource() == frmeditarperfil.jBtVisibilidadPsw) {

            if (frmeditarperfil.jTxPassVisible.getText().equals("")) {
                frmeditarperfil.jBtVisibilidadPsw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/visibility.png")));
                frmeditarperfil.jPFNewPassword.setVisible(false);
                frmeditarperfil.jTxPassVisible.setVisible(true);

                frmeditarperfil.jTxPassVisible.setText(frmeditarperfil.jPFNewPassword.getText());
            } else {
                frmeditarperfil.jBtVisibilidadPsw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/show.png")));
                frmeditarperfil.jPFNewPassword.setVisible(true);
                frmeditarperfil.jTxPassVisible.setVisible(false);

                frmeditarperfil.jPFNewPassword.setText(frmeditarperfil.jTxPassVisible.getText());
                frmeditarperfil.jTxPassVisible.setText("");
            }
        }

        if (e.getSource() == frmeditarperfil.jBtSalir) {
            int respuesta = JOptionPane.showConfirmDialog(frmeditarperfil, "¿Esta seguro de querer salir de la plataforma?",
                    "Fin Editar Perfil", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (respuesta == JOptionPane.YES_OPTION) {
                frmeditarperfil.setVisible(false);
            }
        }
    }
}
