package com.matome.accounts.dto;

import java.math.BigDecimal;

public class ContactDetailDTO {
    public ContactDetailDTO(String mobile, String home, String work, String accountNumber) {

        this.mobile =  new BigDecimal(mobile);
        this.home = home;
        this.work = work;
        this.accountNumber = new BigDecimal(accountNumber);
    }

    public ContactDetailDTO() {
    }


    private BigDecimal mobile;
    private String home;
    private String work;
    private BigDecimal accountNumber;



    public BigDecimal getMobile() {
        return mobile;
    }

    public void setMobile(BigDecimal mobile) {
        this.mobile = mobile;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public BigDecimal getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(BigDecimal accountNumber) {
        this.accountNumber = accountNumber;
    }
}
