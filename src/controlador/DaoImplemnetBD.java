/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import modelo.Usuario;

/**
 *
 * @author Alexander
 */
public class DaoImplemnetBD implements InterfazDao {

    private ResourceBundle configFile;
    private String urlBD, userBD, passwordBD;
    // Atributos
    private Connection con;
    private PreparedStatement stmt;

    DaoImplemnetBD() {
        this.urlBD = "jdbc:mysql://localhost:3306/holamundo?serverTimezone=Europe/Madrid&useSSL=false";
        this.userBD = "root";
        this.passwordBD = "abcd*1234";
    }

    private void openConnection() {
        try {
            con = DriverManager.getConnection(urlBD, this.userBD, this.passwordBD);
            /*
			 * con = DriverManager.getConnection(
			 * "jdbc:mysql://localhost:3306/examendb?serverTimezone=Europe/Madrid&useSSL=false",
			 * "root", "abcd*1234");
             */
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void closeConnection() {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeResult(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    final String BUSCAR_USUARIO = "SELECT * FROM usuario WHERE email = ? AND contrasena = ?";

    public Usuario buscarUsuario(String email, String contra) {
        Usuario u = null;
        ResultSet rs = null;
        try {
            openConnection();
            stmt = con.prepareStatement(BUSCAR_USUARIO);
            stmt.setString(1, email);
            stmt.setString(2, contra);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String correo = rs.getString("email");
                String nombre = rs.getString("nombre");
                String contrasenia = rs.getString("contrasena");
                boolean titulado = rs.getBoolean("titulado");
                String genero = rs.getString("genero");
                Date fechaNace = rs.getDate("fechaNace");
                int cp = rs.getInt("cp");

                u = new Usuario(correo, nombre, contrasenia, titulado, genero, fechaNace, cp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerramos recursos
            closeResult(rs);
            closeConnection();
        }
        return u;
    }
}
