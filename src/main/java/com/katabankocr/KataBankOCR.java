package com.katabankocr;

import java.io.IOException;

/**
 * KataBankOCR
 */
public class KataBankOCR {

    /**
     * @param args
     */
    public static void main( String[] args ) throws IOException {
        if ( args.length == 2 ) {
            OCRFile.convertOCRFileToAccountNumberFile( args[0], args[1] );
            System.out.println( "OCR entry data in " + args[0] + 
                                " converted to account number data in " + args[1] );
        } else {
            System.out.println( "Syntax:  KataBankOCR <input filename> <output filename>" );
        }
    }
}
