package br.com.cdb.digitalbank.model;

import br.com.cdb.digitalbank.model.enums.CardType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_debit_card")
public class DebitCard extends Card {
    public DebitCard() {}
    public DebitCard(String cardNumber, Account account, BigDecimal dailyLimit, LocalDate expirationDate, boolean active, String password, CardType cardType) {
        super(cardNumber, account, dailyLimit, expirationDate, active, password, cardType);
    }
}
