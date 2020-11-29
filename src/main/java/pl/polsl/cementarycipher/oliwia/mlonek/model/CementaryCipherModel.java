/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.cementarycipher.oliwia.mlonek.model;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;



/**
 *
 * @author roza
 */
public class CementaryCipherModel {
    private String encodedValue = "";
    private String decodedValue = "";
    private EncodeAlphabetModel cipheredAlphabet;

    public CementaryCipherModel() {
        this.cipheredAlphabet = new EncodeAlphabetModel();
    }

 
    public String convertToString(String [] userArray)
    {
        String userMessageString = "";
        List<String> list =  new ArrayList<String>(Arrays.asList(userArray));
        
        list.remove(0);
 
        userMessageString = list.stream().collect(Collectors.joining(" "));
        String after = userMessageString.trim().replaceAll(" +", " ");
        return after;
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
            encodedValue += cipheredAlphabet.getMap()
                   .entrySet()
                   .stream()
                   .filter(entry -> entry.getKey().equals(x))
                   .map(Map.Entry::getValue)
                   .collect(Collectors.joining());
           encodedValue += "\n\n";
           it.next();
        }
    }
      
    public String decodeMessage(List<String> decodeText) throws WrongInputException {
        
        
        final int [] count = {0};
          int i = 0;
           if(decodeText == null || decodeText.isEmpty() )
           {
               throw new WrongInputException();
           }
           
         decodedValue = decodeText.stream().map(v ->{
             
               if (v.isBlank() != true) {
                   return cipheredAlphabet.getMap()
                            .entrySet()
                            .stream()
                            .filter(e -> Objects.equals(e.getValue(), v))
                            .map(Map.Entry::getKey)
                            .findAny().orElse("");

               } else {
                  
                   return " ";
               }
             
 
           }).collect(Collectors.joining());
          

        if(decodedValue.isBlank())
            throw new WrongInputException();
        else
         return decodedValue;
    }
    
    public String getEncodedValue()
    {
        return encodedValue;
    }
    
    public String getDecodedValue()
    {
        return decodedValue;
    }
    
    public void resetEncodedValue()
    {
        encodedValue = "";
    }
    
    public void resetDecodedValue()
    {
        decodedValue = "";
    }

    
        

    
}
