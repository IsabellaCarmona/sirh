/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ISABELLA CARMONA C
 */
public class EmpleadoDAO {

    Conexion cn = new Conexion();

    Connection con; //Permite verificar la instruccion sql y ejecutarla
    PreparedStatement ps; //Objeto donde se carga el resultado de la consulta
    ResultSet rs; //Objeto que guarda el resultado de la consulta

    Empleado empleado = new Empleado();

    public boolean agregarEmpleado(Empleado empleado, File ruta, Long longitud) throws FileNotFoundException {

        String sql = "INSERT INTO empleados(CV,tipoDocumento,Cedula,Nombres,Apellidos,Fecha_Nacimiento,Telefono,Direccion,Cargo,RH,EPS,ARL,SalarioBase,estado) VALUES(?, '"
                + empleado.getTipoId() + "','" + empleado.getCedula() + "','" + empleado.getNombres() + "','" + empleado.getApellidos() + "','"
                + empleado.getFechaNacimiento() + "','" + empleado.getTelefono() + "','" + empleado.getDireccion() + "','" + empleado.getCargo() + "','"
                + empleado.getRh() + "','" + empleado.getEps() + "','" + empleado.getArl() + "'," + empleado.getSalarioBase() + ", 'ACTIVO')";

        FileInputStream flujo = new FileInputStream(ruta);

        //Conectarse a la base de datos
        con = cn.getConnection();

        try {
            ps = con.prepareStatement(sql); //Envia la instruccion en comando sql
            ps.setBlob(1, flujo, longitud);
            ps.executeUpdate(); //Ejecuta la instruccion
        } catch (SQLException ex) {
            //Muestra el error en caso de haberlo
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            return false;
        }

        return true;
    }

    public ArrayList traerDatos() throws SQLException {

        ArrayList empleados = new ArrayList();
        Empleado empleado = new Empleado();
        String sql = "SELECT tipoDocumento,Cedula,Nombres,Apellidos FROM empleados WHERE estado='ACTIVO'";

        con = cn.getConnection(); //Establece la conexion
        ps = con.prepareStatement(sql); //Se prepara el codigo sql
        rs = ps.executeQuery();

        while (rs.next()) {
            empleado.setTipoId(rs.getString("tipoDocumento"));
            empleado.setCedula(rs.getString("Cedula"));
            empleado.setNombres(rs.getString("Nombres"));
            empleado.setApellidos(rs.getString("Apellidos"));
            empleados.add(new Empleado(empleado.getTipoId(), empleado.getCedula(), empleado.getNombres(), empleado.getApellidos()));
        }

        return empleados;
    }

    public ArrayList traerDatosTipoID(String tipoId) throws SQLException {

        ArrayList empleados = new ArrayList();
        Empleado empleado = new Empleado();
        String sql = "SELECT tipoDocumento,Cedula,Nombres,Apellidos FROM empleados WHERE tipoDocumento='" + tipoId + "' AND estado='ACTIVO'";

        con = cn.getConnection(); //Establece la conexion
        ps = con.prepareStatement(sql); //Se prepara el codigo sql
        rs = ps.executeQuery();

        while (rs.next()) {
            empleado.setTipoId(rs.getString("tipoDocumento"));
            empleado.setCedula(rs.getString("Cedula"));
            empleado.setNombres(rs.getString("Nombres"));
            empleado.setApellidos(rs.getString("Apellidos"));
            empleados.add(new Empleado(empleado.getTipoId(), empleado.getCedula(), empleado.getNombres(), empleado.getApellidos()));
        }

        return empleados;
    }

    public ArrayList<Empleado> traerDatosID(String Id, String doc) throws SQLException {

        ArrayList<Empleado> empleados = new ArrayList<>();
        Empleado empleado = new Empleado();
        String sql = "SELECT tipoDocumento,Cedula,Nombres,Apellidos FROM empleados WHERE Cedula LIKE '" + Id + "%' AND tipoDocumento='" + doc + "' AND estado='ACTIVO'";

        con = cn.getConnection(); //Establece la conexion
        ps = con.prepareStatement(sql); //Se prepara el codigo sql
        rs = ps.executeQuery();

        while (rs.next()) {
            empleado.setTipoId(rs.getString("tipoDocumento"));
            empleado.setCedula(rs.getString("Cedula"));
            empleado.setNombres(rs.getString("Nombres"));
            empleado.setApellidos(rs.getString("Apellidos"));
            empleados.add(new Empleado(empleado.getTipoId(), empleado.getCedula(), empleado.getNombres(), empleado.getApellidos()));
        }

        return empleados;
    }

    public ArrayList datosEmpleadoID(String Id) throws SQLException {

        ArrayList empleados = new ArrayList();
        Empleado empleado = new Empleado();
        String sql = "SELECT * FROM empleados WHERE Cedula LIKE'" + Id + "%'";

        con = cn.getConnection(); //Establece la conexion
        ps = con.prepareStatement(sql); //Se prepara el codigo sql
        rs = ps.executeQuery();

        while (rs.next()) {
            empleado.setTipoId(rs.getString("tipoDocumento"));
            empleado.setCedula(rs.getString("Cedula"));
            empleado.setNombres(rs.getString("Nombres"));
            empleado.setApellidos(rs.getString("Apellidos"));
            empleados.add(new Empleado(empleado.getTipoId(), empleado.getCedula(), empleado.getNombres(), empleado.getApellidos()));
        }

        return empleados;
    }

    public ArrayList datosEmpleado(String Id) throws SQLException {

        ArrayList empleados = new ArrayList();
        Empleado empleado = new Empleado();
        String sql = "SELECT * FROM empleados WHERE Cedula='" + Id + "'";

        con = cn.getConnection(); //Establece la conexion
        ps = con.prepareStatement(sql); //Se prepara el codigo sql
        rs = ps.executeQuery();

        while (rs.next()) {
            empleado.setTipoId(rs.getString("tipoDocumento"));
            empleado.setCedula(rs.getString("Cedula"));
            empleado.setNombres(rs.getString("Nombres"));
            empleado.setApellidos(rs.getString("Apellidos"));
            empleado.setFechaNacimiento(rs.getDate("Fecha_Nacimiento"));
            empleado.setTelefono(rs.getString("Telefono"));
            empleado.setDireccion(rs.getString("Direccion"));
            empleado.setCargo(rs.getString("Cargo"));
            empleado.setRh(rs.getString("RH"));
            empleado.setEps(rs.getString("EPS"));
            empleado.setArl(rs.getString("ARL"));
            empleado.setSalarioBase(rs.getInt("SalarioBase"));

            empleados.add(new Empleado(empleado.getTipoId(), empleado.getCedula(), empleado.getNombres(), empleado.getApellidos(),
                    empleado.getFechaNacimiento(), empleado.getTelefono(), empleado.getDireccion(), empleado.getCargo(), empleado.getRh(),
                    empleado.getEps(), empleado.getArl(), empleado.getSalarioBase()));
        }

        return empleados;
    }

    public Boolean actualizarDatos(Empleado empl) {

        String sql = "UPDATE empleados SET Nombres='" + empl.getNombres() + "', Apellidos='" + empl.getApellidos()
                + "', Fecha_Nacimiento='" + empl.getFechaNacimiento() + "', Telefono='" + empl.getTelefono()
                + "', Direccion='" + empl.getDireccion() + "', Cargo='" + empl.getCargo() + "', RH='" + empl.getRh()
                + "', EPS='" + empl.getEps() + "', ARL='" + empl.getArl() + "', SalarioBase=" + empl.getSalarioBase()
                + " WHERE Cedula='" + empl.getCedula() + "'";

        //Conectarse a la base de datos
        con = cn.getConnection();

        try {
            ps = con.prepareStatement(sql); //Envia la instruccion en comando sql
            ps.executeUpdate(); //Ejecuta la instruccion
        } catch (SQLException ex) {
            //Muestra el error en caso de haberlo
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            return false;
        }

        return true;
    }

    public Boolean bajaEmpleado(String doc, Empleado empleado) {

        String sql = "UPDATE empleados SET estado='INACTIVO' WHERE cedula='" + doc + "'";

        //Conectarse a BD
        con = cn.getConnection();
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            return false;
        }

        String sqlBaja = "INSERT INTO bajas_empleados(tipoDocumento,numeroDocumento,nombres,apellidos,fecha_nacimiento,telefono,direccion,cargo,RH,EPS,ARL,salarioBase) VALUES('"
                + empleado.getTipoId() + "','" + empleado.getCedula() + "','" + empleado.getNombres() + "','" + empleado.getApellidos() + "','"
                + empleado.getFechaNacimiento() + "','" + empleado.getTelefono() + "','" + empleado.getDireccion() + "','" + empleado.getCargo() + "','"
                + empleado.getRh() + "','" + empleado.getEps() + "','" + empleado.getArl() + "'," + empleado.getSalarioBase() + ")";

        try {
            ps = con.prepareStatement(sqlBaja);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            return false;
        }

        return true;
    }

    public void ejecutar_archivoPDF(String id) {

        ps = null;
        rs = null;
        byte[] b = null;

        try {
            ps = cn.getConnection().prepareStatement("SELECT CV FROM empleados WHERE Cedula ='" + id + "'");
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
}
