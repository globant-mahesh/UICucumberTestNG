package com.testauto.framework.testsuite;

import com.testauto.framework.pageobjects.AdminPage;
import com.testauto.framework.pageobjects.HomePage;
import com.testauto.framework.testsuite.general.Runner;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by maheshb on 6/18/17.
 */
//@Test(groups = {"default-group"})
public class TestNgRunner extends TestNgBase {

    HomePage home;
    WebDriver stDriver;
    EventFiringWebDriver driver;
    Logger logger;

    //    Very Important: to avoid nullpointerexception caused by introduction of groups tag use alwaysRun parameter with Before methods
    @BeforeClass(alwaysRun = true)
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
        //Instantiating driver object and launching browser
        stDriver = new ChromeDriver();
        driver = new EventFiringWebDriver(stDriver);
        WebDriverListener webDriverListener = new WebDriverListener();
        driver.register(webDriverListener);
        logger = Logger.getLogger(Runner.class.getName());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //        List <WebElement> lst = driver.findElements(By.id("xyz"));
        //        Iterator it = lst.iterator();
        //        while (it.hasNext()){
        //            System.out.println(((WebElement)it.next()).getText());
        //        }
    }

    @BeforeTest(alwaysRun = true)
    public void beforeTest() {
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        logger.debug("Test Executed");
    }

    //    Not shown is expectedExceptions & expectedExceptionsMessageRegExp
    //    @Parameters({"location"})
    //    Note that name of the data provider is the name in the @DataProvider annotation & not the annotated method name
    @Test(enabled = true, timeOut = 100000, dataProvider = "data-provider", groups = {"search", "home"})
    public void searchPackage(String location) throws IOException {
        home = new HomePage(driver, "http://www.phptravels.net/").get();
        logger.debug("Page is initialised");
        logger.trace("Searching City Name");
        assertThat(driver.getCurrentUrl(), containsString("phptravels"));
    }

    @Parameters({"location"})
    @Test(enabled = true, timeOut = 120000, groups = {"search", "flights"})
    public void searchFlight(String location) throws InterruptedException {
        home = new HomePage(driver, "http://www.phptravels.net/").get();
        AdminPage flights = home.goToAdminMenu();
    }

    @Test(groups = {"dependent-group"}, dependsOnMethods = {"searchFlight"}, dependsOnGroups = {"flights"})
    public void testTwo() {
        System.out.println("Flight Search is done");
    }

    @Test(groups = {"depending-group"})
    public void testThree() {
        System.out.println("Test 3");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.close();
    }
}
