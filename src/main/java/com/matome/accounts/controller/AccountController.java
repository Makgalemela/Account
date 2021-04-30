package com.matome.accounts.controller;


import com.matome.accounts.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;

import static com.matome.accounts.utils.UrlConstants.ACCOUNTSUMMARY;
import static com.matome.accounts.utils.UrlConstants.ALLACCOUNTSUMMARY;

@RestController
public class AccountController {
    Logger logger = LoggerFactory.getLogger(AccountController.class);


    @Autowired
    AccountService accountService;

    @GetMapping(ACCOUNTSUMMARY)
    public ResponseEntity<Object> findAccountSummaryByAccountNumber(@RequestParam(value = "accountNumber") BigInteger accountNumber){
        return  accountService.findAccountDetailsByAccountNumber(accountNumber);
    }
    @GetMapping(ALLACCOUNTSUMMARY)
    public  ResponseEntity<Object> findAllAccountSummaries(){
        return  accountService.findAllAccountDetails();
    }


}
