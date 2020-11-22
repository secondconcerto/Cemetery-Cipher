/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.cementarycipher.oliwia.mlonek.model;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
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
    
 
    public String convertToString(String [] userArray)
    {
        String userMessageString = "";

        int n = userArray.length-1;
        String[] newArray = new String[n];
        System.arraycopy(userArray,1,newArray,0,n);
        for(int i = 0; i < newArray.length; i++) {
            userMessageString += newArray[i];
            userMessageString += " ";
        }

        return userMessageString;
    }
    
    public void encodeMessage(String userInput) throws WrongInputException
    {
        
        
        CharacterIterator it = new StringCharacterIterator(userInput);
         if (userInput.isBlank())
         {
              throw new WrongInputException();
         }

        while (it.current() != CharacterIterator.DONE) {
         if ( (Character.toString(it.current()).isBlank()) == false ){
             if( !(it.current() >= 'a' && it.current() <= 'z') && !(it.current() >= 'A' && it.current() <= 'Z')) {
                throw new WrongInputException();
             }
         }
        String x = Character.toString(it.current());
        if(cipheredAlphabet.getMap().get(x) != null)
            enocdeValue = enocdeValue + cipheredAlphabet.getMap().get(x)+ " " + it.current() +"\n\n";
        else 
             enocdeValue = enocdeValue + it.current()+"\n\n\n";

        it.next();
        }
    }
      
    public String decodeMessage(List<String> decodeText) throws WrongInputException {
           String x;
           //List<String> output = new ArrayList<>();
           String output = "";
           if(decodeText == null || decodeText.size() == 0 )
           {
               throw new WrongInputException();
           }
             
           
        for (String decodeText1 : decodeText) {
            x = decodeText1;
            int i = 0;
            
            if (x.isBlank())
            {
                output += (" ");
                i++;
            }
            
            else
            {
                for (Entry<String, String> entry : cipheredAlphabet.getMap().entrySet()) {
                    if (entry.getValue().equals(decodeText1)) {
                        output += entry.getKey();
                        i++;
                        break;
                    }
                }
                
            }

        }
        if(output.isBlank())
            throw new WrongInputException();
        else
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
