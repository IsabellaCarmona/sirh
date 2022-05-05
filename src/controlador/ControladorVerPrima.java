/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.ChapterAutoNumber;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Empleado;
import modelo.EmpleadoDAO;
import modelo.Salario;
import modelo.SalarioDAO;
import vista.FrmVerPrima;

/**
 *
 * @author ISABELLA CARMONA C
 */
public class ControladorVerPrima implements ActionListener {

    FrmVerPrima fprima;
    Salario salario;
    SalarioDAO saldao;
    DefaultTableModel modelo = new DefaultTableModel();
    Empleado empleado;
    EmpleadoDAO empldao;

    public ControladorVerPrima(FrmVerPrima fprima, Salario salario, SalarioDAO saldao, Empleado empleado, EmpleadoDAO empldao) {
        this.fprima = fprima;
        this.salario = salario;
        this.saldao = saldao;
        this.empleado = empleado;
        this.empldao = empldao;

        this.fprima.jBtSalir.addActionListener(this);
        this.fprima.jBtGenerarPDF.addActionListener(this);
        this.fprima.jBtBuscarDirectorio.addActionListener(this);

        modelo.addColumn("Empleado");
        modelo.addColumn("Documento");
        modelo.addColumn("Cargo");
        modelo.addColumn("Periodo");
        modelo.addColumn("Salario Base");
        modelo.addColumn("Prima Servicios");
        modelo.addColumn("Total Devengado");
        modelo.addColumn("Neto Pagar");

        ArrayList<String> documentos = new ArrayList();
        ArrayList empl = null;

        try {
            documentos = empldao.traerDocumentos();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorVerPrima.class.getName()).log(Level.SEVERE, null, ex);
        }

        int salarioBase = 0, diasTrabajados = 0;
        double prima;
        java.sql.Date[] fechas = null;

        String[] datos = new String[8];
        String docu;
        DecimalFormat df = new DecimalFormat("0,000");

        for (int i = 0; i < documentos.size(); i++) {
            docu = documentos.get(i);

            String fechaActual = String.valueOf(LocalDate.now());
            String mes = String.valueOf(fechaActual.charAt(5)) + String.valueOf(fechaActual.charAt(6));
            String year = String.valueOf(fechaActual.charAt(0)) + String.valueOf(fechaActual.charAt(1)) + String.valueOf(fechaActual.charAt(2)) + String.valueOf(fechaActual.charAt(3));

            try {
                salarioBase = empldao.traerSalarioBase(docu);
                empl = empldao.traerRegistros(docu);
                fechas = saldao.traerFechasCorte(docu);
                diasTrabajados = saldao.traerDiasPrima(mes, year, docu);
            } catch (SQLException ex) {
                Logger.getLogger(ControladorVerPrima.class.getName()).log(Level.SEVERE, null, ex);
            }

            String nombres = "", tipoID = "", cargo = "", periodo = "";

            for (int j = 0; j < empl.size(); j++) {
                empleado = (Empleado) empl.get(j);
                nombres = empleado.getNombres() + " " + empleado.getApellidos();

                if (empleado.getTipoId().equals("Cédula de Ciudadanía")) {
                    tipoID = "CC";
                } else if (empleado.getTipoId().equals("Cédula de Extranjería")) {
                    tipoID = "CE";
                } else {
                    tipoID = empleado.getTipoId();
                }
                cargo = empleado.getCargo();
            }

            periodo = String.valueOf(fechas[0]) + " a " + String.valueOf(fechas[1]);

            prima = salario.prima(diasTrabajados, salarioBase);

            datos[0] = nombres;
            datos[1] = tipoID + " " + docu;
            datos[2] = cargo;
            datos[3] = periodo;
            datos[4] = "$ " + String.valueOf(df.format(salarioBase));
            datos[5] = "$ " + String.valueOf(df.format(prima));
            datos[6] = "$ " + String.valueOf(df.format(prima));
            datos[7] = "$ " + String.valueOf(df.format(prima));

            modelo.addRow(datos);

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == fprima.jBtSalir) {

            if (e.getSource() == fprima.jBtSalir) {
                int respuesta = JOptionPane.showConfirmDialog(fprima, "¿Está seguro de salir?", "Fin Nómina", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (respuesta == JOptionPane.YES_OPTION) {
                    fprima.dispose();
                }
            }
        }

        if (e.getSource() == fprima.jBtGenerarPDF) {

            int respuesta = JOptionPane.showConfirmDialog(fprima, "¿Está seguro de que desea generar el PDF?\n\n"
                    + "No se podrán hacer cambios en la nómina cuando se genere el PDF", "Fin Nómina", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {

                String archivo = fprima.jLbDirectorio.getText();

                if (archivo.equals("")) {

                    JOptionPane.showMessageDialog(fprima, "Debe nombrar el archivo y el lugar de guardado del\n mismo con el botón 'Buscar Directorio' "
                            + "antes de generar el PDF.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {

                        //Crear y abrir documento pdf
                        Document docPDF = new Document();

                        OutputStream direcArchivo = new FileOutputStream(archivo);

                        PdfWriter.getInstance(docPDF, direcArchivo);

                        String nombres = "", doc = "", cargo = "", periodo = "", salarioBase = "", prima = "", totalDevengado = "", netoPagar = "";

                        Chapter chap = new ChapterAutoNumber("");

                        for (int i = 0; i < fprima.jTbPrima.getRowCount(); i++) {

                            DefaultTableModel modelo = (DefaultTableModel) fprima.jTbPrima.getModel();

                            nombres = (String) modelo.getValueAt(i, 0);
                            doc = (String) modelo.getValueAt(i, 1);
                            cargo = (String) modelo.getValueAt(i, 2);
                            periodo = (String) modelo.getValueAt(i, 3);
                            salarioBase = (String) modelo.getValueAt(i, 4);
                            prima = (String) modelo.getValueAt(i, 5);
                            totalDevengado = (String) modelo.getValueAt(i, 6);
                            netoPagar = (String) modelo.getValueAt(i, 7);

                            docPDF.open();

                            //Agregar imagen
                            String urlImagen = "src/Imagenes/Logo_Nomina.jpg";
                            Image imagen = Image.getInstance(urlImagen);
                            imagen.setAbsolutePosition(7, 720);
                            chap.add(imagen);

                            Paragraph titulo = new Paragraph("RESTAURANTE DELICIAS DE CALASANZ");
                            titulo.setAlignment(Element.ALIGN_CENTER);
                            //titulo.setIndentationLeft(110);
                            chap.add(titulo);

                            chap.add(new Paragraph("\n\n\n\nEMPLEADO(A):                                                              DOCUMENTO:"));
                            Paragraph nombre = new Paragraph(nombres + "                                        " + doc);
                            nombre.setIndentationLeft(90);
                            chap.add(nombre);
                            chap.add(new Paragraph("\nCargo:                 " + cargo + "                                               Periodo:        " + periodo));
                            chap.add(new Paragraph("\nSalario Base:                                " + salarioBase));
                            chap.add(new Paragraph("Prima:                          " + prima));
                            chap.add(new Paragraph("Total Devengado:                        " + totalDevengado));
                            chap.add(new Paragraph("Neto Pagar(total deducciones):   " + netoPagar));
                            chap.add(new Paragraph("\nFIRMA: __________________       "));

                            chap.add(Chunk.NEXTPAGE);
                        }

                        docPDF.add(chap);

                        docPDF.close();
                        direcArchivo.close();
                        PrinterJob job = PrinterJob.getPrinterJob();

                        if (job.printDialog()) {
                            try {
                                job.print();
                            } catch (PrinterException ex) {
                                Logger.getLogger(FrmVerPrima.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            JOptionPane.showMessageDialog(fprima, "La impresión fue cancelada");
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(FrmVerPrima.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (DocumentException | IOException ex) {
                        Logger.getLogger(FrmVerPrima.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }

        if (e.getSource() == fprima.jBtBuscarDirectorio) {

            fprima.jLbDirectorio.setText("");
            JFileChooser jFile = new JFileChooser();
            int respuesta = jFile.showSaveDialog(fprima);

            File file = new File("");
            if (respuesta == JFileChooser.APPROVE_OPTION) {

                file = new File(jFile.getSelectedFile() + ".pdf");
                String archivo = file.toString();
                fprima.jLbDirectorio.setText(archivo);
            }

            while (file.exists()) {

                fprima.jLbDirectorio.setText("");
                int resp = JOptionPane.showConfirmDialog(fprima, "El archivo ya existe\n¿Desea reemplazarlo?");
                if (resp == 0) {
                    String archivo = file.toString();
                    fprima.jLbDirectorio.setText(archivo);
                    break;
                }

                JFileChooser jFil = new JFileChooser();
                respuesta = jFil.showSaveDialog(fprima);

                if (respuesta == JFileChooser.APPROVE_OPTION) {

                    file = new File(jFil.getSelectedFile() + ".pdf");
                    String archivo = file.toString();
                    fprima.jLbDirectorio.setText(archivo);

                }
            }
        }
    }

}
