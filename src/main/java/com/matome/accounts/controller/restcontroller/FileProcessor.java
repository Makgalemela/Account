package com.matome.accounts.controller.restcontroller;


import com.matome.accounts.service.XMLProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static com.matome.accounts.utils.UrlConstants.UPLOADFILE;

@RestController
public class FileProcessor {

    @Autowired
    XMLProcessorService xmlProcessorService;

    @PostMapping(UPLOADFILE)
    public ResponseEntity<Object> fileUpload(@RequestParam("file") MultipartFile file) throws IOException, ParserConfigurationException, SAXException {
        return xmlProcessorService.uploadFile(file);
    }




}
