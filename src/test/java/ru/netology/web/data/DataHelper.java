package ru.netology.web.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class Card {
        private String number;
        private String balance;

    }

    public static Card firstCard() {
        return new Card("5559 0000 0000 0001", "10000");
    }

    public static Card secondCard() {
        return new Card("5559 0000 0000 0002", "10000");
    }

    public static int cardBalanceAfterSending(int balanceBefore, int amount) {
        return balanceBefore - amount;
    }

    public static int cardBalanceUponReceipt(int balanceBefore, int amount) {
        return balanceBefore + amount;
    }



}
