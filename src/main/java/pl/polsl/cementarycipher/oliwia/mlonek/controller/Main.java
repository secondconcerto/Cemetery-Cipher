/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.cementarycipher.oliwia.mlonek.controller;

import pl.polsl.cementarycipher.oliwia.mlonek.model.CementaryCipherModel;
import pl.polsl.cementarycipher.oliwia.mlonek.view.CementaryCipherView;
import pl.polsl.cementarycipher.oliwia.mlonek.view.GUIView;


/**
 *
 * @author roza
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        CementaryCipherModel model = new CementaryCipherModel();
        //Make a view
        CementaryCipherView view = new CementaryCipherView();
        //Make a contorller and assign a model and view to it
        GUIView guiView = new GUIView(model);
        CementaryCipherController controller = new CementaryCipherController(model, view, guiView);
        
        
        

        
        //PRZENIESC DO KONTROLERA
        if(args.length == 0)
            controller.getInput();
        else if ( args.length > 1 && args[0].contentEquals("en"))
            controller.encodeText(args);
        else if (args[0].contentEquals("de"))
            controller.decodeText();
        else
            controller.getInput();
        
        
   

        
    }
    
}
