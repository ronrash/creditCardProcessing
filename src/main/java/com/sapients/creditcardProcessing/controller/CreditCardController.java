package com.sapients.creditcardProcessing.controller;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

import javax.validation.Valid;

import com.sapients.creditcardProcessing.model.dto.CreditCardRequest;
import com.sapients.creditcardProcessing.model.entity.CreditCard;
import com.sapients.creditcardProcessing.service.CreditCardService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CreditCardController to add credit cards and retrieve them
 */
@RestController
@Validated
@RequestMapping("/api/v1")
public class CreditCardController {

    private final CreditCardService creditCardService;

    public CreditCardController(final CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @PostMapping("/cards")
    public ResponseEntity<Integer> addCreditCard(@RequestBody @Valid CreditCardRequest creditCardRequest) {
        return new ResponseEntity<>(creditCardService.saveCreditCard(creditCardRequest), CREATED);
    }

    @GetMapping("/cards")
    public ResponseEntity<List<CreditCard>> retrieveAllCreditCards() {
        return ResponseEntity.ok(creditCardService.getAllCards());
    }
}
