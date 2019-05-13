package it.redhat.quickstart;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class GreetingAsyncService {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    public CompletionStage<String> greeting(String name) {
       return CompletableFuture.supplyAsync(() -> {
         log.info("in service in thread: " + Thread.currentThread().getName());
         try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         return "hello " + name;
        });
    }

}