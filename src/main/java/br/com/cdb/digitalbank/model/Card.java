package br.com.cdb.digitalbank.model;

import br.com.cdb.digitalbank.model.enums.CardType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardNumber;
    private List<CardType> type;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    BigDecimal limitCard;
    BigDecimal dailyLimit;
    private LocalDate expirationDate;
    private boolean active;
    private String password;

    public Card() {}

    public Card(String cardNumber, List<CardType> type, Account account, BigDecimal limitCard, BigDecimal dailyLimit, LocalDate expirationDate, boolean active, String password) {
        this.cardNumber = cardNumber;
        this.type = type;
        this.account = account;
        this.limitCard = limitCard;
        this.dailyLimit = dailyLimit;
        this.expirationDate = expirationDate;
        this.active = active;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public List<CardType> getType() {
        return type;
    }

    public void setType(List<CardType> type) {
        this.type = type;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public BigDecimal getLimitCard() {
        return limitCard;
    }

    public void setLimitCard(BigDecimal limitCard) {
        this.limitCard = limitCard;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return active == card.active && Objects.equals(id, card.id) && Objects.equals(cardNumber, card.cardNumber) && Objects.equals(type, card.type) && Objects.equals(account, card.account) && Objects.equals(limitCard, card.limitCard) && Objects.equals(dailyLimit, card.dailyLimit) && Objects.equals(expirationDate, card.expirationDate) && Objects.equals(password, card.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cardNumber, type, account, limitCard, dailyLimit, expirationDate, active, password);
    }
}
