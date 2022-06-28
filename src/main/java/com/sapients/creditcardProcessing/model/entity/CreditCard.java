package com.sapients.creditcardProcessing.model.entity;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "CREDIT_CARD",uniqueConstraints = { @UniqueConstraint(name = "UniqueCreditCardNumber",
        columnNames = { "cardNumber" }) } )
public class CreditCard {
    @Id
    @GeneratedValue
    private Integer cardId;
    private String name;
    private BigInteger cardNumber;
    private Integer cardLimit;
}
