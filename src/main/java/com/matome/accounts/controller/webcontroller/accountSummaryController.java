package com.matome.accounts.controller.webcontroller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.matome.accounts.controller.restcontroller.AccountController;
import com.matome.accounts.payload.AccountSummaryResponse;
import com.matome.accounts.service.BillService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Scope(value = "session")
@Component(value = "accountSummaryController")
@ELBeanName(value = "accountSummaryController")
@Join(path = "/summary-view", to = "/summary-view.jsf")
@Slf4j
public class accountSummaryController {

    Logger logger = LoggerFactory.getLogger(accountSummaryController.class);

    @Autowired
    AccountController accountController;

    private List<AccountSummaryResponse> summaryResponses = new ArrayList<>();

    @Deferred
    @RequestAction
    @IgnorePostback
    public  void loadAccountSummary() throws Exception {
        summaryResponses.clear();
        ResponseEntity<Object> response  =  accountController.findAllAccountSummaries();
        JSONObject accountSummaryResponseInstance = new JSONObject(response).getJSONObject("body");
        Object data = accountSummaryResponseInstance.get("data");
        JSONArray array = new JSONArray(data.toString());
        for(int itr = 0; itr < array.length() ; itr++){
            ObjectMapper objectMapper = new ObjectMapper();
            AccountSummaryResponse summaryResponse = objectMapper.readValue(array.get(itr).toString() ,AccountSummaryResponse.class);
            summaryResponses.add(summaryResponse);
        }

    }


    public  List<AccountSummaryResponse> getSummaryResponses(){
        return  summaryResponses;
    }
}
