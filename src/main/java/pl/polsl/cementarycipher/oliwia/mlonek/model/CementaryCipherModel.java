
package pl.polsl.cementarycipher.oliwia.mlonek.model;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;




/** 
 * Model class of the application representing rules that govern access to and updates of data. 
 * 
 * @author Oliwia Mlonek
 * @version 1.0
 */
public class CementaryCipherModel {
    /** variable stores final encoded message */
    private String enocdeValue = "";
    /** variable stores final decoded message */
    private String decodedValue = "";
    /** Model of alphabet to encode message */
    private EncodeAlphabetModel cipheredAlphabet;

     /**
     * Costructor of the class
     */
    public CementaryCipherModel() {
        this.cipheredAlphabet = new EncodeAlphabetModel();
    }

 
     /** 
     * Converts array od string to single array.
     * 
     * @param userArray user input from command line stored in an array
     * @return user input convertet to a single string
     */
    public String convertToString(String [] userArray)
    {
        String userMessageString = "";
        List<String> list =  new ArrayList<String>(Arrays.asList(userArray));
        list.remove(0);

        for (String newArray1 : list) {
            userMessageString += newArray1;
            userMessageString += " ";
        }

       
        return userMessageString;
    }
    

     /** 
     * Encodes user input using cemetary cipher written as the alphabet in EncodeAlphabetModel.
     * 
     * @param userInput user message to encode
     * @throws WrongInputException when user input cannot be processed
     */
    public void encodeMessage(String userInput) throws WrongInputException
    {
        
        CharacterIterator it = new StringCharacterIterator(userInput);
            if (userInput.isBlank())
            {
                 throw new WrongInputException("Your input is empty or is written in wrong format!");
            }

           while (it.current() != CharacterIterator.DONE) {
                if ( (Character.toString(it.current()).isBlank()) == false ){
                    if( !(it.current() >= 'a' && it.current() <= 'z') && !(it.current() >= 'A' && it.current() <= 'Z')) {
                       throw new WrongInputException("Your input contains invalid characters!");
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
     
     /** 
     * Decodes user input to english alphabet using cemetary cipher written as the alphabet in EncodeAlphabetModel, 
     * as the input is already translated from the numbers to pictograms.
     * 
     * @param decodeText user message to decode (as pictograms)
     * @return user message as a text written in english alphabet
     * @throws WrongInputException when user input cannot be processed
     */
    public String decodeMessage(List<String> decodeText) throws WrongInputException {
           String x;

           if(decodeText.isEmpty() )
           {
               throw new WrongInputException("Your input contains invalid characters or is written in wrong format!");
           }
             
           
        for (String decodeText1 : decodeText) {
            x = decodeText1;
            int i = 0;
            
            if (x.isBlank())
            {
                decodedValue += (" ");
                i++;
            }
            
            else
            {
                for (Entry<String, String> entry : cipheredAlphabet.getMap().entrySet()) {
                    if (entry.getValue().equals(decodeText1)) {
                        decodedValue += entry.getKey();
                        i++;
                        break;
                    }
                }
                
            }

        }
        if(decodedValue.isBlank())
            throw new WrongInputException("Your input is empty or is written in wrong format!");
        else
         return decodedValue;
    }
    
    /** 
     * Getter of encoded value.
     * 
     * @return final encoded message
     */
    public String getEncodedValue()
    {
        return enocdeValue;
    }
    
     /** 
     * Getter of decoded value.
     * 
     * @return final decoded message
     */
    public String getDecodedValue()
    {
        return decodedValue;
    }
    
    /** 
     * Reset value, so if the same operation is executed, encoded messages will not be merged.
     */
    public void resetValue()
    {
        enocdeValue = "";
    }
    /** 
     * Reset value, so if the same operation is executed, decoded messages will not be merged.
     */
    public void resetDecodedValue()
    {
        decodedValue = "";
    }

    
        

    
}
