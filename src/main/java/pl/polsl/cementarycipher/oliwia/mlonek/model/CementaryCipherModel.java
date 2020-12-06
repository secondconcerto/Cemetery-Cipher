package pl.polsl.cementarycipher.oliwia.mlonek.model;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;



/** 
 * Model class of the application representing rules that govern access to and updates of data. 
 * 
 * @author Oliwia Mlonek
 * @version 2.0
 */
public class CementaryCipherModel {
    
    /** Variable stores final encoded message */
    private String encodedValue = "";
    
    /** Variable stores final decoded message */
    private String decodedValue = "";
    
     /** Model of alphabet to encode message */
    private EncodeAlphabetModel cipheredAlphabet;
    
    /** Model of decoding alphabet to decode message */
    private DecodeAlphabetModel decodeModel = new DecodeAlphabetModel();
     /**
     * Constructor of the class
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
    public String convertToString(List<String>  userArray)
    {
        String userMessageString = "";
        List<String> list =  new ArrayList<String>(userArray);
        
        list.remove(0);
 
        userMessageString = list.stream().collect(Collectors.joining(" "));
        String after = userMessageString.trim().replaceAll(" +", " ");
        return after;
    }
      /** 
     * Checks correctnnes of a given message.
     * 
     * @param word user input 
     * @return if input was correct, return modified message, ready to be decoded.
     * @throws WrongInputException when user input cannot be processed
     */
    public String checkInput(String word) throws WrongInputException
    {
            if (word.isBlank())
                return(" ");
            else if (!word.matches("\\d+"))
            {
                throw new WrongInputException("Invalid character");
            }
            else if  ( 9 < (Integer.parseInt(word)) && (Integer.parseInt(word)) < 36  ) 
               return decodeModel.getMap().get(word);
            else if( !(9 < (Integer.parseInt(word)) && (Integer.parseInt(word)) < 36))
                throw new WrongInputException("Invalid character");
        return "";
    }

    

    /** 
     * Encodes user input using cemetary cipher written as the alphabet in EncodeAlphabetModel.
     * 
     * @param userInput user message to encode
     * @throws WrongInputException when user input cannot be processed
     */
    public void encodeMessage(String userInput) throws WrongInputException
    {
        
        if (userInput == null || userInput.isBlank() )
         {
              throw new WrongInputException("Your input is empty or is written in wrong format!");
         }
        
        CharacterIterator it = new StringCharacterIterator(userInput);
         

        while (it.current() != CharacterIterator.DONE) {
            if ( (Character.toString(it.current()).isBlank()) == false ){
                if( !(it.current() >= 'a' && it.current() <= 'z') && !(it.current() >= 'A' && it.current() <= 'Z')) {
                   throw new WrongInputException("Your input contains invalid characters!");
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
     
    /** 
     * Decodes user input to english alphabet using cemetary cipher written as the alphabet in EncodeAlphabetModel, 
     * as the input is already translated from the numbers to pictograms.
     * 
     * @param decodeText user message to decode (as pictograms)
     * @return user message as a text written in english alphabet
     * @throws WrongInputException when user input cannot be processed
     */
    public String decodeMessage(List<String> decodeText) throws WrongInputException {
        
          int i = 0;
           if(decodeText == null || decodeText.isEmpty() )
           {
               throw new WrongInputException("Your input contains invalid characters or is written in wrong format!");
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
        return encodedValue;
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
    public void resetEncodedValue()
    {
        encodedValue = "";
    }
    
    /** 
     * Reset value, so if the same operation is executed, decoded messages will not be merged.
     */
    public void resetDecodedValue()
    {
        decodedValue = "";
    }

    
        

    
}
