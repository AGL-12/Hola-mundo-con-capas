package exception;

/**
 * Excepción personalizada para errores en el proceso de inicio de sesión.
 * <p>
 * Se lanza cuando ocurre un fallo relacionado con la autenticación de un
 * usuario.
 * </p>
 */
public class LoginException extends Exception {
    /**
     * Crea una nueva {@code LoginException} con un mensaje de error.
     *
     * @param mensaje descripción del error ocurrido
     */
    public LoginException(String mensaje) {
        super(mensaje);
    }

}
