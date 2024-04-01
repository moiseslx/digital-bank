package br.com.cdb.digitalbank.model;

import br.com.cdb.digitalbank.model.enums.CardType;
import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class DebitCard extends Card {

    public DebitCard() {}
    public DebitCard(String cardNumber, Account account, BigDecimal dailyLimit, LocalDate expirationDate, boolean active, String password, CardType cardType) {
        super(cardNumber, account, dailyLimit, expirationDate, active, password, cardType);
    }
}
