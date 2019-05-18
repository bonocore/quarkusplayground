package it.redhat.account.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomUtils;

public class Transaction{

    private String accountId = null;
  
    private String transactionId = null;
  
    private String amount = null;
  
    private String status = null;
  
    private String bookingDateTime = null;

    public Transaction(String accountId, String transactionId, String amount, String status, String bookingDateTime)
    {
        this.accountId=accountId;
        this.transactionId=transactionId;
        this.amount=amount;
        this.status=status;
        this.bookingDateTime=bookingDateTime;
    }

    /**
     * @return the accountId
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * @param accountId the accountId to set
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    /**
     * @return the transactionId
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * @param transactionId the transactionId to set
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * @return the amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the bookingDateTime
     */
    public String getBookingDateTime() {
        return bookingDateTime;
    }

    /**
     * @param bookingDateTime the bookingDateTime to set
     */
    public void setBookingDateTime(String bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }

    @Override
    public String toString() {
        return "Transaction [accountId=" + accountId + ", amount=" + amount + ", bookingDateTime=" + bookingDateTime
                + ", status=" + status + ", transactionId=" + transactionId + "]";
    }

    private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        }
      };
      
public static Transaction generateRandomTransaction(String accountId)
{
    String[] status = {"BOOKED", "CANCELLED", "PENDING"};
    Date end = new Date();
    Date start = new Date( (end.getTime() - (1000*60*60*24*365) ) );
    Date randomDate = new Date(RandomUtils.nextLong(start.getTime(), end.getTime()));
    
    return new Transaction(
        accountId, 
        UUID.randomUUID().toString(), 
        (RandomUtils.nextFloat(0.10f, 2500f))+" EUR", 
        status[RandomUtils.nextInt(0,status.length)],
        df.get().format(randomDate));
}

}

