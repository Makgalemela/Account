package com.matome.accounts.dto;

import java.math.BigDecimal;

public class AccountDTO {

    public AccountDTO(String accountNumber, String accountHolderName, String accountHolderIDNumber) {
        this.accountNumber = new BigDecimal(accountNumber);
        this.accountHolderName = accountHolderName;
        this.accountHolderIDNumber = new BigDecimal(accountHolderIDNumber);
    }


    public AccountDTO() {
    }

    private BigDecimal accountNumber;
    private String accountHolderName;
    private BigDecimal accountHolderIDNumber;

    public BigDecimal getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(BigDecimal accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public BigDecimal getAccountHolderIDNumber() {
        return accountHolderIDNumber;
    }

    public void setAccountHolderIDNumber(BigDecimal accountHolderIDNumber) {
        this.accountHolderIDNumber = accountHolderIDNumber;
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "accountNumber=" + accountNumber +
                ", accountHolderName='" + accountHolderName + '\'' +
                ", accountHolderIDNumber=" + accountHolderIDNumber +
                '}';
    }
}
