package com.testauto.framework.testsuite.factory;

/**
 * Created by maheshb on 7/4/17.
 */
public class Factory extends DataProviderFactory {

    @org.testng.annotations.Factory(dataProvider = "DataProviderFactory")
//    The parameters of the below method will get the outputted parameters of the dataprovider
//    Following method will be executed number of times equal to number of rows OR first dimension of the Object[][] from dataProvider
    public Object[] factoryMethod(String location) {
        return new Object[]{new TestNgFactoryRunner(location)};
    }
}
