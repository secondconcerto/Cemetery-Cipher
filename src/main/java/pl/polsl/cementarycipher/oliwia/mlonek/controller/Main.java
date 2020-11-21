/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.cementarycipher.oliwia.mlonek.controller;

import pl.polsl.cementarycipher.oliwia.mlonek.model.CementaryCipherModel;
import pl.polsl.cementarycipher.oliwia.mlonek.view.CementaryCipherView;

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
        CementaryCipherController controller = new CementaryCipherController(model, view);
        
        if (args[0].contentEquals("en"))
            controller.encodeText();
        else if (args[0].contentEquals("de"))
            controller.decodeText();
        else if (!args[0].contentEquals("en") && !args[0].contentEquals("de"))
            controller.getInput();
        

        
    }
    
}
