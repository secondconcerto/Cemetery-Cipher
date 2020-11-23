
package pl.polsl.cementarycipher.oliwia.mlonek.model;

/**
 * Exception class for objects thrown when attempting to proccess invalid user input.
 *
 * @author Gall Anonim
 * @version 1.1
 */
public class WrongInputException extends Exception{
    
    /**
     * Display error message on the user screen.
     * 
     * @return error message.
     */
    public String what(){
        return "Your input contains invalid characters or is empty!";
    }
}

    