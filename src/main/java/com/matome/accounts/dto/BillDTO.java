package com.matome.accounts.dto;

import java.math.BigDecimal;
import java.math.BigDecimal;

public class BillDTO {

    public BillDTO(String billDate, String charges, String outstanding, String dueDate, String accountNumber, String period) {
        this.billDate = billDate;
        this.charges = new BigDecimal(charges);
        this.outstanding = new BigDecimal(outstanding);
        this.dueDate = dueDate;
        this.accountNumber = new BigDecimal(accountNumber);
        this.period = period;
    }

    public BillDTO() {
    }

    private String billDate;
    private BigDecimal charges;
    private BigDecimal outstanding;
    private String dueDate;
    private BigDecimal accountNumber;
    private String period;

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public BigDecimal getCharges() {
        return charges;
    }

    public void setCharges(BigDecimal charges) {
        this.charges = charges;
    }

    public BigDecimal getOutstanding() {
        return outstanding;
    }

    public void setOutstanding(BigDecimal outstanding) {
        this.outstanding = outstanding;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(BigDecimal accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
