/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.cementarycipher.oliwia.mlonek.controller;


import java.util.Scanner;
import pl.polsl.cementarycipher.oliwia.mlonek.model.CementaryCipherModel;
import pl.polsl.cementarycipher.oliwia.mlonek.model.WrongInputException;
import pl.polsl.cementarycipher.oliwia.mlonek.view.CementaryCipherView;

/**
 *
 * @author roza
 */
public class CementaryCipherController {
    private CementaryCipherModel model;
    private CementaryCipherView view;
    

    public CementaryCipherController (CementaryCipherModel model, CementaryCipherView view) {
        this.model = model;
        this.view = view;
    }
    
    public void getInput()
    {
        while (true) {
        view.printMainMenu();
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        switch (choice) {

            case "1":
                try {
                    model.encodeMessage(view.getTextToEncode());
                
                } catch (WrongInputException e) {
                    model.resetValue();
                    view.printError(e.what());
                }

                view.showResult(model.getEncodedValue());
                model.resetValue();

                break;

            case "2":
                view.showCodeMap();
                try {
                    view.showResult(model.decodeMessage(view.getTextToDecode()));
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
        } catch (WrongInputException e) {
            view.printError(e.what());
        }
        
            view.showResult(model.getEncodedValue());
            model.resetValue();
            getInput();
        
        return;
    }

    public void decodeText() {
        view.showCodeMap();
        try {
            view.showResult(model.decodeMessage(view.getTextToDecode()));
        } catch (WrongInputException e) {
            view.printError(e.what());
        }

        model.resetDecodedValue();
        getInput();
        return;
    }


}
