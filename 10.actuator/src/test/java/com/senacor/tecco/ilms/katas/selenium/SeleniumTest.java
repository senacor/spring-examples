package demo.intern.selenium;

import demo.intern.Application;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

/**
 * Created by fsubasi on 10.02.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest(randomPort = true)
public class SeleniumTest {

    private static ChromeDriver browser;

    @Value("${local.server.port}")
    private int port;

    @BeforeClass
    public static void openBrowser(){
        System.setProperty("webdriver.chrome.driver", "C://Users//fsubasi//Desktop//chromedriver.exe");
        browser = new ChromeDriver();
        browser.manage().timeouts()
                .implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void closeBrowser(){
        browser.quit();
    }

    @Test
    public void userTest(){
        String baseUrl = "http://localhost:" + port + "/views/viewResolver/user";

        browser.get(baseUrl);
        Assert.assertEquals("Spring Multiple Representations Demo", browser.findElementByTagName("h1").getText());
    }
}
