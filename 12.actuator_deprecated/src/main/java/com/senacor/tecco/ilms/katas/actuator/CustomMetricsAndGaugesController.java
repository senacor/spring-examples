package com.senacor.tecco.ilms.katas.actuator;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Optional;

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

    private final MeterRegistry meterRegistry;

    public CustomMetricsAndGaugesController(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @PostConstruct
    public void setUpMetrics() {
        Counter.builder(ACCESS_COUNTER_METRIC)
                .description("The number calls to access this endpoint")
                .register(meterRegistry);

        Gauge.builder(LAST_ACCESSED_GAUGE, System::currentTimeMillis)
                .description("Reports the time of the last method call")
                .register(meterRegistry);

    }

    @RequestMapping("/counterAndGauge")
    public String counterAndGauge(){
        Optional<Counter> counter = Optional.ofNullable(meterRegistry.find(ACCESS_COUNTER_METRIC).counter());
        counter.ifPresent(Counter::increment);
        Optional<Gauge> gauge = Optional.ofNullable(meterRegistry.find(LAST_ACCESSED_GAUGE).gauge());
        gauge.notify();

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