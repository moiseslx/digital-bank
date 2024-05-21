package br.com.cdb.digitalbank.dto;

import br.com.cdb.digitalbank.model.Account;
import br.com.cdb.digitalbank.model.CreditCard;

import java.math.BigDecimal;

public record TransactionCreditDTO(Long creditCardId, Long accountId, BigDecimal amount, String password) {
}
