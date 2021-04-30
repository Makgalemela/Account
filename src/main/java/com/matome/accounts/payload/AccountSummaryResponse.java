package com.matome.accounts.payload;

import com.matome.accounts.model.Account;
import com.matome.accounts.model.Address;
import com.matome.accounts.model.Bill;
import com.matome.accounts.model.ContactDetail;

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
