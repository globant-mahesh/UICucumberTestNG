package com.phptravels.framework.testsuite.general;

import com.phptravels.framework.pageobjects.HomePage;
import com.phptravels.framework.testsuite.WebDriverListener;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
        stDriver = new FirefoxDriver();
        driver = new EventFiringWebDriver(stDriver);
        WebDriverListener webDriverListener = new WebDriverListener();
        driver.register(webDriverListener);
    }

    public void test() throws InterruptedException, IOException {
        home = PageFactory.initElements(driver, HomePage.class);
        logger.debug("Page is initialised");
        searchPackage();
    }

    @Step("Search on the basis of city")
    private void searchPackage() throws InterruptedException, IOException {
        logger.trace("Searching City Name");
        assertThat(driver.getCurrentUrl(), containsString("phptravels"));
        home.search("England");
    }
}
