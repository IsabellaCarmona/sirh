/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.ControladorNomina;
import javax.swing.table.DefaultTableModel;
import modelo.Salario;

/**
 *
 * @author ISABELLA CARMONA C
 */
public class FrmVerNomina extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmVerNomina
     */
    public FrmVerNomina() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTbNomina = new javax.swing.JTable();
        jBtSalir = new javax.swing.JButton();
        jBtGenerarPdf = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLbDirectorio = new javax.swing.JLabel();
        jBtBuscarDirectorio = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 204));

        jTbNomina.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTbNomina.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTbNomina.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTbNominaMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTbNomina);

        jBtSalir.setText("Salir");

        jBtGenerarPdf.setText("Generar PDF");
        jBtGenerarPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtGenerarPdfActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Javanese Text", 0, 24)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("NÓMINA");

        jBtBuscarDirectorio.setText("Buscar directorio");
        jBtBuscarDirectorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtBuscarDirectorioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jBtBuscarDirectorio)
                .addGap(27, 27, 27)
                .addComponent(jLbDirectorio, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 783, Short.MAX_VALUE)
                .addComponent(jBtGenerarPdf)
                .addGap(53, 53, 53)
                .addComponent(jBtSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLbDirectorio, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBtSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBtGenerarPdf)
                        .addComponent(jBtBuscarDirectorio)))
                .addGap(13, 13, 13))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTbNominaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTbNominaMousePressed

        int fila = this.jTbNomina.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) jTbNomina.getModel();

        String nombres = (String) modelo.getValueAt(fila, 0);
        String doc = (String) modelo.getValueAt(fila, 1);
        String cargo = (String) modelo.getValueAt(fila, 2);
        String periodo = (String) modelo.getValueAt(fila, 3);
        String salarioBase = (String) modelo.getValueAt(fila, 4);
        String diasTrabajados = (String) modelo.getValueAt(fila, 5);
        String pagoPeriodo = (String) modelo.getValueAt(fila, 6);
        String auxTransp = (String) modelo.getValueAt(fila, 7);
        String pagoAux = (String) modelo.getValueAt(fila, 8);
        String bonificacion = (String) modelo.getValueAt(fila, 9);
        String totalDevengado = (String) modelo.getValueAt(fila, 10);
        String prestamo = (String) modelo.getValueAt(fila, 12);
        String totalDeducciones = (String) modelo.getValueAt(fila, 13);
        String netoPagar = (String) modelo.getValueAt(fila, 14);

        FrmNomina fnomina = new FrmNomina();
        Salario salario = new Salario();

        fnomina.jTxEmpleado.setText(nombres);
        fnomina.jTxDocumento.setText(doc);
        fnomina.jTxCargo.setText(cargo);
        fnomina.jTxFechaCorte.setText(periodo);
        fnomina.jTxSalarioBase.setText(salarioBase);
        fnomina.jTxDiasTrabajados.setText(diasTrabajados);
        fnomina.jTxPagoQuincena.setText(pagoPeriodo);
        fnomina.jTxAuxTransp.setText(auxTransp);
        fnomina.jTxPagoAuxTransp.setText(pagoAux);
        fnomina.jTxBonificacion.setText(bonificacion);
        fnomina.jTxTotalDevengado.setText(totalDevengado);
        fnomina.jTxPrestamo.setText(prestamo);
        fnomina.jTxTotalDeducciones.setText(totalDeducciones);
        fnomina.jTxNetoPagar.setText(netoPagar);

        ControladorNomina control1 = new ControladorNomina(fnomina, salario, this);
        fnomina.setVisible(true);
    }//GEN-LAST:event_jTbNominaMousePressed

    private void jBtGenerarPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtGenerarPdfActionPerformed

    }//GEN-LAST:event_jBtGenerarPdfActionPerformed

    private void jBtBuscarDirectorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtBuscarDirectorioActionPerformed

    }//GEN-LAST:event_jBtBuscarDirectorioActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jBtBuscarDirectorio;
    public javax.swing.JButton jBtGenerarPdf;
    public javax.swing.JButton jBtSalir;
    private javax.swing.JLabel jLabel16;
    public javax.swing.JLabel jLbDirectorio;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTbNomina;
    // End of variables declaration//GEN-END:variables

}
