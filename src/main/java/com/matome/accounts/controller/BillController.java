package com.matome.accounts.controller;


import com.matome.accounts.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;

import static com.matome.accounts.utils.UrlConstants.ACCOUNTBILLS;

@RestController

public class BillController {


    @Autowired
    BillService billService;

    @GetMapping(ACCOUNTBILLS)
    public ResponseEntity<Object> findAccountDetailsByAccountNumber(@PathVariable(value = "accountNumber") BigInteger accountNumber){
        return  billService.findAccountBillsByAccountNumber(accountNumber);
    }


}
