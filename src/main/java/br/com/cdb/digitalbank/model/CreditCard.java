package br.com.cdb.digitalbank.model;

import br.com.cdb.digitalbank.model.enums.CardType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_credit_card")
public class CreditCard extends Card {
    private BigDecimal limitCard;
    private BigDecimal usedLimit;

    public CreditCard(String cardNumber, Account account, BigDecimal dailyLimit, LocalDate expirationDate, boolean active, String password, CardType cardType) {
        super(cardNumber, account, dailyLimit, expirationDate, active, password, cardType);
    }

    public CreditCard() {}


    public BigDecimal getLimitCard() {
        return limitCard;
    }

    public void setLimitCard(BigDecimal limitCard) {
        this.limitCard = limitCard;
    }

    public BigDecimal getUsedLimit() {
        return usedLimit;
    }

    public void setUsedLimit(BigDecimal usedLimit) {
        this.usedLimit = usedLimit;
    }
}
