package com.matome.accounts.payload;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.matome.accounts.model.Account;
import com.matome.accounts.model.Address;
import com.matome.accounts.model.Bill;
import com.matome.accounts.model.ContactDetail;
import net.bytebuddy.matcher.CollectionOneToOneMatcher;

import java.util.List;

public class AccountSummaryResponse {

    private Account account;
    private Address address;
    private ContactDetail contactDetail;
    private List<Bill> bills;


    public AccountSummaryResponse(Account account, Address address, ContactDetail contactDetail, List<Bill> bills) {
        this.account = account;
        this.address = address;
        this.contactDetail = contactDetail;
        this.bills = bills;
    }



    public AccountSummaryResponse(String account, String address, String contactDetail, String bills) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonObj = objectMapper.writeValueAsString(account);
        this.account = objectMapper.readValue(jsonObj, Account.class);
        String jsonObj1 = objectMapper.writeValueAsString(address);
        this.address =  objectMapper.readValue(jsonObj1, Address.class);
        String jsonObj2 = objectMapper.writeValueAsString(address);
        this.contactDetail = objectMapper.readValue(jsonObj2, ContactDetail.class);
        String jsonObj3 = objectMapper.writeValueAsString(bills);
        this.bills = objectMapper.readValue(jsonObj3,
        new TypeReference<List<Bill>>(){});

    }

    public AccountSummaryResponse(){}


    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ContactDetail getContactDetail() {
        return contactDetail;
    }

    public void setContactDetail(ContactDetail contactDetail) {
        this.contactDetail = contactDetail;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }
}
