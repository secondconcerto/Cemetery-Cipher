/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.cementarycipher.oliwia.mlonek.model;

import java.util.HashMap;

/**
 *
 * @author roza
 */
public class DecodeAlphabet {
    private HashMap<String, String> cipheredTableMap = new HashMap<String, String>()
    {{
        put("1", " \u2022|\n \u203E ");
        
        put("2", "|\u2022|\n \u203E ");
        
        put("3", "|\u2022\n \u203E");
        
        put("4", " _\n \u2022|\n \u203E");
        
        put("5", " _\n|\u2022|\n \u203E");
         
        put("6", " _\n|\u2022 \n \u203E");
       
        put("7", " _\n \u2022|");
        
        put("8", " _\n|\u2022|");
        
        put("9", " _\n|\u2022");
       
        put("10", " _\n|\u2022");

        put("11", " :|\n \u203E ");
        
        put("12", "|:|\n \u203E ");
        
        put("13", "|:\n \u203E");
        
        put("14", " _\n :|\n \u203E");
        
        put("15", " _\n|:|\n \u203E");
        
        put("16", " _\n|: \n \u203E");
        
        put("17", " _\n :|");
        
        put("18", " _\n|:|");
        
        put("19", " _\n|:");
        
        put("20", "  |\n \u203E ");
        
        put("21", "| |\n \u203E ");
        
        put("22", "| \n \u203E");
     
        put("23", " _\n  |\n \u203E");
       
        put("24", " _\n| |\n \u203E");
        
        put("25", " _\n|  \n \u203E");
       
        put("26", " _\n  |");
        
                
                
    }};
    
    public HashMap<String, String> getMap()
            {
                return cipheredTableMap;
            }
}
