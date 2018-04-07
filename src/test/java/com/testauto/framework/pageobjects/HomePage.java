/**
 * To instantiate the webelements declared in the pageobject class, the page must have been loaded before the creation of the object
 * driver must be initialised in the constructor
 */
package com.testauto.framework.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

public class HomePage extends LoadableComponent<HomePage> {

    WebDriver driver;

    @FindBy(how = How.CSS, using = ".evnt-profile-menu>a")
    WebElement adminDropDown;

    @FindBy(how = How.CSS, using = "a.admin-icon")
    WebElement adminAreaOption;

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

    public AdminPage goToAdminMenu() {
        adminDropDown.click();
        adminAreaOption.click();
        return new AdminPage(driver);
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