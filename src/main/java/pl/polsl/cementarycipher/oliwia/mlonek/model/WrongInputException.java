
package pl.polsl.cementarycipher.oliwia.mlonek.model;

/**
 * Exception classthrown when attempting to proccess invalid user input.
 *
 * @author Oliwia Mlonek
 * @version 3.0
 */
public class WrongInputException extends Exception{

    /**
     * Non-parameter constructor
     */
    public WrongInputException() {
    }

   /**
     * Exception class constructor
     *
     * @param message display message
     */ 
    public WrongInputException (String message) {
        super(message);
    }
}

    