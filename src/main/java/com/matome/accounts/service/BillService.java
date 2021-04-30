package com.matome.accounts.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.matome.accounts.dto.BillDTO;
import com.matome.accounts.model.Address;
import com.matome.accounts.model.Bill;
import com.matome.accounts.repository.AccountRepository;
import com.matome.accounts.repository.BillRepository;
import com.matome.accounts.utils.ResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Service
public class BillService {

    Logger logger = LoggerFactory.getLogger(BillService.class);

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    BillRepository billRepository;


    public ResponseEntity<Object> createBill(BillDTO billDTO) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonObj = objectMapper.writeValueAsString(billDTO);
            Bill bill = objectMapper.readValue(jsonObj, Bill.class);
            Boolean accountExist = accountRepository.existsAccountByAccountNumber(bill.getAccountNumber());
            if (accountExist) {
                billRepository.save(bill);
                return ResponseHandler.generateResponse(HttpStatus.CREATED, true, "Successful");
            } else {
                return ResponseHandler.generateResponse(HttpStatus.BAD_GATEWAY, false, "unSuccessful");
            }
        } catch (JsonProcessingException e) {
        }
        return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, false, "unSuccessful");
    }


    public ResponseEntity<Object> findAccountBillsByAccountNumber(BigInteger accountNumber) {
        List<Bill> bills = billRepository.findByAccountNumber(accountNumber);
        if (bills.isEmpty()) {
            return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, false, "unSuccessful");
        } else {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Successful", bills);
        }
    }
}
