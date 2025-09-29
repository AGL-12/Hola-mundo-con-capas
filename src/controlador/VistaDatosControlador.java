package controlador;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import modelo.Usuario;

/**
 * Controlador de la vista de datos de usuario.
 * <p>
 * Esta clase se encarga de mostrar la información de un {@link Usuario} en los
 * distintos campos de texto de la interfaz gráfica definida en el
 * archivo FXML correspondiente.
 * </p>
 */
public class VistaDatosControlador {

    /**Usuario actual cuyos datos se muestran en la vista.*/
    private Usuario u;

    // --- Campos de la interfaz gráfica (inyectados con @FXML) ---
    @FXML private TextField TextField_Email;
    @FXML private TextField TextField_Nombre;
    @FXML private TextField TextField_Contrasenia;
    @FXML private TextField TextField_Genero;
    @FXML private TextField TextField_Fecha;
    @FXML private TextField TextField_CP;
    @FXML private RadioButton rbSi;
    @FXML private RadioButton rbNo;
    @FXML private ToggleGroup grupoTitulado;

    /**
     * Asigna un usuario al controlador y muestra sus datos en la vista.
     *
     * @param usuario el {@code Usuario} cuyos datos se van a mostrar
     */
    public void setUsuario(Usuario usuario) {
        this.u = usuario;
        mostrarDatos();
    }
    
    /**
     * Muestra la información del usuario en los campos de texto de la vista.
     * <p>
     * Si el usuario es {@code null}, no se realiza ninguna acción. Nunca sera el caso.
     * </p>
     */
    private void mostrarDatos() {
        if (u != null) {
            TextField_Email.setText(u.getEmail());
            TextField_Nombre.setText(u.getNombre());
            TextField_Contrasenia.setText(u.getContrasena());
            if (u.isTitulao()) {
                rbSi.setSelected(true);
            } else {
                rbNo.setSelected(true);
            }
            TextField_Genero.setText(u.getGenero());
            TextField_Fecha.setText(u.getFechaNace().toString());
            TextField_CP.setText(String.valueOf(u.getCp()));

        }
    }

}
