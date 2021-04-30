package com.matome.accounts.service;


import com.fasterxml.jackson.core.JsonProcessingException;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        if (account.isPresent()) {
            List<Bill> bills = billRepository.findByAccountNumber(accountNumber);
            ContactDetail contactDetail = contactDetailRepository.findByAccountNumber(accountNumber);
            Address address = addressRepository.findByAccountNumber(accountNumber);
            AccountSummaryResponse accountSummaryResponse = new AccountSummaryResponse(
                    account.get(),
                    address,
                    contactDetail,
                    bills
            );
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Successful", accountSummaryResponse);
        } else {
            return ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, false, "unSuccessful");
        }
    }

    public ResponseEntity<Object> findAllAccountDetails() {
        List<Account> accounts = accountRepository.findAll();
        if (!accounts.isEmpty()) {
            List<AccountSummaryResponse> accountSummaryResponses = new ArrayList<>();
            accounts.stream().forEach(account -> {
                ResponseEntity accountSummaryResponseEntity = findAccountDetailsByAccountNumber(account.getAccountNumber());
                try {
                    JSONObject accountSummaryResponseInstance = new JSONObject(accountSummaryResponseEntity.toString()).getJSONObject("body");
                    Object data = accountSummaryResponseInstance.get("data");
                    ObjectMapper objectMapper = new ObjectMapper();
                    String jsonObj = objectMapper.writeValueAsString(data.toString());
                    AccountSummaryResponse accountSummaryResponse = objectMapper.readValue(jsonObj, AccountSummaryResponse.class);
                    accountSummaryResponses.add(accountSummaryResponse);

                } catch (JSONException | JsonProcessingException e) { }
            });
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Successful", accountSummaryResponses);
        } else {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Successful", accounts);
        }

    }

}
