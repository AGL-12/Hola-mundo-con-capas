package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal de la aplicación.
 * <p>
 * Extiende de {@link javafx.application.Application} y se encarga de iniciar la
 * interfaz cargando la vista de login.
 * </p>
 */
public class Main extends Application {

    /**
     * Método de inicio de la aplicación JavaFX.
     * <p>
     * Se ejecuta automáticamente al lanzar la aplicación y carga la vista
     * {@code Vistalogin.fxml}.
     * </p>
     *
     * @param stage la ventana principal de la aplicación
     * @throws Exception si ocurre un error al cargar la vista FXML
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/vista/Vistalogin.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Iniciar Sesion");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Punto de inicio de la aplicación.
     *
     * @param args argumentos por defecto del main
     */
    public static void main(String[] args) {
        launch(args);
    }
}
