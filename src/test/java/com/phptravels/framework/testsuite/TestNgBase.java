package com.phptravels.framework.testsuite;

import org.testng.annotations.DataProvider;

/**
 * Created by maheshb on 6/20/17.
 */
public class TestNgBase {

    @DataProvider(name="data-provider")
    public Object[][] dataProvider() {
        //It is an array of array of object so inner curly braces represents array of object & outer braces therefore array of array of Object
        Object[][] dp = {{"London"}, {"New Delhi"}};
        return dp;
    }
}
