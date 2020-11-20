/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.cementarycipher.oliwia.mlonek.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import pl.polsl.cementarycipher.oliwia.mlonek.model.CementaryCipherModel;
import pl.polsl.cementarycipher.oliwia.mlonek.model.DecodeAlphabet;

/**
 *
 * @author roza
 */
public class CementaryCipherView {
    
    private CementaryCipherModel model;
    private DecodeAlphabet decodeTableAlphabet = new DecodeAlphabet();
//    public CementaryCipherView(CementaryCipherModel model) {
//        this.model = model;
//        
//    }

    public void printMainMenu() {
        System.out.println("Welcome in the program");
        System.out.println("What would you like to do?");
        System.out.println("1. Encode text");
        System.out.println("2. Decode text");
        System.out.println("3. Exit");

    }

    public String getEncodeText() {
         
        System.out.println("Enter a string: "); 
        Scanner sc= new Scanner(System.in);
        String str= sc.nextLine();             
        System.out.println("You have entered: "+str);     
        return str;
        
    }

    public List<String> getDecodeText() {
        System.out.println("Enter your message using numbers printed above. Please, seperate each number or whitespace with comma , (e.g. 1,2, ,3,4) "); 
        Scanner sc= new Scanner(System.in);
        String str= sc.nextLine(); 
        System.out.println("You have entered: "+str);  
        String[] myStrings = str.split(",");
        List<String> list = new ArrayList<>();
        for(int i = 0; i < myStrings.length;i++)
        {
            list.add(decodeTableAlphabet.getMap().get(myStrings[i]));
        }
        
        return list;
    }
    
    public void showEncodedString( String outputString)
    {
        System.out.println(outputString);
    }
    
    public void showDecodedString( List<String> outputString)
    {
        for(int i = 0; i < outputString.size(); i++) {
            System.out.print(outputString.get(i));
        };
        System.out.print("\n\n");
    }
    
    public void showCodeMap()
    {
        
        for( Map.Entry<String, String> entry : decodeTableAlphabet.getMap().entrySet() ){
            System.out.println( entry.getKey() + "  =>   " + "\n" +entry.getValue() + "\n" );
        };
   
    }
    
}
