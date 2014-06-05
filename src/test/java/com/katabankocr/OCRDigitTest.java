package com.katabankocr;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.katabankocr.OCRDigit;

/**
 * OCRDigitTest
 */
public class OCRDigitTest {

    // Test OCR digits
    private final String ZERO = 
    " _ " +
    "| |" +
    "|_|";

    private final String ONE = 
    "   " + 
    "  |" + 
    "  |";

    private final String TWO = 
    " _ " + 
    " _|" + 
    "|_ ";

    private final String THREE = 
    " _ " + 
    " _|" + 
    " _|";

    private final String FOUR = 
    "   " +
    "|_|" + 
    "  |";

    private final String FIVE = 
    " _ " + 
    "|_ " + 
    " _|";

    private final String SIX = 
    " _ " + 
    "|_ " + 
    "|_|";

    private final String SEVEN = 
    " _ " + 
    "  |" + 
    "  |";

    private final String EIGHT = 
    " _ " + 
    "|_|" + 
    "|_|";

    private final String NINE = 
    " _ " + 
    "|_|" + 
    " _|";

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testValidDigits() {
        testValidDigitInput( ZERO,  '0' );
        testValidDigitInput( ONE,   '1' );
        testValidDigitInput( TWO,   '2' );
        testValidDigitInput( THREE, '3' );
        testValidDigitInput( FOUR,  '4' );
        testValidDigitInput( FIVE,  '5' );
        testValidDigitInput( SIX,   '6' );
        testValidDigitInput( SEVEN, '7' );
        testValidDigitInput( EIGHT, '8' );
        testValidDigitInput( NINE,  '9' );
    }
    
    @Test
    public void testInvalidDigit() {
        OCRDigit invalidDigit = new OCRDigit( "BAD" );
        assertFalse( invalidDigit.isValid() );
        assertEquals( OCRDigit.INVALID_DIGIT_CHAR, invalidDigit.toChar() );
    }
    
    @Test( expected = IllegalArgumentException.class )
    public void testExpectedConstructorException() {
        new OCRDigit( null );
    }
    

    /**
     * Test valid OCR digit input
     * @param input - OCR digit string input to test
     * @param expected - Expected account number character for the input
     */
    private void testValidDigitInput( String input, char expected ) {
        OCRDigit digit = new OCRDigit( input );
        assertTrue( digit.isValid() );
        assertEquals( expected, digit.toChar() );
    }
}
