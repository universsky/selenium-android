package com.timwe.selenium.android;

import static java.lang.Thread.sleep;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.xpath;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.android.AndroidDriver;

import com.timwe.selenium.AppServer;

/**
 * 
 * @author Rashidi Zin
 *
 */
@RunWith(JUnit4.class)
public class AndroidDriverTest {

    AppServer server = null;
    WebDriver driver = null;
    
    @Before
    public void setUp() throws Exception {
        server = new AppServer();
        server.start();

        // Wait for a while just to ensure that the server is up.
        sleep(SECONDS.toMillis(30));
        
        driver = new AndroidDriver();
    }
    
    @Test
    public void testGoogleSearch() {
        driver.get("http://www.google.com.my");
        
        String key = "Malaysia";

        WebElement searchBox = driver.findElement(name("q"));
        searchBox.sendKeys(key);
        searchBox.submit();
        
        assertTrue(driver.getTitle().contains(key));
    }
        
    @After
    public void tearDown() throws Exception {
        driver.quit(); 
        server.stop();
    }

}