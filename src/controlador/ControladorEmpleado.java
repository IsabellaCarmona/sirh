/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Empleado;
import modelo.EmpleadoDAO;
import vista.FrmEmpleados;
import javafx.stage.FileChooser;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import vista.FrmPrincipal;

/**
 *
 * @author SALA404-406
 */
public class ControladorEmpleado implements ActionListener {

    FrmEmpleados frmempleados;
    Empleado empleado;
    EmpleadoDAO empleadodao;

    public ControladorEmpleado(FrmEmpleados frmempleados, Empleado empleado, EmpleadoDAO empleadodao) {
        this.frmempleados = frmempleados;
        this.empleado = empleado;
        this.empleadodao = empleadodao;

        this.frmempleados.jBtAgregar.addActionListener(this);
        this.frmempleados.jBtSalir.addActionListener(this);
        this.frmempleados.jBtAgregarArchivos.addActionListener(this);
    }

    Long longitud;
    String Archivo = "";
    File ruta;
    private FileNameExtensionFilter filter = new FileNameExtensionFilter("pdf", "pdf");

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == frmempleados.jBtAgregar) {

            if (!Archivo.equals("")) {
                ruta = new File(Archivo);
                longitud = ruta.length();
            }

            String tipoID = (String) frmempleados.jCbTipoID.getSelectedItem();
            String cedula = (frmempleados.jTxNumeroID.getText()).replace(" ", "");
            String nombres = frmempleados.jTxNombres.getText();
            String apellidos = frmempleados.jTxApellidos.getText();

            //Capturar fecha
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String fecha = format.format(frmempleados.jDtFechaNacimiento.getDate());
            java.util.Date fechaN = null;
            try {
                fechaN = format.parse(fecha);
            } catch (ParseException ex) {
                Logger.getLogger(ControladorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
            java.sql.Date fechasql = new java.sql.Date(fechaN.getTime());

            String telefono = frmempleados.jTxTelefono.getText();
            String direccion = frmempleados.jTxDireccion.getText();
            String cargo = frmempleados.jTxCargo.getText();
            String rh = (String) frmempleados.jCbRH.getSelectedItem();
            String eps = (String) frmempleados.jTxEPS.getText();
            String arl = (String) frmempleados.jCbARL.getSelectedItem();
            int salario = Integer.parseInt(frmempleados.jTxSalario.getText());

            empleado = new Empleado(tipoID, cedula, nombres, apellidos, fechasql, telefono, direccion, cargo, rh, eps, arl, salario);

            try {
                if (empleadodao.agregarEmpleado(empleado, ruta, longitud)) {
                    limpiarControles();
                    JOptionPane.showMessageDialog(frmempleados, "Empleado(a) " + nombres + " " + apellidos + " registrado exitosamente");
                } else {
                    JOptionPane.showMessageDialog(frmempleados, "Error al registrar el Empleado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ControladorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
            Archivo = "";
        }

        if (e.getSource() == frmempleados.jBtSalir) {
            int respuesta = JOptionPane.showConfirmDialog(frmempleados, "??Est?? seguro de salir?", "Fin ingreso empleados", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                frmempleados.dispose();
            }
        }

        if (e.getSource() == frmempleados.jBtAgregarArchivos) {

            JFileChooser file = new JFileChooser();
            file.setFileFilter(filter);

            int option = file.showOpenDialog(frmempleados);
            if (option == JFileChooser.APPROVE_OPTION) {
                frmempleados.jLbDirecArchivo.setText(file.getSelectedFile().toString());
                Archivo = file.getSelectedFile().getAbsolutePath();
            }
        }
    }

    public void limpiarControles() {

        frmempleados.jCbTipoID.setSelectedIndex(0);
        frmempleados.jCbRH.setSelectedIndex(0);
        frmempleados.jTxNumeroID.setText("");
        frmempleados.jTxNombres.setText("");
        frmempleados.jTxApellidos.setText("");
        frmempleados.jDtFechaNacimiento.setDate(null);
        frmempleados.jTxCargo.setText("");
        frmempleados.jTxTelefono.setText("");
        frmempleados.jTxDireccion.setText("");
        frmempleados.jTxSalario.setText("");
        frmempleados.jTxEPS.setText("");
        frmempleados.jLbDirecArchivo.setText("");

        //Para que el cursor quede en ese campo de texto
        frmempleados.jTxNumeroID.requestFocus();
    }

}
