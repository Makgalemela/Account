package com.matome.accounts.controller.webcontroller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.matome.accounts.dto.AddressDTO;
import com.matome.accounts.dto.ContactDetailDTO;
import com.matome.accounts.model.Account;
import com.matome.accounts.model.ContactDetail;
import com.matome.accounts.payload.AccountSummaryResponse;
import com.matome.accounts.service.AccountService;
import com.matome.accounts.service.AddressService;
import com.matome.accounts.service.ContactDetailService;
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
@Component(value = "EditAccountController")
@ELBeanName(value = "EditAccountController")
@Join(path = "/edit-account", to = "/edit-account.jsf")

public class EditAccountController {
    Logger logger = LoggerFactory.getLogger(EditAccountController.class);


    @Autowired
    AccountService accountService;

    @Autowired
    AddressService addressService;

    @Autowired
    ContactDetailService contactDetailService;


    private AddressDTO editAddress = null;

    private ContactDetailDTO editContacts = null;


    public String getAccountAddressAndContact(BigInteger accountNumber) throws JsonProcessingException {

        ResponseEntity<Object> accountInfo = accountService.findAccountDetailsByAccountNumber(accountNumber);
        JSONObject object = new JSONObject(accountInfo).getJSONObject("body").getJSONObject("data");
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        AccountSummaryResponse accountSummaryResponse = objectMapper.readValue(object.toString(), AccountSummaryResponse.class);
        editAddress = objectMapper.readValue( objectMapper.writeValueAsString(accountSummaryResponse.getAddress()), AddressDTO.class);
        editContacts = objectMapper.readValue( objectMapper.writeValueAsString(accountSummaryResponse.getContactDetail()), ContactDetailDTO.class);
        return "/edit-account?faces-redirect=true";

    }


    public AddressDTO getEditAddress() {
        return editAddress;
    }

    public void setEditAddress() {
        addressService.updateAccountAddressDetails(editAddress);
    }

    public ContactDetailDTO getEditContacts() {
        return editContacts;
    }

    public void setEditContacts() {
        contactDetailService.updateAccountContactDetails(editContacts);
    }
}
