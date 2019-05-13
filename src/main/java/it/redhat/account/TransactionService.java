package it.redhat.account;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import javax.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.redhat.account.model.Transaction;
import it.redhat.account.model.TransactionList;

@ApplicationScoped
public class TransactionService {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    Random randomGenerator = new Random();
    

	public TransactionList listLegacy(String accountId) {
        log.info("in service in thread: " + Thread.currentThread().getName());
        TransactionList toRet = new TransactionList();
            
        try {
            Thread.sleep(2000);
           
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int size= randomGenerator.nextInt(10);
        for(int i=0;i<size;i++)
        {
            toRet.getTransactions().add(Transaction.generateRandomTransaction(accountId));
        }
        return toRet;
	}

    public TransactionList listCreditCard(String accountId) {
        log.info("in service in thread: " + Thread.currentThread().getName());
        TransactionList toRet = new TransactionList();
            
        try {
            Thread.sleep(2000);
           
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int size= randomGenerator.nextInt(10);
        for(int i=0;i<size;i++)
        {
            toRet.getTransactions().add(Transaction.generateRandomTransaction(accountId));
        }
        return toRet;
	}

    public TransactionList listInstant(String accountId) {
        log.info("in service in thread: " + Thread.currentThread().getName());
        TransactionList toRet = new TransactionList();
            
        try {
            Thread.sleep(1000);
           
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int size= randomGenerator.nextInt(10);
        for(int i=0;i<size;i++)
        {
            toRet.getTransactions().add(Transaction.generateRandomTransaction(accountId));
        }
        return toRet;
	}




}
