package com.sapients.creditcardProcessing.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import com.sapients.creditcardProcessing.exception.InvalidCreditCardException;
import com.sapients.creditcardProcessing.model.dto.CreditCardRequest;
import com.sapients.creditcardProcessing.model.entity.CreditCard;
import com.sapients.creditcardProcessing.repository.CreditCardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreditCardServiceTest {
    @InjectMocks
    CreditCardService creditCardService;
    @Mock
    CreditCardRepository creditCardRepository;

    @Test
    void shouldSaveCreditCard_WhenValidCreditCardPassed() {
        CreditCardRequest creditCardRequest = CreditCardRequest.builder().name("James")
                .cardNumber("4242424242426742")
                .build();
        CreditCard card = CreditCard.builder()
                .cardId(1)
                .name("James")
                .cardNumber(new BigInteger("4242424242426742"))
                .cardLimit(0)
                .build();

        lenient().when(creditCardRepository.save(ArgumentMatchers.any(CreditCard.class))).thenReturn(card)
                .thenReturn(card);

        final Integer saveCreditCardId = creditCardService.saveCreditCard(creditCardRequest);
        assertThat(saveCreditCardId).isEqualTo(1);
    }

    @Test
    void shouldThrowException_WhenInvalidCreditCardPassed() {
        CreditCardRequest creditCardRequest = CreditCardRequest.builder().name("James")
                .cardNumber("1111111111111111")
                .build();
        final InvalidCreditCardException invalidCreditCardException =
                assertThrows(InvalidCreditCardException.class,()->creditCardService.saveCreditCard(creditCardRequest));

        assertThat(invalidCreditCardException).hasMessage("Credit Card Number is Invalid");
    }

    @Test
    void shouldReturnAllSavedCreditCards() {
        CreditCard card = CreditCard.builder()
                .cardId(1)
                .name("James")
                .cardNumber(new BigInteger("4242424242426742"))
                .cardLimit(0)
                .build();

        lenient().when(creditCardRepository.findAll()).thenReturn(Arrays.asList(card));

        final List<CreditCard> allCards = creditCardService.getAllCards();
        assertThat(allCards).hasSize(1);
    }
}