/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.cementarycipher.oliwia.mlonek.model;

import java.util.HashMap;

/**
 *
 * @author roza
 */
public class CementaryCipherModel {
    private String cipheredValue;
    private HashMap<String, String> cipheredAlphabet = new HashMap<String, String>()
    {{
       put("A", " \u2022|\n \u203E ");
       put("a", "|\u2022|\n \u203E");
       put("C", "|\u2022\n \u203E");
       put("c", "|\u2022\n \u203E");
       put("D", " _\n \u2022|\n \u203E");
       put("d", " _\n \u2022|\n \u203E");
       put("E", " _\n|\u2022|\n \u203E");
       put("e", " _\n|\u2022|\n \u203E"); 
       put("O", "\u2360");
       put("o", "\u2360");
    }};
 
    
    public void ciphereMessage(String userInput)
    {
        
        for (int i = 0; i < userInput.length(); i++) {
            
        }
    }
      
    public String getCipheredValue()
    {
        return cipheredValue;
    }
        

    
}
