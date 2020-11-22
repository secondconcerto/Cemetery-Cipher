/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.cementarycipher.oliwia.mlonek.model;

import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author roza
 */
public class EncodeAlphabetModel {
    
    private HashMap<String, String> cipheredAlphabet = new HashMap<String, String>()
    {{
        put("A", " \u2022|\n \u203E ");
        put("a", " \u2022|\n \u203E");
        put("B", "|\u2022|\n \u203E ");
        put("b", "|\u2022|\n \u203E");
        put("C", "|\u2022\n \u203E");
        put("c", "|\u2022\n \u203E");
        put("D", " _\n \u2022|\n \u203E");
        put("d", " _\n \u2022|\n \u203E");
        put("E", " _\n|\u2022|\n \u203E");
        put("e", " _\n|\u2022|\n \u203E"); 
        put("F", " _\n|\u2022 \n \u203E");
        put("f", " _\n|\u2022 \n \u203E");
        put("G", " _\n \u2022|");
        put("g", " _\n \u2022|");
        put("H", " _\n|\u2022|");
        put("h", " _\n|\u2022|");
        put("I", " _\n|\u2022");
        put("i", " _\n|\u2022");
        put("J", " _\n|\u2022");
        put("j", " _\n|\u2022");

        put("K", " :|\n \u203E ");
        put("k", " :|\n \u203E");
        put("L", "|:|\n \u203E ");
        put("l", "|:|\n \u203E");
        put("M", "|:\n \u203E");
        put("m", "|:\n \u203E");
        put("N", " _\n :|\n \u203E");
        put("n", " _\n :|\n \u203E");
        put("O", " _\n|:|\n \u203E");
        put("o", " _\n|:|\n \u203E"); 
        put("P", " _\n|: \n \u203E");
        put("p", " _\n|: \n \u203E");
        put("Q", " _\n :|");
        put("q", " _\n :|");
        put("R", " _\n|:|");
        put("r", " _\n|:|");
        put("S", " _\n|:");
        put("s", " _\n|:");
        
        put("T", "  |\n \u203E ");
        put("t", "  |\n \u203E");
        put("U", "| |\n \u203E ");
        put("u", "| |\n \u203E");
        put("V", "| \n \u203E");
        put("v", "| \n \u203E");
        put("W", " _\n  |\n \u203E");
        put("w", " _\n  |\n \u203E");
        put("X", " _\n| |\n \u203E");
        put("x", " _\n| |\n \u203E"); 
        put("Y", " _\n|  \n \u203E");
        put("y", " _\n|  \n \u203E");
        put("Z", " _\n  |");
        put("z", " _\n  |");
                
                
    }};
    
    public HashMap<String, String> getMap()
            {
                return cipheredAlphabet;
            }

    
}
