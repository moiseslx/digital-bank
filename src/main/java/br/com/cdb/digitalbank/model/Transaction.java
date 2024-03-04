package br.com.cdb.digitalbank.model;

import br.com.cdb.digitalbank.model.enums.TransactionType;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private TransactionType type;
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "origin_id", nullable = false)
    private Account origin;

    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    private Account destination;

    public Transaction() {}

    public Transaction(TransactionType type, BigDecimal amount, Account origin, Account destination) {
        this.type = type;
        this.amount = amount;
        this.origin = origin;
        this.destination = destination;
    }

    public Long getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Account getOrigin() {
        return origin;
    }

    public void setOrigin(Account origin) {
        this.origin = origin;
    }

    public Account getDestination() {
        return destination;
    }

    public void setDestination(Account destination) {
        this.destination = destination;
    }
}
