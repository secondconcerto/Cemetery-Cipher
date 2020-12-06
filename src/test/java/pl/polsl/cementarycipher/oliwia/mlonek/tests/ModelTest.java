package pl.polsl.cementarycipher.oliwia.mlonek.tests;


import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.params.provider.CsvSource;
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
     * @throws NoSuchFieldException thrown when an attempt is made to access a field that does not exist
     * @throws SecurityException thrown by the security manager to indicate a security violation.
     * @throws WrongInputException when user input cannot be processed
     * @throws IllegalArgumentException thrown to indicate that a method has been passed an illegal or inappropriate argument.
     * @throws IllegalAccessException thrown when an application tries to set or get a field but the currently executing method does not have access to the definition of the specified class, field, method or constructor.
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
            arguments("a7 B"," \u2022|\n \u203E\n\n\n\n|\u2022|\n \u203E\n\n"),
            arguments("Ąćęcż B"," \u2022|\n \u203E\n\n\n\n|\u2022|\n \u203E\n\n"),
            arguments("    . ??     "," \u2022|\n \u203E\n\n\n\n|\u2022|\n \u203E\n\n"),
            arguments("                 "," \u2022|\n \u203E\n\n\n\n|\u2022|\n \u203E\n\n"),
            arguments(null," \u2022|\n \u203E\n\n|\u2022|\n \u203E\n\n")
     
        
        
        
        );
    }
    
    /** 
     * Checks if encoding function works fine with valid input.
     * @param userInput user input
     * @throws WrongInputException when user input cannot be processed
     */
    @ParameterizedTest
    @CsvSource({"abc", "qwer", "rwu fwu oyw", "aaaaaaaaa"})
    public void testEnocdeValidInput(String userInput) throws WrongInputException
    {
        try {
            model.encodeMessage(userInput);
        } catch (WrongInputException e) {
            fail("An exception should not appear since input is correct!");
        }
    }
    
     /** 
     * Checks if encoding function works fine with invalid input.
     * @param userInput user input
     * @throws WrongInputException when user input cannot be processed
     */
    @ParameterizedTest
    @CsvSource({"123", "ą3ć", "!!! fwu oyw", "aaaaęaaaa"})
    public void testEncodeInvalidInput(String userInput) throws WrongInputException
    {
        try {
            model.encodeMessage(userInput);
            fail("An exception should appear since input is incorrect!");
        } catch (WrongInputException e) {
            
        }
    }
    
    /** 
     * Checks if user message is correctly inspected before decoding when input is valid.
     * @param userInput user input
     * @throws WrongInputException when user input cannot be processed
     */
    @ParameterizedTest
    @CsvSource({"12", "13","34", "35", "10", "11"})
    public void testCheckDecodeValidInput(String userInput) throws WrongInputException
    {
        try {
            model.checkInput(userInput);
        } catch (WrongInputException e) {
            fail("An exception should not appear since input is correct!");
        }
    }
    /** 
     * Checks if user message is correctly inspected before decoding when input is invalid.
     * @param userInput user input
     * @throws WrongInputException when user input cannot be processed
     */
    @ParameterizedTest
    @CsvSource({"1", "1!3","3 6", "3,7", "9", "111", "12."})
    public void testCheckDecodeInvalidInput(String userInput) throws WrongInputException
    {
        try {
            model.checkInput(userInput);
             fail("An exception should appear since input is incorrect!");
        } catch (WrongInputException e) {
           
        }
    }
    
    /** 
     * Checks if user message inside decodingfunction is correctly read when input is valid.
     * @param userInput user input
     * @throws WrongInputException when user input cannot be processed
     */
    @ParameterizedTest
    @MethodSource("stringListProvider4")
    public void testDecodeValidInput(List<String> userInput) throws WrongInputException
    {
        try {
            model.decodeMessage(userInput);
        } catch (WrongInputException e) {
            fail("An exception should not appear since input is correct!");
        }
    }
    
     /** 
     * Function is responsible for passing list and strings to testDecodeValidInput test as the arguments.
     * @return Stream of arguments to be passed to test
     */
     static Stream<Arguments> stringListProvider4() {
        return Stream.of(
            arguments(Arrays.asList(" _\n :|")),
            arguments(Arrays.asList(" \u2022|\n \u203E", " _\n :|")),
            arguments(Arrays.asList(" _\n|\u2022|\n \u203E", "\u2022|\n \u203E")),
            arguments(Arrays.asList(" \u2022|\n \u203E", " \u2022|\n \u203E"))

        );
    }
     
    /** 
     * Checks if user message inside decoding function is correctly read when input is invalid.
     * @param userInput user input
     * @throws WrongInputException when user input cannot be processed
     */
    @ParameterizedTest
   @MethodSource("stringListProvider5")
    public void testDecodeInvalidInput(List <String> userInput) throws WrongInputException
    {
        try {
            model.decodeMessage(userInput);
             fail("An exception should appear since input is incorrect!");
        } catch (WrongInputException e) {
           
        }
    }
     /** 
     * Function is responsible for passing list and strings to testDecodeInvalidInput test as the arguments.
     * @return Stream of arguments to be passed to test
     */
     static Stream<Arguments> stringListProvider5() {
        return Stream.of(
            arguments(Arrays.asList(" ")),
            arguments(Arrays.asList(" "
                    + ""
                    + "")),
            arguments(Arrays.asList(" !")),
            arguments(Arrays.asList(" _\n :|  "))

        );
    }
    
    
    /** 
     * Checks if user message is correctly encoded to Cemetry Cipher.If user message
     * contains incorrect characters or is empty, the exception is excpected to be thrown.
     * 
     * @param userInput user text to be decoded
     * @param cipheredValue variable we expect to get after decoding
     * @throws NoSuchFieldException thrown when an attempt is made to access a field that does not exist
     * @throws SecurityException thrown by the security manager to indicate a security violation.
     * @throws WrongInputException when user input cannot be processed
     * @throws IllegalArgumentException thrown to indicate that a method has been passed an illegal or inappropriate argument.
     * @throws IllegalAccessException thrown when an application tries to set or get a field but the currently executing method does not have access to the definition of the specified class, field, method or constructor.
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
     * @throws NoSuchFieldException thrown when an attempt is made to access a field that does not exist
     * @throws SecurityException thrown by the security manager to indicate a security violation.
     * @throws WrongInputException when user input cannot be processed
     * @throws IllegalArgumentException thrown to indicate that a method has been passed an illegal or inappropriate argument.
     * @throws IllegalAccessException thrown when an application tries to set or get a field but the currently executing method does not have access to the definition of the specified class, field, method or constructor.
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
     * @throws NoSuchFieldException thrown when an attempt is made to access a field that does not exist
     * @throws SecurityException thrown by the security manager to indicate a security violation.
     * @throws WrongInputException when user input cannot be processed
     * @throws IllegalArgumentException thrown to indicate that a method has been passed an illegal or inappropriate argument.
     * @throws IllegalAccessException thrown when an application tries to set or get a field but the currently executing method does not have access to the definition of the specified class, field, method or constructor.
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