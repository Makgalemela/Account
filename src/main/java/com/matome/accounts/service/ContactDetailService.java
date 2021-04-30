package com.matome.accounts.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.matome.accounts.dto.BillDTO;
import com.matome.accounts.dto.ContactDetailDTO;
import com.matome.accounts.model.Bill;
import com.matome.accounts.model.ContactDetail;
import com.matome.accounts.repository.AccountRepository;
import com.matome.accounts.repository.BillRepository;
import com.matome.accounts.repository.ContactDetailRepository;
import com.matome.accounts.utils.ResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ContactDetailService {


    Logger logger = LoggerFactory.getLogger(ContactDetailService.class);

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ContactDetailRepository contactDetailRepository;

    public ResponseEntity<Object> createAccountContactDetail(ContactDetailDTO contactDetailDTO){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonObj = objectMapper.writeValueAsString(contactDetailDTO);
            ContactDetail  contactDetail= objectMapper.readValue(jsonObj, ContactDetail.class);
            Boolean accountExist = accountRepository.existsAccountByAccountNumber(contactDetail.getAccountNumber());
            if(accountExist){
                contactDetailRepository.save(contactDetail);
                return ResponseHandler.generateResponse(HttpStatus.CREATED, true, "Successful");
            }
            else{
                return ResponseHandler.generateResponse(HttpStatus.BAD_GATEWAY, false, "unSuccessful");
            }
        } catch (JsonProcessingException e) {
            return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, false, "unSuccessful");
        }
    }

    public ResponseEntity<Object> updateAccountContactDetails(ContactDetailDTO contactDetailDTO) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonObj = null;
        try {
            jsonObj = objectMapper.writeValueAsString(contactDetailDTO);
            ContactDetail  contactDetail= objectMapper.readValue(jsonObj, ContactDetail.class);
            Boolean contactDetailsExist = contactDetailRepository.existsByAccountNumber(contactDetail.getAccountNumber());
            if(contactDetailsExist){
                contactDetailRepository.save(contactDetail);
                return ResponseHandler.generateResponse(HttpStatus.OK, true, "Successful");
            }else{
                return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, false, "unSuccessful");
            }

        } catch (JsonProcessingException e) {
            return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, false, "unSuccessful");

        }

    }
}
