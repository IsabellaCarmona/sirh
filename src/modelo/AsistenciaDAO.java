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
public class AsistenciaDAO {

    Conexion cn = new Conexion();

    Connection con; //Permite verificar la instruccion sql y ejecutarla
    PreparedStatement ps; //Objeto donde se carga el resultado de la consulta
    ResultSet rs; //Objeto que guarda el resultado de la consulta

    Asistencia asistencia = new Asistencia();

    public boolean guardarDatos(Asistencia asist) {

        String sql = "INSERT INTO asistencia(fechaAsistencia,idEmpleado) VALUES('" + asist.getFechaAsistencia()
                + "','" + asist.getIdEmpleado() + "')";

        con = cn.getConnection();

        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AsistenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
    }
}
