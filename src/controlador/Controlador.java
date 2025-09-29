package controlador;

import modelo.Usuario;

/**
 * Clase de utilidad que centraliza las operaciones de acceso a datos
 * relacionadas con los usuarios.
 * <p>
 * Proporciona métodos estáticos para buscar un usuario tanto en la base de
 * datos como en un archivo, usando implementaciones de {@link InterfazDao}.
 * </p>
 */
public class Controlador {

    /**
     * Implementación del acceso a datos usando base de datos.
     */
    private static InterfazDao daoB = new DaoImplemnetBD();
    /**
     * Implementación del acceso a datos usando archivos.
     */
    private static InterfazDao daoF = new DaoImplementFile();

    /**
     * Busca un usuario en la base de datos según email y contraseña.
     *
     * @param email correo electrónico del usuario
     * @param contrasena contraseña del usuario
     * @return el {@link Usuario} encontrado, o {@code null} si no existe
     */
    public static Usuario buscarUsuarioBD(String email, String contrasena) {
        return daoB.buscarUsuario(email, contrasena);
    }

    /**
     * Busca un usuario en un archivo según email y contraseña.
     *
     * @param email correo electrónico del usuario
     * @param contrasena contraseña del usuario
     * @return el {@link Usuario} encontrado, o {@code null} si no existe
     */
    public static Usuario buscarUsuarioFi(String email, String contrasena) {
        return daoF.buscarUsuario(email, contrasena);
    }

}
