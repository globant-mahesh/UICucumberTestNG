/**
 * To instantiate the webelements declared in the pageobject class, the page must have been loaded before the creation of the object
 * driver must be initialised in the constructor
 */
package com.phptravels.framework.pageobjects;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class HomePage extends LoadableComponent<HomePage> {

    WebDriver driver;

    @FindBy(how = How.CSS, using = "[href*=flight]")
    WebElement flightOption;

    @FindBy(how = How.CSS, using = "#HOTELS [type='submit']")
    WebElement submitButton;

    @FindBy(how = How.CSS, using = ".select2-choice span.select2-chosen")
    WebElement cityHotelName;

    @FindBy(how = How.CSS, using = ".select2-drop-active .select2-input")
    WebElement cityHotelNameInput;

    @FindBy(how = How.CSS, using = "ul.select2-result-sub .select2-result-label")
    List<WebElement> options;

    //    This is the constructor used by the initElement method of PageFactory class by default
    public HomePage(WebDriver driver) {
        this.driver = driver;
        //        Note that implicitwait need not be specified in page object but it must be used in a file that refers to page object
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    // Overloaded Constructor
    public HomePage(WebDriver driver, String url) {
        // Constructor chaining
        this(driver);
        driver.get(url);
        // Both constructor & initElement method return the object so we can pass 'this' instance to the initElements method
        PageFactory.initElements(driver, this);
    }

    public FlightsPage goToFlightsOption() {
        flightOption.click();
        return new FlightsPage(driver);
    }

    public void search(String location) throws IOException {
        cityHotelName.click();
        cityHotelNameInput.sendKeys(location);
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.cssSelector("ul.select2-result-sub .select2-result-label")));
        options.get(0).click();
        File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File f1 = new File("~/screenshot.png");
        FileUtils.copyFile(f, f1);
        submitButton.click();
    }

    @Override
    protected void load() {
        driver.get("http://www.phptravels.net/");
    }

    @Override
    protected void isLoaded() throws Error {
        Assert.assertTrue(driver.getCurrentUrl().contains("phptravels"));
    }
}