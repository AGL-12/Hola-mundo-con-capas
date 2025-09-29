package controlador;

import exception.LoginException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Usuario;

/**
 * Controlador de la vista de inicio de sesión (login).
 * <p>
 * Se encarga de procesar los datos introducidos por el usuario, validarlas en
 * la base de datos o un fichero local, y mostrar la información del usuario si
 * el login es exitoso.
 * </p>
 */
public class VistaLogControlador {

    /**
     * Campo de texto para el email del usuario.
     */
    @FXML
    private TextField tfEmail;

    /**
     * Campo de texto para la contraseña del usuario.
     */
    @FXML
    private PasswordField tpContra;

    /**
     * Botón para iniciar sesión.
     */
    @FXML
    private Button btnLogin;

    /**
     * Metodo para cerrar la ventana
     */
    @FXML
    private void CerrarVent() {
        // Obtener el Stage actual desde cualquier nodo (ej: el botón)
        Stage stage = (Stage) btnLogin.getScene().getWindow();
        stage.close();
    }

    /**
     * Intenta iniciar sesión con las credenciales introducidas.
     * <p>
     * Primero busca el usuario en la base de datos, y si no lo encuentra, lo
     * busca en el fichero. Si el usuario no existe en ninguno de los dos, se
     * lanza una {@link LoginException}.
     * </p>
     *
     * @throws LoginException si el usuario no se encuentra
     */
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

    /**
     * Abre una nueva ventana con los datos del usuario logueado.
     *
     * @param u el usuario verificado
     */
    private void abrirVentenaDatos(Usuario u) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaDatos.fxml"));
            Scene scene = new Scene(loader.load());

            VistaDatosControlador controlador = loader.getController();
            controlador.setUsuario(u);

            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Datos del usuario");
            stage.setScene(scene);

            // BLOQUEA LA VENTANA ANTERIOR  
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Muestra una alerta en pantalla con un título y un mensaje.
     *
     * @param titulo título de la ventana de alerta
     * @param mensaje contenido del mensaje
     */
    private void mostrarAlerta(String titulo, String mensaje) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
