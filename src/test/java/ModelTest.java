/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit
.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.commons.util.StringUtils;
import pl.polsl.cementarycipher.oliwia.mlonek.model.CementaryCipherModel;
import pl.polsl.cementarycipher.oliwia.mlonek.model.WrongInputException;
/**
 *
 * @author roza
 */
public class ModelTest {
    
    private CementaryCipherModel model = new CementaryCipherModel();
    
    @ParameterizedTest
    @MethodSource("stringListProvider")
    void testConvertingToString( String[] array, String expectedString) {
        String codeValue = model.convertToString(array);
        assertEquals(expectedString, codeValue, "Something wrong!");
        
    }

    static Stream<Arguments> stringListProvider() {
        return Stream.of(
            arguments(new String[] { "en", "a" , "b"}, "a b"),
            arguments(new String[] {"en", " ","cats", " ", "black"}, "cats black"),
            arguments(new String[] {"en", "world"}, "world"),
            arguments(new String[] {"en", " "}, "")
        );
    }
    
    @ParameterizedTest
    @MethodSource("stringListProvider2")
    public void testEncodingMessage(String userInput, String cipheredValue) throws NoSuchFieldException, 
      SecurityException, IllegalArgumentException, IllegalAccessException
    {
        try {
            model.encodeMessage(userInput);
            Field field = CementaryCipherModel.class.getDeclaredField("encodedValue");
            field.setAccessible(true);
            String fieldValue = (String) field.get(model);
            assertEquals(cipheredValue, fieldValue, "Obtained results cover not only capital letters");
 
            
        } catch (WrongInputException e) {
            
        }
    }
    
    static Stream<Arguments> stringListProvider2() {
        return Stream.of(
            arguments("ab"," \u2022|\n \u203E\n\n|\u2022|\n \u203E\n\n"),
            arguments("AB"," \u2022|\n \u203E\n\n|\u2022|\n \u203E\n\n"),
            arguments("Ab"," \u2022|\n \u203E\n\n|\u2022|\n \u203E\n\n"),
            arguments("aB"," \u2022|\n \u203E\n\n|\u2022|\n \u203E\n\n"),
            arguments("a b"," \u2022|\n \u203E\n\n\n\n|\u2022|\n \u203E\n\n"),
            arguments("A B"," \u2022|\n \u203E\n\n\n\n|\u2022|\n \u203E\n\n"),
            arguments("A b"," \u2022|\n \u203E\n\n\n\n|\u2022|\n \u203E\n\n"),
            arguments("a B"," \u2022|\n \u203E\n\n\n\n|\u2022|\n \u203E\n\n"),
            arguments("a7 B"," \u2022|\n \u203E\n\n\n\n|\u2022|\n \u203E\n\n"),
            arguments("Ąćęcż B"," \u2022|\n \u203E\n\n\n\n|\u2022|\n \u203E\n\n"),
            arguments("    . ??     "," \u2022|\n \u203E\n\n\n\n|\u2022|\n \u203E\n\n"),
            arguments("                 "," \u2022|\n \u203E\n\n\n\n|\u2022|\n \u203E\n\n")
     
        
        
        
        );
    }
    
    @ParameterizedTest
    @MethodSource("stringListProvider3")
    public void testDecodingMessage(List<String> userInput, String cipheredValue) throws NoSuchFieldException, 
      SecurityException, IllegalArgumentException, IllegalAccessException
    {
        try {
            model.decodeMessage( userInput);
            Field field = CementaryCipherModel.class.getDeclaredField("encodedValue");
            field.setAccessible(true);
            String fieldValue = (String) field.get(model);
            assertEquals(cipheredValue, fieldValue, "Obtained results cover not only capital letters");
 
            
        } catch (WrongInputException e) {
            
        }
        
       
    }
    
    static Stream<Arguments> stringListProvider3() {
        return Stream.of(
            arguments(Arrays.asList("\u2022|\n \u203E", "\u2022|\n \u203E"),"ab"),
            arguments(Arrays.asList("\u2022|\n \u203E", "\u2022|\n \u203E"),"AB"),
            arguments(Arrays.asList("\u2022|\n \u203E", "\u2022|\n \u203E"),"aB"),
            arguments(Arrays.asList("\u2022|\n \u203E", "\u2022|\n \u203E"),"AB"),
            arguments(Arrays.asList("\u2022|\n \u203E"," ","\u2022|\n \u203E"),"a b"),
            arguments(Arrays.asList("\u2022|\n \u203E"," ","\u2022|\n \u203E"),"A B"),
            arguments(Arrays.asList("\u2022|\n \u203E"," ","\u2022|\n \u203E"),"a B"),
            arguments(Arrays.asList("\u2022|\n \u203E"," ","\u2022|\n \u203E"),"A b"),
            arguments(Arrays.asList("        "),"a b"),
            arguments(Arrays.asList("ą","ę","\u2022|\n \u203E"),""),
            arguments(Arrays.asList("!?      "),"a b")
//         
        );
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"a", "ab", "ę45!"}) // add "" 
    void testResetEncoded(String candidate) throws NoSuchFieldException, 
      SecurityException, IllegalArgumentException, IllegalAccessException{
        try {
            model.encodeMessage(candidate);
            model.resetEncodedValue();
            Field field = CementaryCipherModel.class.getDeclaredField("encodedValue");
            field.setAccessible(true);
            String fieldValue = (String) field.get(model);
            assertTrue(StringUtils.isBlank(fieldValue), "String is not reseted!");
        } catch (WrongInputException ex) {
           
        }
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"a", "ab", "ąą22"}) // add "" 
    void testResetDecoded(String candidate) throws NoSuchFieldException, 
      SecurityException, IllegalArgumentException, IllegalAccessException{
        try {
            model.decodeMessage(Arrays.asList("candidate"));
            model.resetDecodedValue();
            Field field = CementaryCipherModel.class.getDeclaredField("decodedValue");
            field.setAccessible(true);
            String fieldValue = (String) field.get(model);
            assertTrue(StringUtils.isBlank(fieldValue), "String is not reseted!");
        } catch (WrongInputException ex) {
           
        }
    }

}
