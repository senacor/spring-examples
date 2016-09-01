package com.senacor.tecco.ilms.katas.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fsubasi on 09.02.2016.
 * In this demonstration we would like to show how to create a custom metric and gauge. This simple example
 * counts the number of times the controller method has been invoked using a metric and the last time it was accessed
 * using a gauge.
 */
@RestController
@RequestMapping("/actuatorDemo")
public class CustomMetricsAndGaugesController {
    private final String  ACCESS_COUNTER_METRIC = "actuatordemo.access.count";
    private final String LAST_ACCESSED_GAUGE = "actuatordemo.last.accessed";

    @Autowired
    private CounterService counterService;

    @Autowired
    private GaugeService gaugeService;


    @RequestMapping("/counterAndGauge")
    public String counterAndGauge(){
        counterService.increment(ACCESS_COUNTER_METRIC);
        gaugeService.submit(LAST_ACCESSED_GAUGE, System.currentTimeMillis());
        return "ACTUATOR TEST";
    }
}
/**
  EXERCISE QUESTION:
  We update the metric values here inside the controller method. However, this is polluting our method since
  we have logic inside the controller method that is not in any way related to handling the request. To remedy this situation
  refactor the code so that metric values are updated outside the controller method using @Aspects. Intercept the controller
  methods calls and update the metrics inside the @Aspect class.
 **/