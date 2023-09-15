package seleniumFramework.flows;

import seleniumFramework.pages.TransactionsPage;

public class TransactionFlow {

    TransactionsPage transactionsPage;

    public TransactionFlow() {
        transactionsPage = new TransactionsPage();
    }

    public void addTransaction(
            String date,
            String paymentDate,
            String description,
            String intrested,
            String value,
            String accoount,
            String radioOption
    ) {
        transactionsPage.fillFieldTransactionDate(date);
        transactionsPage.fillFieldPaymentDate(paymentDate);
        transactionsPage.fillFieldDescription(description);
        transactionsPage.fillFieldIntrested(intrested);
        transactionsPage.fillFieldValue(value);
        transactionsPage.selectAccount(accoount);
        transactionsPage.selectRadioByLabel(radioOption);

        transactionsPage.clickSubmitButton();
    }
}
