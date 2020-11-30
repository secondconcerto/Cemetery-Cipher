
package pl.polsl.cementarycipher.oliwia.mlonek.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    
    /** view used interact with user */
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
     * @param values optional users initial parameters from the command line
     */
    public void getInput(String ... values)
    {
        List<String> list =  new ArrayList<String>(Arrays.asList(values));
        while (true) {
            String choice = "";
            if (list.size() == 0)
                {
                    view.printMainMenu();
                    choice = view.getUserChoice();
                }
                else if(list.get(0).equals("en"))
                    choice = "1";
                else if(list.get(0).equals("de"))
                    choice = "2";
            


            switch (choice) {

                case "1":
                    try {
                        if(list.size() == 0)
                            model.encodeMessage(view.getTextToEncode());
                        else 
                           model.encodeMessage(model.convertToString(list));

                    view.showResult(model.getEncodedValue());

                    } catch (WrongInputException e) {
                        model.resetEncodedValue();
                        view.printError(e.getMessage());
                    }

                    model.resetEncodedValue();
                    list.clear();
                    break;

                case "2":
                    view.showCodeMap(decodeTableAlphabet.getMap());
                    try {
                        view.showResult(model.decodeMessage(view.getTextToDecode(decodeTableAlphabet.getMap())));
                    } catch (WrongInputException e) {
                        model.resetDecodedValue();
                        view.printError(e.getMessage());
                    }

                    model.resetDecodedValue();
                    list.clear();
                    break;

                case "3":
                    return;

                default:
                    getInput();
                    return;

            }
        }
    }   
}
