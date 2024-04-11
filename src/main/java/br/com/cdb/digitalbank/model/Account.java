package br.com.cdb.digitalbank.model;

import br.com.cdb.digitalbank.model.enums.AccountType;
import br.com.cdb.digitalbank.model.enums.KeyType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "tb_account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long number;
    private Integer agency;
    private AccountType type;
    private BigDecimal balance;

    private LocalDate creationDate;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;

    public Account() {}

    public Account(AccountType type, Customer customer) {
        this.type = type;
        this.customer = customer;
        this.balance = BigDecimal.valueOf(100.00);
        this.creationDate = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Integer getAgency() {
        return agency;
    }

    public void setAgency(Integer agency) {
        this.agency = agency;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }
}
