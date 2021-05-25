package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class MoneyTransferPage {
    private SelenideElement amount = $("[data-test-id=amount] input");
    private SelenideElement cardFromTransferMoney = $("[data-test-id=from] input");
    private SelenideElement buttonActionTransfer = $("[data-test-id=action-transfer]");
    private SelenideElement buttonCancelTransfer = $("[data-test-id=action-cancel]");
    private SelenideElement errorTransfer = $("[data-test-id=error-notification] .notification__content");

    MoneyTransferPage() {}

    public DashboardPage transferMoney(int amountTransfer,DataHelper.Card card) {
        amount.setValue(String.valueOf(amountTransfer));
        cardFromTransferMoney.setValue(card.getNumber());
        buttonActionTransfer.click();
        return new DashboardPage();

    }

    public DashboardPage cancelTransfer() {
        buttonCancelTransfer.click();
        return new DashboardPage();
    }

    public DashboardPage errorTransfer() {
        buttonActionTransfer.click();
        errorTransfer.shouldBe(Condition.text("Ошибка!"));
        return new DashboardPage();
    }

}
