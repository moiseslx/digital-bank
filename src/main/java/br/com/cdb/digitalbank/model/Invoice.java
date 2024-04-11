package br.com.cdb.digitalbank.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal invoiceValue;

    private LocalDate openingDate;
    private LocalDate closingDate;

    private boolean payed;

    @ManyToOne
    @JoinColumn(name = "credit_card_id")
    private CreditCard creditCard;

    public Invoice() {}

    public Invoice(LocalDate openingDate, LocalDate closingDate, boolean payed) {
        this.invoiceValue = BigDecimal.ZERO;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.payed = payed;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getInvoiceValue() {
        return invoiceValue;
    }

    public void setInvoiceValue(BigDecimal invoiceValue) {
        this.invoiceValue = invoiceValue;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    public LocalDate getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(LocalDate closingDate) {
        this.closingDate = closingDate;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public boolean isPastDue() {
        LocalDate currentDate = LocalDate.now();
        return !payed && currentDate.isAfter(closingDate);
    }
}
