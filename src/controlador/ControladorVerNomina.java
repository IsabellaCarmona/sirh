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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import modelo.AsistenciaDAO;
import modelo.Empleado;
import modelo.EmpleadoDAO;
import modelo.Salario;
import modelo.SalarioDAO;
import vista.FrmVerNomina;

/**
 *
 * @author ISABELLA CARMONA C
 */
public class ControladorVerNomina implements ActionListener {

    Empleado empleado;
    EmpleadoDAO empldao;
    AsistenciaDAO asistdao = new AsistenciaDAO();
    Salario salario;
    DefaultTableModel modelo = new DefaultTableModel();
    FrmVerNomina fnomina;

    public ControladorVerNomina(Empleado empleado, EmpleadoDAO empldao, AsistenciaDAO asistdao, Salario salario, FrmVerNomina fnomina) {
        this.empleado = empleado;
        this.empldao = empldao;
        this.asistdao = asistdao;
        this.salario = salario;
        this.fnomina = fnomina;

        this.fnomina.jBtGenerarPdf.addActionListener(this);
        this.fnomina.jBtBuscarDirectorio.addActionListener(this);
        this.fnomina.jBtSalir.addActionListener(this);

        modelo.addColumn("Empleado");
        modelo.addColumn("Documento");
        modelo.addColumn("Salario Base");
        modelo.addColumn("Días trabajados");
        modelo.addColumn("Pago Periodo");
        modelo.addColumn("Pago Auxilio");
        modelo.addColumn("Bonificación");
        modelo.addColumn("Total Devengado");
        modelo.addColumn("Préstamo");
        modelo.addColumn("Total deducciones");
        modelo.addColumn("Total Pagar");
        fnomina.jTbNomina.setModel(modelo);

        TableColumnModel columnModel = fnomina.jTbNomina.getColumnModel();

        columnModel.getColumn(0).setPreferredWidth(520); //Empleado
        columnModel.getColumn(1).setPreferredWidth(250); //Documento
        columnModel.getColumn(2).setPreferredWidth(190); //Salario Base
        columnModel.getColumn(3).setPreferredWidth(220); //Días trabajados
        columnModel.getColumn(4).setPreferredWidth(200); //Pago Periodo
        columnModel.getColumn(5).setPreferredWidth(180); //Pago Auxilio
        columnModel.getColumn(6).setPreferredWidth(170); //Bonificación
        columnModel.getColumn(7).setPreferredWidth(240); //Total Devengado
        columnModel.getColumn(8).setPreferredWidth(170); //Préstamo
        columnModel.getColumn(9).setPreferredWidth(250); //Total deducciones
        columnModel.getColumn(10).setPreferredWidth(203); //Total Pagar

        ArrayList<String> documentos = new ArrayList();
        ArrayList empl = null;

        try {
            documentos = empldao.traerDocumentos();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorVerNomina.class.getName()).log(Level.SEVERE, null, ex);
        }

        int salarioBase = 0, diasTrabajados = 0;

        String[] datos = new String[11];
        String docu;
        DecimalFormat df = new DecimalFormat("0,000");
        for (int i = 0; i < documentos.size(); i++) {
            docu = documentos.get(i);

            try {
                salarioBase = empldao.traerSalarioBase(docu);
                diasTrabajados = salario.diasTrabajados(docu);
                empl = empldao.traerRegistros(docu);
            } catch (SQLException ex) {
                Logger.getLogger(ControladorVerNomina.class.getName()).log(Level.SEVERE, null, ex);
            }

            String nombres = "", tipoID = "";

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
            }

            double pagoPeriodo = salario.validacion(salarioBase, diasTrabajados);
            double auxTransp = salario.auxTransp(salarioBase);
            double auxD;
            if (auxTransp != 0) {
                auxD = (auxTransp / 30) * diasTrabajados;
            } else {
                auxD = 0;
            }

            double totalD = salario.totalD(salarioBase, diasTrabajados);
            double totalDeducciones = salario.deducciones(salarioBase, diasTrabajados);
            double totalP = salario.totalPagar(salarioBase, diasTrabajados);

            datos[0] = nombres;
            datos[1] = tipoID + " " + docu;
            datos[2] = "$ " + String.valueOf(df.format(salarioBase));
            datos[3] = String.valueOf(diasTrabajados);
            datos[4] = "$ " + String.valueOf(df.format(pagoPeriodo));
            datos[5] = "$ " + String.valueOf(df.format(auxD));
            datos[6] = "$ " + String.valueOf(0);
            datos[7] = "$ " + String.valueOf(df.format(totalD));
            datos[8] = "$ " + String.valueOf(0);
            datos[9] = "$ " + String.valueOf(df.format(totalDeducciones));
            datos[10] = "$ " + String.valueOf(df.format(totalP));

            modelo.addRow(datos);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == fnomina.jBtSalir) {

            if (e.getSource() == fnomina.jBtSalir) {
                int respuesta = JOptionPane.showConfirmDialog(fnomina, "¿Está seguro de salir?", "Fin Nómina", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (respuesta == JOptionPane.YES_OPTION) {
                    fnomina.dispose();
                }
            }
        }

        if (e.getSource() == fnomina.jBtGenerarPdf) {

            int respuesta = JOptionPane.showConfirmDialog(fnomina, "¿Está seguro de que desea generar el PDF?\n\n"
                    + "No se podrán hacer cambios en la nómina cuando se genere el PDF", "Fin Nómina", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {

                String archivo = fnomina.jLbDirectorio.getText();

                if (archivo.equals("")) {

                    JOptionPane.showMessageDialog(fnomina, "Debe nombrar el archivo y el lugar de guardado del\n mismo con el botón 'Buscar Directorio' "
                            + "antes de generar el PDF.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {

                    try {

                        //Crear y abrir documento pdf
                        Document docPDF = new Document();

                        OutputStream direcArchivo = new FileOutputStream(archivo);

                        PdfWriter.getInstance(docPDF, direcArchivo);

                        String nombres = "", doc = "", cargo = "", periodo = "", salarioBase = "", diasTrabajados = "", pagoPeriodo = "", auxTransp = "",
                                pagoAux = "", bonificacion = "", totalDevengado = "", prestamo = "", totalDeducciones = "", netoPagar = "";

                        Chapter chap = new ChapterAutoNumber("");

                        for (int i = 0; i < fnomina.jTbNomina.getRowCount(); i++) {

                            DefaultTableModel modelo = (DefaultTableModel) fnomina.jTbNomina.getModel();

                            nombres = (String) modelo.getValueAt(i, 0);
                            doc = (String) modelo.getValueAt(i, 1);
                            periodo = String.valueOf(LocalDate.now());
                            salarioBase = (String) modelo.getValueAt(i, 2);
                            diasTrabajados = (String) modelo.getValueAt(i, 3);
                            pagoPeriodo = (String) modelo.getValueAt(i, 4);
                            pagoAux = (String) modelo.getValueAt(i, 5);
                            bonificacion = (String) modelo.getValueAt(i, 6);
                            totalDevengado = (String) modelo.getValueAt(i, 7);
                            prestamo = (String) modelo.getValueAt(i, 8);
                            totalDeducciones = (String) modelo.getValueAt(i, 9);
                            netoPagar = (String) modelo.getValueAt(i, 10);

                            String[] vectorNumero = doc.split(" ");
                            String numero = vectorNumero[1];

                            ArrayList empl = new ArrayList();
                            try {
                                empl = empldao.traerRegistros(numero);
                            } catch (SQLException ex) {
                                Logger.getLogger(ControladorVerNomina.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            for (int j = 0; j < empl.size(); j++) {
                                empleado = (Empleado) empl.get(j);
                            }

                            cargo = empleado.getCargo();

                            vectorNumero = salarioBase.split(" ");
                            numero = vectorNumero[1];
                            vectorNumero = numero.split("\\.");
                            int sala = cambioDecimal(vectorNumero);
                            if (sala <= 1000000 * 2) {
                                auxTransp = "117172";
                            }

                            int diasT = Integer.parseInt(diasTrabajados);
                            java.sql.Date fechaCorte = new java.sql.Date(new java.util.Date().getTime());
                            String[] vectorNum = doc.split(" ");
                            String documento = vectorNum[1];
                            salario = new Salario(diasT, fechaCorte, documento);

                            SalarioDAO saladao = new SalarioDAO();
                            saladao.insertarDatos(salario);
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
                            chap.add(new Paragraph("\nSalario Base:                                " + salarioBase + "                     Deducciones"));
                            chap.add(new Paragraph("Días Trabajados:                          " + diasTrabajados + "                                        SALUD(4%)"));
                            chap.add(new Paragraph("Pago Periodo(" + diasTrabajados + " días):                " + pagoPeriodo + "                             PENSIÓN(4%)"));
                            chap.add(new Paragraph("Auxilio Transporte:                       " + auxTransp + "                        Otras deducciones:"));
                            chap.add(new Paragraph("Pago periodo Aux(" + diasTrabajados + " días):         " + pagoAux + "                              Préstamo:   " + prestamo));
                            chap.add(new Paragraph("Bonificación:                                 " + bonificacion));
                            Paragraph totalDeduc = new Paragraph("TOTAL DEDUCCIONES " + totalDeducciones);
                            totalDeduc.setIndentationLeft(313);
                            chap.add(totalDeduc);
                            chap.add(new Paragraph("Total Devengado:                        " + totalDevengado));
                            chap.add(new Paragraph("Neto Pagar(total deducciones):   " + netoPagar));
                            chap.add(new Paragraph("\nFIRMA: __________________       "));

                            chap.add(Chunk.NEXTPAGE);
                        }

                        docPDF.add(chap);

                        docPDF.close();
                        direcArchivo.close();
                        JOptionPane.showMessageDialog(fnomina, "PDF creado de forma exitosa");
                        fnomina.dispose();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(FrmVerNomina.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (DocumentException | IOException ex) {
                        Logger.getLogger(FrmVerNomina.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }

        if (e.getSource() == fnomina.jBtBuscarDirectorio) {

            fnomina.jLbDirectorio.setText("");
            JFileChooser jFile = new JFileChooser();
            int respuesta = jFile.showSaveDialog(fnomina);

            File file = new File("");
            if (respuesta == JFileChooser.APPROVE_OPTION) {

                file = new File(jFile.getSelectedFile() + ".pdf");
                String archivo = file.toString();
                fnomina.jLbDirectorio.setText(archivo);
            }

            while (file.exists()) {

                fnomina.jLbDirectorio.setText("");
                int resp = JOptionPane.showConfirmDialog(fnomina, "El archivo ya existe\n¿Desea reemplazarlo?");
                if (resp == 0) {
                    String archivo = file.toString();
                    fnomina.jLbDirectorio.setText(archivo);
                    break;
                }

                JFileChooser jFil = new JFileChooser();
                respuesta = jFil.showSaveDialog(fnomina);

                if (respuesta == JFileChooser.APPROVE_OPTION) {

                    file = new File(jFil.getSelectedFile() + ".pdf");
                    String archivo = file.toString();
                    fnomina.jLbDirectorio.setText(archivo);

                }
            }
        }
    }

    public int cambioDecimal(String[] numero) {

        int numeroInt = 0;
        String numerito = "";
        for (int i = 0; i < Arrays.asList(numero).size(); i++) {

            numerito += Arrays.asList(numero).get(i);
        }
        numeroInt = Integer.parseInt(numerito);
        return numeroInt;
    }
}
