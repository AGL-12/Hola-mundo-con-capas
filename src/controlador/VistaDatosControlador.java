package controlador;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import modelo.Usuario;

public class VistaDatosControlador {

    private Usuario u;

    @FXML
    private TextField TextField_Email;
    @FXML
    private TextField TextField_Nombre;
    @FXML
    private TextField TextField_Contrasenia;
    @FXML
    private TextField TextField_Genero;
    @FXML
    private TextField TextField_Fecha;
    @FXML
    private TextField TextField_CP;
    @FXML
    private RadioButton rbSi;
    @FXML
    private RadioButton rbNo;
    @FXML
    private ToggleGroup grupoTitulado;

    public void setUsuario(Usuario usuario) {
        this.u = usuario;
        mostrarDatos();
    }

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
