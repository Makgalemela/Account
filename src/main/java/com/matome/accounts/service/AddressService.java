package com.matome.accounts.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.matome.accounts.dto.AddressDTO;
import com.matome.accounts.model.Address;
import com.matome.accounts.repository.AccountRepository;
import com.matome.accounts.repository.AddressRepository;
import com.matome.accounts.utils.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddressService {


    @Autowired
    AddressRepository addressRepository;

    @Autowired
    AccountRepository accountRepository;

    public ResponseEntity<Object> createAccountAddress(AddressDTO addressDTO){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonObj = objectMapper.writeValueAsString(addressDTO);
            Address address = objectMapper.readValue(jsonObj, Address.class);
            Boolean accountExist = accountRepository.existsAccountByAccountNumber(address.getAccountNumber());
            if(accountExist){
                addressRepository.save(address);
                return ResponseHandler.generateResponse(HttpStatus.CREATED, true, "Successful");
            }
            else{
                return ResponseHandler.generateResponse(HttpStatus.BAD_GATEWAY, false, "unSuccessful");
            }
        } catch (JsonProcessingException e) {
            return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, false, "unSuccessful");
        }
    }


    public ResponseEntity<Object> updateAccountAddressDetails(AddressDTO addressDTO) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonObj = objectMapper.writeValueAsString(addressDTO);
            Address address = objectMapper.readValue(jsonObj, Address.class);
            Boolean addressExist = addressRepository.existsByAccountNumber(address.getAccountNumber());
            if(addressExist){
                addressRepository.save(address);
                return ResponseHandler.generateResponse(HttpStatus.OK, true, "Successful");
            } else{
                return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, false, "unSuccessful");
            }
        } catch (JsonProcessingException e) {
            return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, false, "unSuccessful");
        }
    }
}
