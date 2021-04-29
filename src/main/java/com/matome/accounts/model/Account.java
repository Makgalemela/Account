package com.matome.accounts.model;


import com.matome.accounts.payload.AccountHolderDetails;
import com.matome.accounts.payload.Bills;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigInteger;


@Entity
public class Account {

    @Id
    private BigInteger accountNumber;
    private String accountHolderName;
    private BigInteger accountHolderIDNumber;

    public BigInteger getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(BigInteger accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public BigInteger getAccountHolderIDNumber() {
        return accountHolderIDNumber;
    }

    public void setAccountHolderIDNumber(BigInteger accountHolderIDNumber) {
        this.accountHolderIDNumber = accountHolderIDNumber;
    }
}

