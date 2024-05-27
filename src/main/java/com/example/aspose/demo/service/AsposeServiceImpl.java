package com.example.aspose.demo.service;

import org.springframework.stereotype.Service;

import com.aspose.pdf.Document;
import com.aspose.pdf.HtmlLoadOptions;
import com.aspose.pdf.HtmlSaveOptions;
import com.example.aspose.demo.constants.Constants;
import com.example.aspose.demo.util.Utils;

@Service
public class AsposeServiceImpl implements AsposeService
{

    /**
     * {@inheritDoc}
     */
    @Override
    public void generatePdfFileFromHtml(String dir, String htmlPath)
    {
        // Initialize HTMLLoadSave Options
        HtmlLoadOptions options = new HtmlLoadOptions(dir);
        
        Utils.setHtmlOptions(options);
        
        // Set Render to single page property
        // options.setRenderToSinglePage(true);
        // Load document
        Document doc = new Document(htmlPath, options);
        // Save
        String outputfilename = dir + Constants.OUTPUT_PDF_FILE_NAME;
        doc.save(outputfilename);        
        
        doc.close();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void generateHtmlFromPDF(String dir, String pdfPath)
    {
        // Load PDF document
        Document pdfDocument = new Document(pdfPath);
        // Instantiate HtmlSaveOptions instance
        HtmlSaveOptions saveOptions = new HtmlSaveOptions();
        
        // Specify the folder (that exist) to save images during conversion process
        // saveOptions.setSpecialFolderForAllImages(dir + "imageFolder/");
        
        // Save the resultant HTML file
        pdfDocument.save(dir + Constants.OUTPUT_HTML_FILE_NAME, saveOptions);
        
        pdfDocument.close();
    }

}
