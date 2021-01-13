package it.rh.quickstart.model;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Transaction extends PanacheEntity{

    public String accountId;
    public String transactionId;
    public String amount;
    public String status;
    public String type;
    public String bookingDateTime;

    public Transaction(){};

    public Transaction(String accountId, String transactionId, String amount, String status,String type, String bookingDateTime)
    {
        this.accountId=accountId;
        this.transactionId=transactionId;
        this.amount=amount;
        this.status=status;
        this.type=type;
        this.bookingDateTime=bookingDateTime;
    } 

}

