package it.redhat.account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.opentracing.Span;
import io.opentracing.SpanContext;
import io.opentracing.Tracer;
import io.opentracing.propagation.Format;
import io.opentracing.propagation.TextMapExtractAdapter;
import it.redhat.account.model.Transaction;

@ApplicationScoped
public class TransactionAsyncService {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    Tracer configuredTracer;
 
    
    public List<Transaction> listInstant(String accountId, HashMap<String,String> textMapTransport) {
      
        Span span = extractSpan(textMapTransport,"listInstant");

        log.info("start list instant");
        delay(500,1000);
        List<Transaction> toRet = populateRandomTransactionList(accountId);
       
        log.info("end list instant");
        span.finish();

        return toRet;

    }

    
    public List<Transaction> listLegacy(String accountId, HashMap<String, String> textMapTransport) {

        Span span = extractSpan(textMapTransport, "listLegacy");

        log.info("start list legacy");

        delay(300,2000);
        List<Transaction> toRet = populateRandomTransactionList(accountId);
        log.info("end list legacy");
        span.finish();

        return toRet;

    }

    public List<Transaction> listCreditCard(String accountId, HashMap<String, String> textMapTransport) {

        Span span = extractSpan(textMapTransport, "listCreditCard");

        log.info("start list creditCard");
        
        delay(300,1500);
        List<Transaction> toRet = populateRandomTransactionList(accountId);
        log.info("end list creditCard");

        span.finish();
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


    private Span extractSpan(HashMap<String, String> textMapTransport, String methodName) {
        Tracer.SpanBuilder spanBuilder = configuredTracer.buildSpan(methodName);
        SpanContext spanContext = configuredTracer.extract(Format.Builtin.TEXT_MAP,
                new TextMapExtractAdapter(textMapTransport));
        spanBuilder.asChildOf(spanContext);
        Span span = spanBuilder.start();
        return span;
    }
    
}