/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.Integer.parseInt;
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
import vista.FrmConsultarNovedad;

/**
 *
 * @author ISABELLA CARMONA C
 */
public class ControladorConsultarNovedad implements ActionListener {

    FrmConsultarNovedad frmnovedades;
    Novedades novedades;
    NovedadesDAO novedadesdao;

    public ControladorConsultarNovedad(FrmConsultarNovedad frmnovedades, Novedades novedades, NovedadesDAO novedadesdao) {
        this.frmnovedades = frmnovedades;
        this.novedades = novedades;
        this.novedadesdao = novedadesdao;

        this.frmnovedades.jBtEditar.addActionListener(this);
        this.frmnovedades.jBtEliminar.addActionListener(this);
        this.frmnovedades.jBtVerArchivo.addActionListener(this);
        this.frmnovedades.jBtArchivos.addActionListener(this);
        this.frmnovedades.jBtSalir.addActionListener(this);
    }

    //Predeterminados de archivos
    Long longitud;
    String Archivo = "";
    File ruta;
    private FileNameExtensionFilter filter = new FileNameExtensionFilter("pdf", "pdf");

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == frmnovedades.jBtEditar) {

            int x = JOptionPane.showConfirmDialog(frmnovedades, "¿Está seguro de querer editar estos campos?", "Editar",
                    JOptionPane.YES_NO_OPTION);

            if (x == 0) {

                if (!Archivo.equals("")) {
                    ruta = new File(Archivo);
                    longitud = ruta.length();
                }

                String tipoNov = frmnovedades.jCbTipoNovedad.getSelectedItem().toString();

                int cod = parseInt(frmnovedades.jTFCodigo.getText());

                String descripcion = frmnovedades.jTADescription.getText();
                java.sql.Date fechaIniciosql = AdquirirFecha(frmnovedades.jDCFechaNovInic.getDate());
                java.sql.Date fechaFinsql = AdquirirFecha(frmnovedades.jDCFechaNovFin.getDate());

                novedades = new Novedades(cod, tipoNov, descripcion, fechaIniciosql, fechaFinsql);

                try {
                    if (novedadesdao.EditarNovedad(novedades, ruta, longitud)) {
                        JOptionPane.showMessageDialog(frmnovedades, "La novedad fue actualizada exitosamente");
                        frmnovedades.dispose();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorNovedades.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(frmnovedades, "Hubo un error al actualizar la novedad");
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ControladorNovedades.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        if (e.getSource() == frmnovedades.jBtEliminar) {

            int cod = Integer.parseInt(frmnovedades.jTFCodigo.getText());
            int x = JOptionPane.showConfirmDialog(frmnovedades, "¿Está seguro de querer eliminar esta novedad?\n\n"
                    + "Los datos serán eliminados de forma permanente", "Eliminar",
                    JOptionPane.YES_NO_OPTION);
            if (x == 0) {
                try {
                    if (novedadesdao.EliminarNovedad(cod)) {
                        JOptionPane.showMessageDialog(frmnovedades, "Novedad eliminada con éxito");
                        frmnovedades.dispose();
                    } else {
                        JOptionPane.showMessageDialog(frmnovedades, "Hubo un error al eliminar la novedad");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorConsultarNovedad.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        if (e.getSource() == frmnovedades.jBtVerArchivo) {

            int cod = Integer.parseInt(frmnovedades.jTFCodigo.getText());
            novedadesdao.ejecutar_archivoPDF(cod);
            try {
                Desktop.getDesktop().open(new File("new.pdf"));
            } catch (Exception ex) {
                System.err.println("El error es: " + ex);
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
