package com.katabankocr;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 * OCRFile
 */
public class OCRFile {

    /**
     * Construction not necessary
     */
    private OCRFile() {
    }

    /**
     * Convert the specified input file containing OCR entry data
     * to an output file containing account number data
     * @param inputFilename - String containing the input filename
     * @param outputFilename - String containing the output filename
     * @throws IOException
     */
    public static void convertOCRFileToAccountNumberFile( String inputFilename, String outputFilename ) throws IOException {
        if ( inputFilename == null ) {
            throw new IllegalArgumentException( "inputFilename parameter cannot be null" );
        }
        if ( outputFilename == null ) {
            throw new IllegalArgumentException( "outputFilename parameter cannot be null" );
        }
        File inputFile = new File( inputFilename );
        if ( !inputFile.isFile() ) {
            throw new IllegalArgumentException( inputFilename + " does not exist" );
        }
        
        File outputFile = new File( outputFilename );
        if ( outputFile.exists() ) {
            outputFile.delete();
        }
        
        // Each entry in the input file is 4 lines.  The 1st three
        // contain the OCR entry data and the 4th is blank
        String[] entryLines = new String[3];
        List<String> allInputLines = FileUtils.readLines( inputFile );
        String accountNumber;
        for ( int index = 0; ( index + 2 ) < allInputLines.size(); index += 4 ) {
            entryLines[0] = allInputLines.get( index );
            entryLines[1] = allInputLines.get( index + 1 );
            entryLines[2] = allInputLines.get( index + 2 );
            
            accountNumber = OCREntry.convertOCREntryToAccountNumber( entryLines );
            FileUtils.writeStringToFile( outputFile, accountNumber + "\n", true );
        }
    }
}
