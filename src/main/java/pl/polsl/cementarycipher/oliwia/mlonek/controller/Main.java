
package pl.polsl.cementarycipher.oliwia.mlonek.controller;

import pl.polsl.cementarycipher.oliwia.mlonek.model.CementaryCipherModel;
import pl.polsl.cementarycipher.oliwia.mlonek.view.CementaryCipherView;
import pl.polsl.cementarycipher.oliwia.mlonek.view.GUIView;


/** 
 * Main class of the application realizing encoding and decoding 
 * message with Cemetary Cipher.
 * 
 * @author Oliwia Mlonek
 * @version 3.0
 */
public class Main {

     /**
     * Main method of launching, proccessing and passing data into cipher.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /** model used to perform actions */
        CementaryCipherModel model = new CementaryCipherModel();
          /** view used to interact with user */
        CementaryCipherView view = new CementaryCipherView();
         /** GUI view used to interact with user */
        GUIView guiView = new GUIView(model);
         /** controller used to manage operations of program */
        CementaryCipherController controller = new CementaryCipherController(model, view, guiView);
     
        if(args.length == 0)
            controller.getInput();
        else if ( args[0].contentEquals("en"))
            controller.getInput(args);
        else if (args[0].contentEquals("de"))
            controller.getInput(args);
        else
            controller.getInput();
        
        
   

        
    }
    
}
