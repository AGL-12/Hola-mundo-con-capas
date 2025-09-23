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

    private static InterfazDao daoU = new DaoImplemnetBD();
    private static InterfazDao daoF = new DaoImplementFile();
    
    
    public static Usuario buscarUsuarioBD(String nombre, String contrasena) {
        return daoU.buscarUsuario(nombre, contrasena);
    }
    
        public static Usuario buscarUsuarioFi(String nombre, String contrasena) {
        return daoF.buscarUsuario(nombre, contrasena);
    }


}
