package it.redhat.account;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import javax.enterprise.context.ApplicationScoped;

import org.apache.commons.lang3.RandomUtils;
import org.eclipse.microprofile.opentracing.Traced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.redhat.account.model.Transaction;

@ApplicationScoped
@Traced
public class TransactionService {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    
	public List<Transaction>  listLegacy(String accountId) {
        log.info("start list legacy");
        
        delay(3500,5500);
        List<Transaction> toRet = populateRandomTransactionList(accountId);
        log.info("end list legacy");
        return toRet;
	}

    public List<Transaction>  listCreditCard(String accountId) {
        log.info("start list creditCard");
        
        delay(3000,4500);
        List<Transaction> toRet = populateRandomTransactionList(accountId);
        log.info("end list creditCard");
        return toRet;
	}
  
    public List<Transaction>  listInstant(String accountId) {
        log.info("start list instant");
        
        delay(500,2500);
        List<Transaction> toRet = populateRandomTransactionList(accountId);
        log.info("end list instant");
        return toRet;
	}
    private void delay(long min, long max) {
        
        try {
            Thread.sleep(RandomUtils.nextLong(min,max));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private  List<Transaction> populateRandomTransactionList(String accountId) {
        List<Transaction> toRet = new ArrayList<Transaction>();
        int size = RandomUtils.nextInt(3,20);
        for (int i = 0; i < size; i++) {
            toRet.add(Transaction.generateRandomTransaction(accountId));
        }
        return toRet;
    }
}
