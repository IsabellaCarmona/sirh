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
    
    public boolean agregarEmpleado(Empleado empleado){
        
        String sql="INSERT INTO empleados(Cedula,Nombres,Apellidos,Fecha_Nacimiento,Telefono,Direccion,RH,EPS,ARL) VALUES('"
                +empleado.getCedula()+"','"+empleado.getNombres()+"','"+empleado.getApellidos()+"','"
                +empleado.getFechaNacimiento()+"','"+empleado.getTelefono()+"','"+empleado.getDireccion()+"','"
                +empleado.getRh()+"','"+empleado.getEps()+"','"+empleado.getArl()+"')";
        
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
    
    public boolean modificarEmpleado(){
        
    }
}
