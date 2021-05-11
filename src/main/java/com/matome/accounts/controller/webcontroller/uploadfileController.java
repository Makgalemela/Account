package com.matome.accounts.controller.webcontroller;


import com.matome.accounts.controller.restcontroller.FileProcessor;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.servlet.http.Part;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Scope(value = "session")
@Component(value = "uploadfileController")
@ELBeanName(value = "uploadfileController")
@Join(path = "/upload-file", to = "/upload-form.jsf")
public class uploadfileController {


    private Part uploadedFile;
    private String fileName;
    private byte[] fileContents;


    public Part getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    @Autowired
    FileProcessor fileProcessor;


    public String processFiles() throws ParserConfigurationException, SAXException, IOException {
        fileProcessor.fileUpload((MultipartFile) uploadedFile);
        return "/summary-view?faces-redirect=true";
    }

}
