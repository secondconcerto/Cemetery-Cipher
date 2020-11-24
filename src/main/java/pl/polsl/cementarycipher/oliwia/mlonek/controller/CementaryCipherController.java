
package pl.polsl.cementarycipher.oliwia.mlonek.controller;

import java.util.concurrent.TimeUnit;
import pl.polsl.cementarycipher.oliwia.mlonek.model.CementaryCipherModel;
import pl.polsl.cementarycipher.oliwia.mlonek.model.DecodeAlphabetModel;
import pl.polsl.cementarycipher.oliwia.mlonek.model.WrongInputException;
import pl.polsl.cementarycipher.oliwia.mlonek.view.CementaryCipherView;

/** 
 * Controller class of the application realizing user's interactions with the view 
 * into actions that the model will perform. 
 * 
 * @author Oliwia Mlonek
 * @version 1.0
 */
public class CementaryCipherController {
   
    /** model used to perform actions */
    private CementaryCipherModel model;
    
    /** view used interact with user */
    private CementaryCipherView view;
    
    /** Model of alphabet to decode message */
    private DecodeAlphabetModel decodeTableAlphabet = new DecodeAlphabetModel();
    
    /**
     * Costructor of the class
     * 
     * @param model used model
     * @param view used view
     */
    public CementaryCipherController (CementaryCipherModel model, CementaryCipherView view) {
        this.model = model;
        this.view = view;
    }
    
    
    /**
     * Displayes menu of the program and passes user actions from view to model.
     */
    public void getInput()
    {
        
        /** Main loop of the program */
        while (true) {
        view.printMainMenu();

        switch (view.getUserChoice()) {
           
            /** If first option was chosen, take data and encode it */
            case "1":
                while(true)
                {
                    try {
                        model.encodeMessage(view.getTextToEncode());
                        view.showResult(model.getEncodedValue());
                        break;

                    } catch (WrongInputException e) {
                        model.resetValue();
                        view.printError(e.what());
                    }
                }

                model.resetValue();

                break;
            /** If second option was chosen, take data and decode it */
            case "2":
                while(true)
                {
                    view.showCodeMap(decodeTableAlphabet.getMap());
                    try {
                        view.showResult(model.decodeMessage(view.getTextToDecode(decodeTableAlphabet.getMap())));
                        break;
                    } catch (WrongInputException e) {
                        model.resetDecodedValue();
                        view.printError(e.what());
                        
                    }
                }
                
                model.resetDecodedValue();
                break;
                
            /** If third option was chosen, exit the program */
            case "3":    
                return;
            /** If ambiguous option was chosen, print menu again */ 
            default:
                getInput();
                return;
                
        }
        }
    }
    
     /** 
     * Enocode text if it was passed as a parameter in command line with 'en' option. Then go back
     * to main loop.
     * 
     * @param textFromConsole words passed by the user to encode
     */
    public void encodeText(String[] textFromConsole)
    {
         /** Convert input to single string, convert to cipher, print on the screen, go back to menu*/
        try {
            model.encodeMessage(model.convertToString(textFromConsole));
            view.showResult(model.getEncodedValue());
           
            
        } catch (WrongInputException e) {
            view.printError(e.what());
        }
        
            model.resetValue();
            getInput();
        
    }

     /** 
     * Decode text if right option was passed as a parameter in command line ('de'). Then go back
     * to main loop.
     */
    public void decodeText() {
        
         /** Show table to put ciphere message as numbers, enocde it, print on the screen, go back to menu*/
        view.showCodeMap(decodeTableAlphabet.getMap());
        try {
            view.showResult(model.decodeMessage(view.getTextToDecode(decodeTableAlphabet.getMap())));
        } catch (WrongInputException e) {
            view.printError(e.what());
        }

        model.resetDecodedValue();
        getInput();
    }


}
