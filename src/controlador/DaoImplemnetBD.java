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
 * Implementación de {@link InterfazDao} que gestiona el acceso a datos de
 * usuarios utilizando una base de datos MySQL.
 * <p>
 * Esta clase se encarga de abrir y cerrar conexiones, ejecutar consultas y
 * construir objetos {@link Usuario} a partir de los resultados.
 * </p>
 */
public class DaoImplemnetBD implements InterfazDao {

    /**
     * Archivo de propiedades que contiene la configuración de la base de datos.
     * <p>
     * Se carga desde un fichero <code>BD.properties</code>, donde se definen la
     * URL de conexión, el usuario y la contraseña.
     * </p>
     */
    private ResourceBundle configFile;
    /**
     * Parámetros de conexión a la base de datos.
     * <ul>
     *     <li>{@link #urlBD} → dirección de la base de datos (ej: jdbc:mysql://...)</li>
     *     <li>{@link #userBD} → usuario para la conexión</li>
     *     <li>{@link #passwordBD} → contraseña del usuario</li>
     * </ul>
     */
    private String urlBD, userBD, passwordBD;
    
    /**
     * Conexión activa con la base de datos.
     */
    private Connection con;

    /**
     * Sentencia SQL preparada para ejecutar consultas o actualizaciones
     * de forma segura y eficiente.
     */    
    private PreparedStatement stmt;

    /**
     * Consulta SQL para buscar un usuario por email y contraseña.
     */
    private static final String BUSCAR_USUARIO = "SELECT * FROM usuario WHERE email = ? AND contrasena = ?";

    /**
     * Constructor por defecto.
     * <p>
     * Inicializa los parámetros de conexión con la base de datos.
     * </p>
     */
    DaoImplemnetBD() {
        this.configFile = ResourceBundle.getBundle("config.DB");
        this.urlBD = this.configFile.getString("Conn");
        this.userBD = this.configFile.getString("DBUser");
        this.passwordBD = this.configFile.getString("DBPass");
    }

    /**
     * Abre la conexión con la base de datos.
     * <p>
     * Si ocurre un error al conectar, se muestra un mensaje en un cuadro de
     * diálogo.
     * </p>
     */
    private void openConnection() {
        try {
            con = DriverManager.getConnection(urlBD, this.userBD, this.passwordBD);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Cierra la conexión y la sentencia preparada si están abiertas.
     */
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

    /**
     * Cierra un {@link ResultSet} si no es nulo.
     *
     * @param rs el conjunto de resultados a cerrar
     */
    private void closeResult(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Busca un usuario en la base de datos a partir de su email y contraseña.
     *
     * @param email correo electrónico del usuario
     * @param contra contraseña del usuario
     * @return un objeto {@link Usuario} con los datos obtenidos, o {@code null}
     * si no se encuentra ningún usuario
     */
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
