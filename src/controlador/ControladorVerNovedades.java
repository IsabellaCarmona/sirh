/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelo.Novedades;
import modelo.NovedadesDAO;
import vista.FrmVerNovedades;

/**
 *
 * @author ISABELLA CARMONA C
 */
public class ControladorVerNovedades implements ActionListener, KeyListener {

    private FrmVerNovedades fnovedad;
    private Novedades novedad;
    private NovedadesDAO novdao;
    DefaultTableModel modelo = new DefaultTableModel();

    public ControladorVerNovedades(FrmVerNovedades fnovedad, Novedades novedad, NovedadesDAO novdao) throws SQLException {
        this.fnovedad = fnovedad;
        this.novedad = novedad;
        this.novdao = novdao;
        modelo.addColumn("Código");
        modelo.addColumn("Fecha Inicio");
        modelo.addColumn("Fecha Fin");
        modelo.addColumn("Nombres completos");
        modelo.addColumn("Tipo y Nro Documento");
        modelo.addColumn("Tipo novedad");
        fnovedad.jTbNovedades.setModel(modelo);

        TableRowSorter<TableModel> ordenar = new TableRowSorter<TableModel>(modelo);
        fnovedad.jTbNovedades.setRowSorter(ordenar);
        fnovedad.jTbNovedades.getRowSorter().toggleSortOrder(0);

        TableColumnModel columnModel = fnovedad.jTbNovedades.getColumnModel();

        columnModel.getColumn(0).setPreferredWidth(65);
        columnModel.getColumn(1).setPreferredWidth(90);
        columnModel.getColumn(2).setPreferredWidth(90);
        columnModel.getColumn(3).setPreferredWidth(200);
        columnModel.getColumn(4).setPreferredWidth(135);
        columnModel.getColumn(5).setPreferredWidth(203);

        this.fnovedad.jCbTipoID.addActionListener(this);
        this.fnovedad.jBtSalir.addActionListener(this);
        this.fnovedad.jTxID.addKeyListener(this);

        ResultSet rs = null;

        try {
            rs = novdao.traerDatos();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorVerNovedades.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (rs.next()) {
            String tipoDoc = "";
            if (rs.getString("tipoDocumento").equals("Cédula de Ciudadanía")) {
                tipoDoc = "CC";
            } else if (rs.getString("tipoDocumento").equals("Cédula de Extranjería")) {
                tipoDoc = "CE";
            } else {
                tipoDoc = rs.getString("tipoDocumento");
            }
            String[] datos = new String[6];
            datos[0] = String.valueOf(rs.getInt("Id_Novedades"));
            datos[1] = String.valueOf(rs.getDate("Fecha_Inicio"));
            datos[2] = String.valueOf(rs.getDate("Fecha_Fin"));
            datos[3] = rs.getString("Nombres") + " " + rs.getString("Apellidos");
            datos[4] = tipoDoc + " " + rs.getString("Cedula");
            datos[5] = rs.getString("tipoNovedad");
            modelo.addRow(datos);
        }
    }

    public void limpiarJTable() {
        while (fnovedad.jTbNovedades.getRowCount() != 0) {
            ((DefaultTableModel) fnovedad.jTbNovedades.getModel()).removeRow(0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == fnovedad.jCbTipoID) {

            if (!fnovedad.jCbTipoID.getSelectedItem().equals("-SELECCIONE TIPO DE DOCUMENTO-")) {

                limpiarJTable();
                String tipoID = (String) fnovedad.jCbTipoID.getSelectedItem();

                ResultSet rs = null;

                try {
                    rs = novdao.traerDatosTipoID(tipoID);
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorVerNovedades.class.getName()).log(Level.SEVERE, null, ex);
                }

                String tipoDoc = "";
                try {
                    while (rs.next()) {

                        if (tipoID.equals("Cédula de Ciudadanía")) {
                            tipoDoc = "CC";
                        } else if (tipoID.equals("Cédula de Extranjería")) {
                            tipoDoc = "CE";
                        } else {
                            tipoDoc = rs.getString("tipoDocumento");
                        }
                        String[] datos = new String[6];
                        datos[0] = String.valueOf(rs.getInt("Id_Novedades"));
                        datos[1] = String.valueOf(rs.getDate("Fecha_Inicio"));
                        datos[2] = String.valueOf(rs.getDate("Fecha_Fin"));
                        datos[3] = rs.getString("Nombres") + " " + rs.getString("Apellidos");
                        datos[4] = tipoDoc + " " + rs.getString("Cedula");
                        datos[5] = rs.getString("tipoNovedad");
                        modelo.addRow(datos);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorVerNovedades.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (fnovedad.jCbTipoID.getSelectedItem().equals("-SELECCIONE TIPO DE DOCUMENTO-")) {

                limpiarJTable();
                ResultSet rs = null;

                try {
                    rs = novdao.traerDatos();
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorVerNovedades.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    while (rs.next()) {
                        String tipoDoc = "";
                        if (rs.getString("tipoDocumento").equals("Cédula de Ciudadanía")) {
                            tipoDoc = "CC";
                        } else if (rs.getString("tipoDocumento").equals("Cédula de Extranjería")) {
                            tipoDoc = "CE";
                        } else {
                            tipoDoc = rs.getString("tipoDocumento");
                        }
                        String[] datos = new String[6];
                        datos[0] = String.valueOf(rs.getInt("Id_Novedades"));
                        datos[1] = String.valueOf(rs.getDate("Fecha_Inicio"));
                        datos[2] = String.valueOf(rs.getDate("Fecha_Fin"));
                        datos[3] = rs.getString("Nombres") + " " + rs.getString("Apellidos");
                        datos[4] = tipoDoc + " " + rs.getString("Cedula");
                        datos[5] = rs.getString("tipoNovedad");
                        modelo.addRow(datos);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorVerNovedades.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        if (e.getSource() == fnovedad.jBtSalir) {
            int respuesta = JOptionPane.showConfirmDialog(fnovedad, "¿Está seguro de salir?", "Fin ver novedades", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                fnovedad.dispose();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getSource() == fnovedad.jTxID) {

            if (fnovedad.jCbTipoID.getSelectedItem().equals("-SELECCIONE TIPO DE DOCUMENTO-")) {

                limpiarJTable();
                ResultSet rs = null;
                String documento = (fnovedad.jTxID.getText()).replace(" ", "");

                try {
                    rs = novdao.traerDatosNroID(documento);
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorVerNovedades.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    while (rs.next()) {

                        if (empiezaPor(fnovedad.jTxID.getText())) {

                            String doc = rs.getString("tipoDocumento");
                            if (doc.equals("Cédula de Ciudadanía")) {
                                doc = "CC";
                            } else if (doc.equals("Cédula de Extranjería")) {
                                doc = "CE";
                            }

                            String[] datos = new String[6];
                            datos[0] = String.valueOf(rs.getInt("Id_Novedades"));
                            datos[1] = String.valueOf(rs.getDate("Fecha_Inicio"));
                            datos[2] = String.valueOf(rs.getDate("Fecha_Fin"));
                            datos[3] = rs.getString("Nombres") + " " + rs.getString("Apellidos");
                            datos[4] = doc + " " + rs.getString("Cedula");
                            datos[5] = rs.getString("tipoNovedad");
                            modelo.addRow(datos);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorVerNovedades.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (!fnovedad.jCbTipoID.getSelectedItem().equals("-SELECCIONE TIPO DE DOCUMENTO-")) {

                limpiarJTable();
                ResultSet rs = null;
                String documento = fnovedad.jTxID.getText();
                String doc = (String) fnovedad.jCbTipoID.getSelectedItem();

                try {
                    rs = novdao.traerDatosID(documento, doc);
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorVerNovedades.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    while (rs.next()) {

                        if (empiezaPor(fnovedad.jTxID.getText())) {

                            if (doc.equals("Cédula de Ciudadanía")) {
                                doc = "CC";
                            } else if (doc.equals("Cédula de Extranjería")) {
                                doc = "CE";
                            }

                            String[] datos = new String[6];
                            datos[0] = String.valueOf(rs.getInt("Id_Novedades"));
                            datos[1] = String.valueOf(rs.getDate("Fecha_Inicio"));
                            datos[2] = String.valueOf(rs.getDate("Fecha_Fin"));
                            datos[3] = rs.getString("Nombres") + " " + rs.getString("Apellidos");
                            datos[4] = doc + " " + rs.getString("Cedula");
                            datos[5] = rs.getString("tipoNovedad");
                            modelo.addRow(datos);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorVerNovedades.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

    public boolean empiezaPor(String inicio) throws SQLException {

        ResultSet rs = null;
        rs = novdao.traerDatos();
        String cedula = "";
        boolean bandera = false;
        while (rs.next()) {
            cedula = rs.getString("Cedula");

            if (inicio.length() > cedula.length()) {
                bandera = false;
            }

            for (int i = 0; i < inicio.length(); i++) {
                if (inicio.charAt(i) != cedula.charAt(i)) {
                    bandera = false;
                }
            }

            bandera = true;
        }

        return bandera;
    }

}
