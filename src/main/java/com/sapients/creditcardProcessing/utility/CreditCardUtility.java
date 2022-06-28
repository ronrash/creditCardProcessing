package com.sapients.creditcardProcessing.utility;

import static java.lang.Integer.*;

import lombok.var;

public class CreditCardUtility {

    private CreditCardUtility() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isValidCreditCard(String cardNumber) {
        boolean isSecondValue = false;
        int totalSum = 0;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            var tempValue = parseInt(String.valueOf(cardNumber.charAt(i)));
            if (isSecondValue) {
                tempValue = tempValue * 2;
            }
            totalSum = totalSum + tempValue / 10;
            totalSum = totalSum + tempValue % 10;

            isSecondValue = !isSecondValue;

        }
        return (totalSum % 10 == 0);
    }
}
