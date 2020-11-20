/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.cementarycipher.oliwia.mlonek.view;

import java.util.Scanner;
import pl.polsl.cementarycipher.oliwia.mlonek.model.CementaryCipherModel;

/**
 *
 * @author roza
 */
public class CementaryCipherView {
    
    private CementaryCipherModel model;

    public CementaryCipherView(CementaryCipherModel model) {
        this.model = model;
        
    }

    public void printMainMenu() {
        System.out.println("Welcome in the program");
        System.out.println("What would you like to do?");
        System.out.println("1. Encode text");
        System.out.println("2. Decode text");
        System.out.println("3. Exit");
        
 
        String a = " \u2022|\n \u203E ";
        String b = "|\u2022|\n \u203E";
        String c = "|\u2022\n \u203E";
        String d = " _\n \u2022|\n \u203E";
        String h = " :|\n \u203E ";
        System.out.println(a+"\n" + b+"\n"+c+"\n"+d+"\n"+h);
        
        
    }

    public String getEncodeText() {
         
        System.out.println("Enter a string: "); 
        Scanner sc= new Scanner(System.in);
        String str= sc.nextLine();             
        System.out.println("You have entered: "+str);     
        return str;
        
    }

    public void getDecodeText() {
        
      }
    
}
