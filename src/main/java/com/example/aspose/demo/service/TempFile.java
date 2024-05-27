package com.example.aspose.demo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.example.aspose.demo.constants.FileType;

/**
 * This class helps to create temporary file and self closable file stream.
 *
 * @author dgurikar
 */
public class TempFile implements AutoCloseable
{

    /**
     * Temporary file prefix.
     */
    private static final String FILE_PREFIX = "input_convert";

    /**
     * Path for temporary file.
     */
    private final Path tempPfdFile;

    /**
     * Constructs a TempPfdFile.
     * 
     * @throws IOException
     */
    public TempFile(FileType fileType) throws IOException
    {

        tempPfdFile = Files.createTempFile(FILE_PREFIX, fileType.toString());
    }

    /**
     * This method returns temporary file path.
     * 
     * @return path
     */
    public Path getTempPfdFile()
    {
        return tempPfdFile;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.AutoCloseable#close()
     */
    @Override
    public void close() throws IOException
    {

        Files.deleteIfExists(tempPfdFile);
    }

}
