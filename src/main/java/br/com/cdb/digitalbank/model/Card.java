package br.com.cdb.digitalbank.model;

import br.com.cdb.digitalbank.model.enums.CardType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@MappedSuperclass
public abstract class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardNumber;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    private BigDecimal dailyLimit;
    private LocalDate expirationDate;
    private boolean active;

    @JsonIgnore
    private String password;
    private CardType cardType;

    public Card(String cardNumber, Account account, BigDecimal dailyLimit, LocalDate expirationDate, boolean active, String password, CardType cardType) {
        this.cardNumber = cardNumber;
        this.account = account;
        this.dailyLimit = dailyLimit;
        this.expirationDate = expirationDate;
        this.active = active;
        this.password = password;
        this.cardType = cardType;
    }

    public Card() {}

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public BigDecimal getDailyLimit() {
        return dailyLimit;
    }

    public void setDailyLimit(BigDecimal dailyLimit) {
        this.dailyLimit = dailyLimit;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }
}
