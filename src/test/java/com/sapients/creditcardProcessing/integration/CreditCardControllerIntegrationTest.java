package com.sapients.creditcardProcessing.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigInteger;

import javax.annotation.Resource;

import com.sapients.creditcardProcessing.model.entity.CreditCard;
import com.sapients.creditcardProcessing.repository.CreditCardRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CreditCardControllerIntegrationTest {

    private static final String CREDIT_CARD_URL = "/api/v1/cards";

    @Resource
    MockMvc mockMvc;

    @Autowired
    CreditCardRepository creditCardRepository;


    @BeforeEach
    void setup() {
        final CreditCard creditCard = CreditCard.builder()
                .cardNumber(new BigInteger("4242424242426742"))
                .name("James")
                .cardLimit(0)
                .build();
        creditCardRepository.save(creditCard);
    }

    @Test
    void shouldReturnAllSavedCreditCards() throws Exception {
        mockMvc.perform(get(CREDIT_CARD_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].cardId").value(1))
                .andExpect(jsonPath("[0].name").value("James"))
                .andExpect(jsonPath("[0].cardLimit").value(0));
    }
}
