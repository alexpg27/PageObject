package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }

    @Test
    public void transferringMoneyFromTheFirstCard() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val VerificationPage = loginPage.validLogin(authInfo);
        val verificationCode =  DataHelper.getVerificationCodeFor(authInfo);
        val DashboardPage = VerificationPage.validVerify(verificationCode);
        val balanceFirstCardBeforeTransfer = DashboardPage.getFirstCardBalance();
        val balanceSecondCardBeforeTransfer = DashboardPage.getSecondCardBalance();
        val MoneyTransferPage = DashboardPage.firstCardButtonTransfer();
        int amount = 1000;
        MoneyTransferPage.transferMoney(amount,DataHelper.firstCard());
        val balanceFirstCardAfterTransfer = DataHelper.
                cardBalanceAfterSending(balanceFirstCardBeforeTransfer, amount);
        val balanceSecondCardAfterTransfer = DataHelper.
                cardBalanceUponReceipt(balanceSecondCardBeforeTransfer, amount);
        assertEquals((balanceFirstCardBeforeTransfer - amount), balanceFirstCardAfterTransfer);
        assertEquals((balanceSecondCardBeforeTransfer + amount), balanceSecondCardAfterTransfer);

    }

    @Test
    public void transferringMoneyFromTheSecondCard() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val VerificationPage = loginPage.validLogin(authInfo);
        val verificationCode =  DataHelper.getVerificationCodeFor(authInfo);
        val DashboardPage = VerificationPage.validVerify(verificationCode);
        val balanceFirstCardBeforeTransfer = DashboardPage.getFirstCardBalance();
        val balanceSecondCardBeforeTransfer = DashboardPage.getSecondCardBalance();
        val MoneyTransferPage = DashboardPage.secondCardButtonTransfer();
        int amount = 1000;
        MoneyTransferPage.transferMoney(amount,DataHelper.secondCard());
        val balanceFirstCardAfterTransfer = DataHelper.
                cardBalanceUponReceipt(balanceFirstCardBeforeTransfer, amount);
        val balanceSecondCardAfterTransfer = DataHelper.
                cardBalanceAfterSending(balanceSecondCardBeforeTransfer, amount);
        assertEquals((balanceFirstCardBeforeTransfer + amount), balanceFirstCardAfterTransfer);
        assertEquals((balanceSecondCardBeforeTransfer - amount), balanceSecondCardAfterTransfer);

    }

    @Test
    public void cardReplenishmentWhenFieldsEmpty() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val VerificationPage = loginPage.validLogin(authInfo);
        val verificationCode =  DataHelper.getVerificationCodeFor(authInfo);
        val DashboardPage = VerificationPage.validVerify(verificationCode);
        val MoneyTransferPage = DashboardPage.firstCardButtonTransfer();
        MoneyTransferPage.errorTransfer();
    }

    @Test
    public void cancellationOfCardReplenishment() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val VerificationPage = loginPage.validLogin(authInfo);
        val verificationCode =  DataHelper.getVerificationCodeFor(authInfo);
        val DashboardPage = VerificationPage.validVerify(verificationCode);
        val MoneyTransferPage = DashboardPage.firstCardButtonTransfer();
        MoneyTransferPage.cancelTransfer();
    }
}
