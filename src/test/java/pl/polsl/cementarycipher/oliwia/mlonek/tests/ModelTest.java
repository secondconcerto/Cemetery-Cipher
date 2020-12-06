package pl.polsl.cementarycipher.oliwia.mlonek.tests;


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
 * ModelTest Class runs on a few tests to validate the functions contained 
 * in the CementaryCipherModel file, such as: converting list to string, encoding
 * and decoding user message and reseting variables that stores ouput messages.
 * 
 * @author Oliwia Mlonek
 * @version 2.0
 */
public class ModelTest {
    
     /** model used to perform actions */
    private CementaryCipherModel model = new CementaryCipherModel();
    
    /** 
     * Checks if list of strings is correctly converted to single string.
     * @param array user input
     * @param expectedString variable we expect to get at the end 
     */
    @ParameterizedTest
    @MethodSource("stringListProvider")
    void testConvertingToString( List<String> array, String expectedString) {
        String codeValue = model.convertToString(array);
        assertEquals(expectedString, codeValue, "Something wrong, conversion failed!");
        
    }

    /** 
     * Function is responsible for passing list and string to testConvertingToString test as the arguments.
     * @return Stream of arguments to be passed to test
     */
    static Stream<Arguments> stringListProvider() {
        return Stream.of(
            arguments(Arrays.asList( "en", "a" , "b"), "a b"),
            arguments(Arrays.asList("en", " ","cats", " ", "black"), "cats black"),
            arguments(Arrays.asList("en", "world"), "world"),
            arguments(Arrays.asList("en", " "), ""),
            arguments(Arrays.asList("", ""), ""),
            arguments(Arrays.asList(""), "")
           
        );
    }
    
    /** 
     * Checks if user message is correctly encoded to Cemetry Cipher.If user message
 contains incorrect characters or is empty, the exception is excpected to be thrown.
     * 
     * @param userInput user text to be encoded
     * @param cipheredValue variable we expect to get after encoding
     * @throws java.lang.NoSuchFieldException thrown when an attempt is made to access a field that does not exist
     * @throws WrongInputException when user input cannot be processed
     * @throws java.lang.IllegalAccessException thrown when an application tries to set or get a field but the currently executing method does not have access to the definition of the specified class, field, method or constructor.
     */
    @ParameterizedTest
    @MethodSource("stringListProvider2")
    public void testEncodingMessage(String userInput, String cipheredValue) throws NoSuchFieldException, 
      SecurityException, IllegalArgumentException, IllegalAccessException, WrongInputException
    {
        try {
            model.encodeMessage(userInput);
            Field field = CementaryCipherModel.class.getDeclaredField("encodedValue");
            field.setAccessible(true);
            String fieldValue = (String) field.get(model);
            assertEquals(cipheredValue, fieldValue, "Something wrong, encoding failed!");
 
            
        } catch (WrongInputException e) {
            
        }
    }
    
    /** 
     * Function is responsible for passing strings to testEncodingMessage test as the arguments.
     * @return Stream of arguments to be passed to test
     */
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
            arguments("a B"," \u2022|\n \u203E\n\n\n\n|\u2022|\n \u203E\n\n"),
            arguments("Ąćęcż B"," \u2022|\n \u203E\n\n\n\n|\u2022|\n \u203E\n\n"),
            arguments("    . ??     "," \u2022|\n \u203E\n\n\n\n|\u2022|\n \u203E\n\n"),
            arguments("                 "," \u2022|\n \u203E\n\n\n\n|\u2022|\n \u203E\n\n"),
            arguments(null," \u2022|\n \u203E\n\n|\u2022|\n \u203E\n\n")
     
        
        
        
        );
    }
    
    
    /** 
     * Checks if user message is correctly encoded to Cemetry Cipher.If user message
     * contains incorrect characters or is empty, the exception is excpected to be thrown.
     * 
     * @param userInput user text to be decoded
     * @param cipheredValue variable we expect to get after decoding
     * @throws java.lang.NoSuchFieldException thrown when an attempt is made to access a field that does not exist
     * @throws WrongInputException when user input cannot be processed
     * @throws java.lang.IllegalAccessException thrown when an application tries to set or get a field but the currently executing method does not have access to the definition of the specified class, field, method or constructor.
     */
    @ParameterizedTest
    @MethodSource("stringListProvider3")
    public void testDecodingMessage(List<String> userInput, String cipheredValue) throws NoSuchFieldException, 
      SecurityException, IllegalArgumentException, IllegalAccessException, WrongInputException
    {
        try {
            model.decodeMessage(userInput);
            Field field = CementaryCipherModel.class.getDeclaredField("encodedValue");
            field.setAccessible(true);
            String fieldValue = (String) field.get(model);
            assertEquals(cipheredValue, fieldValue, "Something wrong, decoding failed!");
 
            
        } catch (WrongInputException e) {
            
        }
        
       
    }
    
    /** 
     * Function is responsible for passing list and stringsto testDecodingMessage test as the arguments.
     * @return Stream of arguments to be passed to test
     */
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
            arguments(Arrays.asList(""),"a b"),
            arguments(Arrays.asList("ą","ę","\u2022|\n \u203E"),"a b"),
            arguments(Arrays.asList("!?      "),"a b"),
            arguments(null,"a b")
//         
        );
    }
    
    /** 
     * Checks if private field in model class, that stores the encoded message
     * is correctly cleaned before starting next operation.
     * @param candidate exemplary value holded by the field
     * @throws java.lang.NoSuchFieldException thrown when an attempt is made to access a field that does not exist
     * @throws WrongInputException when user input cannot be processed
     * @throws java.lang.IllegalAccessException thrown when an application tries to set or get a field but the currently executing method does not have access to the definition of the specified class, field, method or constructor.
     */
    @ParameterizedTest
    @ValueSource(strings = {"a", "ab", "ę45!"}) 
    void testResetEncoded(String candidate) throws NoSuchFieldException, 
      SecurityException, IllegalArgumentException, IllegalAccessException, WrongInputException
    {
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
    
    /** 
     * Checks if private field in model class, that stores the decoded message
     * is correctly cleaned before starting next operation.
     * @param candidate exemplary value holded by the field
     * @throws java.lang.NoSuchFieldException thrown when an attempt is made to access a field that does not exist
     * @throws WrongInputException when user input cannot be processed
     * @throws java.lang.IllegalAccessException thrown when an application tries to set or get a field but the currently executing method does not have access to the definition of the specified class, field, method or constructor.
     */
    @ParameterizedTest
    @ValueSource(strings = {"a", "ab", "ąą22"}) 
    void testResetDecoded(String candidate) throws NoSuchFieldException, 
      SecurityException, IllegalArgumentException, IllegalAccessException,WrongInputException
    {
        try {
            model.decodeMessage(Arrays.asList(candidate));
            model.resetDecodedValue();
            Field field = CementaryCipherModel.class.getDeclaredField("decodedValue");
            field.setAccessible(true);
            String fieldValue = (String) field.get(model);
            assertTrue(StringUtils.isBlank(fieldValue), "String is not reseted!");
        } catch (WrongInputException ex) {
           
        }
    }

}