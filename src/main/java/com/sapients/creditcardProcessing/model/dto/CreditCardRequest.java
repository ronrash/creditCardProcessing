package com.sapients.creditcardProcessing.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreditCardRequest {
    @NotNull(message = "Name should be provided")
    private String name;
    @NotNull(message = "Card Number should be provided")
    @Size(min = 15,max = 19,message = "Credit Card Number should be between 15 to 19 characters")
    private String cardNumber;
    private Integer cardLimit;
}
