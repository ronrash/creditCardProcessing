package com.sapients.creditcardProcessing.service;

import static com.sapients.creditcardProcessing.utility.CreditCardUtility.isValidCreditCard;

import java.math.BigInteger;
import java.util.List;

import com.sapients.creditcardProcessing.exception.InvalidCreditCardException;
import com.sapients.creditcardProcessing.model.dto.CreditCardRequest;
import com.sapients.creditcardProcessing.model.entity.CreditCard;
import com.sapients.creditcardProcessing.repository.CreditCardRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;

    public CreditCardService(final CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    @SneakyThrows
    public Integer saveCreditCard(final CreditCardRequest creditCardRequest) {

        log.info("save credit card for user: {}", creditCardRequest.getName());
        final boolean validCreditCard = isValidCreditCard(creditCardRequest.getCardNumber());
        if (!validCreditCard) {
            throw new InvalidCreditCardException("Credit Card Number is Invalid");
        }
        final CreditCard creditCard = CreditCard.builder()
                .cardNumber(new BigInteger(creditCardRequest.getCardNumber()))
                .name(creditCardRequest.getName())
                .cardLimit(creditCardRequest.getCardLimit())
                .build();
        final CreditCard creditcard = creditCardRepository.save(creditCard);
        return creditcard.getCardId();
    }

    public List<CreditCard> getAllCards() {
        return creditCardRepository.findAll();
    }
}
