package controlador;

import modelo.Usuario;

/**
 * Interfaz que define las operaciones de acceso a datos para la entidad
 * {@link Usuario}.
 * <p>
 * Se puede implementar con distintas fuentes de datos, como base de datos o
 * archivos.
 * </p>
 */
public interface InterfazDao {

    /**
     * Busca un usuario según email y su contraseña.
     *
     * @param email identificador del usuario.
     * @param contrasena contraseña del usuario
     * @return el {@link Usuario} encontrado, o {@code null} si no existe
     */
    public Usuario buscarUsuario(String email, String contrasena);
}
