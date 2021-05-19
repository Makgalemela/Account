package com.matome.accounts.controller.webcontroller;


import com.matome.accounts.controller.restcontroller.AccountController;
import com.matome.accounts.payload.AccountSummaryResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Scope(value = "session")
@Component(value = "DetailedSummaryController")
@ELBeanName(value = "DetailedSummaryController")
@Join(path = "/detailed-view", to = "/detailed-view.jsf")
@Slf4j
public class DetailedSummaryController {

    Logger logger = LoggerFactory.getLogger(DetailedSummaryController.class);


    @Autowired
    AccountController accountController;


    private AccountSummaryResponse summaryResponse = null;

    public String accountDetailedSummary(BigInteger accountNumber){
        loadAccountSummary(accountNumber);
        return "/detailed-view?faces-redirect=true";
    }

    private   void  loadAccountSummary(BigInteger accountNumber){
        ResponseEntity<Object> accountSummary =  accountController.findAccountSummaryByAccountNumber(accountNumber);
        ResponseEntity<Object> response  =  accountController.findAllAccountSummaries();
        JSONObject accountSummaryResponseInstance = new JSONObject(accountSummary).getJSONObject("body");
        Object data = accountSummaryResponseInstance.get("data");
        logger.info("Data ==> "+data);

    }

    public  AccountSummaryResponse getSummaryResponse(){
        return summaryResponse;
    }

}
