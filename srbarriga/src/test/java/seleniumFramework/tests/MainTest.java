package seleniumFramework.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import seleniumFramework.core.TestBase;
import seleniumFramework.flows.AccountFlow;
import seleniumFramework.flows.LoginFlows;
import seleniumFramework.flows.MainFlows;
import seleniumFramework.flows.TransactionFlow;
import seleniumFramework.pages.AccountPage;
import seleniumFramework.pages.MainPage;
import seleniumFramework.pages.TransactionsPage;
import seleniumFramework.utils.DateUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MainTest extends TestBase {

    LoginFlows loginFlows;
    MainFlows mainFlows;
    MainPage mainPage;
    AccountPage accountPage;
    TransactionsPage transactionsPage;
    TransactionFlow transactionFlow;

    AccountFlow accountFlow;


    @BeforeMethod
    public void init() {
        mainFlows = new MainFlows();
        mainPage = new MainPage();
        loginFlows = new LoginFlows();
        accountPage = new AccountPage();
        accountFlow = new AccountFlow();
        transactionsPage = new TransactionsPage();
        transactionFlow = new TransactionFlow();

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
        String name = "conta editar";
        String newName = "Conta modificada no test";
        String expectedAccountMessage = "Conta adicionada com sucesso!";
        String expectedSuccessMessage = "Conta alterada com sucesso!";

        mainFlows.navigateToInsertAccount();
        accountFlow.addAccount(name);
        Assert.assertEquals(accountPage.getTextAlert(), expectedAccountMessage);

        mainFlows.navigateToAccountList();
        accountPage.clickAlterAccount();

        accountPage.fillAccountName(newName);
        accountPage.clickSubmitButton();
        Assert.assertEquals(accountPage.getTextAlert(), expectedSuccessMessage);

        Assert.assertEquals(accountPage.getTextCell("Conta", newName), newName);
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

        Date currentDate = DateUtils.getCurrentDate();
        String dateFormated = DateUtils.formatDate(currentDate, "dd/MM/YYYY");

        String description = "movimentação";
        String intrested = "carlitos";
        String value = "997";
        String radioOption = "Pendente";
        String expectedAlertMessage = "Movimentação adicionada com sucesso!";

        mainFlows.navigateToTransactionsPage();

        // TODO selecionar se transação é despesa ou receita
        // TODO selecionar conta para a movimentação
        transactionsPage.fillFieldTransactionDate(dateFormated);
        transactionsPage.fillFieldPaymentDate(dateFormated);
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

        Assert.assertEquals(errors.size(), 6);
        for (String s : expected) {
            Assert.assertTrue(transactionsPage.checkListContainsText(errors, s));
        }
    }

    @Test
    public void deleteTransaction() {
        String accountName = "Conta Delete";

        Date currentDate = DateUtils.getCurrentDate();
        String dateFormated = DateUtils.formatDate(currentDate, "dd/MM/YYYY");
        String description = "delete movimentação";
        String intrested = "carlitos";
        String value = "997";
        String radioOption = "Pendente";

        String expectedAccountMessage = "Conta adicionada com sucesso!";
        String expectedAlertMessage = "Movimentação removida com sucesso!";

        mainFlows.navigateToInsertAccount();
        accountFlow.addAccount(accountName);
        Assert.assertEquals(accountPage.getTextAlert(), expectedAccountMessage);

        mainFlows.navigateToTransactionsPage();
        transactionFlow.addTransaction(dateFormated, dateFormated, description, intrested, value, accountName, radioOption);

        mainPage.goToMonthResume();

        int sizeBeforeDelete = transactionsPage.numberOfTransactions();
        transactionsPage.removeTransaction("Descrição", "delete movimentação");

        Assert.assertEquals(mainPage.getTextAlert(), expectedAlertMessage);
        Assert.assertEquals(transactionsPage.numberOfTransactions(), (sizeBeforeDelete - 1));
    }

    @Test
    public void removeAccountWithTransaction(){
        String accountToDelete = "conta alterada";
        String expectedAlertMessage = "Conta em uso na movimentações";

        // TODO criar conta aki para tornar test independente

        mainFlows.navigateToAccountList();
        accountPage.clickRemoveAccountButton("Conta", accountToDelete);

        Assert.assertEquals(mainPage.getTextAlert(), expectedAlertMessage);
    }

    @Test
    public void checkErrorTransactionWithTransactionDateBiggerThenCurrentDate() {

        Date currentDate = DateUtils.getFutureDate(10);


        String transactionDate = DateUtils.formatDate(currentDate, "dd/MM/YYYY");
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

    @Test
    public void checkBalanceAccount(){
        String name = "conta checa saldo";
        Date currentDate = DateUtils.getCurrentDate();
        String dateFormated = DateUtils.formatDate(currentDate, "dd/MM/YYYY");

        String description = "movimentação";
        String intrested = "carlitos";
        String value = "997.00";
        String radioOption = "Pago";
        String expectedSuccessMessage = "Conta adicionada com sucesso!";
        String expectedTransactionMessage = "Movimentação adicionada com sucesso!";

        mainFlows.navigateToInsertAccount();
        accountFlow.addAccount(name);
        Assert.assertEquals(accountPage.getTextAlert(), expectedSuccessMessage);

        mainFlows.navigateToTransactionsPage();
        transactionFlow.addTransaction(dateFormated, dateFormated, description, intrested, value, name, radioOption);
        Assert.assertEquals(accountPage.getTextAlert(), expectedTransactionMessage);

        mainPage.goToHome();
        Assert.assertEquals(mainPage.getAccountBalance(name), value);

    }

    //    Testes
    //    [x] - Inserir conta com mesmo nome
    //    [x] - Inserir movimentação
    //    [X] - Campos obrigatórios de movimentação
    //    [x] - Movimentação futura
    //    [x] - Remover movimentação
    //    [x] - Remover conta com movimentação
    //    [x] - Saldo Das contas
    //    [] - Resumo mensal

}
