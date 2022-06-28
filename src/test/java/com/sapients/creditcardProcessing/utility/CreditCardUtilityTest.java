package com.sapients.creditcardProcessing.utility;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CreditCardUtilityTest {

    @Test
    void shouldReturnTrue_WhenValidCardNumberPassed() {
        String card = "4242424242426742";
        final boolean validCreditCard = CreditCardUtility.isValidCreditCard(card);
        assertTrue(validCreditCard);
    }

    @Test
    void shouldReturnFalse_WhenInvalidCardNumberPassed() {
        String card = "1111111111111111";
        final boolean validCreditCard = CreditCardUtility.isValidCreditCard(card);
        assertFalse(validCreditCard);
    }
}