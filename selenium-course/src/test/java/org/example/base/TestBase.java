package org.example.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    protected WebDriver driver;

    @BeforeMethod
    public void beforeTest(){
        String url = "/src/test/resourses/componentes.html";

        driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + url);
    }


    @AfterMethod
    public void afterTest() {
        driver.quit();
    }
}
