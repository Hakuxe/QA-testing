package seleniumFramework.flows;

import seleniumFramework.pages.AccountPage;

public class AccountFlow {
    AccountPage accountPage;

    public AccountFlow(){
         accountPage = new AccountPage();
    }

    public void addAccount(String accountName){

        accountPage.fillAccountName(accountName);
        accountPage.clickSubmitButton();
    }
}
