package it.rh.quickstart;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.opentracing.Traced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Traced
@ApplicationScoped
public class GreetingService {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    public String greeting(String name) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello " + name;
    }

}
