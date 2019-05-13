package it.redhat.quickstart;

import javax.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class GreetingService {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    public String greeting(String name) {
        log.info("in service in thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello " + name;
    }

}
