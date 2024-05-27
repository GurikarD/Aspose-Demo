package com.example.aspose.demo.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class TemplateServiceImpl implements TemplateService
{
    private static final Logger LOGGER = LogManager.getLogger(TemplateServiceImpl.class);
    
    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public String getHtmlStringFromTemplate(String templatePath)
    {
        final Context ctx = new Context();
        ctx.setVariable("name", "No name");
        ctx.setVariable("imageResourceName", "https://www.w3schools.com/images/lamp.jpg"); // so that we can reference it from HTML

        final String htmlContent = this.templateEngine.process(templatePath, ctx);
        LOGGER.info(htmlContent);
        return htmlContent;
    }
    
}
