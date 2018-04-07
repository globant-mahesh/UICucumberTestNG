package com.testauto.framework.testsuite.general;

import com.testauto.framework.pageobjects.HomePage;
import com.testauto.framework.testsuite.WebDriverListener;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by maheshb on 6/17/17.
 */
public class Test {

    HomePage home;
    WebDriver stDriver;
    EventFiringWebDriver driver;
    Logger logger;

    public Test() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
        //Instantiating driver object and launching browser
        stDriver = new ChromeDriver();
        driver = new EventFiringWebDriver(stDriver);
        driver = new EventFiringWebDriver(stDriver);
        WebDriverListener webDriverListener = new WebDriverListener();
        driver.register(webDriverListener);
    }

    public void test() throws InterruptedException, IOException {
        home = PageFactory.initElements(driver, HomePage.class);
        logger.debug("Page is initialised");
        searchUrl();
    }

    @Step("Search on the basis of city")
    private void searchUrl() throws InterruptedException, IOException {
        logger.trace("Go to Admin Page");
        assertThat(driver.getCurrentUrl(), containsString("autorqa"));
    }
}
