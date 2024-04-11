package br.com.cdb.digitalbank.model;

import br.com.cdb.digitalbank.model.enums.CardType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_credit_card")
public class CreditCard extends Card {
    private BigDecimal limitCard;
    private BigDecimal usedLimit;

    @JsonIgnore
    @OneToMany(mappedBy = "creditCard", cascade = CascadeType.ALL)
    private List<Invoice> invoices = new ArrayList<>();

    public CreditCard(String cardNumber, Account account, BigDecimal dailyLimit, LocalDate expirationDate, boolean active, String password, CardType cardType, BigDecimal limitCard, BigDecimal usedLimit) {
        super(cardNumber, account, dailyLimit, expirationDate, active, password, cardType);
        this.limitCard = limitCard;
        this.usedLimit = usedLimit;
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

    public List<Invoice> getInvoices() {
        return invoices;
    }
}
