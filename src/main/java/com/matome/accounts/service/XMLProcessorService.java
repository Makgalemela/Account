package com.matome.accounts.service;


import com.matome.accounts.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.ws.Action;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

@Service
public class XMLProcessorService {

    Logger logger = LoggerFactory.getLogger(XMLProcessorService.class);

    @Autowired
    AccountService accountService;

    @Autowired
    AddressService addressService;

    @Autowired
    BillService billService;

    @Autowired
    ContactDetailService contactDetailService;

    private ResponseEntity<Object> processXMLFile(File XMLFile) throws ParserConfigurationException, IOException, SAXException {


        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(XMLFile);
        document.getDocumentElement().normalize();

        NodeList accountList = document.getElementsByTagName("account");

        for (int itr = 0; itr < accountList.getLength(); itr++) {
            Node node = accountList.item(itr);
            String accountNumber = null;
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                accountNumber = element.getElementsByTagName("accountNumber").item(0).getTextContent();
                AccountDTO accountDTO = new AccountDTO(
                        element.getElementsByTagName("accountNumber").item(0).getTextContent(),
                        element.getElementsByTagName("accountHolderName").item(0).getTextContent(),
                        element.getElementsByTagName("accountHolderIDNumber").item(0).getTextContent()
                );
                accountService.createAccount(accountDTO);
                Node accountHolderDetailsNode = element.getElementsByTagName("contactDetails").item(0);
                if (accountHolderDetailsNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element accountHolderDetailsElement = (Element) accountHolderDetailsNode;
                    ContactDetailDTO contactDetailDTO =
                            new ContactDetailDTO(accountHolderDetailsElement.getElementsByTagName("mobile").item(0).getTextContent(),
                                    accountHolderDetailsElement.getElementsByTagName("home").item(0).getTextContent(),
                                    accountHolderDetailsElement.getElementsByTagName("work").item(0).getTextContent(),
                                    accountNumber);
                    contactDetailService.createAccountContactDetail(contactDetailDTO);
                }

                NodeList addressList = element.getElementsByTagName("address");
                for (int it = 0; it < addressList.getLength(); it++) {
                    Node addressNode = addressList.item(it);
                    if (addressNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element addressElement = (Element) addressNode;
                        AddressDTO addressDTO =
                                new AddressDTO(
                                        addressElement.getElementsByTagName("line1").item(0).getTextContent()
                                        , addressElement.getElementsByTagName("line2").item(0).getTextContent()
                                        , addressElement.getElementsByTagName("line3").item(0).getTextContent()
                                        , addressElement.getElementsByTagName("postalCode").item(0).getTextContent(),
                                        false, accountNumber);

                        addressService.createAccountAddress(addressDTO);

                    }
                }

                NodeList billList = element.getElementsByTagName("bill");
                for (int it = 0; it < billList.getLength(); it++) {
                    Node listNode = billList.item(it);
                    if (listNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element billElement = (Element) listNode;
                        BillDTO billDTO =
                                new BillDTO(
                                        billElement.getElementsByTagName("billDate").item(0).getTextContent(),
                                        billElement.getElementsByTagName("charges").item(0).getTextContent(),
                                        billElement.getElementsByTagName("outstanding").item(0).getTextContent(),
                                        billElement.getElementsByTagName("dueDate").item(0).getTextContent(),
                                        accountNumber,
                                        billElement.getElementsByTagName("period").item(0).getTextContent());
                        billService.createBill(billDTO);

                    }
                }

            }

        }
        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity<Object> uploadFile(MultipartFile multipartFile) throws IOException, ParserConfigurationException, SAXException {
        File file = new File(multipartFile.getOriginalFilename());
        file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(multipartFile.getBytes());
        fileOutputStream.close();

        return processXMLFile(file);
    }
}

