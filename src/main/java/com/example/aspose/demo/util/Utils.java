package com.example.aspose.demo.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.aspose.pdf.HtmlLoadOptions;
import com.aspose.pdf.LoadOptions;

public class Utils
{
    private static File dir = null;
    
    private static List<String> resourceUrls = Arrays.asList("w3schools");

    public static String getDataDir(String testID) {
        return getSharedDataDir() + testID;
    }

    public static String getOutDir(String testID) {
        return getSharedDataDir() + "../../../testout/" + testID;
    }

    public static String getSharedDataDir() {
        if (dir == null) {
            dir = new File(dir, "src/main/resources/");
        }
        
        return dir.toString() + File.separator;
    }
    
    public static void setHtmlOptions(HtmlLoadOptions htmlLoadOptions) {
        htmlLoadOptions.setCustomLoaderOfExternalResources(new LoadOptions.ResourceLoadingStrategy() {
            public LoadOptions.ResourceLoadingResult invoke(String resourceURI) {
                // Creating clear template resource for replacing:
                LoadOptions.ResourceLoadingResult res = new LoadOptions.ResourceLoadingResult(getImageFromUrl(resourceURI));
                // Return empty byte array in case i.imgur.com server
                if (resourceUrls.stream().anyMatch(resourceUrl -> resourceURI.contains(resourceUrl))) {
                    return res;
                } else {
                    // Process resources with default resource loader
                    res.setLoadingCancelled(Boolean.TRUE);
                    return res;
                }
            }
        });
    }
    
    private static byte[] getImageFromUrl(String url) {
        try
        {
            byte[] fileContent = IOUtils.toByteArray(new URL(url));
            return fileContent;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return new byte[]{};
    }
}
