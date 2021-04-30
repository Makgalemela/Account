package com.matome.accounts.controller;


import com.matome.accounts.dto.AddressDTO;
import com.matome.accounts.dto.ContactDetailDTO;
import com.matome.accounts.service.AddressService;
import com.matome.accounts.service.ContactDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.matome.accounts.utils.UrlConstants.ACCOUNTADDRESS;
import static com.matome.accounts.utils.UrlConstants.ACCOUNTCONTACTDETAILS;

@RestController
public class ContactDetailsController {


    @Autowired
    ContactDetailService contactDetailService;

    @Autowired
    AddressService addressService;

    @PutMapping(ACCOUNTCONTACTDETAILS)
    public ResponseEntity<Object> updateContactDetails(@RequestBody ContactDetailDTO contactDetailDTO){
        return  contactDetailService.updateAccountContactDetails(contactDetailDTO);
    }

    @PutMapping(ACCOUNTADDRESS)
    public ResponseEntity<Object> updateAddressDetails(@RequestBody AddressDTO addressDTO){
        return  addressService.updateAccountAddressDetails(addressDTO);
    }

}
