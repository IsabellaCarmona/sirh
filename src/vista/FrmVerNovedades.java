/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.ControladorConsultarNovedad;
import controlador.ControladorVerNovedades;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelo.Novedades;
import modelo.NovedadesDAO;

/**
 *
 * @author ISABELLA CARMONA C
 */
public class FrmVerNovedades extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmVerNovedades
     */
    public FrmVerNovedades() throws SQLException {
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jCbTipoID = new javax.swing.JComboBox<>();
        jTxID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTbNovedades = new javax.swing.JTable();
        jBtSalir = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("NOVEDADES");

        jLabel2.setText("Tipo Identificación");

        jCbTipoID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-SELECCIONE TIPO DE DOCUMENTO-", "Cédula de Ciudadanía", "Cédula de Extranjería", "PEP" }));

        jLabel3.setText("Número");

        jTbNovedades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTbNovedades.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTbNovedades.getTableHeader().setReorderingAllowed(false);
        jTbNovedades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTbNovedadesMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTbNovedades);

        jBtSalir.setBackground(new java.awt.Color(102, 102, 102));
        jBtSalir.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jBtSalir.setForeground(new java.awt.Color(255, 255, 255));
        jBtSalir.setText("Salir");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 795, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jCbTipoID, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jTxID, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 702, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBtSalir)
                        .addGap(20, 20, 20)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jCbTipoID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTxID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtSalir)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTbNovedadesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTbNovedadesMousePressed

        int fila = this.jTbNovedades.getSelectedRow();

        NovedadesDAO novdao = new NovedadesDAO();
        Novedades novedad = new Novedades();
        FrmConsultarNovedad form = new FrmConsultarNovedad();

        DefaultTableModel modelo = (DefaultTableModel) jTbNovedades.getModel();
        int id = Integer.parseInt((String) modelo.getValueAt(fila, 0));
        String nombres = (String) modelo.getValueAt(fila, 3);

        try {
            novedad = novdao.ConsultarNovedad(id);
        } catch (SQLException ex) {
            Logger.getLogger(FrmVerNovedades.class.getName()).log(Level.SEVERE, null, ex);
        }

        form.jCbTipoNovedad.setSelectedItem(novedad.getTipoNovedad());
        form.jTFCodigo.setText(String.valueOf(id));
        form.jTxNombres.setText(nombres);
        form.jTFCedula.setText(novedad.getIdEmpleado());
        form.jDCFechaNovInic.setDate(novedad.getFechaNovedadInicio());
        form.jDCFechaNovFin.setDate(novedad.getFechaNovedadFin());
        form.jTADescription.setText(novedad.getDescripcion());

        ControladorConsultarNovedad control1 = new ControladorConsultarNovedad(form, novedad, novdao);
        form.setVisible(true);

    }//GEN-LAST:event_jTbNovedadesMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jBtSalir;
    public javax.swing.JComboBox<String> jCbTipoID;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTbNovedades;
    public javax.swing.JTextField jTxID;
    // End of variables declaration//GEN-END:variables
}
