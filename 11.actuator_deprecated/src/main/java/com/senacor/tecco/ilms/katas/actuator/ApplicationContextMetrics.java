//package com.senacor.tecco.ilms.katas.actuator;
//
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Component;
//
///**
// * Created by fsubasi on 09.02.2016.
// * In this demonstration we would like to show how to create custom metrics by implementing PublicMetrics
// */
//@Component
//public class ApplicationContextMetrics implements PublicMetrics {
//    private ApplicationContext context;
//    @Autowired
//    public ApplicationContextMetrics(ApplicationContext context) {
//        this.context = context;
//    }
//    @Override
//    public Collection<Metric<?>> metrics() {
//        List<Metric<?>> metrics = new ArrayList<Metric<?>>();
//        // lets create a metric that keeps application startup time
//        metrics.add(new Metric<Long>("spring.context.startup-date", context.getStartupDate()));
//        // a metric that counts the number of active profiles
//        metrics.add(new Metric<Integer>("active-profile-count", context.getEnvironment().getActiveProfiles().length));
//        // a metric that counts the number of @Aspect s
//        metrics.add(new Metric<Integer>("aspects",  context.getBeanNamesForAnnotation(Aspect.class).length));
//
//        return metrics;
//    }
//}