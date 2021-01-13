package it.rh.quickstart;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.transaction.Transactional;

import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.runtime.StartupEvent;
import it.rh.quickstart.model.Transaction;

@ApplicationScoped
@Transactional
public class TransactionUtil {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        }
      };

      void onStart(@Observes StartupEvent ev) {               
        LOGGER.info("Populating Fake Txs...");
        populateFakeTx(10,4);
        LOGGER.info("Populated Fake Txs");
    }   
    public void populateFakeTx(int numTx, int numAccounts)
    {
        for(int i=0;i<numAccounts;i++)
        {
            String accountId=UUID.randomUUID().toString();
            for(int j=0;j<numTx;j++)
            {
                generateRandomTransaction(accountId).persist();            
            }
        }
    }
public  Transaction generateRandomTransaction(String accountId)
{
    String[] status = {"BOOKED", "CANCELLED", "PENDING"};
    String[] type = {"CREDIT_CARD", "DEBIT", "TRANSFER","INSTANT"};
    Date end = new Date();
    Date start = new Date( (end.getTime() - (1000*60*60*24*365) ) );
    Date randomDate = new Date(RandomUtils.nextLong(start.getTime(), end.getTime()));
    
    return new Transaction(
        accountId, 
        UUID.randomUUID().toString(), 
        (RandomUtils.nextFloat(0.10f, 2500f))+" EUR", 
        status[RandomUtils.nextInt(0,status.length)],
        type[RandomUtils.nextInt(0,type.length)],
        df.get().format(randomDate));
}
}
