/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.cementarycipher.oliwia.mlonek.controller;

import java.util.List;
import java.util.Scanner;
import pl.polsl.cementarycipher.oliwia.mlonek.model.CementaryCipherModel;
import pl.polsl.cementarycipher.oliwia.mlonek.model.DecodeAlphabet;
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
    
    void getInput()
    {
        while (true) {
        view.printMainMenu();
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {

            case 1:
                try {
                    model.encodeMessage(view.getEncodeText());
                
                } catch (WrongInputException e) {
                    view.printError(e.what());
                }
                finally
                {
                view.showEncodedString(model.getEncodedValue());
                model.resetValue();
                }
               
                
                break;

            case 2:
                view.showCodeMap();
                List<String> input = view.getDecodeText();
                if(input != null && input.size() != 0 )
                {

                 view.showDecodedString(model.decodeMessage(input));

                }

                else
                {
                    System.out.println("Wrong input. Try again!"); 
                }
                model.resetDecodedValue();
                break;

            case 3:
                System.out.println("exit");
                return;
        }
        }
    }
    
    void encodeText(String x)
    {
        try {
            model.encodeMessage(x);
        } catch (WrongInputException e) {
            view.printError(e.what());
        }
        
        finally
        {
            view.showEncodedString(model.getEncodedValue());
            model.resetValue();
            getInput();
        }
        
        return;
    }

    void decodeText() {
        view.showCodeMap();
        List<String> input = view.getDecodeText();
        if(input.size() != 0 && input != null )
        {

         view.showDecodedString(model.decodeMessage(input));

        }
        else
        {
            view.printError("Wrong input. Try again!"); 
        }
        getInput();
        return;
    }


}
