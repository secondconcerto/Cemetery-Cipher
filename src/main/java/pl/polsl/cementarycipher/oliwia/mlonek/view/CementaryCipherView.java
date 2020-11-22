/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.cementarycipher.oliwia.mlonek.view;


import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
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

    public String getTextToEncode() {
         
        System.out.println("Enter a string: "); 
        Scanner sc= new Scanner(System.in);
        String str= sc.nextLine();             
        System.out.println("You have entered: "+str);  
        return str;
        
    }

    public List<String> getTextToDecode() {
       
        System.out.println("Enter your message using numbers printed above. To put whitespaces between words type enter. To finish message type dot ."); 
        
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
                list.add(decodeTableAlphabet.getMap().get(word));
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
    
//    public void showDecodedString( String outputString)
//    {
//        System.out.println(outputString);
////        for(int i = 0; i < outputString.size(); i++) {
////            System.out.print(outputString.get(i));
////        };
////        System.out.print("\n\n");
//    }
    
    public void showCodeMap()
    {
//     Map<String, String> map = new TreeMap<String, String>(decodeTableAlphabet.getMap());
//      Set set2 = map.entrySet();
//      Iterator iterator2 = set2.iterator();
//      while(iterator2.hasNext()) {
//         Map.Entry me2 = (Map.Entry)iterator2.next();
//         System.out.print(me2.getKey() + ": \n");
//         System.out.println(me2.getValue() + "\n");
//      }
        
//        for( Map.Entry<String, String> entry : decodeTableAlphabet.getMap().entrySet() ){
//            System.out.println( entry.getKey() + "  =>   " + "\n" +entry.getValue() + "\n" );
//        };

            TreeMap<String, String> sorted = new TreeMap<>(decodeTableAlphabet.getMap()); 
            Set<Entry<String, String>> mappings = sorted.entrySet();
            System.out.println("HashMap after sorting by keys in ascending order "); 
            for(Entry<String, String> mapping : mappings){
                System.out.println(mapping.getKey() + "  =>   " + "\n" +mapping.getValue() + "\n" ); 
            }



   
    }

    public void printError(String what) {
        System.out.println("There is an error: " + what); 
    }
    
}
