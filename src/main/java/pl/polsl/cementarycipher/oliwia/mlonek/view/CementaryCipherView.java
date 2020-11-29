/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.cementarycipher.oliwia.mlonek.view;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;
import pl.polsl.cementarycipher.oliwia.mlonek.model.WrongInputException;


/**
 *
 * @author roza
 */
public class CementaryCipherView {
    
    

    public void printMainMenu() {
        System.out.println("Welcome in the program");
        System.out.println("What would you like to do?");
        System.out.println("1. Encode text");
        System.out.println("2. Decode text");
        System.out.println("3. Exit");

    }

    public String getTextToEncode() {
         
        System.out.println("Enter a string: "); 
        Scanner sc= new Scanner(System.in);
        String str= sc.nextLine();             
        System.out.println("You have entered: "+str);  

        return str;
        
    }
    
     interface CheckPerson {
        boolean test(int p);
    }

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
    
    public void showResult( String outputString)
    {
        System.out.println(outputString);
    }
    

    
    public void showCodeMap(HashMap<String, String> decodeTableAlphabet )
    {
        
            TreeMap<String, String> sorted = new TreeMap<>(decodeTableAlphabet); 

            sorted.entrySet().stream()
                    .map(e -> e.getKey() + "  =>   " + "\n" + e.getValue()+ "\n" )
                    .forEach(System.out::println);
                
            
      
            
           

    }

   public void printError(WrongInputException errorText) {
        System.out.println("There is an error: "); 
        System.err.println(errorText.getMessage());
        long start = System.currentTimeMillis();
        long end = start + 20*100;
        while (System.currentTimeMillis() < end) {
        }
    }

    public String getUserChoice() {
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();  
        return choice;
    }
        
    
}
