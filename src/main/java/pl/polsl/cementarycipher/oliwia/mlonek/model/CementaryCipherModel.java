/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.cementarycipher.oliwia.mlonek.model;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;



/**
 *
 * @author roza
 */
public class CementaryCipherModel {
    private String enocdeValue = "";
    private String decodedValue = "";
    private EncodeAlphabet cipheredAlphabet = new EncodeAlphabet();
    
 
    
    public void encodeMessage(String userInput) throws WrongInputException
    {
        CharacterIterator it = new StringCharacterIterator(userInput);

        
        while (it.current() != CharacterIterator.DONE) {
         if (!(it.current() >= 'a' && it.current() <= 'z') || it.current() >= 'A' && it.current() <= 'Z' ) {
            throw new WrongInputException();
         }
            String x = Character.toString(it.current());
            if(cipheredAlphabet.getMap().get(x) != null)
                enocdeValue = enocdeValue + cipheredAlphabet.getMap().get(x)+ " " + it.current() +"\n";
            else 
                 enocdeValue = enocdeValue + it.current()+"\n\n\n";
            
            it.next();
        }
    }
      
    public List<String> decodeMessage(List<String> decodeText) {
           String x;
           List<String> output = new ArrayList<>();
           
        for (String decodeText1 : decodeText) {
            x = decodeText1;
            int i = 0;
            
            if (x.isBlank())
            {
                output.add(" ");
                i++;
            }
            
            else
            {
                    for (Entry<String, String> entry : cipheredAlphabet.getMap().entrySet()) {
                    if (entry.getValue().equals(decodeText1)) {
                        output.add(entry.getKey());
                        i++;
                        break;
                    }
                }
                
            }
               
           
            
            
            

        }
        return output;
    }
    
    public String getEncodedValue()
    {
        return enocdeValue;
    }
    
    public String getDecodedValue()
    {
        return decodedValue;
    }
    
    public void resetValue()
    {
        enocdeValue = "";
    }
    
    public void resetDecodedValue()
    {
        decodedValue = "";
    }

    
        

    
}
