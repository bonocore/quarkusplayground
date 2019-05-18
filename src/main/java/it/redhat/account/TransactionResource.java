package it.redhat.account;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.opentracing.Tracer;
import it.redhat.account.model.*;

@Path("/transaction")
public class TransactionResource {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    TransactionService service;

    @Inject
    Tracer configuredTracer;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list/{accountId}")
    public  List<Transaction> list(@PathParam("accountId") String accountId) {
        
        configuredTracer.activeSpan().setTag("accountId", accountId);

        List<Transaction> toRet=new ArrayList<Transaction>();
     
       
        toRet.addAll(service.listInstant(accountId));
       
        toRet.addAll(service.listLegacy(accountId));
        
        toRet.addAll(service.listCreditCard(accountId));
        
      
        return toRet;
    }

}