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

    public Usuario buscarUsuario(String email, String password) {
        List<Usuario> usuarios = leerUsuarios();
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email) && u.getContrasena().equals(password)) {
                return u;
            }
        }
        return null;
    }

    private List<Usuario> leerUsuarios() {
        ObjectInputStream ois = null;
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usu;

        try {
            ois = new ObjectInputStream(new FileInputStream(filePath));
            while (true) {
                usu = (Usuario) ois.readObject();
                usuarios.add(usu);
            }
        } catch (EOFException e) {
            System.out.println("Fin del fichero");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return usuarios;

    }
}
