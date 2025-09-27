package controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import modelo.Usuario;

public class VistaLogControlador {

    @FXML
    private TextField tfEmail;
    @FXML
    private PasswordField tpContra;
    @FXML
    private Button btnLogin;

    @FXML
    private void initialize() {
        btnLogin.setOnAction(e -> iniciarSesion());
    }

    private void iniciarSesion() {
        String email = tfEmail.getText();
        String pass = tpContra.getText();

        Usuario u = Controlador.buscarUsuarioBD(email, pass);
        String origen = null;

        if (u != null) {
            origen = "Base de datos";
        } else {
            u = Controlador.buscarUsuarioFi(email, pass);
            if (u != null) {
                origen = "Fichero";
            }

        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (u != null) {
            alert.setTitle("Usuario encontrado");
            alert.setHeaderText("Usuario encontrado en " + origen);
            alert.setContentText("Nombre: " + u.getNombre() + "\nEmail: " + u.getEmail());
        } else {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Usuario no encontrado");
            alert.setHeaderText(null);
            alert.setContentText("No se ha encontrado el usuario en la base de datos ni en el fichero.");
        }
        alert.showAndWait();
    }
}
