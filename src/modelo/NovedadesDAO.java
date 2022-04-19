/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import modelo.Novedades;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.String.valueOf;
import java.sql.Blob;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import vista.FrmNovedades;

/**
 *
 * @author 57322
 */
public class NovedadesDAO {

    Conexion cn = new Conexion();

    Connection con; //Permite verificar la instruccion sql y ejecutarla
    PreparedStatement ps; //Objeto donde se carga el resultado de la consulta
    ResultSet rs; //Objeto que guarda el resultado de la consulta

    Novedades novedad = new Novedades();
    FrmNovedades frmnovedades;

    public boolean GuardarNovedad(Novedades novedad, File ruta, Long longitud) throws SQLException, FileNotFoundException {

        String sql = "INSERT INTO novedades(ArchivoNovedad,Descripcion,Fecha_Inicio,Fecha_Fin,Id_Empleado,tipoNovedad) VALUES (?, '"
                + novedad.getDescripcion() + "','" + novedad.getFechaNovedadInicio() + "','" + novedad.getFechaNovedadFin() + "','" + novedad.getIdEmpleado() + "','"
                + novedad.getTipoNovedad() + "')";

        FileInputStream flujo = new FileInputStream(ruta);

        //Conectarse a la base de datos
        con = cn.getConnection();

        try {
            ps = con.prepareStatement(sql); //Envia la instruccion en comando sql
            ps.setBlob(1, flujo, longitud);
            ps.executeUpdate(); //Ejecuta la instruccion
        } catch (SQLException ex) {
            //Muestra el error en caso de haberlo
            Logger.getLogger(NovedadesDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            return false;
        }

        return true;
    }

    public Novedades ConsultarNovedad(int idNovedades) throws SQLException {

        String sql = "SELECT * FROM novedades WHERE Id_Novedades = '" + idNovedades + "'";

        //Conectarse a la base de datos
        con = cn.getConnection(); // Establece la conexión
        ps = con.prepareStatement(sql); // Se prepara el código sql
        rs = ps.executeQuery();
        while (rs.next()) {
            novedad.setIdNovedades(rs.getInt("Id_Novedades"));
            novedad.setTipoNovedad(rs.getString("tipoNovedad"));
            novedad.setDescripcion(rs.getString("Descripcion"));
            novedad.setFechaNovedadInicio(rs.getDate("Fecha_Inicio"));
            novedad.setFechaNovedadFin(rs.getDate("Fecha_Fin"));
            novedad.setIdEmpleado(rs.getString("Id_Empleado"));
        }
        return novedad;
    }

    public boolean EditarNovedad(Novedades novedad, File ruta, Long longitud) throws SQLException, FileNotFoundException {

        String sql = "UPDATE novedades SET ArchivoNovedad = ?, tipoNovedad = '" + novedad.getTipoNovedad() + "', Descripcion = '" + novedad.getDescripcion()
                + "', Fecha_Inicio = '" + novedad.getFechaNovedadInicio() + "', Fecha_Fin = '" + novedad.getFechaNovedadFin()
                + "' WHERE Id_Novedades = '" + novedad.getIdNovedades() + "'";

        FileInputStream flujo = new FileInputStream(ruta);

        //Conectarse a la base de datos
        con = cn.getConnection();

        try {
            ps = con.prepareStatement(sql); //Envia la instruccion en comando sql
            ps.setBlob(1, flujo, longitud);
            ps.executeUpdate(); //Ejecuta la instruccion
        } catch (SQLException ex) {
            //Muestra el error en caso de haberlo
            Logger.getLogger(NovedadesDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            return false;
        }

        return true;
    }

    public boolean EliminarNovedad(int idNovedades) throws SQLException {

        String sql = "DELETE FROM novedades WHERE Id_Novedades = '" + idNovedades + "'";

        //Conectarse a la base de datos
        con = cn.getConnection();

        try {
            ps = con.prepareStatement(sql); //Envia la instruccion en comando sql
            ps.executeUpdate(); //Ejecuta la instruccion

        } catch (SQLException ex) {
            //Muestra el error en caso de haberlo
            Logger.getLogger(NovedadesDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            return false;
        }

        return true;
    }

    public int ObtenerID() throws SQLException {

        String sql = "SELECT * FROM novedades ORDER BY Id_Novedades DESC LIMIT 1";

        //Conectarse a la base de datos
        con = cn.getConnection(); // Establece la conexión
        ps = con.prepareStatement(sql); // Se prepara el código sql
        rs = ps.executeQuery();

        int id = 0;

        while (rs.next()) {
            id = rs.getInt("Id_Novedades");
        }
        return id;
    }

    public void ejecutar_archivoPDF(int id) {

        ps = null;
        rs = null;
        byte[] b = null;

        try {
            ps = cn.getConnection().prepareStatement("SELECT archivoNovedad FROM novedades WHERE Id_Novedades = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                b = rs.getBytes(1);
            }
            InputStream bos = new ByteArrayInputStream(b);

            int tamanoInput = bos.available();
            byte[] datosPDF = new byte[tamanoInput];
            bos.read(datosPDF, 0, tamanoInput);

            OutputStream out = new FileOutputStream("new.pdf");
            out.write(datosPDF);

            //abrir archivo
            out.close();
            bos.close();
            ps.close();
            rs.close();

        } catch (IOException | NumberFormatException | SQLException ex) {
            System.out.println("Error al abrir archivo PDF " + ex.getMessage());
        }
    }

    public ResultSet traerDatos() throws SQLException {

        String sql = "SELECT novedades.Id_Novedades,Fecha_Inicio,Fecha_Fin,tipoNovedad, empleados.Cedula,Nombres,Apellidos,tipoDocumento "
                + "FROM novedades "
                + "JOIN empleados "
                + "ON novedades.Id_Empleado=empleados.Cedula";

        con = cn.getConnection(); // Establece la conexión
        ps = con.prepareStatement(sql); // Se prepara el código sql
        rs = ps.executeQuery();

        return rs;
    }

    public ResultSet traerDatosID(String nroDoc, String tipoDoc) throws SQLException {

        String sql = "SELECT novedades.Id_Novedades,Fecha_Inicio,Fecha_Fin,tipoNovedad, empleados.Cedula,Nombres,Apellidos,tipoDocumento "
                + "FROM novedades "
                + "JOIN empleados "
                + "ON novedades.Id_Empleado=empleados.Cedula "
                + "WHERE empleados.Cedula LIKE '" + nroDoc + "%' AND empleados.tipoDocumento='" + tipoDoc + "'";

        con = cn.getConnection(); // Establece la conexión
        ps = con.prepareStatement(sql); // Se prepara el código sql
        rs = ps.executeQuery();

        return rs;
    }

    public ResultSet traerDatosTipoID(String tipoDoc) throws SQLException {

        String sql = "SELECT novedades.Id_Novedades,Fecha_Inicio,Fecha_Fin,tipoNovedad, empleados.Cedula,Nombres,Apellidos,tipoDocumento "
                + "FROM novedades "
                + "JOIN empleados "
                + "ON novedades.Id_Empleado=empleados.Cedula "
                + "WHERE empleados.tipoDocumento='" + tipoDoc + "'";

        con = cn.getConnection(); // Establece la conexión
        ps = con.prepareStatement(sql); // Se prepara el código sql
        rs = ps.executeQuery();

        return rs;
    }

    public ResultSet traerDatosNroID(String nroDoc) throws SQLException {

        String sql = "SELECT novedades.Id_Novedades,Fecha_Inicio,Fecha_Fin,tipoNovedad, empleados.Cedula,Nombres,Apellidos,tipoDocumento "
                + "FROM novedades "
                + "INNER JOIN empleados "
                + "ON novedades.Id_Empleado=empleados.Cedula "
                + "WHERE empleados.Cedula LIKE '" + nroDoc + "%'";

        con = cn.getConnection(); // Establece la conexión
        ps = con.prepareStatement(sql); // Se prepara el código sql
        rs = ps.executeQuery();

        return rs;
    }
}
