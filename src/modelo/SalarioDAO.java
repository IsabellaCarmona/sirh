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

    public int traerDias(String id) throws SQLException {

        String sql = "SELECT SUM(Dias_Trabajados) FROM salario WHERE Id_Empleado='" + id + "'";
        int dias = 0;
        con = cn.getConnection(); //Establece la conexion
        ps = con.prepareStatement(sql); //Se prepara el codigo sql
        rs = ps.executeQuery();

        while (rs.next()) {
            dias = rs.getInt("SUM(Dias_Trabajados)");
        }

        return dias;
    }

    public java.sql.Date traerFechaCorte(String id) throws SQLException {

        String sql = "SELECT MIN(fechaCorte) FROM salario WHERE Id_Empleado='" + id + "'";

        con = cn.getConnection(); //Establece la conexion
        ps = con.prepareStatement(sql); //Se prepara el codigo sql
        rs = ps.executeQuery();

        java.sql.Date fechaCorte = null;
        while (rs.next()) {
            fechaCorte = rs.getDate("MIN(fechaCorte)");
        }

        return fechaCorte;
    }
}