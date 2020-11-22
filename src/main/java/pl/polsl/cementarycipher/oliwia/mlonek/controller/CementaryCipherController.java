/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.cementarycipher.oliwia.mlonek.controller;

import pl.polsl.cementarycipher.oliwia.mlonek.model.CementaryCipherModel;
import pl.polsl.cementarycipher.oliwia.mlonek.model.DecodeAlphabetModel;
import pl.polsl.cementarycipher.oliwia.mlonek.model.WrongInputException;
import pl.polsl.cementarycipher.oliwia.mlonek.view.CementaryCipherView;

/**
 *
 * @author roza
 */
public class CementaryCipherController {
   
    private CementaryCipherModel model;
    private CementaryCipherView view;
    private DecodeAlphabetModel decodeTableAlphabet = new DecodeAlphabetModel();

    public CementaryCipherController (CementaryCipherModel model, CementaryCipherView view) {
        this.model = model;
        this.view = view;
    }
    
    public void getInput()
    {
        while (true) {
        view.printMainMenu();

        switch (view.getUserChoice()) {

            case "1":
                try {
                    model.encodeMessage(view.getTextToEncode());
                    view.showResult(model.getEncodedValue());
                
                } catch (WrongInputException e) {
                    model.resetValue();
                    view.printError(e.what());
                }

                model.resetValue();

                break;

            case "2":
                view.showCodeMap(decodeTableAlphabet.getMap());
                try {
                    view.showResult(model.decodeMessage(view.getTextToDecode(decodeTableAlphabet.getMap())));
                } catch (WrongInputException e) {
                    model.resetDecodedValue();
                    view.printError(e.what());
                }
                
                model.resetDecodedValue();
                break;

            case "3":
                
                return;
                
            default:
                getInput();
                return;
                
        }
        }
    }
    
    public void encodeText(String[] textFromConsole)
    {
        try {
            model.encodeMessage(model.convertToString(textFromConsole));
            view.showResult(model.getEncodedValue());
           
            
        } catch (WrongInputException e) {
            view.printError(e.what());
        }
        
            model.resetValue();
            getInput();
        
        return;
    }

    public void decodeText() {
        view.showCodeMap(decodeTableAlphabet.getMap());
        try {
            view.showResult(model.decodeMessage(view.getTextToDecode(decodeTableAlphabet.getMap())));
        } catch (WrongInputException e) {
            view.printError(e.what());
        }

        model.resetDecodedValue();
        getInput();
        return;
    }


}
