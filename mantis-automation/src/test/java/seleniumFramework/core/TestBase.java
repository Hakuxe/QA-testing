package seleniumFramework.core;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class TestBase {
    // base para criação dos tests

    private String testName = "";

    @BeforeSuite
    public void beforeSuite(){
        new GlobalProperties();
    }

    @BeforeMethod
    public void beforeTest(Method method){
        DriverFactory.getDriver();
        testName = method.getName();
        DriverFactory.getDriver().manage().window().maximize();
        DriverFactory.getDriver().navigate().to(GlobalProperties.URL);
    }

    @AfterMethod
    public void afterTest() {
        TakesScreenshot screenshot = (TakesScreenshot) DriverFactory.getDriver();
        File file = screenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("target" + File.separator + "screenshots" + File.separator + testName +".jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DriverFactory.quitDriver();
    }
}
