package it.redhat.account;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.redhat.account.model.TransactionList;

@Path("/transactionAsync")
public class TransactionAsyncResource {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    TransactionAsyncService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list/{accountId}")
    public CompletionStage<TransactionList> list(@PathParam("accountId") String accountId) {
        
        TransactionList toRet=new TransactionList();
        long beforeMillis = System.currentTimeMillis();
     
        log.info("before listInstant in thread: " + Thread.currentThread().getName());
        toRet.getTransactions().addAll(service.listInstant(accountId).getTransactions());
        log.info("after listInstant in thread: " + Thread.currentThread().getName());

        log.info("before listLegacy in thread: " + Thread.currentThread().getName());
        toRet.getTransactions().addAll(service.listLegacy(accountId).getTransactions());
        log.info("after listLegacy in thread: " + Thread.currentThread().getName());
        
        log.info("before listCreditCard in thread: " + Thread.currentThread().getName());
        toRet.getTransactions().addAll(service.listCreditCard(accountId).getTransactions());
        log.info("after listCreditCard in thread: " + Thread.currentThread().getName());
        
        toRet.setElapsed(System.currentTimeMillis()-beforeMillis);

        return toRet;
    }

   

    
}
