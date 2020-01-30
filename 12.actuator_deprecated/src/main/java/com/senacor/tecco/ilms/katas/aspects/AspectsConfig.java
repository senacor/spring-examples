package com.senacor.tecco.ilms.katas.aspects;

import com.senacor.tecco.ilms.katas.aspects.e01_advice.e02_around.Audience;
import com.senacor.tecco.ilms.katas.aspects.e02_introduction.DefaultEncoreable;
import com.senacor.tecco.ilms.katas.aspects.e02_introduction.Encoreable;
import com.senacor.tecco.ilms.katas.aspects.e02_introduction.EncoreableIntroducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by fsubasi on 12.01.2016.
 * @EnableAspectJAutoProxy searches for beans annotated with @Aspect and creates proxies around
 * other beans if they are a match for a pointcut defined in an @Aspect bean.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan
public class AspectsConfig {

    @Bean
    public EncoreableIntroducer encoreableIntroducer(){
        return new EncoreableIntroducer();
    }

    @Bean
    public Audience audience(){
        return new Audience();
    }

    @Bean
    public Encoreable encoreable(){
        return new DefaultEncoreable();
    }


    @Bean
    public Performance theaterPerformance(){
        return new TheaterPerformance();
    }
}