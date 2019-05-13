package it.redhat.quickstart;

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

@Path("/helloAsync")
public class GreetingAsyncResource {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    GreetingAsyncService service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/greeting/{name}")
    public CompletionStage<String> greeting(@PathParam("name") String name) {
        log.info("before service in thread: " + Thread.currentThread().getName());
        CompletionStage<String> toRet = service.greeting(name);
        log.info("after service in thread: " + Thread.currentThread().getName());
       
        return toRet;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public CompletionStage<String> hello() {
        return CompletableFuture.supplyAsync(() -> {
            return "hello";
        });
    }
}
