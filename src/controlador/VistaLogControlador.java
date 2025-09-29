package controlador;

import javafx.fxml.FXML;
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
        System.out.println("Vamo mu mal");
        if (u != null) {
            
        } else {
            u = Controlador.buscarUsuarioFi(email, pass);
            if (u != null) {

            }

        }

    }
}
