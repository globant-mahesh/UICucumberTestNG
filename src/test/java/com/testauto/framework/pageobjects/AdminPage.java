/**
 * To instantiate the webelements declared in the pageobject class, the page must have been loaded before the creation of the object
 * driver must be initialised in the constructor
 */
package com.testauto.framework.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.LoadableComponent;

public class AdminPage extends LoadableComponent<AdminPage> {

    WebDriver driver;

    @FindBy(how = How.CSS, using = "a.uui-button")
    WebElement newEventButton;

    //    This is the constructor used by the initElement method of PageFactory class by default
    public AdminPage(WebDriver driver) {
        this.driver = driver;
    }

    public EventPage goToAdminMenu() {
        newEventButton.click();
        return new EventPage(driver);
    }

    protected void load() {
        System.out.println("Loaded Admin Page");
    }

    protected void isLoaded() throws Error {
        System.out.println("Loaded checked");
    }
}