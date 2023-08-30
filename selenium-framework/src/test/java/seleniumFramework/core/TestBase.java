package seleniumFramework.core;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    // base para criação dos tests

    @BeforeMethod
    public void beforeTest(){
        DriverFactory.createDriver();
        DriverFactory.DRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void afterTest() {
       DriverFactory.quitDriver();
    }
}
