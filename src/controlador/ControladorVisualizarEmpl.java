/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import modelo.Empleado;
import modelo.EmpleadoDAO;
import vista.FrmPrincipal;
import vista.FrmVisualizarEmpleados;

/**
 *
 * @author ISABELLA CARMONA C
 */
public class ControladorVisualizarEmpl implements ActionListener {

    private FrmVisualizarEmpleados fvisualizar;
    private Empleado empleado;
    private EmpleadoDAO empleadodao;
    DefaultTableModel modelo = new DefaultTableModel();

    public ControladorVisualizarEmpl(FrmVisualizarEmpleados fvisualizar, Empleado empleado, EmpleadoDAO empleadodao) {
        this.fvisualizar = fvisualizar;
        this.empleado = empleado;
        this.empleadodao = empleadodao;
        modelo.addColumn("Tipo de Documento");
        modelo.addColumn("Numero");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        fvisualizar.jTbEmpleados.setModel(modelo);

        this.fvisualizar.jCbTipoID.addActionListener(this);
        this.fvisualizar.jTxID.addActionListener(this);
    }

    public void limpiarJTable() {
        while (fvisualizar.jTbEmpleados.getRowCount() != 0) {
            ((DefaultTableModel) fvisualizar.jTbEmpleados.getModel()).removeRow(0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == fvisualizar.jCbTipoID) {
            if (!fvisualizar.jCbTipoID.getSelectedItem().equals("-SELECCIONE TIPO DE DOCUMENTO-")) {

                limpiarJTable();
                String TipoID = (String) fvisualizar.jCbTipoID.getSelectedItem();
                ArrayList listaEmpleados = null;

                try {
                    listaEmpleados = empleadodao.traerDatosTipoID(TipoID);
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorVisualizarEmpl.class.getName()).log(Level.SEVERE, null, ex);
                }

                for (int i = 0; i < listaEmpleados.size(); i++) {
                    String[] datos = new String[4];
                    empleado = (Empleado) listaEmpleados.get(i);
                    datos[0] = TipoID;
                    datos[1] = empleado.getCedula();
                    datos[2] = empleado.getNombres();
                    datos[3] = empleado.getApellidos();
                    modelo.addRow(datos);
                }

                if (!fvisualizar.jTxID.getText().equals("")) {

                    limpiarJTable();
                    String documento = fvisualizar.jTxID.getText();
                    ArrayList listEmpleados = null;

                    try {
                        listaEmpleados = empleadodao.traerDatosID(documento);
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorVisualizarEmpl.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    for (int i = 0; i < listEmpleados.size(); i++) {
                        String[] datos = new String[4];
                        empleado = (Empleado) listEmpleados.get(i);
                        datos[0] = empleado.getTipoId();
                        datos[1] = documento;
                        datos[2] = empleado.getNombres();
                        datos[3] = empleado.getApellidos();
                        modelo.addRow(datos);
                    }
                }
            } else if (fvisualizar.jCbTipoID.getSelectedItem().equals("-SELECCIONE TIPO DE DOCUMENTO-")) {
                limpiarJTable();
                Empleado empleado = new Empleado();
                EmpleadoDAO empleadod = new EmpleadoDAO();
                ArrayList empleados = null;
                DefaultTableModel modelo = new DefaultTableModel();

                modelo.addColumn("Tipo de Documento");
                modelo.addColumn("Numero");
                modelo.addColumn("Nombres");
                modelo.addColumn("Apellidos");
                fvisualizar.jTbEmpleados.setModel(modelo);

                try {
                    empleados = empleadod.traerDatos();
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorVisualizarEmpl.class.getName()).log(Level.SEVERE, null, ex);
                }

                //Ciclo que llena la Table, despues de la consulta
                for (int i = 0; i < empleados.size(); i++) {
                    String[] datos = new String[4];
                    empleado = (Empleado) empleados.get(i);
                    datos[0] = empleado.getTipoId();
                    datos[1] = empleado.getCedula();
                    datos[2] = empleado.getNombres();
                    datos[3] = empleado.getApellidos();
                    modelo.addRow(datos);
                }
            }
        }

    }

}
