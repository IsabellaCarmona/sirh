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
import java.time.LocalDate;

/**
 *
 * @author ISABELLA CARMONA C
 */
public class SalarioDAO {

    Conexion cn = new Conexion();

    Connection con; //Permite verificar la instruccion sql y ejecutarla
    PreparedStatement ps; //Objeto donde se carga el resultado de la consulta
    ResultSet rs; //Objeto que guarda el resultado de la consulta

    Salario salario = new Salario();

    public java.sql.Date traerFechaCorte() throws SQLException {

        String sql = "SELECT MAX(fechaCorte) FROM salario";

        con = cn.getConnection(); //Establece la conexion
        ps = con.prepareStatement(sql); //Se prepara el codigo sql
        rs = ps.executeQuery();

        java.sql.Date fechaCorte = null;
        while (rs.next()) {
            fechaCorte = rs.getDate("fechaCorte");
        }

        return fechaCorte;
    }
}
