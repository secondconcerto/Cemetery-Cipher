/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.cementarycipher.oliwia.mlonek.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import pl.polsl.cementarycipher.oliwia.mlonek.model.CementaryCipherModel;
import pl.polsl.cementarycipher.oliwia.mlonek.model.DecodeAlphabetModel;
import pl.polsl.cementarycipher.oliwia.mlonek.model.WrongInputException;
import pl.polsl.cementarycipher.oliwia.mlonek.view.CementaryCipherView;
import pl.polsl.cementarycipher.oliwia.mlonek.view.GUIView;

/**
 *
 * @author roza
 */
public class CementaryCipherController {
   
    private CementaryCipherModel model;
    private CementaryCipherView view;
    private GUIView guiView;
    private DecodeAlphabetModel decodeTableAlphabet = new DecodeAlphabetModel();

    public CementaryCipherController (CementaryCipherModel model, CementaryCipherView view, GUIView guiView) {
        this.model = model;
        this.view = view;
        this.guiView = guiView;
    }
    
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
        
        //else 
            
        
            
       
        
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
        
  
