package controlador;

import exception.LoginException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import modelo.Usuario;

public class VistaLogControlador {

    @FXML
    private TextField tfEmail;
    @FXML
    private PasswordField tpContra;
    @FXML
    private Button btnLogin;

    @FXML
    private void iniciarSesion() throws LoginException {
        String email = tfEmail.getText();
        String pass = tpContra.getText();

        Usuario u = null;
        try {
            u = Controlador.buscarUsuarioBD(email, pass);
            if (u != null) {
                abrirVentenaDatos(u);
                mostrarAlerta("Éxito", "Usuario encontrado en la base de datos");
            } else {
                u = Controlador.buscarUsuarioFi(email, pass);
                if (u != null) {
                    abrirVentenaDatos(u);
                    mostrarAlerta("Éxito", "Usuario encontrado en el fichero");
                } else {
                    throw new LoginException("Usuario no encontrado con email: " + email);
                }
            }
        } catch (LoginException e) {
            mostrarAlerta("Error", e.getMessage());
        } catch (Exception e) {
            //e.printStackTrace();
            mostrarAlerta("Error", "Ha ocurrido un error inesperado.");
        }

    }

    private void abrirVentenaDatos(Usuario u) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaDatos.fxml"));
            Scene scene = new Scene(loader.load());

            VistaDatosControlador controlador = loader.getController();
            controlador.setUsuario(u);

            Stage stage = new Stage();
            stage.setTitle("Datos del usuario");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
