package com.matome.accounts.service;

import org.json.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.matome.accounts.dto.AccountDTO;
import com.matome.accounts.model.Account;
import com.matome.accounts.model.Address;
import com.matome.accounts.model.Bill;
import com.matome.accounts.model.ContactDetail;
import com.matome.accounts.payload.AccountSummaryResponse;
import com.matome.accounts.repository.AccountRepository;
import com.matome.accounts.repository.AddressRepository;
import com.matome.accounts.repository.BillRepository;
import com.matome.accounts.repository.ContactDetailRepository;
import com.matome.accounts.utils.ResponseHandler;
import com.sun.xml.bind.api.TypeReference;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.util.*;

@Service
public class AccountService {

    Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ContactDetailRepository contactDetailRepository;

    @Autowired
    BillRepository billRepository;

    public ResponseEntity<Object> createAccount(AccountDTO accountDTO) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonObj = objectMapper.writeValueAsString(accountDTO);
            Account account = objectMapper.readValue(jsonObj, Account.class);
            Boolean accountExist = accountRepository.existsAccountByAccountNumber(account.getAccountNumber());
            if (!accountExist) {
                accountRepository.save(account);
                return ResponseHandler.generateResponse(HttpStatus.CREATED, true, "Successful");
            } else {
                return ResponseHandler.generateResponse(HttpStatus.BAD_GATEWAY, false, "unSuccessful");
            }

        } catch (JsonProcessingException e) {
        }
        return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, false, "Successful");

    }

    public ResponseEntity<Object> findAccountDetailsByAccountNumber(BigInteger accountNumber) {
        AccountSummaryResponse accountSummaryResponse = getAccountByAccountNumber(accountNumber);
        if (Objects.nonNull(accountSummaryResponse)) {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Successful", accountSummaryResponse);
        } else {
            return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, false, "unSuccessful");
        }
    }

    public ResponseEntity<Object> findAllAccountDetails() throws JSONException {

        List<Account> accounts = accountRepository.findAll();
        if (!accounts.isEmpty()) {
            List<AccountSummaryResponse> accountSummaryResponses = new ArrayList<>();
            accounts.stream().forEach(account -> {
                AccountSummaryResponse accountSummaryEntity = getAccountByAccountNumber(account.getAccountNumber());
                accountSummaryResponses.add(accountSummaryEntity);
            });
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Successful", accountSummaryResponses);
        } else {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Successful", accounts);
        }
    }


    private AccountSummaryResponse getAccountByAccountNumber(BigInteger accountNumber){
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        AccountSummaryResponse accountSummaryResponse = null;

        if (account.isPresent()) {
            List<Bill> bills = billRepository.findByAccountNumber(accountNumber);
            ContactDetail contactDetail = contactDetailRepository.findByAccountNumber(accountNumber);
            Address address = addressRepository.findByAccountNumber(accountNumber);
           accountSummaryResponse = new AccountSummaryResponse(
                    account.get(),
                    address,
                    contactDetail,
                    bills
            );
        }
        return  accountSummaryResponse;
    }

}
