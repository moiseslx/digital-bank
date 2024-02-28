package br.com.cdb.digitalbank.model;

import br.com.cdb.digitalbank.model.enums.AccountType;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer number;
    private String agency;
    private AccountType type;
    private BigDecimal balance;
    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;

    public Account() {}

    public Account(AccountType type, Customer customer) {
        this.type = type;
        this.customer = customer;
        this.balance = BigDecimal.valueOf(100.00);
    }

    public Long getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
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
}
