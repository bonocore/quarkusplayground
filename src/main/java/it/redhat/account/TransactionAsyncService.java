package it.redhat.account;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.redhat.account.model.Transaction;
import it.redhat.account.model.TransactionList;

@ApplicationScoped
public class TransactionAsyncService {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    Random randomGenerator = new Random();
    
    public CompletionStage<TransactionList> listInstant(String accountId) {
       return CompletableFuture.supplyAsync(() -> {
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
        });
    }
    public CompletionStage<TransactionList> listLegacy(String accountId) {
        return CompletableFuture.supplyAsync(() -> {
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
         });
     }
     public CompletionStage<TransactionList> listCreditCard(String accountId) {
        return CompletableFuture.supplyAsync(() -> {
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
         });
     }
}