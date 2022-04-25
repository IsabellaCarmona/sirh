/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.Novedades;
import modelo.NovedadesDAO;
import vista.FrmNovedades;

/**
 *
 * @author 57322
 */
public class ControladorNovedades implements ActionListener {

    FrmNovedades frmnovedades;
    Novedades novedades;
    NovedadesDAO novedadesdao;

    public ControladorNovedades() {
    }

    public ControladorNovedades(FrmNovedades frmnovedades, Novedades novedades, NovedadesDAO novedadesdao) {
        this.frmnovedades = frmnovedades;
        this.novedades = novedades;
        this.novedadesdao = novedadesdao;

        this.frmnovedades.jBtArchivos.addActionListener(this);
        this.frmnovedades.jBtGuardar.addActionListener(this);
        this.frmnovedades.jBtSalir.addActionListener(this);
    }

    //Predeterminados de archivos
    Long longitud;
    String Archivo = "";
    File ruta;
    private FileNameExtensionFilter filter = new FileNameExtensionFilter("pdf", "pdf");

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == frmnovedades.jBtSalir) {
            int respuesta = JOptionPane.showConfirmDialog(frmnovedades, "¿Está seguro de salir?", "Fin Novedades", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                frmnovedades.dispose();
            }
        }

        if (e.getSource() == frmnovedades.jBtGuardar) {

            if (!Archivo.equals("")) {
                ruta = new File(Archivo);
                longitud = ruta.length();
            }

            String TipoNov = frmnovedades.jCbTipoNovedad.getSelectedItem().toString();
            String nroDoc = frmnovedades.jTFCedula.getText();

            String descripción = frmnovedades.jTADescription.getText();

            java.sql.Date fechaInicsql = AdquirirFecha(frmnovedades.jDCFechaNovInic.getDate());
            java.sql.Date fechaFinsql = AdquirirFecha(frmnovedades.jDCFechaNovFin.getDate());

            if (!ValidarControlesCompletos()) {
                JOptionPane.showMessageDialog(frmnovedades, "Todos los campos"
                        + "\ndeben ser insertados");
            } else {
                novedades = new Novedades(TipoNov, descripción, fechaInicsql, fechaFinsql, nroDoc);

                try {
                    int codigo = 0;
                    if (novedadesdao.GuardarNovedad(novedades, ruta, longitud)) {
                        codigo = novedadesdao.ObtenerID();
                    }

                    frmnovedades.dispose();
                    JOptionPane.showMessageDialog(frmnovedades, "Novedad guardada con éxito\n\nCódigo: " + codigo);
                    JOptionPane.showMessageDialog(frmnovedades, "Recuerde guardar el código de la novedad por si desea consultarlo\n\nCódigo: " + codigo,
                            "Advertencia", JOptionPane.WARNING_MESSAGE);
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorNovedades.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ControladorNovedades.class.getName()).log(Level.SEVERE, null, ex);
                }
                Archivo = "";
            }
        }

        if (e.getSource() == frmnovedades.jBtArchivos) {
            JFileChooser file = new JFileChooser();
            file.setFileFilter(filter);

            int option = file.showOpenDialog(frmnovedades);
            if (option == JFileChooser.APPROVE_OPTION) {
                frmnovedades.jLbDirecArchivo.setText(file.getSelectedFile().toString());
                Archivo = file.getSelectedFile().getAbsolutePath();
            }
        }

    }

    public boolean ValidarControlesCompletos() {
        if (frmnovedades.jCbTipoNovedad.getSelectedItem().equals("[Seleccionar]")
                || frmnovedades.jTxNombres.getText().equals("")
                || frmnovedades.jTFCedula.getText().equals("")
                || frmnovedades.jTADescription.getText().equals("")
                || frmnovedades.jLbDirecArchivo.getText().equals("")) {
            return false;
        }
        return true;
    }

    public java.sql.Date AdquirirFecha(java.util.Date Fecha) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = format.format(Fecha);
        java.util.Date fechaN = null;
        try {
            fechaN = format.parse(fecha);
        } catch (ParseException ex) {
            System.err.println("Error en: \n" + ex);
        }
        java.sql.Date fechasql = new java.sql.Date(fechaN.getTime());
        return fechasql;
    }
}
