
package pl.polsl.cementarycipher.oliwia.mlonek.controller;

import pl.polsl.cementarycipher.oliwia.mlonek.model.CementaryCipherModel;
import pl.polsl.cementarycipher.oliwia.mlonek.view.CementaryCipherView;

/** 
 * Main class of the application realizing encoding and decoding 
 * message with Cemetary Cipher.
 * 
 * @author Oliwia Mlonek
 * @version 1.0
 */
public class Main {
    
    /**
     * Main method of launching, proccessing and passing data into cipher.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Make a model
        CementaryCipherModel model = new CementaryCipherModel();
        //Make a view
        CementaryCipherView view = new CementaryCipherView();
        //Make a contorller and assign a model and view to it
        CementaryCipherController controller = new CementaryCipherController(model, view);
        
        //Check if app was launched with parameters in command line
        if(args.length == 0) // If no, launch program menu
            controller.getInput();
        else if ( args.length > 1 && args[0].contentEquals("en")) //If 'en' was passed with at least one more argument, go to encoding.
            controller.encodeText(args);
        else if (args[0].contentEquals("de")) // If 'de' was passed, go to decoding and display numeric-pictogram table to type input.
            controller.decodeText();
        else // If arguments were passes, but don't contain required parameters, launch program menu.
            controller.getInput();
        
                    

        
    }
    
}
