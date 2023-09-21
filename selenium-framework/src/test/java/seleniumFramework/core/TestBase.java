package seleniumFramework.core;

import com.sun.org.apache.xpath.internal.axes.FilterExprWalker;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class TestBase {
    // base para criação dos tests

    private String testName = "";
    String url = Properties.URL;

    @BeforeMethod
    public void beforeTest(Method method){
        DriverFactory.createDriver();
        testName = method.getName();
        DriverFactory.DRIVER.manage().window().maximize();
        DriverFactory.DRIVER.navigate().to(url);
    }

    @AfterMethod
    public void afterTest() {
        TakesScreenshot screenshot = (TakesScreenshot) DriverFactory.DRIVER;
        File file = screenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("target" + File.separator + "screenshots" + File.separator + testName +".jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DriverFactory.quitDriver();
    }
}
