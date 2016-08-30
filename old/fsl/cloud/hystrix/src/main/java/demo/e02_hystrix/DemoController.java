package demo.e02_hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * Created by fsubasi on 04.02.2016.
 * The following configuration properties are used in fsl mortgage_sale service
 * *******************************************************************************
 * hystrix.command.%commandKey%.execution.isolation.thread.timeoutInMilliseconds: 2500
 * When the time spent inside the @HystrixCommand annotated method exceed the amount of time specified
 * in this property, hystrix redu
 *********************************************************************************
 * hystrix.command.%commandKey%.circuitBreaker.requestVolumeThreshold: 5
 * The number of failed requests after which the circuit will be opened(tripped).
 * ******************************************************************************
 * hystrix.command.%commandKey%.circuitBreaker.sleepWindowInMilliseconds: 2000
 * The amount of time to wait before allowing attempts to detect if the circuit should be closed again, here it is 2 secs
 * ******************************************************************************
 * hystrix.command.%commandKey%.metrics.rollingStats.timeInMilliseconds: 2000
 */
@RestController
public class DemoController {
    public Random random = new Random();
volatile int i = 0;
    @RequestMapping("/hystrixDemo")
    @HystrixCommand(commandKey = "demo", fallbackMethod = "hystrixDemoFallback")
    public String hystrixDemo(){
        // simulate a synchronous backend call using RestTemplate
        // timeout has been set to 2.5 seconds so the user sees the message
        // created in hystrixDemoFallback method.
        // return imitateBackendCall();
        // throw new RuntimeException("Sum Tin Wong");
        System.out.println("New call" + ++i);
        return standardCall();
    }


    private String imitateBackendCall(){
        try{
            Thread.sleep(3000);
        } catch(InterruptedException e){
            throw new RuntimeException("thread interrupted.");
        }
        return "responseFromBackend";
    }

    private String standardCall(){
        try{
            int delay = random.nextInt(1000);
            if(delay > 400)
                System.out.println("Should reduce");
            Thread.sleep(delay);
        } catch(InterruptedException e){
            throw new RuntimeException("thread interrupted.");
        }
        return "Standard call";
    }

    public String hystrixDemoFallback(){
        return "Our servers our overloaded right now. Please try again later";
    }
}
