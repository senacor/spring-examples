package demo.intern.aspects.advice;

import demo.intern.Application;
import com.senacor.tecco.ilms.katas.aspects.e02_introduction.Encoreable;
import demo.intern.properties.e01_basics.TestConfiguration;
import com.senacor.tecco.ilms.katas.aspects.Performance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by fsubasi on 15.01.2016.
 */
@SpringApplicationConfiguration(classes = {Application.class, TestConfiguration.class})
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class AspectTest {

    @Autowired
    private Performance performance;

    @Test
    public void testIntroduction(){
        ((Encoreable)performance).performEncore();
    }
}
