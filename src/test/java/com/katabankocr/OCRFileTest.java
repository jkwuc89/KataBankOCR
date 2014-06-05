package com.katabankocr;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class OCRFileTest {
    
    private static final String TEST_INPUT_FILE_DATA = 
        "    _  _     _  _  _  _  _ \n" + 
        "  | _| _||_||_ |_   ||_||_|\n" + 
        "  ||_  _|  | _||_|  ||_| _|\n" +
        "                           \n" +
        "    _  _     _  _  _  _  _ \n" +
        "| | _| _||_||_ |_   ||_||_|\n" + 
        "| ||_  _|  | _||_|  ||_| _|\n" +
        "                           \n" +
        " _  _  _     _  _  _  _  _ \n" + 
        "| | _| _||_||_ |_   ||_||_|\n" + 
        "|_||_  _|  | _||_|  ||_| _|\n";
    
    private static final String EXPECTED_OUTPUT_FILE_DATA = 
        "123456789\n" +
        "?23456789 ILL\n" +
        "023456789 ERR\n";
    
    private static final String TEST_INPUT_FILENAME = "testinputfile.txt";
    private static final String TEST_OUTPUT_FILENAME = "testoutputfile.txt";
    
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testConvertOCRFileToAccountNumberFile() throws IOException {
        File testInputFile = new File( TEST_INPUT_FILENAME );
        FileUtils.writeStringToFile( testInputFile, TEST_INPUT_FILE_DATA );
        OCRFile.convertOCRFileToAccountNumberFile( TEST_INPUT_FILENAME, TEST_OUTPUT_FILENAME );
        String actualOutputFileData = FileUtils.readFileToString( new File(TEST_OUTPUT_FILENAME) );
        assertEquals( EXPECTED_OUTPUT_FILE_DATA, actualOutputFileData );
    }
    
    @Test
    public void testConvertOCRFileToAccountNumberFileWithNullInputFilename() throws IOException {
        exception.expect( IllegalArgumentException.class );
        exception.expectMessage( "inputFilename parameter cannot be null" );
        OCRFile.convertOCRFileToAccountNumberFile( null, "output.txt" );
    }
    
    @Test
    public void testConvertOCRFileToAccountNumberFileWithMissingInputFilename() throws IOException {
        exception.expect( IllegalArgumentException.class );
        exception.expectMessage( "missingfile.txt does not exist" );
        OCRFile.convertOCRFileToAccountNumberFile( "missingfile.txt", "output.txt" );
    }
    
    @Test
    public void testConvertOCRFileToAccountNumberFileWithNullOutputFilename() throws IOException {
        exception.expect( IllegalArgumentException.class );
        exception.expectMessage( "outputFilename parameter cannot be null" );
        OCRFile.convertOCRFileToAccountNumberFile( "input.txt", null );
    }
}
