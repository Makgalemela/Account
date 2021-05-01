package com.matome.accounts.controller;


import com.matome.accounts.dto.AccountDTO;
import com.matome.accounts.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;

import static com.matome.accounts.utils.UrlConstants.ACCOUNT;
import static com.matome.accounts.utils.UrlConstants.ALLACCOUNTS;


@RestController
public class AccountController {
    Logger logger = LoggerFactory.getLogger(AccountController.class);


    @Autowired
    AccountService accountService;

    @GetMapping(ACCOUNT)
    public ResponseEntity<Object> findAccountSummaryByAccountNumber(@RequestParam(value = "accountNumber") BigInteger accountNumber){
        return  accountService.findAccountDetailsByAccountNumber(accountNumber);
    }

    @GetMapping(ALLACCOUNTS)
    public  ResponseEntity<Object> findAllAccountSummaries(){
        return  accountService.findAllAccountDetails();
    }


    @PostMapping(ACCOUNT)
    public  ResponseEntity<Object> createNewAccount(@RequestBody AccountDTO accountDTO){
        return accountService.createAccount(accountDTO);
    }

}
