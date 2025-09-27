/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import modelo.Usuario;

/**
 *
 * @author Alexander
 */
public class DaoImplementFile implements InterfazDao {

    private String filePath;

    public DaoImplementFile() {
        this.filePath = "src/data/usuarios";
    }

    public Usuario buscarUsuario(String email, String contra) {
        List<Usuario> usuarios = leerUsuarios();
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email.trim()) && u.getContrasena().equals(contra.trim())) {
                return u;
            }
        }
        return null;
    }

    private List<Usuario> leerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        java.io.File file = new java.io.File(filePath);

        try (java.util.Scanner sc = new java.util.Scanner(file)) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                String[] campos = linea.split(";");
                String email = campos[0];
                String nombre = campos[1];
                String contrasena = campos[2];
                boolean titulado = Boolean.parseBoolean(campos[3]);
                String genero = campos[4];
                java.sql.Date fechaNace = java.sql.Date.valueOf(campos[5]);
                int cp = Integer.parseInt(campos[6]);

                usuarios.add(new Usuario(email, nombre, contrasena, titulado, genero, fechaNace, cp));

            }
        } catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
        }

        return usuarios;
    }
}
