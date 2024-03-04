package br.com.cdb.digitalbank.dto;

import java.math.BigDecimal;

public record TransactionDTO(Long originId, Long destinationId, BigDecimal amount) {
}
