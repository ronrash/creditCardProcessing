package com.sapients.creditcardProcessing.model.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;

class CreditCardRequestValidationTest {

    @Test
    void shouldErrorForNameConstraint()
    {
        final CreditCardRequest creditCardRequest = CreditCardRequest.builder().name("james").build();
        assertThat(validate(creditCardRequest)).hasSize(1);
    }


    @Test
    void shouldErrorForCardNumberConstraint()
    {
        final CreditCardRequest creditCardRequest = CreditCardRequest.builder().cardNumber("1234567891234567").build();
        assertThat(validate(creditCardRequest)).hasSize(1);
    }

    @Test
    void shouldNotErrorForConstraints()
    {
        final CreditCardRequest creditCardRequest = CreditCardRequest.builder().name("james").cardNumber("1234567891234567").build();
        assertThat(validate(creditCardRequest)).hasSize(0);
    }

    private Set<ConstraintViolation<CreditCardRequest>> validate(CreditCardRequest creditCardRequest)
    {
        final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        final Validator validator = validatorFactory.getValidator();
        return validator.validate(creditCardRequest);
    }
}