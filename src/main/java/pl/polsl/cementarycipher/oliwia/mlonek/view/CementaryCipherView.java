
package pl.polsl.cementarycipher.oliwia.mlonek.view;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;



/** 
 * View class represents the visualization of the data. 
 * Provides interaction with the user.
 * 
 * @author Oliwia Mlonek
 * @version 2.0
 */
public class CementaryCipherView {
    
    

    /** 
    * Print the application menu on the screen.
    */
    public void printMainMenu() {
        System.out.println("Welcome in the program");
        System.out.println("What would you like to do?");
        System.out.println("1. Encode text");
        System.out.println("2. Decode text");
        System.out.println("3. Exit");

    }
    
    /** 
    * Ask for and take user input as a text to encode.
    * @return text entered by the user
    */
    public String getTextToEncode() {
         
        System.out.println("Enter a string: "); 
        Scanner sc= new Scanner(System.in);
        String str= sc.nextLine();             
        System.out.println("You have entered: "+str);  

        return str;
        
    }
    

     
    /** 
    * Ask for and take user input as a text (numbers), in specific format, to decode.
    * 
    * @param decodeTableAlphabet structure holding the alphabet, needed to read the pictogram by the number.
    * @return message entered by the user (as the pictograms)
    */
    public List<String> getTextToDecode(HashMap<String, String> decodeTableAlphabet) {
       
        System.out.println("Enter your message using numbers printed above. To put whitespace between words type enter. To finish message type dot ."); 
        
        Scanner input = new Scanner(System.in);
        
        List<String> list = new ArrayList<>();
        
        
        int i = 0;
        
        while (i == 0)
        {
            String word = input.nextLine();
            
            if(word.contentEquals("."))
                return list;
            else if (word.isBlank())
                list.add(" ");
            else if (!word.matches("\\d+"))
            {
                list.clear();
                return list;
            }
            else if  ( 9 < (Integer.parseInt(word)) && (Integer.parseInt(word)) < 36  ) 
                list.add(decodeTableAlphabet.get(word));
            else 
                i = 1;
        }
        
        list.clear();
        return list;
    }
    
    /** 
    * Print the result on the user screen.
    * 
    * @param outputString value that holds encoded/decoded message
    */
    public void showResult( String outputString)
    {
        System.out.println(outputString);
    }
    

    /** 
    * Print the decoding map ( number - pictogram), so the user can put the message to decode in form of numbers.
    * 
    * @param decodeTableAlphabet structure holding the alphabet map
    */
    public void showCodeMap(HashMap<String, String> decodeTableAlphabet )
    {
        
            TreeMap<String, String> sorted = new TreeMap<>(decodeTableAlphabet); 

            sorted.entrySet().stream()
                    .map(e -> e.getKey() + "  =>   " + "\n" + e.getValue()+ "\n" )
                    .forEach(System.out::println);
                

    }

    /** 
    * Print the error message on the user screen.
    * 
    * @param errorText text to be displayed
    */
   public void printError(String errorText) {
        System.out.println("There is an error: " + errorText); 
         long start = System.currentTimeMillis();
         long end = start + 20*100;
         while (System.currentTimeMillis() < end) {
         }
        
    }
   
   
    /** 
    * Retrieve the user's choice on the operation to be performed.
    * 
    * @return user choice
    */
    public String getUserChoice() {
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();  
        return choice;
    }
        
    
}
