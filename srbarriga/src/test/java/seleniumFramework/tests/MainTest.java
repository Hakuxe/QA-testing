package seleniumFramework.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import seleniumFramework.core.TestBase;
import seleniumFramework.flows.LoginFlows;
import seleniumFramework.flows.MainFlows;
import seleniumFramework.pages.AccountPage;
import seleniumFramework.pages.MainPage;
import seleniumFramework.pages.TransactionsPage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainTest extends TestBase {

    LoginFlows loginFlows;
    MainFlows mainFlows;
    MainPage mainPage;
    AccountPage accountPage;
    TransactionsPage transactionsPage;


    @BeforeMethod
    public void init() {
        mainFlows = new MainFlows();
        mainPage = new MainPage();
        loginFlows = new LoginFlows();
        accountPage = new AccountPage();
        transactionsPage = new TransactionsPage();

        loginFlows.login();
    }

    @Test
    public void basicLogin() {
        loginFlows.login();
    }

    @Test
    public void insertAccount() {
        String name = "naruto";
        String expectedSuccessMessage = "Conta adicionada com sucesso!";


        mainFlows.navigateToInsertAccount();
        accountPage.fillAccountName(name);
        accountPage.clickSubmitButton();

        Assert.assertEquals(accountPage.getTextAlert(), expectedSuccessMessage);
    }

    @Test
    public void editAccount() {
        String name = "conta alterada";
        String expectedSuccessMessage = "Conta alterada com sucesso!";

        mainFlows.navigateToAccountList();


        accountPage.clickAlterAccount();

        accountPage.fillAccountName(name);
        accountPage.clickSubmitButton();
        Assert.assertEquals(accountPage.getTextAlert(), expectedSuccessMessage);

        Assert.assertEquals(accountPage.getTextCell("Conta", name), name);
    }

    @Test
    public void insertAccountWithSameName() {
        String name = "narutoo";
        String expectedErrorMessage = "Já existe uma conta com esse nome!";


        mainFlows.navigateToInsertAccount();
        accountPage.fillAccountName(name);
        accountPage.clickSubmitButton();

        Assert.assertEquals(accountPage.getTextAlert(), expectedErrorMessage);
    }

    @Test
    public void insertTransaction() {

        String transactionDate = "01/09/2023";
        String paymentDate = "01/09/2023";
        String description = "movimentação";
        String intrested = "carlitos";
        String value = "997";
        String radioOption = "Pendente";
        String expectedAlertMessage = "Movimentação adicionada com sucesso!";

        mainFlows.navigateToTransactionsPage();

        // TODO selecionar se transação é despesa ou receita
        // TODO selecionar conta para a movimentação
        transactionsPage.fillFieldTransactionDate(transactionDate);
        transactionsPage.fillFieldPaymentDate(paymentDate);
        transactionsPage.fillFieldDescription(description);
        transactionsPage.fillFieldIntrested(intrested);
        transactionsPage.fillFieldValue(value);
        transactionsPage.selectRadioByLabel(radioOption);

        transactionsPage.clickSubmitButton();
        Assert.assertEquals(accountPage.getTextAlert(), expectedAlertMessage);
    }

    @Test
    public void checkErrorMessageRequiredFieldTransactionForm() {

        List<String> expected = new ArrayList<String>(Arrays.asList(
                "Data da Movimentação é obrigatório",
                "Data do pagamento é obrigatório",
                "Descrição é obrigatório",
                "Interessado é obrigatório",
                "Valor é obrigatório",
                "Valor deve ser um número"
        ));

        mainFlows.navigateToTransactionsPage();
        transactionsPage.clickSubmitButton();

        List<WebElement> errors = transactionsPage.getErrorListMessages();

        for (int i = 0; i < expected.size() ; i++) {
            Assert.assertTrue(transactionsPage.checkListContainsText(errors, expected.get(i)));
        }
    }

    @Test
    public void checkErrorTransactionWithTransactionDateBiggerThenCurrentDate(){
        String transactionDate = "20/09/2023";
        String paymentDate = "01/09/2023";
        String description = "movimentação";
        String intrested = "carlitos";
        String value = "997";
        String radioOption = "Pendente";
        String expectedAlertMessage = "Data da Movimentação deve ser menor ou igual à data atual";

        mainFlows.navigateToTransactionsPage();

        // TODO selecionar se transação é despesa ou receita
        // TODO selecionar conta para a movimentação
        transactionsPage.fillFieldTransactionDate(transactionDate);
        transactionsPage.fillFieldPaymentDate(paymentDate);
        transactionsPage.fillFieldDescription(description);
        transactionsPage.fillFieldIntrested(intrested);
        transactionsPage.fillFieldValue(value);
        transactionsPage.selectRadioByLabel(radioOption);

        transactionsPage.clickSubmitButton();
        Assert.assertEquals(accountPage.getTextAlert(), expectedAlertMessage);
    }

    //    Testes
    //    [x] - Inserir conta com mesmo nome
    //    [x] - Inserir movimentação
    //    [X] - Campos obrigatórios de movimentação
    //    [x] - Movimentação futura
    //    [] - Remover movimentação
    //    [] - Remover conta com movimentação
    //    [] - Saldo Das contas
    //    [] - Resumo mensal

}
