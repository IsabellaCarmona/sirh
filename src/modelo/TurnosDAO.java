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
 * @author Sala-406
 */
public class TurnosDAO {

    Conexion cn = new Conexion();

    Connection con; //Permite verificar la instruccion sql y ejecutarla
    PreparedStatement ps; //Objeto donde se carga el resultado de la consulta
    ResultSet rs; //Objeto que guarda el resultado de la consulta

    Turnos turnos = new Turnos();

    public boolean asignarTurno(Turnos turno) {

        String sql = "INSERT INTO turnos(Fecha_Inicio,Fecha_Fin,Hora_Inicio,Hora_Fin,Id_Empleado) VALUES("
                + "'" + turno.getFechaInicio() + "','" + turno.getFechaFin() + "','" + turno.getHoraInicio() + "','"
                + turno.getHoraFin() + "','" + turno.getIdEmpleado() + "')";

        //Conectarse a la base de datos
        con = cn.getConnection();

        try {
            ps = con.prepareStatement(sql); //Envia la instruccion en comando sql
            ps.executeUpdate(); //Ejecuta la instruccion
        } catch (SQLException ex) {
            Logger.getLogger(TurnosDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            return false;
        }

        return true;
    }
}
