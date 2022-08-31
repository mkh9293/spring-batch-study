package com.apress.batch.chapter10.domain;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.util.Date;

public class Transaction2 {
    private long transactionId;
    private String account;
    private BigDecimal amount;
    private Date timestamp;

    public Transaction2() {
    }

    public Transaction2(String account, BigDecimal amount, Date timestamp) {
        this.account = account;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getTimestamp() {
        return timestamp;
    }

//    @XmlJavaTypeAdapter(JaxbDateSerialzier.class)
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

}

