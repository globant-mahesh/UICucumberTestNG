package com.testauto.framework.testsuite.general;

import com.testauto.framework.pageobjects.HomePage;
import com.testauto.framework.testsuite.WebDriverListener;
import org.apache.log4j.Logger;
import org.junit.*;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
        //Instantiating driver object and launching browser
        stDriver = new ChromeDriver();
        driver = new EventFiringWebDriver(stDriver);
        WebDriverListener webDriverListener = new WebDriverListener();
        driver.register(webDriverListener);
        logger = Logger.getLogger(Runner.class.getName());
        driver.manage().window().maximize();
        driver.get("https://autorqa.events.epam.com/autoqa-login?email=auto_event_owner_public@epam.com&return_url=/");
    }

    @AfterClass
    public static void tearDown() {
        driver.close();
    }

    @Before
    public void before() {
        logger.debug("Before Test");
    }

    @After
    public void after() {
        logger.debug("After Test");
    }

    @Test
    public void test() throws IOException {
        home = PageFactory.initElements(driver, HomePage.class);
        logger.debug("Page is initialised");
        searchUrl();
        home.goToAdminMenu();
    }

    @Step("Search on the basis of city")
    private void searchUrl() throws IOException {
        logger.trace("Searching City Name");
        assertThat(driver.getCurrentUrl(), containsString("autorqa"));
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(Runner.class);
        for (Failure failure : result.getFailures()) {
            logger.trace(failure.toString());
        }
        logger.trace(result.wasSuccessful());
    }

}
