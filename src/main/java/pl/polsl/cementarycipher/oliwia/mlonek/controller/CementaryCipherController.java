

package pl.polsl.cementarycipher.oliwia.mlonek.controller;


import pl.polsl.cementarycipher.oliwia.mlonek.model.CementaryCipherModel;
import pl.polsl.cementarycipher.oliwia.mlonek.model.DecodeAlphabetModel;
import pl.polsl.cementarycipher.oliwia.mlonek.view.CementaryCipherView;
import pl.polsl.cementarycipher.oliwia.mlonek.view.GUIView;

/** 
 * Controller class of the application realizing user's interactions with the view 
 * into actions that the model will perform. 
 * 
 * @author Oliwia Mlonek
 * @version 3.0
 */
public class CementaryCipherController {
    /** model used to perform actions */
    private CementaryCipherModel model;
     /** view used interact with user */
    private CementaryCipherView view;
     /** GUI view used interact with user */
    private GUIView guiView;
     /** Model of decoding alphabet to decode message */
    private DecodeAlphabetModel decodeTableAlphabet = new DecodeAlphabetModel();

    /**
     * Costructor of the class
     * 
     * @param model used model
     * @param view used view
     * @param guiView used GUI view
     */
    public CementaryCipherController (CementaryCipherModel model, CementaryCipherView view, GUIView guiView) {
        this.model = model;
        this.view = view;
        this.guiView = guiView;
    }
    
     /**
     * Displayes menu of the program and passes user actions from view to model.
     * @param values optional users initial parameters from the command line
     */
    public void getInput(String ... values)
    {
        if(values.length == 0)
            guiView.start();
        else  if (values.length > 0 && values[0].equals("en")){
            guiView.start();
            guiView.encodeView();
        }
        else  if (values.length > 0 && values[0].equals("de")){
            guiView.start();
            guiView.decodeView();
        }
        else 
            guiView.start();

        
    }
}
   
    //view.printMainMenu();

//        switch (view.getUserChoice()) {
//
//            case "1":
//                try {mmo
//                    model.encodeMessage(view.getTextToEncode());
//                    view.showResult(model.getEncodedValue());
//                
//                } catch (WrongInputException e) {
//                    model.resetValue();
//                    //view.printError(e.what());
//                }
//
//                model.resetValue();
//
//                break;
//
//            case "2":
//                view.showCodeMap(decodeTableAlphabet.getMap());
//                try {
//                    view.showResult(model.decodeMessage(view.getTextToDecode(decodeTableAlphabet.getMap())));
//                } catch (WrongInputException e) {
//                    model.resetDecodedValue();
//                    //view.printError(e.what());
//                }
//                
//                model.resetDecodedValue();
//                break;
//
//            case "3":
//                
//                return;
//                
//            default:
//                getInput();
//                return;
//                
        
  
