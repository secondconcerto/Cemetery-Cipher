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
        put("10", " \u2022|\n \u203E ");
        
        put("11", "|\u2022|\n \u203E ");
        
        put("12", "|\u2022\n \u203E");
        
        put("13", " _\n \u2022|\n \u203E");
        
        put("14", " _\n|\u2022|\n \u203E");
         
        put("15", " _\n|\u2022 \n \u203E");
       
        put("16", " _\n \u2022|");
        
        put("17", " _\n|\u2022|");
        
        put("18", " _\n|\u2022");
       
        put("19", " _\n|\u2022");

        put("20", " :|\n \u203E ");
        
        put("21", "|:|\n \u203E ");
        
        put("22", "|:\n \u203E");
        
        put("23", " _\n :|\n \u203E");
        
        put("24", " _\n|:|\n \u203E");
        
        put("25", " _\n|: \n \u203E");
        
        put("26", " _\n :|");
        
        put("27", " _\n|:|");
        
        put("28", " _\n|:");
        
        put("29", "  |\n \u203E ");
        
        put("30", "| |\n \u203E ");
        
        put("31", "| \n \u203E");
     
        put("32", " _\n  |\n \u203E");
       
        put("33", " _\n| |\n \u203E");
        
        put("34", " _\n|  \n \u203E");
       
        put("35", " _\n  |");
        
                
                
    }};
    
    public HashMap<String, String> getMap()
            {
                return cipheredTableMap;
            }
}
