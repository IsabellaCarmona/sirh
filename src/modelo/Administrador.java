/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author ISABELLA CARMONA C
 */
public class Administrador {

    private String user;
    private String password;
    private String documento;

    public Administrador() {
    }

    public Administrador(String user, String password, String documento) {
        this.user = user;
        this.password = password;
        this.documento = documento;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

}
