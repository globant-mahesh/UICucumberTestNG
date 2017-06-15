package com.phptravels.framework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

/**
 * Created by maheshb on 6/26/17.
 */
public class FlightsPage extends LoadableComponent<FlightsPage> {

    WebDriver driver;

    @FindBy(how = How.CSS, using = "li [href*=flight]")
    WebElement flightOption;

    @FindBy(how = How.CSS, using = "input#flights-destination-prepop-whitelabel_en")
    WebElement destination;

    @FindBy(how = How.CSS, using = ".mewtwo-flights-submit_button > button")
    WebElement searchButton;

    //  This is the constructor used by the initElement method of PageFactory class by default
    public FlightsPage(WebDriver driver) {
        this.driver = driver;
        driver.switchTo().frame(driver.findElement(By.id("tpcwl_iframe")));
        PageFactory.initElements(driver, this);
    }

    public FlightsPage(WebDriver driver, String url) {
        this(driver);
//        Note that implicitwait need not be specified in page object but it must be used in a file that refers to page object
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);
        driver.switchTo().frame(driver.findElement(By.id("tpcwl_iframe")));
        //  Page elements can be initialised once current class object instance is created i.e. this object
        PageFactory.initElements(driver, this);
    }

    public void goToFlightsPage() {
        flightOption.click();
    }

    public void searchDestination(String location) throws InterruptedException {
        destination.click();
        destination.sendKeys(location);
        searchButton.click();
    }

    @Override
    protected void load() {
        driver.get("http:");
    }

    @Override
    protected void isLoaded() throws Error {
        Assert.assertTrue(driver.getCurrentUrl().contains("/flights"));
    }
}
