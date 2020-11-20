/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.cementarycipher.oliwia.mlonek.model;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.HashMap;

/**
 *
 * @author roza
 */
public class CementaryCipherModel {
    private String cipheredValue = "";
    private EncodeAlphabet cipheredAlphabet = new EncodeAlphabet();
    
 
    
    public void ciphereMessage(String userInput)
    {
        CharacterIterator it = new StringCharacterIterator(userInput);

        
        while (it.current() != CharacterIterator.DONE) {
            String x = Character.toString(it.current());
            if(cipheredAlphabet.getMap().get(x) != null)
                cipheredValue = cipheredValue + cipheredAlphabet.getMap().get(x)+ " " + it.current() +"\n";
            else 
                 cipheredValue = cipheredValue + it.current()+"\n\n\n";
            
            it.next();
        }
    }
      
    public String getCipheredValue()
    {
        return cipheredValue;
    }
    
    public void resetValue()
    {
        cipheredValue = "";
    }
        

    
}
