package com.github.alexwolfgoncharov.termdata.servlets;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.github.alexwolfgoncharov.termdata.helpers.Transliterator;

@WebServlet("/upload")
@MultipartConfig(fileSizeThreshold=1024*1024*2,		// 2MB
					maxFileSize=1024*1024*10,		// 10MB
					maxRequestSize=1024*1024*50)	// 50MB
public class UploadFiles extends HttpServlet {
 
    /**
     * Name of the directory where uploaded files will be saved, relative to
     * the web application directory.
     */
    private static final String SAVE_DIR = "uploadFiles";
     
    /**
     * handles file upload
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // gets absolute path of the web application
        String appPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + SAVE_DIR;
        StringBuilder str1 = new StringBuilder();
        Gson json = new Gson();
        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        List fileNames = new ArrayList<String>();
        for (Part part : request.getParts()) {
        	String fileName = extractFileName(part);
            part.write(savePath + File.separator + fileName);
            fileNames.add(fileName);
        }
        
        str1.append("{ \"status\" : \"ok\",\"filenames\" :");
        str1.append(json.toJson(fileNames));
        str1.append("}");
        
        
        
        PrintWriter out = response.getWriter();
		out.println(str1.toString());
		out.close();
       
    }
 
    /**
     * Extracts file name from HTTP header content-disposition
     */
    private String extractFileName(Part part) {
    	Transliterator trans = new Transliterator();
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        String a = "";
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return trans.transliterate(s.substring(s.indexOf("=") + 2, s.length()-1));
            }
        }
        
        return a;
    }
    
   
}
