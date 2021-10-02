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
import javax.swing.JOptionPane;

/**
 *
 * @author ISABELLA CARMONA C
 */
public class AdministradorDAO {

    Conexion cn = new Conexion();

    Connection con; //Permite verificar la instruccion sql y ejecutarla
    PreparedStatement ps; //Objeto donde se carga el resultado de la consulta
    ResultSet rs; //Objeto que guarda el resultado de la consulta

    Administrador admin = new Administrador();

    public boolean cambiarPassword(String newPassword) {

        Administrador admin = new Administrador();
        String sql = "UPDATE administrador SET password='" + newPassword + "' WHERE password='" + admin.getPassword() + "'";

        //Conectarse a la base de datos
        con = cn.getConnection();

        try {
            ps = con.prepareStatement(sql); //Envia la instruccion en comando sql
            ps.executeUpdate(); //Ejecuta la instruccion
        } catch (SQLException ex) {
            //Muestra el error en caso de haberlo
            Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            return false;
        }

        return true;
    }

    public boolean validarUsuario(String usuario, String contrasena) throws SQLException {

        String sql = "SELECT *FROM administrador";

        con = cn.getConnection();

        ps = con.prepareStatement(sql); //Se prepara el codigo sql
        rs = ps.executeQuery(); //Se ejecuta

        while (rs.next()) {
            if (rs.getString("user").trim().equals(usuario) && rs.getString("password").trim().equals(contrasena)) {
                return true;
            }
        }
        return false;
    }
}
