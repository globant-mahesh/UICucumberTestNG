package com.testauto.framework.testsuite.factory;

import com.testauto.framework.pageobjects.AdminPage;
import com.testauto.framework.pageobjects.HomePage;
import com.testauto.framework.testsuite.TestNgBase;
import com.testauto.framework.testsuite.WebDriverListener;
import com.testauto.framework.testsuite.general.Runner;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by maheshb on 6/18/17.
 */
//@Test(groups = {"default-group"})
public class TestNgFactoryRunner extends TestNgBase {

    private HomePage home;
    private WebDriver stDriver;
    private EventFiringWebDriver driver;
    private Logger logger;
    private String location;

    public TestNgFactoryRunner() {

    }

    public TestNgFactoryRunner(String location) {
        this.location = location;
    }

    //Very Important: to avoid nullpointerexception caused by introduction of groups tag use alwaysRun parameter with Before methods
    @BeforeClass(alwaysRun = true)
    public void setUp() {
    //Start the Remotewebdriver on terminal by executing command like
    //java -jar selenium-webdriver-standalone-3.5.0.jar (for gecko driver latest is preferred)
        System.setProperty("webdriver.gecko.driver", "/home/maheshb/phptravels/geckodriver");
        DesiredCapabilities capabilities = new DesiredCapabilities();
    //DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setBrowserName("firefox");
        capabilities.setCapability("marionette", false);
    //capabilities.setPlatform(Platform.LINUX);
        RemoteWebDriver remote = null;
        try {
            remote = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
    //WebDriver remote = new FirefoxDriver(capabilities);
        driver = new EventFiringWebDriver(remote);
        WebDriverListener webDriverListener = new WebDriverListener();
        driver.register(webDriverListener);
        logger = Logger.getLogger(Runner.class.getName());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeTest(alwaysRun = true)
    public void beforeTest() {
    //driver.get("http://www.phptravels.net/");
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        logger.debug("Test Executed");
    }

    //Not shown is expectedExceptions & expectedExceptionsMessageRegExp
    //@Parameters({"location"})
    //Note that name of the data provider is the name in the @DataProvider annotation & not the annotated method name
    @Test(enabled = true, timeOut = 100000, groups = {"search", "home"})
    public void searchPackage() throws IOException {
        home = new HomePage(driver, "http://www.phptravels.net/").get();
        logger.debug("Page is initialised");
        logger.trace("Searching City Name");
        assertThat(driver.getCurrentUrl(), containsString("phptravels"));
    }

    @Test(enabled = true, timeOut = 120000, groups = {"search", "flights"})
    public void searchFlight() throws InterruptedException {
        home = new HomePage(driver, "http://www.phptravels.net/").get();
        AdminPage admin = home.goToAdminMenu();
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
    //Closes all browser windows and safely ends the session useful for single test run
        driver.quit();
    //Close the browser window that the driver has focus on useful for parallel test run
    //driver.close();
    }
}
