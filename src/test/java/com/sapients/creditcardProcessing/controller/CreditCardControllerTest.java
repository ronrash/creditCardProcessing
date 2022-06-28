package com.sapients.creditcardProcessing.controller;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.math.BigInteger;

import com.sapients.creditcardProcessing.model.dto.CreditCardRequest;
import com.sapients.creditcardProcessing.model.entity.CreditCard;
import com.sapients.creditcardProcessing.service.CreditCardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreditCardControllerTest {

    CreditCardController creditCardController;
    @Mock
    CreditCardService creditCardService;

    @BeforeEach
    void setup() {
        creditCardController = new CreditCardController(creditCardService);
    }

    @Test
    void shouldReturn201_WhenCreditCardAdded() {
        CreditCardRequest creditCardRequest = CreditCardRequest.builder()
                .name("James")
                .cardNumber("4242424242426742")
                .build();

        when(creditCardService.saveCreditCard(creditCardRequest)).thenReturn(anyInt());
        assertThat(creditCardController.addCreditCard(creditCardRequest).getStatusCodeValue()).isEqualTo(201);
    }

    @Test
    void shouldReturn200_WhenCreditCardAdded() {
        CreditCard creditCard = CreditCard.builder()
                .name("James")
                .cardNumber(BigInteger.valueOf(42424242))
                .build();

        when(creditCardService.getAllCards()).thenReturn(singletonList(creditCard));
        assertThat(creditCardController.retrieveAllCreditCards().getStatusCodeValue()).isEqualTo(200);
    }
}