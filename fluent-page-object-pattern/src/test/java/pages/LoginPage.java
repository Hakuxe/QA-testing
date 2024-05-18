package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    // fluent pages
    // A qual página essa ação pertence?
    // pra onde ela me leva? outra página?

    private final WebDriver browser;

    public LoginPage(WebDriver browser) {
        this.browser = browser;
    }

    // fluent pages (retorna a próxima página ou a página atual)
    public LoginPage enterUsername(String user){
        browser.findElement(By.cssSelector("label[for='usuario']")).click();
        browser.findElement(By.id("usuario")).sendKeys(user);

        // retorna a próxima página usada no teste (mesmo que seja a mesma)
        return this;
    }

    public LoginPage enterPassword(String password){
        browser.findElement(By.cssSelector("label[for='senha']")).click();
        browser.findElement(By.id("senha")).sendKeys(password);

        return this;
    }

    public ProductListPage clickLoginButton(){
        browser.findElement(By.cssSelector("button[type='submit']")).click();

        return new ProductListPage(browser);
    }
}
