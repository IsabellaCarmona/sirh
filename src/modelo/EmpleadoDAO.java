/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

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

    public boolean agregarEmpleado(Empleado empleado) {

        String sql = "INSERT INTO empleados(tipoDocumento,Cedula,Nombres,Apellidos,Fecha_Nacimiento,Telefono,Direccion,RH,EPS,ARL,SalarioBase) VALUES('"
                + empleado.getTipoId() + "','" + empleado.getCedula() + "','" + empleado.getNombres() + "','" + empleado.getApellidos() + "','"
                + empleado.getFechaNacimiento() + "','" + empleado.getTelefono() + "','" + empleado.getDireccion() + "','"
                + empleado.getRh() + "','" + empleado.getEps() + "','" + empleado.getArl() + "'," + empleado.getSalarioBase() + ")";

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

    public ArrayList traerDatos() throws SQLException {

        ArrayList empleados = new ArrayList();
        Empleado empleado = new Empleado();
        String sql = "SELECT tipoDocumento,Cedula,Nombres,Apellidos FROM empleados";

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
        String sql = "SELECT tipoDocumento,Cedula,Nombres,Apellidos FROM empleados WHERE tipoDocumento='" + tipoId + "'";

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
        String sql = "SELECT tipoDocumento,Cedula,Nombres,Apellidos FROM empleados WHERE Cedula LIKE '" + Id + "%' AND tipoDocumento='" + doc + "'";

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
}
