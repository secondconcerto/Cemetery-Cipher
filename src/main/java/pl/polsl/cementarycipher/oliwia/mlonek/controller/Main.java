
package pl.polsl.cementarycipher.oliwia.mlonek.controller;

import pl.polsl.cementarycipher.oliwia.mlonek.model.CementaryCipherModel;
import pl.polsl.cementarycipher.oliwia.mlonek.view.CementaryCipherView;
/** 
 * Main class of the application realizing encoding and decoding 
 * message with Cemetary Cipher.
 * 
 * @author Oliwia Mlonek
 * @version 2.0
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
        
        //Make a controller and assign a model and view to it
        CementaryCipherController controller = new CementaryCipherController(model, view);
        
        //Check if app was launched with parameters in command line
        if(args.length == 0)
          controller.getInput();
        else if ( args.length > 1 && args[0].contentEquals("en"))
            controller.getInput(args);
        else if (args[0].contentEquals("de"))
            controller.getInput(args);
        else
            controller.getInput();
        
                    

        
    }
    
}
