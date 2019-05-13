package it.redhat.account.model;

import java.util.ArrayList;
import java.util.List;

public class TransactionList {

    private List<Transaction> transactions;
    private long elapsed;
    
    public TransactionList()
    {
        this.transactions= new ArrayList<Transaction>();
    }

    public TransactionList(List<Transaction> transactions)
    {
        if(transactions!=null)
        {
            this.transactions=transactions;
        }else{
            this.transactions= new ArrayList<Transaction>();
        }
    }

    /**
     * @return the transactions
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * @param transactions the transactions to set
     */
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    /**
     * @return the elapsed
     */
    public long getElapsed() {
        return elapsed;
    }

    /**
     * @param elapsed the elapsed to set
     */
    public void setElapsed(long elapsed) {
        this.elapsed = elapsed;
    }
  
}

