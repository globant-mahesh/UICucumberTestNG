package com.phptravels.framework.testsuite.general;

import com.phptravels.framework.pageobjects.HomePage;
import com.phptravels.framework.testsuite.WebDriverListener;
import org.apache.log4j.Logger;
import org.junit.*;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import ru.yandex.qatools.allure.annotations.*;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by maheshb on 6/15/17.
 *
 * Allure annotations are used along with the junit annotation to facilitate to generate the allure report
 * @Title @Description @Step are allure annotations
 *
 * Allure Reports for Junit framework:
 * https://github.com/allure-framework/allure1/wiki/JUnit
 *
 * Junit runner classes such as JUnitcore Result & Failure are used to drive the Junit test case
 * http://junit.sourceforge.net/javadoc/org/junit/runner/JUnitCore.html
 *
 * Comparison of Hamcrest assertion matcher framework with Junit4 assertion framework style
 * http://www.vogella.com/tutorials/Hamcrest/article.html
 *
 * Debugger used log4j 2.x
 * https://logging.apache.org/log4j/2.x/manual/index.html
 *
 */
@Title("Test for Php Travels booking")
@Description("The objective of the test case is to search the packages available for the given destination")

public class Runner {

    static WebDriver stDriver;
    static EventFiringWebDriver driver;
    static Logger logger;
    HomePage home;

    @BeforeClass
    public static void setUp() {
        stDriver = new FirefoxDriver();
        driver = new EventFiringWebDriver(stDriver);
        WebDriverListener webDriverListener = new WebDriverListener();
        driver.register(webDriverListener);
        logger = Logger.getLogger(Runner.class.getName());
        driver.manage().window().maximize();
        driver.get("http://www.phptravels.net/");
    }

    @AfterClass
    public static void tearDown() {
        driver.close();
    }

    @Before
    public void before() {
        System.out.println("Before Test");
    }

    @After
    public void after() {
        System.out.println("After Test");
    }

    @Test
    public void test() throws IOException {
        home = PageFactory.initElements(driver, HomePage.class);
        logger.debug("Page is initialised");
        searchPackage();
    }

    @Step("Search on the basis of city")
    private void searchPackage() throws IOException {
        logger.trace("Searching City Name");
        assertThat(driver.getCurrentUrl(), containsString("phptravels"));
        home.search("England");
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(Runner.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }

}
