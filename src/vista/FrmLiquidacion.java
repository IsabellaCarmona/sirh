/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

/**
 *
 * @author ISABELLA CARMONA C
 */
public class FrmLiquidacion extends javax.swing.JFrame implements Printable {

    /**
     * Creates new form FrmNomina
     */
    public FrmLiquidacion() {
        initComponents();

        this.setResizable(false);
        setLocationRelativeTo(null);

        jTxEmpleado.setEnabled(false);
        jTxDocumento.setEnabled(false);
        jTxCargo.setEnabled(false);
        jTxSalarioBase.setEnabled(false);
        jTxPrima.setEnabled(false);
        jTxInteresesCesan.setEnabled(false);
        jTxCesantias.setEnabled(false);
        jTxVacaciones.setEnabled(false);
        jTxTotalDevengado.setEnabled(false);
        jTxNetoPagar.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTxEmpleado = new javax.swing.JTextField();
        jTxCargo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTxSalarioBase = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTxCesantias = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTxInteresesCesan = new javax.swing.JTextField();
        jLbPagoPeriodo = new javax.swing.JLabel();
        jTxVacaciones = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTxTotalDevengado = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTxNetoPagar = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTxDocumento = new javax.swing.JTextField();
        jTxFechaCorte = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTxConsumo = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTxTotalDeducciones = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTxPrima = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTxPrimaPagada = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jBtGenerarPDF = new javax.swing.JButton();
        jBtEditar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Logo_Nomina.jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel16.setFont(new java.awt.Font("Javanese Text", 2, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("LIQUIDACI??N CONTRATO DE TRABAJO");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 443, 36));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("EMPLEADO(A):");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        jTxEmpleado.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTxEmpleado.setBorder(null);
        jPanel1.add(jTxEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 190, -1));

        jTxCargo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTxCargo.setBorder(null);
        jPanel1.add(jTxCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 210, 171, -1));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Intereses Cesantias");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, -1, -1));

        jTxSalarioBase.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTxSalarioBase.setBorder(null);
        jPanel1.add(jTxSalarioBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 260, 150, -1));

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Prima Servicios");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        jTxCesantias.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTxCesantias.setBorder(null);
        jPanel1.add(jTxCesantias, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 320, 150, -1));

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("_______________________________");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 570, 260, 20));

        jTxInteresesCesan.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTxInteresesCesan.setBorder(null);
        jPanel1.add(jTxInteresesCesan, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 350, 150, -1));

        jLbPagoPeriodo.setBackground(new java.awt.Color(0, 0, 0));
        jLbPagoPeriodo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLbPagoPeriodo.setForeground(new java.awt.Color(0, 0, 0));
        jLbPagoPeriodo.setText("Cesantias");
        jPanel1.add(jLbPagoPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, -1, -1));

        jTxVacaciones.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTxVacaciones.setBorder(null);
        jPanel1.add(jTxVacaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 380, 150, -1));

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Vacaciones");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, -1, -1));

        jTxTotalDevengado.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTxTotalDevengado.setBorder(null);
        jPanel1.add(jTxTotalDevengado, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 470, 150, -1));

        jLabel10.setBackground(new java.awt.Color(0, 0, 0));
        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Total Devengado:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, -1, -1));

        jTxNetoPagar.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTxNetoPagar.setBorder(null);
        jPanel1.add(jTxNetoPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 510, 150, -1));

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Neto Pagar (total deducciones):");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 510, -1, -1));

        jLabel12.setBackground(new java.awt.Color(0, 0, 0));
        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("FIRMA");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 570, -1, -1));

        jLabel13.setBackground(new java.awt.Color(0, 0, 0));
        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("CARGO:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        jLabel14.setBackground(new java.awt.Color(0, 0, 0));
        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Periodo:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 210, -1, -1));

        jTxDocumento.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTxDocumento.setBorder(null);
        jPanel1.add(jTxDocumento, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 140, 170, -1));

        jTxFechaCorte.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTxFechaCorte.setBorder(null);
        jPanel1.add(jTxFechaCorte, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 210, 180, -1));

        jLabel15.setBackground(new java.awt.Color(0, 0, 0));
        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("DOCUMENTO");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 110, -1, -1));

        jLabel17.setBackground(new java.awt.Color(0, 0, 0));
        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Prima Pagada");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 350, -1, -1));

        jLabel20.setBackground(new java.awt.Color(0, 0, 0));
        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("TOTAL DEDUCCIONES");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 450, -1, -1));

        jTxConsumo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTxConsumo.setBorder(null);
        jPanel1.add(jTxConsumo, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 300, 170, -1));

        jLabel21.setBackground(new java.awt.Color(0, 0, 0));
        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Otras deducciones:");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 270, -1, -1));

        jTxTotalDeducciones.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTxTotalDeducciones.setBorder(null);
        jPanel1.add(jTxTotalDeducciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 440, 170, -1));

        jLabel22.setBackground(new java.awt.Color(0, 0, 0));
        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("SALARIO BASE:");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, -1));

        jTxPrima.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTxPrima.setBorder(null);
        jPanel1.add(jTxPrima, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 290, 150, -1));

        jLabel18.setBackground(new java.awt.Color(0, 0, 0));
        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Consumo");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 300, -1, -1));

        jTxPrimaPagada.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTxPrimaPagada.setBorder(null);
        jPanel1.add(jTxPrimaPagada, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 350, 170, -1));

        jLabel19.setFont(new java.awt.Font("Javanese Text", 0, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Restaurante Delicias de Calasanz");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(329, 15, 443, 36));

        jBtGenerarPDF.setText("Generar PDF");
        jPanel1.add(jBtGenerarPDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 560, -1, -1));

        jBtEditar.setText("Editar");
        jPanel1.add(jBtEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 560, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-6, 0, 910, 606));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmLiquidacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmLiquidacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmLiquidacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmLiquidacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmLiquidacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jBtEditar;
    public javax.swing.JButton jBtGenerarPDF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLbPagoPeriodo;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JTextField jTxCargo;
    public javax.swing.JTextField jTxCesantias;
    public javax.swing.JTextField jTxConsumo;
    public javax.swing.JTextField jTxDocumento;
    public javax.swing.JTextField jTxEmpleado;
    public javax.swing.JTextField jTxFechaCorte;
    public javax.swing.JTextField jTxInteresesCesan;
    public javax.swing.JTextField jTxNetoPagar;
    public javax.swing.JTextField jTxPrima;
    public javax.swing.JTextField jTxPrimaPagada;
    public javax.swing.JTextField jTxSalarioBase;
    public javax.swing.JTextField jTxTotalDeducciones;
    public javax.swing.JTextField jTxTotalDevengado;
    public javax.swing.JTextField jTxVacaciones;
    // End of variables declaration//GEN-END:variables

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

        if (pageIndex != 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D graphics2d = (Graphics2D) graphics;
        graphics2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        graphics2d.scale(0.66, 0.66);

        jPanel1.printAll(graphics2d);

        return PAGE_EXISTS;
    }
}
