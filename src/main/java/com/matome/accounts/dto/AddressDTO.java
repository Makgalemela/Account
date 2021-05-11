package com.matome.accounts.dto;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
public class AddressDTO {
    public AddressDTO(String line1, String line2, String line3, String postalCode, Boolean isActive, String accountNumber) {
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
        this.postalCode = new BigDecimal(postalCode);
        this.isActive = isActive;
        this.accountNumber = new BigDecimal(accountNumber);
    }

    public AddressDTO() {
    }

    private String line1;
    private String line2;
    private String line3;
    private BigDecimal postalCode;
    private Boolean isActive;
    private BigDecimal accountNumber;

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getLine3() {
        return line3;
    }

    public void setLine3(String line3) {
        this.line3 = line3;
    }

    public BigDecimal getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(BigDecimal postalCode) {
        this.postalCode = postalCode;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public BigDecimal getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(BigDecimal accountNumber) {
        this.accountNumber = accountNumber;
    }
}
