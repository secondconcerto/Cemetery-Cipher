/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.stream.Stream;
import org.junit.jupiter.api.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import pl.polsl.cementarycipher.oliwia.mlonek.model.CementaryCipherModel;
/**
 *
 * @author roza
 */
public class ModelTest {
    
    private CementaryCipherModel model;
    
    
    
//    @ParameterizedTest
//    @CsvSource({"I,like,cats,a,lot,Ilikecatsalot", "Hello,' ',world, Hello World", "I , Want, to, sleep,I Want to sleep" })
//    public void testConvertingToString( String input1, String input2, String input3, String input4, String outputString)
//    {
//    }
    
    @ParameterizedTest
    @MethodSource("stringListProvider")
    void testConvertingToString( String[] array, String str) {
        
    }

    static Stream<Arguments> stringListProvider() {
        return Stream.of(
            arguments(new String[] { "a" , "b"}, "Ilike"),
            arguments(new String[] {"My", " ","cats", " ", "black"}, "My cats black"),
            arguments(new String[] {"Hello", "world"}, "Hello        world")
        );
    }
    
    @Test
    public void testDecodingMessage()
    {
//        try {
//            
//        } catch (WrongInputException e) {
//        }
    }
    
     @Test
    public void testEncodingMessage()
    {
//        try {
//            
//        } catch (WrongInputException e) {
//        }
        
       
    }
    
    @ParameterizedTest
    @CsvSource({"SomeMessage,''", "Message2,Something"})
    public void testresetEncodedValue()
    {
    }
    
    @ParameterizedTest
    @CsvSource({"SomeMessage,''","Message2,Something"})
    public void resetDecodedValue()
    {
    }
}
