package com.example.aspose.demo.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.aspose.demo.constants.Constants;
import com.example.aspose.demo.constants.FileType;
import com.example.aspose.demo.request.TemplateRequest;
import com.example.aspose.demo.service.AsposeService;
import com.example.aspose.demo.service.TempFile;
import com.example.aspose.demo.service.TemplateService;

@RestController
@RequestMapping("/pdf")
public class AsposeController
{

    /**
     * Private logger field.
     */
    private static final Logger LOGGER = LogManager.getLogger(AsposeController.class);
    
    /**
     * Private string state that Not able to generate PDF.
     */
    private static final String STR_NOT_ABLE_TO_GENERATE_PDF = "Not able to generate PDF";
    
    /** Private field that CACHE_CONTROL. */
    private static final String CACHE_CONTROL = "Cache-Control";
    
    /** Private field that CONTENT_DISPOSITION. */
    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    
    @Autowired
    private AsposeService asposeService;
    
    @Autowired
    private TemplateService templateService;
    
    @GetMapping(path = "/generate", consumes =
    { MediaType.TEXT_HTML_VALUE })
    public ResponseEntity<InputStreamResource> genaratePdf(@RequestBody final String payload) throws Exception
    {
        return generatePDFFromHtmlString(payload);
    }
    
    @GetMapping(path = "/generate/template", consumes =
    { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<InputStreamResource> genaratePdfFromHtmlTemplate(@RequestBody final TemplateRequest request) throws Exception
    {
        String htmlPayload = templateService.getHtmlStringFromTemplate(request.getPath());
        return generatePDFFromHtmlString(htmlPayload);
    }
    
    private ResponseEntity<InputStreamResource> generatePDFFromHtmlString(String htmlPayload) throws Exception {
        try (TempFile  htmlFile= new TempFile(FileType.HTML))
        {
            Path htmlFilePath = htmlFile.getTempPfdFile();
            Files.write(htmlFilePath, htmlPayload.getBytes(StandardCharsets.UTF_8));
            
            String htmlPath = htmlFilePath.toFile().getAbsolutePath();
            String dir = htmlFilePath.toFile().getParent();
            LOGGER.info("Temporary HTML file is created at path: " + htmlPath);
            
            Path pdfFilePath = Paths.get(dir + Constants.OUTPUT_PDF_FILE_NAME);
            asposeService.generatePdfFileFromHtml(dir, htmlPath);
            
            if (Files.exists(pdfFilePath))
            {

                final String fileName = String.valueOf(pdfFilePath.getFileName());
                final long fileSize = Files.size(pdfFilePath);
                final HttpHeaders headers = getResponseHeader(fileName, fileSize);

                return new ResponseEntity<InputStreamResource>(
                    new InputStreamResource(Files.newInputStream(pdfFilePath)), headers, HttpStatus.OK);
            }

        }
        catch (final IOException e)
        {
            throw new Exception(STR_NOT_ABLE_TO_GENERATE_PDF);
        }
        return null;
    }
    
    /**
     * This method set HTTP headers in response.
     *
     * @param fileName Name of the file
     * @param length Total file size
     * @return HttpHeaders
     */
    private HttpHeaders getResponseHeader(final String fileName, final long length)
    {

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
        headers.add("Access-Control-Allow-Headers", "Content-Type");
        headers.add(CONTENT_DISPOSITION, "filename=" + fileName);
        headers.add(CACHE_CONTROL, "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.setContentLength(length);

        return headers;
    }
    
}
