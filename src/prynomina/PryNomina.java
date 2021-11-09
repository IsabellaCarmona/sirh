/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prynomina;

import controlador.ControladorInicio;
import modelo.Administrador;
import modelo.AdministradorDAO;
import vista.FrmInicio;

/**
 *
 * @author ISABELLA CARMONA C
 */
public class PryNomina {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        FrmInicio form1 = new FrmInicio();
        AdministradorDAO admindao = new AdministradorDAO();
        Administrador admin = new Administrador();

        ControladorInicio control = new ControladorInicio(form1, admin, admindao);
        form1.setVisible(true);

    }

}
