package com.katabankocr;

/**
 * OCRDigit
 */
public class OCRDigit {

    public static final char INVALID_DIGIT_CHAR = '?';
    
    private String input;

    /**
     * OCRDigit constructor
     * @param input - String containing the scanned OCR digit
     */
    public OCRDigit( String input ) {
        if ( input == null ) {
            throw new IllegalArgumentException( "input parameter cannot be null" );
        }
        this.input = input;
    }

    /**
     * Is the OCR digit input valid?
     * @return boolean
     */
    public boolean isValid() {
        return toChar() != '?';
    }

    /**
     * Convert the OCR digit input to 
     * its equivalent account number char using the
     * OCR digit input's hashcode
     * @return char containing the account number or '?'
     *         if the OCR digit input is not valid
     */
    public char toChar() {
        int code = input.hashCode();
        switch ( code ) {
            case -2021980254:
                return '0';
            case 1511113376:
                return '1';
            case -302713119:
                return '2';
            case -302801439:
                return '3';
            case -91790205:
                return '4';
            case -1966627615:
                return '5';
            case -1966539203:
                return '6';
            case -360985215:
                return '7';
            case -1963798431:
                return '8';
            case -1963886843:
                return '9';
        }
        return INVALID_DIGIT_CHAR;
    }
}
