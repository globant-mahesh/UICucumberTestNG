package com.phptravels.framework.testsuite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

/**
 * Created by maheshb on 6/17/17.
 */
public class WebDriverListener extends AbstractWebDriverEventListener{

    @Override
    public void beforeNavigateForward(WebDriver driver) {
        super.beforeNavigateForward(driver);
        System.out.println("Before moving to url");
    }

    @Override
    public void afterNavigateForward(WebDriver driver) {
        super.afterNavigateForward(driver);
        System.out.println("Moved to url" + driver.getCurrentUrl());
    }

    @Override
    public void beforeNavigateBack(WebDriver driver) {
        super.beforeNavigateBack(driver);
    }

    @Override
    public void afterNavigateBack(WebDriver driver) {
        super.afterNavigateBack(driver);
    }

    @Override
    public void beforeFindBy(By by, WebElement el, WebDriver driver) {
        super.beforeFindBy(by, el, driver);
        System.out.println("Before locating the element");
    }

    @Override
    public void afterFindBy(By by, WebElement el, WebDriver driver) {
        super.afterFindBy(by, el, driver);
        System.out.println("Located element using locator " + by);
    }

    @Override
    public void beforeClickOn(WebElement el, WebDriver driver) {
        super.beforeClickOn(el, driver);
    }

    @Override
    public void afterClickOn(WebElement el, WebDriver driver) {
        super.afterClickOn(el, driver);
        System.out.println("Clicked on element: " + el);
    }

    @Override
    public void beforeChangeValueOf(WebElement el, WebDriver driver, CharSequence[] charArr) {
        super.beforeChangeValueOf(el, driver, charArr);
    }

    @Override
    public void afterChangeValueOf(WebElement el, WebDriver driver, CharSequence[] charArr) {
        super.afterChangeValueOf(el, driver, charArr);
        System.out.println("Changed value of element " + el);
    }

    @Override
    public void beforeScript(java.lang.String script, WebDriver driver) {
        super.beforeScript(script, driver);
    }

    @Override
    public void afterScript(java.lang.String script, WebDriver driver) {
        super.afterScript(script, driver);
    }

    @Override
    public void onException(java.lang.Throwable e, WebDriver driver) {
        super.onException(e, driver);
    }
}
