package demo.intern.actuator;

/**
 * Created by fsubasi on 09.02.2016.
 * Actuator example: Here we show actuator Endpoints
 *
 * GET   /autoconfig        Provides an auto-configuration report describing what autoconfiguration conditions passed and failed.
   GET   /configprops       Describes how beans have been injected with configuration properties (including default values).
   GET   /beans             Describes all beans in the application context and their relationship to each other.
   GET   /dump              Retrieves a snapshot dump of thread activity.
   GET   /env               Retrieves all environment properties.
   GET   /env/{name}        Retrieves a specific environment value by name.
   GET   /health            Reports health metrics for the application, as provided by HealthIndicator implementations.
   GET   /info              Retrieves custom information about the application, as provided by any properties prefixed with info.
   GET   /mappings          Describes all URI paths and how they’re mapped to controllers (including Actuator endpoints).
   GET   /metrics           Reports various application metrics such as memory usage and HTTP request counters.
   GET   /metrics/{name}    Reports an individual application metric by name.
   POST  /shutdown          Shuts down the application; requires that endpoints.shutdown.enabled be set to true.
   GET   /trace             Provides basic trace information (timestamp, headers, and so on) for HTTP requests.
 */
public class ActuatorInfo {
}
