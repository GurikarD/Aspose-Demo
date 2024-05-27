package com.example.aspose.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.aspose.demo.util.Utils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AsponseServiceImplTest
{

    @Autowired
    AsposeServiceImpl asposeServiceImpl;
    
    @Test
    public void testHtmlToPDF() {
        String dir = "/templates/htmltopdf/1";
        String dataDir = Utils.getDataDir(dir);
        asposeServiceImpl.generatePdfFileFromHtml(dataDir, dataDir + "/input.html");
    }
    
    @Test
    public void testPDFToHtml() {
        String dir = "/templates/pdftohtml/1";
        String dataDir = Utils.getDataDir(dir);
        asposeServiceImpl.generateHtmlFromPDF(dataDir, dataDir + "/sample.pdf");
    }
}
