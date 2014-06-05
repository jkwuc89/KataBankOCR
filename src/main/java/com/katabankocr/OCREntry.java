package com.katabankocr;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

/**
 * OCREntry
 */
public class OCREntry {
    
    public static final int ACCOUNT_NUMBER_LENGTH = 9;
    public static final int DIGIT_WIDTH = 3;
    public static final int VALID_ENTRY_LENGTH_IN_LINES = 3;
    
    public static final String ENTRY_CONTAINS_ILLEGAL_CHARS = " ILL";
    public static final String ENTRY_IS_INVALID_ACCT_NUMBER = " ERR";

    /**
     * Construction of this class is not necessary
     */
    private OCREntry() {}

    /**
     * Get an OCR digit from the entry lines
     * @param String[] - OCR entry lines from which the digit is extracted 
     * @param index - which digit to get
     * @return OCRDigit
     */
    private static OCRDigit getDigit( String[] entryLines, int index ) {
        OCRDigit digit = new OCRDigit( entryLines[0].substring( index * DIGIT_WIDTH,
                                                                (index * DIGIT_WIDTH) + DIGIT_WIDTH ) +
                                       entryLines[1].substring( index * DIGIT_WIDTH,
                                                                (index * DIGIT_WIDTH) + DIGIT_WIDTH ) +
                                       entryLines[2].substring( index * DIGIT_WIDTH,
                                                                (index * DIGIT_WIDTH) + DIGIT_WIDTH ) );
        return digit;
    }
    
    /**
     * Convert the OCR digits inside String array into
     * an account number.  The returned account number will
     * include ILL if it contains input contains illegal chars.
     * The returned account number will include ERR if the 
     * converted account number is invalid
     * 
     * @param String[] entryLines - String array containing the OCR entry to convert 
     * @return String containing the account number
     */
    public static String convertOCREntryToAccountNumber( String[] entryLines ) {
        if ( entryLines == null ) {
            throw new IllegalArgumentException( "entryLines parameter cannot be null" );
        }
        if ( entryLines.length != VALID_ENTRY_LENGTH_IN_LINES ) {
            throw new IllegalArgumentException( "OCR entry data does not contain " +
                                                OCREntry.VALID_ENTRY_LENGTH_IN_LINES + " lines" );
        }
        
        // Break the entry lines into individual OCR digits
        ArrayList<OCRDigit> digits = new ArrayList<OCRDigit>();
        for ( int index = 0; index < ACCOUNT_NUMBER_LENGTH; index++ ) {
            digits.add( getDigit( entryLines, index ) );
        }

        // Convert the digits into the account number
        StringBuilder result = new StringBuilder( ACCOUNT_NUMBER_LENGTH );
        for ( OCRDigit digit : digits ) {
            result.append( digit.toChar() );
        }

        // Append the appropriate marker if the account number contains
        // illegal chars or the account number is invalid
        if ( StringUtils.contains( result, OCRDigit.INVALID_DIGIT_CHAR ) ) {
            result.append( ENTRY_CONTAINS_ILLEGAL_CHARS );
        } else if ( !isValidAccountNumber( result.toString() ) ) {
            result.append( ENTRY_IS_INVALID_ACCT_NUMBER );
        }

        return result.toString();
    }

    /**
     * Is the converted account number valid?  This is checked by
     * using the following checksum calculation:
     * 
     * account number:  3  4  5  8  8  2  8  6  5
     * position names:  d9 d8 d7 d6 d5 d4 d3 d2 d1
     * checksum calculation:
     * (d1+2*d2+3*d3 +..+9*d9) mod 11 = 0
     * 
     * @return boolean
     */
    private static boolean isValidAccountNumber( String accountNumber ) {
        char[] accountChars = accountNumber.toCharArray();
        int checksum = 0;
        for ( int position = 1; position <= ACCOUNT_NUMBER_LENGTH; position++ ) {
            int value = Integer.parseInt( Character.toString( accountChars[9 - position] ) );
            checksum += value * position;
        }
        return ( ( checksum % 11 ) == 0 );
    }
}
