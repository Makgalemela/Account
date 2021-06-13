package com.matome.accounts.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;


@Entity
public class Bill {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    @Type(type="date")
    @JsonFormat( pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    private Date billDate;
    private BigDecimal charges;
    private BigDecimal outstanding;
    @Type(type="date")
    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    private Date dueDate;
    private BigInteger accountNumber;
    @Type(type="date")
    @JsonFormat(pattern = "yyyy-MM",shape = JsonFormat.Shape.STRING)
    private Date period;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigInteger getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(BigInteger accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Date getPeriod() {
        return period;
    }

    public void setPeriod(Date period) {
        this.period = period;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
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

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", billDate=" + billDate +
                ", charges=" + charges +
                ", outstanding=" + outstanding +
                ", dueDate=" + dueDate +
                ", accountNumber=" + accountNumber +
                ", period=" + period +
                '}';
    }
}
