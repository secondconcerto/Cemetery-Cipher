/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.cementarycipher.oliwia.mlonek.model;

/**
 *
 * @author roza
 */
public class WrongInputException extends Exception{
    
    public String what(){
        return "Your input contains invalid characters or is empty!";
    }
}

    