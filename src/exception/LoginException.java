/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author ander
 */
public class LoginException extends Exception {
    private String mensaje;
    public LoginException(String mensaje) {
        super(mensaje);
    }

}
