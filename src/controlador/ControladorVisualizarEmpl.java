/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        modelo.addColumn("Código");
        modelo.addColumn("Tipo");
        modelo.addColumn("Nombre Producto");
        modelo.addColumn("Fecha de lanzamiento");
        fvisualizar.jTbEmpleados.setModel(modelo);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
