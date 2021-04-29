package com.matome.accounts.payload;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigInteger;


@Entity
public class Account {

    @Id
    protected BigInteger accountNumber;
    protected String accountHolderName;
    protected BigInteger accountHolderIDNumber;
    protected AccountHolderDetails accountHolderDetails;
    protected Bills bills;

    /**
     * Gets the value of the accountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the value of the accountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setAccountNumber(BigInteger value) {
        this.accountNumber = value;
    }

    /**
     * Gets the value of the accountHolderName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountHolderName() {
        return accountHolderName;
    }

    /**
     * Sets the value of the accountHolderName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountHolderName(String value) {
        this.accountHolderName = value;
    }

    /**
     * Gets the value of the accountHolderIDNumber property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getAccountHolderIDNumber() {
        return accountHolderIDNumber;
    }

    /**
     * Sets the value of the accountHolderIDNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setAccountHolderIDNumber(BigInteger value) {
        this.accountHolderIDNumber = value;
    }

    /**
     * Gets the value of the accountHolderDetails property.
     * 
     * @return
     *     possible object is
     *     {@link AccountHolderDetails }
     *     
     */
    public AccountHolderDetails getAccountHolderDetails() {
        return accountHolderDetails;
    }

    /**
     * Sets the value of the accountHolderDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountHolderDetails }
     *     
     */
    public void setAccountHolderDetails(AccountHolderDetails value) {
        this.accountHolderDetails = value;
    }

    /**
     * Gets the value of the bills property.
     * 
     * @return
     *     possible object is
     *     {@link Bills }
     *     
     */
    public Bills getBills() {
        return bills;
    }

    /**
     * Sets the value of the bills property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bills }
     *     
     */
    public void setBills(Bills value) {
        this.bills = value;
    }

}
