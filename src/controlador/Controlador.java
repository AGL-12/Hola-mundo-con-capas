/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.Usuario;

/**
 *
 * @author Alexander
 */
public class Controlador {

    private static InterfazDao daoB = new DaoImplemnetBD();
    private static InterfazDao daoF = new DaoImplementFile();

    public static Usuario buscarUsuarioBD(String email, String contrasena) {
        return daoB.buscarUsuario(email, contrasena);
    }

    public static Usuario buscarUsuarioFi(String email, String contrasena) {
        return daoF.buscarUsuario(email, contrasena);
    }

}
