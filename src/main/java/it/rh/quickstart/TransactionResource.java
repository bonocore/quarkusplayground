package it.rh.quickstart;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import it.rh.quickstart.model.Transaction;

@Path("/tx")
public class TransactionResource {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public  List<Transaction> get() {
        return Transaction.listAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cc")
    public  List<Transaction> getCreditCard() {
        return Transaction.list("type","CREDIT_CARD");
    }

}
