package com.senacor.tecco.ilms.katas.advice;

import com.senacor.tecco.ilms.katas.Application;
import com.senacor.tecco.ilms.katas.aspects.Performance;
import com.senacor.tecco.ilms.katas.aspects.e02_introduction.Encoreable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
public class AspectTest {

    @Autowired
    private Performance performance;

    @Test
    public void testIntroduction(){
        ((Encoreable)performance).performEncore();
    }
}
