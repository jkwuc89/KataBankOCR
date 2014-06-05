package com.katabankocr;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * OCREntryTest
 */
public class OCREntryTest {
    
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testConvertValidOCREntryToAccountNumber() {
        // This OCR entry contains a valid account number
        String[] entryLines = new String[] {
            "    _  _     _  _  _  _  _ ", 
            "  | _| _||_||_ |_   ||_||_|", 
            "  ||_  _|  | _||_|  ||_| _|"
        };
        assertEquals( "123456789", OCREntry.convertOCREntryToAccountNumber( entryLines ) );
    }

    @Test
    public void testConvertOCREntryWithIllegalCharsToAccountNumber() {
        // This OCR entry contains an illegal character
        String[] entryLines = new String[] {
            "    _  _     _  _  _  _  _ ", 
            "| | _| _||_||_ |_   ||_||_|", 
            "| ||_  _|  | _||_|  ||_| _|"
        };
        assertEquals( "?23456789" + OCREntry.ENTRY_CONTAINS_ILLEGAL_CHARS,
                      OCREntry.convertOCREntryToAccountNumber( entryLines ) );
    }

    @Test
    public void testConvertOCREntryWithInvalidAccountNumberToAccountNumber() {
        // This OCR entry contains an invalid account number
        String[] entryLines = new String[] {
            " _  _  _     _  _  _  _  _ ", 
            "| | _| _||_||_ |_   ||_||_|", 
            "|_||_  _|  | _||_|  ||_| _|"
        };
        assertEquals( "023456789" + OCREntry.ENTRY_IS_INVALID_ACCT_NUMBER,
                      OCREntry.convertOCREntryToAccountNumber( entryLines ) );
    }
    
    @Test
    public void testConvertOCREntryWithInvalidNumOfLinesToAccountNumber() {
        // Entry with less than 3 lines throws IllegalArgumentException
        exception.expect( IllegalArgumentException.class );
        exception.expectMessage( "OCR entry data does not contain " +
                                 OCREntry.VALID_ENTRY_LENGTH_IN_LINES + " lines" );
        
        // This OCR entry contains an invalid number of lines
        String[] entryLines = new String[] {
            "| | _| _||_||_ |_   ||_||_|", 
            "|_||_  _|  | _||_|  ||_| _|"
        };
        OCREntry.convertOCREntryToAccountNumber( entryLines );
    }

    @Test
    public void testConvertOCREntryWithNullInputToAccountNumber() {
        // Entry with less than 3 lines throws IllegalArgumentException
        exception.expect( IllegalArgumentException.class );
        exception.expectMessage( "entryLines parameter cannot be null" );
        OCREntry.convertOCREntryToAccountNumber( null );
    }
}
