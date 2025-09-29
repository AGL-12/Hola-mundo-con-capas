package controlador;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import modelo.Usuario;

/**
 * Implementación de {@link InterfazDao} que gestiona el acceso a datos de
 * usuarios almacenados en un archivo de texto.
 * <p>
 * Cada línea del archivo representa un usuario con sus atributos separados por
 * punto y coma (<code>;</code>).
 * </p>
 */
public class DaoImplementFile implements InterfazDao {

    /**
     * Ruta al archivo de usuarios.
     */
    private String filePath;

    /**
     * Constructor por defecto.
     * <p>
     * Inicializa la ruta del archivo donde se almacenan los usuarios.
     * </p>
     */
    public DaoImplementFile() {
        this.filePath = "src/data/usuarios";
    }

    /**
     * Busca un usuario en el archivo comparando email y contraseña.
     *
     * @param email correo electrónico del usuario
     * @param contra contraseña del usuario
     * @return un objeto {@link Usuario} si se encuentra, o {@code null} en caso
     * contrario
     */
    @Override
    public Usuario buscarUsuario(String email, String contra) {
        List<Usuario> usuarios = leerUsuarios();
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email.trim()) && u.getContrasena().equals(contra.trim())) {
                return u;
            }
        }
        return null;
    }

    /**
     * Lee todos los usuarios almacenados en el archivo.
     * <p>
     * Cada línea debe tener el siguiente formato:
     * <pre>
     * email;nombre;contraseña;titulado;genero;fechaNace;cp
     * </pre>
     * </p>
     *
     * @return una lista de usuarios leídos del archivo
     */
    private List<Usuario> leerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        File file = new File(filePath);

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
