package modulos.produto;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;

@DisplayName("Testes Web módulo de produto")
public class ProductTests {

    private WebDriver browser;

    @BeforeEach
    public void beforeEach() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/drivers/chromedriver-linux64/chromedriver");
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.get("http://165.227.93.41/lojinha-web/v2/");
    }

    @AfterEach
    public void afterEach() {
        browser.quit();
    }


    @Test
    @DisplayName("Não deve ser possível registrar valor zero para o valor do produto")
    public void testRegistrarProdutoComValorZero() {

        String expectedErrorMessage = "O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00";

        String toastText = new LoginPage(browser)
                .enterUsername("admin")
                .enterPassword("admin")
                .clickLoginButton()
                .openFormToAddProduct()
                .fillProductName("Tijolo")
                .fillProductValue("000")
                .fillProductColors("laranja")
                .sendFormWithErrors()
                .getToastMessage();

        Assertions.assertEquals(expectedErrorMessage, toastText);


    }


    @Test
    @DisplayName("Não deve ser possível registrar produto com valor acima de 7000")
    public void testRegistrarProdutoComValorAcimaDoPermitido() {

        String expectedErrorMessage = "O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00";

        String toastMessage = new LoginPage(browser)
                .enterUsername("admin")
                .enterPassword("admin")
                .clickLoginButton()
                .openFormToAddProduct()
                .fillProductName("Copo de plástico")
                .fillProductValue("720000")
                .fillProductColors("branco")
                .sendFormWithErrors()
                .getToastMessage();

        Assertions.assertEquals(expectedErrorMessage, toastMessage);

    }


    @Test
    @DisplayName("Adicionar produto com valor dentro do limite válido")
    public void testAdicionarProdutoComValorValido() {
        String expectedSuccessMessage = "Produto adicionado com sucesso";

        String toastMessage = new LoginPage(browser)
                .enterUsername("admin")
                .enterPassword("admin")
                .clickLoginButton()
                .openFormToAddProduct()
                .fillProductName("Relógio")
                .fillProductValue("100")
                .fillProductColors("preto")
                .saveProduct()
                .getToastMessage();

        Assertions.assertEquals(expectedSuccessMessage, toastMessage);
    }

    @Test
    @DisplayName("Adicionar produto com valor 7000")
    public void testAdicionarProdutoComValorSeteMil() {
        String expectedSuccessMessage = "Produto adicionado com sucesso";

        String toastMessage = new LoginPage(browser)
                .enterUsername("admin")
                .enterPassword("admin")
                .clickLoginButton()
                .openFormToAddProduct()
                .fillProductName("Relógio")
                .fillProductValue("700000")
                .fillProductColors("preto")
                .saveProduct()
                .getToastMessage();

        Assertions.assertEquals(expectedSuccessMessage, toastMessage);
    }


}
