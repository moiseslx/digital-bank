package br.com.cdb.digitalbank.dto;

import java.math.BigDecimal;

public record TransactionDebitDTO(Long debitCardId, Long accountId, BigDecimal amount, String password) {
}
