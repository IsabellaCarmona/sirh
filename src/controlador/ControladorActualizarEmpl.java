/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Empleado;
import modelo.EmpleadoDAO;
import vista.FrmActualizarEmpl;

/**
 *
 * @author ISABELLA CARMONA C
 */
public class ControladorActualizarEmpl implements ActionListener {

    FrmActualizarEmpl frmAct;
    Empleado empleado;
    EmpleadoDAO empleadodao;

    public ControladorActualizarEmpl(FrmActualizarEmpl frmAct, Empleado empleado, EmpleadoDAO empleadodao) {
        this.frmAct = frmAct;
        this.empleado = empleado;
        this.empleadodao = empleadodao;

        this.frmAct.jBtActualizar.addActionListener(this);
        this.frmAct.jBtEliminar.addActionListener(this);
        this.frmAct.jBtSalir.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == frmAct.jBtActualizar) {

            String nombres = frmAct.jTxNombres.getText();
            String apellidos = frmAct.jTxApellidos.getText();

            int respuesta = JOptionPane.showConfirmDialog(frmAct, "¿Esta seguro de que desea actualizar los datos de " + nombres + " " + apellidos + "?",
                    "Actualizar Datos", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {

                String doc = frmAct.jTxNumeroID.getText();
                //Capturar fecha
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String fecha = format.format(frmAct.jDtFechaNacimiento.getDate());
                java.util.Date fechaN = null;
                try {
                    fechaN = format.parse(fecha);
                } catch (ParseException ex) {
                    Logger.getLogger(ControladorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                }
                java.sql.Date fechaNacSql = new java.sql.Date(fechaN.getTime());

                String telefono = frmAct.jTxTelefono.getText();
                String direccion = frmAct.jTxDireccion.getText();
                String rh = (String) frmAct.jCbRH.getSelectedItem();
                String cargo = frmAct.jTxCargo.getText();
                String arl = (String) frmAct.jCbARL.getSelectedItem();
                String eps = frmAct.jTxEPS.getText();
                int salario = Integer.parseInt(frmAct.jTxSalario.getText());

                empleado = new Empleado(doc, nombres, apellidos, fechaNacSql, telefono, direccion, cargo, rh, arl, eps, salario);

                if (empleadodao.actualizarDatos(empleado)) {
                    JOptionPane.showMessageDialog(frmAct, "Se ha actualizado al empleado(a) " + " " + nombres + " " + apellidos);
                } else {
                    JOptionPane.showMessageDialog(frmAct, "Error al actualizar al empleado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if (e.getSource() == frmAct.jBtEliminar) {

            String nombres = frmAct.jTxNombres.getText();
            String apellidos = frmAct.jTxApellidos.getText();

            int respuesta = JOptionPane.showConfirmDialog(frmAct, "¿Esta seguro de que desea dar de baja al empleado(a) " + nombres + " " + apellidos + "?",
                    "Dar de baja empleado", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {

                String tipoDoc = (String) frmAct.jCbTipoID.getSelectedItem();
                String doc = frmAct.jTxNumeroID.getText();
                //Capturar fecha
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String fecha = format.format(frmAct.jDtFechaNacimiento.getDate());
                java.util.Date fechaN = null;
                try {
                    fechaN = format.parse(fecha);
                } catch (ParseException ex) {
                    Logger.getLogger(ControladorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                }
                java.sql.Date fechaNacSql = new java.sql.Date(fechaN.getTime());

                String telefono = frmAct.jTxTelefono.getText();
                String direccion = frmAct.jTxDireccion.getText();
                String rh = (String) frmAct.jCbRH.getSelectedItem();
                String cargo = frmAct.jTxCargo.getText();
                String arl = (String) frmAct.jCbARL.getSelectedItem();
                String eps = frmAct.jTxEPS.getText();
                int salario = Integer.parseInt(frmAct.jTxSalario.getText());

                empleado = new Empleado(tipoDoc, doc, nombres, apellidos, fechaNacSql, telefono, direccion, cargo, rh, arl, eps, salario);

                if (empleadodao.bajaEmpleado(doc, empleado)) {
                    JOptionPane.showMessageDialog(frmAct, "Se ha dado de baja al empleado(a)" + nombres + apellidos);
                    frmAct.dispose();
                } else {
                    JOptionPane.showMessageDialog(frmAct, "Error al dar de baja al empleado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if (e.getSource() == frmAct.jBtSalir) {
            int respuesta = JOptionPane.showConfirmDialog(frmAct, "¿Esta seguro de salir?", "Fin ingreso empleados", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                frmAct.setVisible(false);
            }
        }
    }

}
