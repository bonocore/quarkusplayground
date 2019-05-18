package it.redhat.account;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.opentracing.SpanContext;
import io.opentracing.Tracer;
import io.opentracing.propagation.Format;
import io.opentracing.propagation.TextMapExtractAdapter;
import io.opentracing.propagation.TextMapInjectAdapter;
import it.redhat.account.model.Transaction;

@Path("/transactionAsync")
public class TransactionAsyncResource {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    TransactionAsyncService service;

    @Inject
    Tracer configuredTracer;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list/{accountId}")
    public CompletionStage<List<Transaction>> list(@PathParam("accountId") String accountId) {
        
        configuredTracer.activeSpan().setTag("accountId", accountId);
        

        HashMap<String,String> textMapTransport = new HashMap<String,String>();
        configuredTracer.inject(configuredTracer.activeSpan().context(), Format.Builtin.TEXT_MAP, new TextMapInjectAdapter(textMapTransport));
        
        CompletableFuture<List<Transaction>> resultInstant = CompletableFuture.supplyAsync(() -> {
            return service.listInstant(accountId,textMapTransport);
        });
      
        CompletableFuture<List<Transaction>> resultLegacy = CompletableFuture.supplyAsync(() -> {
            return service.listLegacy(accountId,textMapTransport);
        });
        
        CompletableFuture<List<Transaction>> resultCreditCard = CompletableFuture.supplyAsync(() -> {
            return service.listCreditCard(accountId,textMapTransport);
        });
        
        CompletionStage<List<Transaction>> combinedDataCompletionStage = 
        CompletableFuture.allOf(
            resultInstant, resultLegacy, resultCreditCard)
            .thenApply(resultCombine -> combine(
                resultInstant.join(), 
                resultLegacy.join(),
                resultCreditCard.join()));
  
        return combinedDataCompletionStage;
    }

    private List<Transaction> combine(List<Transaction> resultInstant, List<Transaction> resultLegacy, List<Transaction> resultCreditCard) {
        List<Transaction> combined = new ArrayList<Transaction>();
        combined.addAll(resultInstant);
        combined.addAll(resultLegacy);
        combined.addAll(resultCreditCard);
        return combined;
    }

   

    
}
