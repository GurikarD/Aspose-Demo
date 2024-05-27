package com.example.aspose.demo.service;

public interface AsposeService
{
    /**
     * generates PDF from html path.
     * @param dir String - denotes directory
     * @param htmlPath String
     */
    void generatePdfFileFromHtml(String dir, String htmlPath);
    
    /**
     * generates HTML from pdf file path.
     * @param dir String - denotes directory
     * @param htmlPath String
     */
    void generateHtmlFromPDF(String dir, String pdfPath);
}
