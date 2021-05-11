package com.matome.accounts.controller.restcontroller;


import com.matome.accounts.dto.AddressDTO;
import com.matome.accounts.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.matome.accounts.utils.UrlConstants.ACCOUNTADDRESS;

@RestController
public class AddressController {

    @Autowired
    AddressService addressService;

    @PutMapping(ACCOUNTADDRESS)
    public ResponseEntity<Object> updateAddressDetails(@RequestBody AddressDTO addressDTO){
        return  addressService.updateAccountAddressDetails(addressDTO);
    }

    @PostMapping(ACCOUNTADDRESS)
    public  ResponseEntity<Object> createAccountAddress(@RequestBody AddressDTO addressDTO){
        return  addressService.createAccountAddress(addressDTO);
    }

}
