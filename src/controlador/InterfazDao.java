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
public interface InterfazDao {
    public Usuario buscarUsuario(String usuario, String contrasena);
}
